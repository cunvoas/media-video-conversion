package com.github.cunvoas.mediavideoconversion.tasks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TaskPool {
	private static final Logger LOGGER = LoggerFactory .getLogger(TaskPool.class);
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
		int maxPoolSize = Math.max(1, Runtime.getRuntime().availableProcessors()-1);

		// Intercepteur des erreurs durant la conversion
		observer=new TaskMonitor();
		TasklRejectHandler rejectionHandler = new TasklRejectHandler(observer);
		
		// Init du pool d'attente
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(THREAD_POOL_SIZE);
		
		// Executor pool multithread
		executor = new ThreadPoolExecutor(1, maxPoolSize, 12, TimeUnit.HOURS, 
				queue, threadFactory, rejectionHandler);
		
		// on démarre de suite
		executor.prestartAllCoreThreads();
		
		
//		while (! executor.isTerminated()) {
//			 try {
//                 Thread.sleep(10000);
//             } catch (InterruptedException e) {
//                 LOGGER.error("Sleep Exception");
//             }
//		}
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
	
	/**
	 * shutdown
	 */
	public void shutdown() {
		executor.shutdown();
	}
	
	

}
