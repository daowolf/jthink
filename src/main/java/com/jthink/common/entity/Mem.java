package com.jthink.common.entity;

import java.io.Serializable;

import cn.hutool.core.util.NumberUtil;

public class Mem implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    public void setTotal(double total) {
		this.total = total;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getTotal() {
        return NumberUtil.div(total, (1024 * 1024 * 1024), 2);
    }

    public double getUsed() {
        return NumberUtil.div(used, (1024 * 1024 * 1024), 2);
    }


    public double getFree() {
        return NumberUtil.div(free, (1024 * 1024 * 1024), 2);
    }

    public double getUsage() {
        return NumberUtil.mul(NumberUtil.div(used, total, 4), 100);
    }
}