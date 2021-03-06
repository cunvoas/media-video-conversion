package com.github.cunvoas.mediavideoconversion.tasks;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cunvoas
 */
public class TaskMonitor implements Observer {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskMonitor.class);
	
	/**
	 * Methode invoquee apres notification.
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof Task) {
			Task task = (Task)o;
			
			if (task.durationBeforeError()<0) {
				LOGGER.info("Video {} pooled during {} in error in {}", task.getVideoFile(), task.durationQueued(), task.durationBeforeError());
			} else {
				LOGGER.info("Video {} pooled during {} converted in {}", task.getVideoFile(), task.durationQueued(), task.durationExec());
			}
		}
	}

}
