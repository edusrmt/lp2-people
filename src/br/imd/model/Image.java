package br.imd.model;

import java.util.List;

public class Image {
	private boolean hasPerson;
	private List<Float> features;
	
	public Image (boolean hasPerson, List<Float> features) {
		this.hasPerson = hasPerson;
		this.features = features;
	}
	
	public boolean hasPerson () {
		return this.hasPerson;
	}
	
	public List<Float> getFeatures () {
		return this.features;
	}
}
