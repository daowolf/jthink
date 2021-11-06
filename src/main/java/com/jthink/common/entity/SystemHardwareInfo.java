package com.jthink.common.entity;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import cn.hutool.core.util.NumberUtil;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

public class SystemHardwareInfo {
	private static final int OSHI_WAIT_SECOND = 1000;

	/**
	 * CPU相关信息
	 */
	private Cpu cpu = new Cpu();

	/**
	 * 內存相关信息
	 */
	private Mem mem = new Mem();

	/**
	 * JVM相关信息
	 */
	private Jvm jvm = new Jvm();

	/**
	 * 服务器相关信息
	 */
	private Sys sys = new Sys();

	public Cpu getCpu() {
		return cpu;
	}

	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	public Mem getMem() {
		return mem;
	}

	public void setMem(Mem mem) {
		this.mem = mem;
	}

	public Jvm getJvm() {
		return jvm;
	}

	public void setJvm(Jvm jvm) {
		this.jvm = jvm;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public List<SysFile> getSysFiles() {
		return sysFiles;
	}

	public void setSysFiles(List<SysFile> sysFiles) {
		this.sysFiles = sysFiles;
	}

	/**
	 * 磁盘相关信息
	 */
	private List<SysFile> sysFiles = new LinkedList<SysFile>();

	public void copyTo() throws Exception {
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();

		setCpuInfo(hal.getProcessor());

		setMemInfo(hal.getMemory());

		setSysInfo();

		setJvmInfo();

		setSysFiles(si.getOperatingSystem());
	}

	/**
	 * 设置CPU信息
	 */
	private void setCpuInfo(CentralProcessor processor) {
		// CPU信息
		long[] prevTicks = processor.getSystemCpuLoadTicks();
		Util.sleep(OSHI_WAIT_SECOND);
		long[] ticks = processor.getSystemCpuLoadTicks();
		long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
		long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
		long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
		long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
		long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
		long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
		long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
		long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
		long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
		cpu.setCpuNum(processor.getLogicalProcessorCount());
		cpu.setTotal(totalCpu);
		cpu.setSys(cSys);
		cpu.setUsed(user);
		cpu.setWait(iowait);
		cpu.setFree(idle);
	}

	/**
	 * 设置内存信息
	 */
	private void setMemInfo(GlobalMemory memory) {
		mem.setTotal(memory.getTotal());
		mem.setUsed(memory.getTotal() - memory.getAvailable());
		mem.setFree(memory.getAvailable());
	}

	/**
	 * 设置服务器信息
	 */
	private void setSysInfo() {
		Properties props = System.getProperties();
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			sys.setComputerName(addr.getHostName());
			sys.setComputerIp(addr.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		sys.setOsName(props.getProperty("os.name"));
		sys.setOsArch(props.getProperty("os.arch"));
		sys.setUserDir(props.getProperty("user.dir"));
	}

	/**
	 * 设置Java虚拟机
	 */
	private void setJvmInfo() {
		Properties props = System.getProperties();
		jvm.setTotal(Runtime.getRuntime().totalMemory());
		jvm.setMax(Runtime.getRuntime().maxMemory());
		jvm.setFree(Runtime.getRuntime().freeMemory());
		jvm.setVersion(props.getProperty("java.version"));
		jvm.setHome(props.getProperty("java.home"));
	}

	/**
	 * 设置磁盘信息
	 */
	private void setSysFiles(OperatingSystem os) {
		FileSystem fileSystem = os.getFileSystem();
		List<OSFileStore> fileList = fileSystem.getFileStores();
		for (OSFileStore fs : fileList) {
			long free = fs.getUsableSpace();
			long total = fs.getTotalSpace();
			long used = total - free;
			SysFile sysFile = new SysFile();
			sysFile.setDirName(fs.getMount());
			sysFile.setSysTypeName(fs.getType());
			sysFile.setTypeName(fs.getName());
			sysFile.setTotal(convertFileSize(total));
			sysFile.setFree(convertFileSize(free));
			sysFile.setUsed(convertFileSize(used));
			sysFile.setUsage(NumberUtil.mul(NumberUtil.div(used, total, 4), 100));
			sysFiles.add(sysFile);
		}
	}

	/**
	 * 字节转换
	 *
	 * @param size 字节大小
	 * @return 转换后值
	 */
	public String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;
		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else {
			return String.format("%d B", size);
		}
	}

	public void prinInfo() {
		System.out.println("----------------cpu信息----------------");

		System.out.println("cpu核数:" + cpu.getCpuNum());
		System.out.println("cpu系统使用率:" + new DecimalFormat("#.##%").format(cpu.getSys() * 1.0 / cpu.getTotal()));
		System.out.println("cpu用户使用率:" + new DecimalFormat("#.##%").format(cpu.getUsed() * 1.0 / cpu.getTotal()));
		System.out.println("cpu当前等待率:" + new DecimalFormat("#.##%").format(cpu.getWait() * 1.0 / cpu.getTotal()));
		System.out
				.println("cpu当前使用率:" + new DecimalFormat("#.##%").format(1.0 - (cpu.getFree() * 1.0 / cpu.getTotal())));

		SystemInfo systemInfo = new SystemInfo();
		GlobalMemory memory = systemInfo.getHardware().getMemory();
		// 总内存
		long totalByte = memory.getTotal();
		// 剩余
		long acaliableByte = memory.getAvailable();
		System.out.println("总内存 = " + formatByte(totalByte));
		System.out.println("使用" + formatByte(totalByte - acaliableByte));
		System.out.println("剩余内存 = " + formatByte(acaliableByte));
		System.out.println("使用率：" + new DecimalFormat("#.##%").format((totalByte - acaliableByte) * 1.0 / totalByte));

		System.out.println("----------------操作系统信息----------------");
		Properties props = System.getProperties();
		// 系统名称
		String osName = props.getProperty("os.name");
		// 架构名称
		String osArch = props.getProperty("os.arch");
		System.out.println("操作系统名 = " + osName);
		System.out.println("系统架构 = " + osArch);

		System.out.println("----------------jvm信息----------------");
		Runtime runtime = Runtime.getRuntime();
		// jvm总内存
		long jvmTotalMemoryByte = runtime.totalMemory();
		// jvm最大可申请
		long jvmMaxMoryByte = runtime.maxMemory();
		// 空闲空间
		long freeMemoryByte = runtime.freeMemory();
		// jdk版本
		String jdkVersion = props.getProperty("java.version");
		// jdk路径
		String jdkHome = props.getProperty("java.home");
		System.out.println("jvm内存总量 = " + formatByte(jvmTotalMemoryByte));
		System.out.println("jvm已使用内存 = " + formatByte(jvmTotalMemoryByte - freeMemoryByte));
		System.out.println("jvm剩余内存 = " + formatByte(freeMemoryByte));
		System.out.println("jvm内存使用率 = "
				+ new DecimalFormat("#.##%").format((jvmTotalMemoryByte - freeMemoryByte) * 1.0 / jvmTotalMemoryByte));
		System.out.println("java版本 = " + jdkVersion);

		System.out.println("----------------线程信息----------------");
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

		while (currentGroup.getParent() != null) {
			// 返回此线程组的父线程组
			currentGroup = currentGroup.getParent();
		}
		// 此线程组中活动线程的估计数
		int noThreads = currentGroup.activeCount();

		Thread[] lstThreads = new Thread[noThreads];
		// 把对此线程组中的所有活动子组的引用复制到指定数组中。
		currentGroup.enumerate(lstThreads);
		for (Thread thread : lstThreads) {
			System.out.println("线程数量：" + noThreads + " 线程id：" + thread.getId() + " 线程名称：" + thread.getName() + " 线程状态："
					+ thread.getState());
		}

	}

	public String formatByte(long byteNumber) {
		// 换算单位
		double FORMAT = 1024.0;
		double kbNumber = byteNumber / FORMAT;
		if (kbNumber < FORMAT) {
			return new DecimalFormat("#.##KB").format(kbNumber);
		}
		double mbNumber = kbNumber / FORMAT;
		if (mbNumber < FORMAT) {
			return new DecimalFormat("#.##MB").format(mbNumber);
		}
		double gbNumber = mbNumber / FORMAT;
		if (gbNumber < FORMAT) {
			return new DecimalFormat("#.##GB").format(gbNumber);
		}
		double tbNumber = gbNumber / FORMAT;
		return new DecimalFormat("#.##TB").format(tbNumber);
	}

	public static void main(String[] args) {
		SystemHardwareInfo sysInfo = new SystemHardwareInfo();
		try {
			sysInfo.copyTo();
			sysInfo.prinInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
