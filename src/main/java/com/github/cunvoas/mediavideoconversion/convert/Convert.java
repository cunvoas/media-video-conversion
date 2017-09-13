package com.github.cunvoas.mediavideoconversion.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.mediavideoconversion.runner.ExecutableBundle;
import com.github.cunvoas.mediavideoconversion.tasks.Task;
import com.github.cunvoas.mediavideoconversion.tasks.TaskPool;
import com.github.cunvoas.mediavideoconversion.vlc.VlcAudioFormat;
import com.github.cunvoas.mediavideoconversion.vlc.VlcCommandLine;
import com.github.cunvoas.mediavideoconversion.vlc.VlcVideoFormat;

public class Convert {
	private static final Logger LOGGER = LoggerFactory .getLogger(Convert.class);

	public void withVlc(String srcFile, String dstFile, VlcVideoFormat videoFormat, int vBitrate,VlcAudioFormat audioFormat, int aBitrate, int aSampleRate) {
		TaskPool pool = TaskPool.getInstance();
		

		String exec = ExecutableBundle.getResource("vlc.win64");
		VlcCommandLine vlcCommandLine = new VlcCommandLine();
		String[] args = vlcCommandLine.produce(srcFile, dstFile, videoFormat.getCodec(), audioFormat.getValue(), vBitrate, aBitrate, aSampleRate);
		
		LOGGER.info("exec: {}", exec);
		for (String string : args) {
			LOGGER.info("arg: {}", string);
		}
		
		Task task = new Task(exec, args, srcFile);
		task.run();
		
		//pool.addTask(task);
		
		
		
		pool.shutdown();
	}
}
