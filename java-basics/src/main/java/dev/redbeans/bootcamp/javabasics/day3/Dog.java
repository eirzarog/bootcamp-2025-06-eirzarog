package dev.redbeans.bootcamp.javabasics.day3;

public class Dog  extends Animal implements Roarable {

    private String breed;

    public Dog() {
        super();
    }

        public Dog(String name, int hungerLevel, String breed) {
        super(name, hungerLevel);
        this.breed = breed;
    }

    public void eatMeat() {
        hungerLevel -= 10; // Dog eats more than Sheep but less than Lion
    }

    public void eatGrass(){
        hungerLevel -= 5; // Dog can also eat grass, but less than Sheep
    }

    // @Override
    public void roar(){
        System.out.println("Woof Woof!");
    }


}
