package nl.nickhartjes.han.multithreading.singlethread;

public class Main {

    public static void main(String[] args) {
        double sum = 0;
        int numberOfComputations = 50;
        int samplePoints = 50000;

        long start = System.nanoTime();

        Simulation sim = new Simulation(samplePoints);

        for (int i = 0; i < numberOfComputations; i++) {
            sim.computePI();
            sum += sim.getError();
        }

        long end = System.nanoTime();

        System.out.printf("Average error is %g  %n", sum / numberOfComputations);
        System.out.printf("Simulation took %g seconds %n", (double) (end - start) / 1e9);
    }
}
