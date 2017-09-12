package com.github.cunvoas.mediavideoconversion.tasks;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author cunvoas
 */
public class TasklRejectHandler extends Observable implements RejectedExecutionHandler {
 
	private Observer observer=null;
	public TasklRejectHandler(Observer observer) {
		super();
		this.observer=observer;
	}
	
    /**
     * @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
     */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    	
    	if (r instanceof Task) {
    		Task task = (Task)r;
    		setChanged();
    		//notifyObservers(observer);
    		notifyObservers(task);
    	}
    }
 
}
