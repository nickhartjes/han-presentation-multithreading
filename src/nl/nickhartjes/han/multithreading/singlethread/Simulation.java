package nl.nickhartjes.han.multithreading.singlethread;

import java.util.Random;

class Simulation {

    private double result;
    private double error;
    private int amountOfSamplePoints;

    double getResult() {
        return result;
    }

    double getError() {
        return error;
    }

    Simulation(int amountOfSamplePoints) {
        this.amountOfSamplePoints = amountOfSamplePoints;
    }

    void computePI() {
        int factor = 1000;
        int factorCounter = 0;
        int counter = 0;
        Random random = new Random();

        for (int index = 0; index < amountOfSamplePoints; index++)
            for (factorCounter = 0; factorCounter < factor; factorCounter++) {

                double x = random.nextDouble();
                double y = random.nextDouble();

                double r = Math.sqrt(x * x + y * y);
                if (r <= 1.0) {
                    counter++;
                }
            }

        this.result = 4 * counter / (double) (this.amountOfSamplePoints * factorCounter);
        this.error = 100 * Math.abs(this.result - Math.PI) / Math.PI;

        System.out.printf("Result = %g, error = %.2g %n", this.result, this.error);
    }
}