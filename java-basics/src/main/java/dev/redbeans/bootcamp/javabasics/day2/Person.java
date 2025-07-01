package dev.redbeans.bootcamp.javabasics.day2;

public class Person {
    private String name;
    private int year;
    private double height;
    private int age; //double
    private int months;
    private double weight;

    // Constructor
    public Person(){}

    public Person(String name, int year, double height, int age, int months) {
        this.name = name;
        this.year = year;
        this.height = height;
        this.age = age;
        this.months = months;

    }

    public Person(int year){
        this.year = year;
        this.name = null;
        this.height = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public double getAge() {
//        return age + (months / 12.0); // age in return statement is int
//    }

    public void setAge(double age) {
        this.age = (int)age; // casting to int
        this.months = (int)((age - this.age) * 12); // calculating months from the decimal part of age
    }



    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        this.months = 0; // reset months when setting age
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    // Is utility method for person to calculate body mass index
    public double bodyMassIndex(){
        if(height==0){
            return 0; // Avoid division by zero
        }
        return weight / (height * height);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", height=" + height +
                ", age=" + age +
                ", months=" + months +
                ", weight=" + weight +
                '}';
    }
}
