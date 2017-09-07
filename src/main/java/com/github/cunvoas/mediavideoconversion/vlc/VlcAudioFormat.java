package com.github.cunvoas.mediavideoconversion.vlc;

/**
 * @author UNVOAS
 * @see https://wiki.videolan.org/MPEG-4/
 */
public enum VlcAudioFormat {
	MP4("mp4a"),
	MPG("mpga"),
	MP3("mp3");
	
	private String format;
	private VlcAudioFormat(String f) {
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
