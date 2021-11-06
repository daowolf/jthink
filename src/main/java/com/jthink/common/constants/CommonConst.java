package com.jthink.common.constants;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CommonConst {
	public static String FILE_STORAGE_ROOT = getRootPath();

	private static String getRootPath() {
		try {
			Resource resource = new ClassPathResource("");
			return resource.getFile().getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
