package com.github.cunvoas.mediavideoconversion.vlc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CUNVOAS
 *-I dummy VID_20170708_131626527.mp4  --sout=#transcode{vcodec=h264,vb=480,acodec=mp3,ab=192,channels=2,deinterlace}:standard{access=file,mux=ts,dst=cmdLine_mp3.mpeg4}
 * @see https://wiki.videolan.org/VLC_HowTo/Transcode_multiple_videos/
 */
public class VlcCommandLine {
	
	public String[] produce(String srcFile, String dstFile, String vcodec, String acodec, int vBitrate, int aBitrate) {
		
		List<String> args = new ArrayList<String>();
		args.add("-I dummy");
		//args.add("-vvv");
		args.add(srcFile);
		args.add(transcode(vcodec, acodec, dstFile, vBitrate, aBitrate, 44100));
		
		args.add("vlc://quit");
		
		return args.toArray(new String[args.size()]);
	}
	
	private String transcode(String vcodec, String acodec, String dstFile, int vBitrate, int aBitrate, int aSampleRate) {
		StringBuilder sb = new StringBuilder();
		sb.append("--sout=#transcode{vcodec=").append(vcodec);
		if (vBitrate>0) {
			sb.append(",vb=").append(String.valueOf(vBitrate));
		}
		sb.append(",acodec=").append(acodec);
		if (aBitrate>0) {
			sb.append(",ab=").append(String.valueOf(aBitrate));
		}
		if (aSampleRate>0) {
			sb.append(",samplerate=").append(String.valueOf(aSampleRate));
		}
		
		sb.append(",channels=2,deinterlace");
		sb.append(":standard{access=file");//,mux=ts
		sb.append(",dst=").append(dstFile).append("}");
		
		return sb.toString();
	}
}
