package com.github.cunvoas.mediavideoconversion.convert;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.github.cunvoas.mediavideoconversion.vlc.VlcAudioFormat;
import com.github.cunvoas.mediavideoconversion.vlc.VlcVideoFormat;

public class TestConvert {
	private Convert tested=null;

	@Before
	public void setUp() throws Exception {
		tested = new Convert();
	}

	@Test
	public void testWithVlc() {
		String srcFile="D:/_SOCLE/_GIT/clones/media-video-conversion/src/test/resources/vids/sample1.mp4";
		String destFile="D:/opt/sample1.mpg";
				
		tested.withVlc(srcFile, destFile, VlcVideoFormat.MPG, 96, VlcAudioFormat.MPG, 64);
		
	}

}
