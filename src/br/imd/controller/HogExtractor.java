package br.imd.controller;

import java.util.List;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

public class HogExtractor {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static List<Float> extractFeatures (String filepath) {
		HOGDescriptor hog = new HOGDescriptor();
		Mat img = new Mat();
		MatOfFloat features = new MatOfFloat();
		
		img = Imgcodecs.imread(filepath, Imgcodecs.IMREAD_GRAYSCALE);
		hog.compute(img, features);
		List<Float> arrayOfFeatures = features.toList();
		
		return arrayOfFeatures;
	}
}
