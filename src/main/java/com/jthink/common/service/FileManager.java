package com.jthink.common.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service(value = "fileManager")
public class FileManager {
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
//	@Value("${jthink.upload-file-path}")
    private String storageRoot;
	public String upload(CommonsMultipartFile multipartFile) throws FileNotFoundException {
		String storageRoot = ResourceUtils.getURL("classpath:").getPath()+ "uploads";
		
		String path = "";
		String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename())
				.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String fileName = SIMPLE_DATE_FORMAT.format(new Date()) + "-"
				+ UUID.randomUUID().toString().replaceAll("-", "").toLowerCase() + suffix;
		File file = new File(storageRoot+File.separator+"uploadimgs"+File.separator+fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			multipartFile.transferTo(file);
			path = "/uploadimgs/" + fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public boolean remove(String url) {
		String path = storageRoot + File.separator + "imgs" + url;
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			return file.delete();
		}
		return false;
	}

	public void remove(Collection<String> urls) {
		if (!CollectionUtils.isEmpty(urls)) {
			urls.stream().allMatch(this::remove);
		}
	}
}
