package com.sharko.daniel.main;

import com.sharko.daniel.array.ArrayType;

public class Main {

    public static void main(String[] args) throws Exception {
        int numberOfExperiments = 20;
        if (args.length == 1) {
            try {
                numberOfExperiments = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                numberOfExperiments = 20;
            }
        }

        System.out.println("Starting experiment with " + numberOfExperiments + " runs.");
        new Experiment().startExperiment(numberOfExperiments, ArrayType.values());
        System.out.println("Done.");

        MyExcelUtils.createCharts(numberOfExperiments);
    }

}
