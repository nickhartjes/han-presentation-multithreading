package nl.nickhartjes.han.multithreading.multithreading;

import java.util.Random;

class Simulation extends Thread {

    private double result;
    private double error;
    private int amountOfSamplePoints;
    private String name;

    double getResult() {
        return result;
    }

    double getError() {
        return error;
    }

    public Simulation(ThreadGroup group, String name, int amountOfSamplePoints) {
        super(group, name);
        this.name = name;
        this.amountOfSamplePoints = amountOfSamplePoints;
    }

    @Override
    public void run() {
        super.run();
        this.computePI();
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

        System.out.printf("Name = %s, Result = %g, error = %.2g %n", this.name, this.result, this.error);
    }
}