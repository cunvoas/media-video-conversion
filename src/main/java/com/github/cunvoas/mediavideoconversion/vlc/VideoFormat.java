package com.github.cunvoas.mediavideoconversion.vlc;

/**
 * @author UNVOAS
 * @see https://wiki.videolan.org/MPEG-4/
 */
public enum VideoFormat {
	MP4("mp4v"),
	MPG("mpgv"),
	H264("h264"),
	H265("h265");
	
	private String format;
	private VideoFormat(String f) {
		this.format=f;
	}
	/**
	 * Getter for format.
	 * @return the format
	 */
	public final String getValue() {
		return format;
	}
	
}
