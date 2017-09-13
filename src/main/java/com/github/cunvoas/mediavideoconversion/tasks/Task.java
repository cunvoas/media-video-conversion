package com.github.cunvoas.mediavideoconversion.tasks;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.mediavideoconversion.runner.Exec;

/**
 * Classe utilisable en multithread.
 * @author CUNVOAS
 */
public class Task extends Observable implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);
	
	private String executable;
	private String[] args;
	private TaskMonitor observer;
	private String videoFile;
	
	private String returnConsole;
	
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
	protected void setObserver(TaskMonitor observer) {
		this.observer = observer;
	}

	/**
	 * Constructor.
	 * @param executable
	 * @param args
	 * @param videoFile
	 */
	public Task(String executable, String[] args, String videoFile) {
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

	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		try {
			this.start();
			
			Exec exec = new Exec();
			returnConsole = exec.execute(executable, args);
			
			this.end();
			
			LOGGER.info(returnConsole);
			
		} catch (Exception e) {
			this.stop();
			LOGGER.error("imprevu", e);
		} finally {
			// notification à l'observer
			setChanged();
			notifyObservers(observer);
		}
		
	}
	/**
	 * @return the returnConsole
	 */
	public String getReturnConsole() {
		return returnConsole;
	}

}
