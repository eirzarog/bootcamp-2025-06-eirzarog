package dev.redbeans.bootcamp.javabasics.day3;

public class Ant extends Animal{

    private int legs;

    public Ant(){}

    public Ant(String name, int hungerLevel, int legs) {
        super(name, hungerLevel);
        this.legs = legs;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public String toString(){
        return "Ant{" +
                "name='" + name + '\'' +
                ", hungerLevel=" + hungerLevel +
                '}';
    }
}
