package nl.nickhartjes.han.multithreading.multithreading;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        double sum = 0;
        int numberOfComputations = 50;
        int samplePoints = 50000;
        int amountDone = 0;

        List<Simulation> simulationList = new ArrayList<>();
        ThreadGroup threadGroup = new ThreadGroup("main");

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        long start = System.nanoTime();

        for (int index = 0; index < numberOfComputations; index++) {
            simulationList.add(new Simulation(threadGroup, "Pi " + index, samplePoints));
        }

        while (amountDone < simulationList.size()) {
            if (threadGroup.activeCount() < availableProcessors) {
                Simulation simulation = simulationList.get(amountDone);
                simulation.start();
                amountDone++;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        while (threadGroup.activeCount() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();

        System.out.printf("Average error is %g  %n", sum / numberOfComputations);
        System.out.printf("Simulation took %g seconds %n", (double) (end - start) / 1e9);
    }
}
