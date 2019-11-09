package br.imd.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import br.imd.model.Image;

public class DatasetLoader {
	public static List<Image> readCSV (String filepath) {   
		List<Image> images = new ArrayList<Image>();
		
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filepath))) {
        	String row = csvReader.readLine();
        	
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                List<Float> arrayOfFeatures = new ArrayList<Float>();
                
                for (int i = 0; i < 1000; i++) {
                	arrayOfFeatures.add(Float.parseFloat(data[i]));
                }
                
                images.add(new Image (data[1000].equals("person"), arrayOfFeatures));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return images;
    }
}

