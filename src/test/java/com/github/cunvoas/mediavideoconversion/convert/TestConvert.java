package com.github.cunvoas.mediavideoconversion.convert;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.mediavideoconversion.vlc.VlcAudioFormat;
import com.github.cunvoas.mediavideoconversion.vlc.VlcVideoFormat;

public class TestConvert {
	private static final Logger LOGGER = LoggerFactory .getLogger(TestConvert.class);
	private Convert tested=null;

	@Before
	public void setUp() throws Exception {
		tested = new Convert();
	}

	@Test
	public void testWithVlc() {
		
		URL sample = Thread.currentThread().getContextClassLoader().getResource("./vids/sample1.mp4");
		String srcFile = sample.getPath();
		if (System.getProperty("os.name").contains("Win")) {
			srcFile = srcFile.replaceAll("/", "\\\\");
		}
		LOGGER.info("sample video file : {}", srcFile);
		
		
		String destFile= System.getProperty("java.io.tmpdir")+"sample1.mpg";
		//destFile = destFile.replaceAll("//", "\\\\");
		LOGGER.info("output video file : {}", destFile);
		
				
		tested.withVlc(srcFile, destFile, VlcVideoFormat.H264, 480, VlcAudioFormat.MP3, 192, -1); //44100
		
	}

}
