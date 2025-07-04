package dev.redbeans.bootcamp.javabasics.day3;

public class Animal {
    protected String name;
    protected int hungerLevel;

    public Animal() {
        this.name = "Animal";
        this.hungerLevel = 0;
    }

    public Animal(String name, int hungerLevel) {
        this.name = name;
        this.hungerLevel = hungerLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

//    public void roar(){
//        System.out.println("Animal makes a sound!");
//    }
}
