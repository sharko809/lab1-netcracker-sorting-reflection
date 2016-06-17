package com.sharko.daniel.main;

import com.sharko.daniel.array.ArrayType;

public class Main {

	public static void main(String[] args) throws Exception {

		int exNumber = 20;

		Experiment exp = new Experiment();
		exp.startExperiment(exNumber, ArrayType.class.getDeclaredFields());

		MyExcelUtils.createCharts(exNumber);

	}

}
