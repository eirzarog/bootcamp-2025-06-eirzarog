package dev.redbeans.bootcamp.javabasics.day2;

import java.util.Scanner;

public class MainDay2 {

    public static void main(String[] args) {
       System.out.println("Hello World!");
       int x = 5;

       if(x > 0) {
           System.out.println("x is positive");
       } else if (x < 0) {
           System.out.println("x is negative");
       } else {
           System.out.println("x is zero");
       }

     for (int i = 0; i < 10; i++) {
          System.out.println("i is: " + i);
     }

     int[] numbers = {1, 2, 3, 4, 5}; // hardcoded initialization, saves memory in stack, native type
     int[] numbers2 = new int[5]; // int[] numbers2; saves in stack and with the new keyword saves in heap memory allocation
     // initializing an array with a for loop
     for (int i = 0; i < numbers2.length; i++) {
            numbers2[i] = numbers[i] * 2;
        }

//        for (int number : numbers) {
//            System.out.print(number + " ");
//        }

        // printing the contents of the array
        for (int i=0; i < numbers2.length; i++) {
            System.out.print("numbers[" + i + "] = " + numbers[i] + " ");
            System.out.println("|| numbers2[" + i + "] = " + numbers2[i] + " ");
        }

    Person p = new Person();
        p.setName("John Doe");
        p.setYear(1990);
        p.setHeight(1.75);
        p.setAge(33);
        p.setMonths(6); // setting months to 6, which means 6 months after 33 years

        System.out.println("Person name: " + p.getName());
        System.out.println("Person year: " + p.getYear());
        System.out.println("Person height: " + p.getHeight());
        System.out.print("Give Person age: ");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Object inputAge;
        try {
            inputAge = Integer.parseInt(input);
        } catch (NumberFormatException e1) {
            try {
                inputAge = Double.parseDouble(input);
            } catch (NumberFormatException e2) {
                inputAge = null;
            }
        }

        System.out.println("The age is type of: " + (inputAge !=null ? inputAge.getClass() : null));
        System.out.println("Person age: " + (inputAge !=null ? inputAge: ""));

        Person person2 = new Person("Jane Doe", 1995, 1.65, 28, 3);

        Person baby = new Person(2025);

        System.out.println("Person 2 name: " + person2.toString());
        System.out.println("Baby name: " + baby.getName());
        System.out.println("Baby: " + baby);

        int[] k = new int[5];
        Person[] person = new Person[5];

        for (int i =0; i<person.length; i++) {
            person[i] = new Person("Person " + (i + 1), 2000 + i, 1.70 + (i * 0.05), 20 + i, i);
        }

        for(int i= 0; i < person.length; i++) {
            System.out.println("Person " + (i + 1) + ": " + person[i]);
        }

    }
}
