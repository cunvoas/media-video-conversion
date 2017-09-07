package com.github.cunvoas.mediavideoconversion.conversion;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.mediavideoconversion.runner.Exec;

/**
 * Classe utilisable en multithread.
 * @author CUNVOAS
 */
public class ConversionTask extends Observable implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversionTask.class);
	

	private String executable;
	private String[] args;
	private ConversionMonitor observer;
	private String videoFile;
	
	private long pooled;
	private long started;
	private long stopped;
	private long finished;
	
	protected void pooled() {
		this.pooled=System.currentTimeMillis();
	}
	protected void start() {
		this.started=System.currentTimeMillis();
	}
	protected void stop() {
		this.stopped=System.currentTimeMillis();
	}
	protected void end() {
		this.finished=System.currentTimeMillis();
	}
	
	public long durationQueued() {
		return  started-pooled;
	}
	public long durationBeforeError() {
		return  stopped-started;
	}
	public long durationExec() {
		return  finished-started;
	}
	
	/**
	 * @param observer
	 */
	protected void setObserver(ConversionMonitor observer) {
		this.observer = observer;
	}

	public ConversionTask(String executable, String[] args, String videoFile) {
		super();
		this.executable=executable;
		this.args=args;
		this.videoFile=videoFile;
	}

	/**
	 * @return the videoFile
	 */
	public String getVideoFile() {
		return videoFile;
	}

	@Override
	public void run() {
		
		try {
			Exec exec = new Exec();
			exec.execute(executable, args);
			
			
		} catch (Exception e) {
			LOGGER.error("imprevu", e);
		} finally {
			// notification à l'observer
			setChanged();
			notifyObservers(observer);
		}
		
	}

}
