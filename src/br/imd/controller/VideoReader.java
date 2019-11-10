package br.imd.controller;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.imd.model.*;
import br.imd.exception.UnicodeFilePathException;

public class VideoReader {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static Video read (String filePath) throws UnicodeFilePathException {		
        /*if (!Paths.get(filePath).toFile().exists()){
             System.out.println("File " + filePath + " does not exist!");
             return;
        }*/

        VideoCapture camera = new VideoCapture(filePath);
        
        if (!camera.isOpened()) {
        	System.out.println("Unicode");
        	throw new UnicodeFilePathException();
        }
        
        double fps = camera.get(5);
        double frameCount = camera.get(7);
        Mat frame = new Mat();
        List<String> frames = new ArrayList<String>();
        
        int secs = 5;
        double step = secs * fps;
        double currentFrame = 0;

        while (currentFrame < frameCount) {
        	camera.set(1, (int)currentFrame);
        	
        	if (camera.read(frame)) {
        		String writePath = "captures/frame-" + (int)currentFrame + ".jpg";
        		Imgcodecs.imwrite(writePath, frame);
        		frames.add(writePath);
        	}           
        	
        	currentFrame += step;
        }
        
        camera.release();
        return new Video(fps, frames);        
	}

}
