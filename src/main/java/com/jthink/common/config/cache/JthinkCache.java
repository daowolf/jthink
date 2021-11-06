package com.jthink.common.config.cache;

public interface JthinkCache {
	public void set(String key, String value, long seconds);

	public void set(String key, String value);

	public void removeItem(String key);

	public String getKey(String key);

}
