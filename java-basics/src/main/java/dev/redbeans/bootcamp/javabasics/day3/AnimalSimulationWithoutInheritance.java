package dev.redbeans.bootcamp.javabasics.day3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AnimalSimulationWithoutInheritance implements Simulation {

    private List<Dog> dogs= new ArrayList<>();
    private List<Lion> lions = new ArrayList<>();
    private List<Sheep> sheeps = new LinkedList<>();

    public AnimalSimulationWithoutInheritance() { }

    public void initialize() {

        Random random = new Random();

        // Manipulate the number of animals
        int totalLions = random.nextInt(5) + 1;
        int totalDogs = random.nextInt(5) + 1;
        int totalSheeps = random.nextInt(5) + 1;

        for(int i=0; i<totalDogs; i++) {
            dogs.add(new Dog("Dog" + (i + 1), random.nextInt(100), null));
        }

        for(int i=0; i<totalLions; i++) {
            lions.add(new Lion("Lion" + (i + 1), random.nextInt(100)));
        }

        for(int i=0; i<totalSheeps; i++) {
            sheeps.add(new Sheep("Sheep" + (i + 1), random.nextInt(100)));
        }

        System.out.println("Simulation initialized:");
        System.out.println("Total Dogs: " + totalDogs);
        System.out.println("Total Lions: " + totalLions);
        System.out.println("Total Sheeps: " + totalSheeps);
        System.out.println("----------------------------");
    }

    public void simulate(){

        Random random = new Random();

        // Math.random() returns a double value between 0.0 (inclusive) and 1.0 (exclusive)
        // int x = (int) Math.random(); // [0, 1) returns always 0, 0.789465654 -> returns 0
        // int y = (int) (Math.random() * 100); // [0, 100) returns always 0, 0.789465654 * 100 = 78.9465654 -> returns 78

        int totalDays = 10; // Simulate for 10 days

        System.out.println("Simulation starting for " + totalDays + " days...");

        for(int i=0; i<totalDays; i++) {
            System.out.println("Day #" + (i + 1) + " of simulation, started...");

            for(Dog dog : dogs) {
                dog.setHungerLevel(dog.getHungerLevel()+1);
                // int roarChance = random.nextInt(10); // 0-9
                // if (roarChance <=1) { // 20% chance to roar
                int roarChance = random.nextInt(100); // 0-99

                if (roarChance < 10) { // 10% chance to roar
                    System.out.println("Dog name " + dog.getName());
                    dog.roar();
                }
            }

            for(Lion lion : lions) {
                lion.setHungerLevel(lion.getHungerLevel()+1);
                int roarChance = random.nextInt(100);

                if (roarChance < 20) { // 20% chance to roar
                    System.out.println("Lion name " + lion.getName());
                    lion.roar();
                }
            }

            for(Sheep sheep : sheeps) {
                sheep.setHungerLevel(sheep.getHungerLevel()+1);
                int bleatChance = random.nextInt(100);

                if (bleatChance < 30) { // 30% chance to bleat
                    System.out.println("Sheep name " + sheep.getName());
                    sheep.roar();
                }
            }

            System.out.println("Day #" + (i + 1) + " of simulation, ended...");
        }
        System.out.println("Simulation finished!");

    }

    public String getName() {
        return "without-inheritance";
    }

}
