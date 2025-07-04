package dev.redbeans.bootcamp.javabasics.day3;

public class Lion extends Animal implements Roarable {

    public Lion() {
        super();
    }

    public Lion(String name, int hungerLevel) {
        super(name, hungerLevel);
    }

    // @Override
    public void roar() {
        System.out.println("Roar Roar!");
    }

    public void eatMeat(){
        hungerLevel -= 10; // Lion eats more than Dog and Sheep
    }
}
