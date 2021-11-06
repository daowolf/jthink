package com.jthink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jthink.common.entity.SystemHardwareInfo;

@Controller
@RequestMapping("/system/sysinfo")
public class SysInfoController {
	@RequestMapping({ "", "/index" })
	public String index(Model ui) throws Exception {
		SystemHardwareInfo sysInfo = new SystemHardwareInfo();
		sysInfo.copyTo();
		ui.addAttribute("sysInfo", sysInfo);
		return "sysinfo/sysinfo";
	}
}
