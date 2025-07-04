package dev.redbeans.bootcamp.javabasics.day3;

public class Sheep extends Animal implements Roarable {
    
    public Sheep() {
        super();
    }

    public Sheep(String name, int hungerLevel) {
        super(name, hungerLevel);
    }

    // @Override
    public void roar() {
        System.out.println("Baa Baa!");
    }

    public void eatGrass() {
        hungerLevel -= 5; // Sheep eats less than Lion and Dog
    }
}
