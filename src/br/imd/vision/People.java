package br.imd.vision;

import br.imd.model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.util.Pair;

import br.imd.controller.*;

public class People {

	public static void main(String[] args) {
		List<Image> dataset = DatasetLoader.readCSV("C:\\lp2-people\\dataset.csv");
		List<Float> imageFeatures = HogExtractor.extractFeatures("C:\\lp2-people\\noPerson.png").subList(0, 1000);
		
		List<Pair<Double, Boolean>> distances = new ArrayList<Pair<Double, Boolean>>();
		
		for (Image img : dataset) {
			List<Float> features = img.getFeatures();
			float sum = 0;
			
			for (int i = 0; i < 1000; i++) {
				sum += Math.pow(features.get(i) - imageFeatures.get(i), 2);
			}
			
			distances.add(new Pair<Double, Boolean>(Math.sqrt(sum), img.hasPerson()));
		}
		
		Collections.sort(distances, new Comparator<Pair<Double, Boolean>>() {
		    @Override
		    public int compare(final Pair<Double, Boolean> lhs, final Pair<Double, Boolean> rhs) {
		        if (lhs.getKey() < rhs.getKey())
		        	return -1;
		        else if (lhs.getKey() == rhs.getKey())
		        	return 0;
		        else
		        	return 1;
		    }
		});
		
		int trueCounter = 0;
		
		for (int i = 0; i < 3; i++) {
			if (distances.get(i).getValue())
				trueCounter += 1;
		}
		
		if (trueCounter >= 1)
			System.out.println("A IMAGEM POSSUI PESSOA");
		else
			System.out.println("A IMAGEM NÃO POSSUI PESSOA");		
	}

}
