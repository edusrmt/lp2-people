package br.imd.model;

import java.util.List;

public class Video {
	private double framerate;
	private List<String> frames;
	
	public Video (double framerate, List<String> frames) {
		this.framerate = framerate;
		this.frames = frames;
	}
	
	public List<String> getFrames() {
		return frames;
	}
}
