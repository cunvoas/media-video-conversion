package com.github.cunvoas.mediavideoconversion.conversion;

import java.util.Observer;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;


public class TestConversionTask {
	
	private ConversionTask tested;
	
	@Before
	public void setup() {
		tested = new ConversionTask("C:/Windows/SysWOW64/tree.com", new String[]{"/F", "/A"}, "file.video");
	}

	@Test
	public void testPooled() {
		tested.pooled();
		Assert.assertEquals("Pooled", System.currentTimeMillis(), (long)Whitebox.getInternalState(tested, "pooled"));
	}

	@Test
	public void testStart() {
		tested.start();
		Assert.assertEquals("Pooled", System.currentTimeMillis(), (long)Whitebox.getInternalState(tested, "started"));
	}

	@Test
	public void testStop() {
		tested.stop();
		Assert.assertEquals("Pooled", System.currentTimeMillis(), (long)Whitebox.getInternalState(tested, "stopped"));
	}

	@Test
	public void testEnd() {
		tested.end();
		Assert.assertEquals("Pooled", System.currentTimeMillis(), (long)Whitebox.getInternalState(tested, "finished"));
	}

	@Test
	public void testDurationQueued() {
		Whitebox.setInternalState(tested, "pooled", 1000L);
		Whitebox.setInternalState(tested, "started", 2000L);
		Assert.assertEquals("Pooled", 1000L, tested.durationQueued());
	}

	@Test
	public void testDurationBeforeError() {
		Whitebox.setInternalState(tested, "stopped", 3000L);
		Whitebox.setInternalState(tested, "started", 2000L);
		Assert.assertEquals("stopped", 1000L, tested.durationBeforeError());
	}

	@Test
	public void testDurationExec() {
		Whitebox.setInternalState(tested, "finished", 3000L);
		Whitebox.setInternalState(tested, "started", 2000L);
		Assert.assertEquals("finished", 1000L, tested.durationExec());
	}

	@Test
	public void testSetObserver() {
		tested.setObserver(new ConversionMonitor());
		Object obs = Whitebox.getInternalState(tested, "observer");
				
		MatcherAssert.assertThat(obs, Matchers.is(Matchers.instanceOf(Observer.class)));
	}

	@Test
	public void testConversionTask() {
		String exec = (String)Whitebox.getInternalState(tested, "executable");
		Assert.assertEquals("executable", "C:/Windows/SysWOW64/tree.com", exec);

		String[] args = (String[])Whitebox.getInternalState(tested, "args");
		Assert.assertEquals("args", 2, args.length);

		String file = (String)Whitebox.getInternalState(tested, "videoFile");
		Assert.assertEquals("videoFile", "file.video", file);
	}

	@Test
	public void testGetVideoFile() {
		Assert.assertEquals("video", "file.video", tested.getVideoFile());
	}

	@Test
	public void testRun() {
		tested.run();
		// fichier de test dans la structure des répertoires en test
		MatcherAssert.assertThat(tested.getReturnConsole(), Matchers.containsString("sample1.mp4"));
	}

}
