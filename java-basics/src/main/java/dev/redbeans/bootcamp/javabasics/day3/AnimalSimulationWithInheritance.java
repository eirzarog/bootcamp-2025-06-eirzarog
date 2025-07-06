package dev.redbeans.bootcamp.javabasics.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalSimulationWithInheritance implements Simulation {

    private List<Animal> animals = new ArrayList<>();

    public void initialize() {
        Random random = new Random();
        int totalAnimals = random.nextInt(10) + 1; // Random number of animal

        int totalDogs = 0;
        int totalLions = 0;
        int totalSheeps = 0;

        for (int i = 0; i < totalAnimals; i++) {
            int animalType = random.nextInt(3); // 0 for Dog, 1 for Lion, 2 for Sheep

            Animal animal;

            if (animalType == 0) {
                animal = new Dog("Dog" + (i + 1), random.nextInt(100), null);
                totalDogs++;
            } else if (animalType == 1) {
                animal = new Lion("Lion" + (i + 1), random.nextInt(100));
                totalLions++;
            } else {
                animal = new Sheep("Sheep" + (i + 1), random.nextInt(100));
                totalSheeps++;
            }

            animals.add(animal);
        }

        System.out.println("Simulation initialized: ");
        System.out.println("Total dogs: " + totalDogs);
        System.out.println("Total lions: " + totalLions);
        System.out.println("Total sheeps: " + totalSheeps);
        System.out.println("--------------------------------");
    }

    public void simulate() {

        // Random random = new Random();

        int totalDays = 10; // Simulate for 10 days

        System.out.println("Simulation starting for " + totalDays + " days...");

        for(int i=0; i<totalDays; i++) {
            simulateDay(i);
        }

        System.out.println("Simulation finished!");
    }

    private void simulateDay(int i) {
        System.out.println("Day #" + (i + 1) + " of simulation, started...");

        for(Animal animal : animals) {
            simulateAnimal(animal);
        }
        System.out.println("Day #" + (i + 1) + " of simulation, ended...");
    }

    private static void simulateAnimal(Animal animal) {
        animal.setHungerLevel(animal.getHungerLevel() + 1);

        if (animal instanceof Roarable){
            calculateRoarChance(animal);
        }
    }

    private static void calculateRoarChance(Animal animal) {

        Random random = new Random();

        int roarChance = random.nextInt(100); // 0-99

        if (roarChance < 10) { // 10% chance to roar
        // String animalType = animal.getClass().getSimpleName();
        // System.out.println(animalType + " name " + animal.getName());
            if (animal instanceof Dog) {
                System.out.print("Dog ");
            } else if (animal instanceof Lion) {
                System.out.print("Lion ");
            } else if (animal instanceof Sheep) {
                System.out.print("Sheep ");
            }
            System.out.println("name " + animal.getName());
            ((Roarable) animal).roar();
        }
    }

    public String getName() {
        return "with-inheritance";
    }


}
