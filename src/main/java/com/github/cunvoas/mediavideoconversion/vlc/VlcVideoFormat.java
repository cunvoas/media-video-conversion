package com.github.cunvoas.mediavideoconversion.vlc;

/**
 * @author UNVOAS
 * @see https://wiki.videolan.org/MPEG-4/
 */
public enum VlcVideoFormat {
	MP4("mp4v", "mp4"),
	MPG("mpgv", "mpeg1"),
	H264("h264", "ts"),
	H265("h265", "ts");
	
	private String codec;
	private String mux;
	private VlcVideoFormat(String f, String m) {
		this.codec=f;
		this.mux=m;
	}
	
	/**
	 * Getter for format.
	 * @return the format
	 */
	public final String getCodec() {
		return codec;
	}
	
	/**
	 * @return the mux
	 */
	public String getMux() {
		return mux;
	}
	
}
