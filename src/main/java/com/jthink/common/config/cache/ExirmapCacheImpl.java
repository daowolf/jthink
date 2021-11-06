package com.jthink.common.config.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import net.jodah.expiringmap.ExpiringMap;

@Service("localCache")
public class ExirmapCacheImpl implements JthinkCache {
	ExpiringMap<String, String> map = ExpiringMap.builder().maxSize(2000).variableExpiration().build();

	@Override
	public void set(String key, String value, long seconds) {
		map.put(key, value, seconds, TimeUnit.SECONDS);
	}

	@Override
	public void set(String key, String value) {
		map.put(key, value);
	}

	@Override
	public void removeItem(String key) {
		map.remove(key);
	}

	@Override
	public String getKey(String key) {
		return map.get(key);
	}

}
