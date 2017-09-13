package com.github.cunvoas.mediavideoconversion.vlc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestVlcCommandLine {
	
	private VlcCommandLine tested;

	@Before
	public void setUp() throws Exception {
		tested = new VlcCommandLine();
	}

	@Test
	public void testProduce() {
		String[] args = tested.produce("src.vid", "dst.vid", VlcVideoFormat.H264.getCodec(), VlcAudioFormat.MP3.getValue(), 1000, 192, 44100);
		
		Assert.assertEquals ("nb args", 4, args.length);
		
		
	}

}
