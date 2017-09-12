package com.github.cunvoas.mediavideoconversion.tasks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskPool {
	private static final TaskPool INSTANCE = new TaskPool();
	public static final int THREAD_POOL_SIZE=16;
	
	private ThreadPoolExecutor executor = null;
	private TaskMonitor observer=null;
	
	public static TaskPool getInstance() {
		return INSTANCE;
	}

	private TaskPool () {
		super();
		
		// creation de gestionnaire de threads
		// n CPU = n Threads
		int maxPoolSize = Runtime.getRuntime().availableProcessors();

		// Intercepteur des erreurs durant la conversion
		observer=new TaskMonitor();
		TasklRejectHandler rejectionHandler = new TasklRejectHandler(observer);
		
		// Init du pool d'attente
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(THREAD_POOL_SIZE);
		
		// Executor pool multithread
		executor = new ThreadPoolExecutor(maxPoolSize, maxPoolSize, 2, TimeUnit.HOURS, 
				queue, threadFactory, rejectionHandler);
		
		// on démarre de suite
		executor.prestartAllCoreThreads();
	}
	
	/**
	 * @param task
	 */
	public void addTask(Task task) {
		task.setObserver(observer);
		task.pooled();
		executor.execute(task);
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		executor.shutdownNow();
		
		super.finalize();
	}
	
	

}
