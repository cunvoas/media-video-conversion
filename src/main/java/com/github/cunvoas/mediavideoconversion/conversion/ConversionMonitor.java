package com.github.cunvoas.mediavideoconversion.conversion;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cunvoas
 */
public class ConversionMonitor implements Observer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversionMonitor.class);
	
	private ConversionRejectHandler rejectionHandler = null;

	/**
	 * Methode invoquee apres notification.
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ConversionTask) {
			ConversionTask item = (ConversionTask)o;
			
			LOGGER.info("Video {} converted in {}", item.getVideoFile());
		}
	}

}
