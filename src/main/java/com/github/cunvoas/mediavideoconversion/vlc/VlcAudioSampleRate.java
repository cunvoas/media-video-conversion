package com.github.cunvoas.mediavideoconversion.vlc;

/**
 * @author CUNVOAS
 * @see https://en.wikipedia.org/wiki/Sampling_(signal_processing)
 *
 */
public enum VlcAudioSampleRate {
	PHONE("8000"),
	PCM("11025"),
	VOPIP("16000"),
	MPEG("22050"),
	DV("32000"),
	CD("44100"),
	HD("48000");
	
	private String sampleRate;
	
	private VlcAudioSampleRate(String f) {
		this.sampleRate=f;
	}
	/**
	 * Getter for sampleRate.
	 * @return the sampleRate
	 */
	public final String getSampleRate() {
		return sampleRate;
	}

}
