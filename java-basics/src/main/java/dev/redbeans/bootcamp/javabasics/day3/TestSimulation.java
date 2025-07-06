package dev.redbeans.bootcamp.javabasics.day3;

public class TestSimulation implements Simulation{
    @Override
    public void initialize() {
        System.out.println("Initiating Test Simulation");
    }

    @Override
    public void simulate() {
        System.out.println("Running Test Simulation");
    }

    @Override
    public String getName() {
        return "test-simulation";
    }
}
