package com.github.cunvoas.mediavideoconversion.runner;

import java.util.ResourceBundle;

public class ExecutableBundle {
	private static ResourceBundle resource = ResourceBundle.getBundle("video-converter");
	
	public static String getResource(String key) {
		return resource.getString(key);
	}
	
}
