package br.imd.vision;

import br.imd.model.*;

import java.util.List;

import br.imd.controller.*;

public class People {

	public static void main(String[] args) {
		List<Image> dataset = DatasetLoader.readCSV("C:\\lp2-people\\dataset.csv");
		List<Float> imageFeatures = HogExtractor.extractFeatures("C:\\lp2-people\\mark.jpg");
		
		System.out.println(dataset.size());
		System.out.println(imageFeatures.size());

	}

}
