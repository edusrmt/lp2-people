package br.imd.controller;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.imd.model.*;
import br.imd.exception.UnicodeFilePathException;

public class VideoReader {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static Video read (String filePath, int timeStep) throws UnicodeFilePathException {	
		new File("captures/").mkdir();

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

        double step = timeStep * fps;
        double currentFrame = 0;
        int counter = 0;

        while (currentFrame < frameCount) {
        	camera.set(1, (int)currentFrame);
        	
        	if (camera.read(frame)) {
        		String writePath = "captures/frame-" + counter + ".jpg";
        		Imgproc.resize(frame, frame, new Size(64, 128), 0.5, 0.5, Imgproc.INTER_LINEAR);
        		Imgcodecs.imwrite(writePath, frame);
        		frames.add(writePath);
        	}           
        	
        	currentFrame += step;
        	counter++;
        }
        
        camera.release();
        return new Video(fps, frames);        
	}

}
