package dev.redbeans.bootcamp.javabasics.day3;

public class SimulationRunner {
    public static void main(String[] args) {
        //AnimalSimulationWithoutInheritance simulation = new AnimalSimulationWithoutInheritance();
        AnimalSimulationWithInheritance simulation = new AnimalSimulationWithInheritance();
        simulation.initialize();
        simulation.simulate();
    }
}
