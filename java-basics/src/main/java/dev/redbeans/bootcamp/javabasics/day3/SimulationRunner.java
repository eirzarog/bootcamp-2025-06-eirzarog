package dev.redbeans.bootcamp.javabasics.day3;

import java.util.*;


public class SimulationRunner {

//  private static List<Simulation> simulationClasses = new ArrayList<>();
    private static Map<String, Simulation> simulations = new HashMap<>();
    // The current code uses List<Class>, which is a raw type and not type-safe.
    // To make it type-safe, use List<Class<?>>. This ensures the list only contains Class objects
    // of any type and avoids raw type warnings.
//    private static List<Class<?>> simulationClasses = List.of(
//            AnimalSimulationWithInheritance.class,
//            AnimalSimulationWithoutInheritance.class,
//            TestSimulation.class
//    );


    public static void main(String[] args) {
        Animal b = new Ant("Anty", 5, 6);
        System.out.println(((Ant)b).getLegs()); // προσοχή για runtime error ClassCastException
//        Ant a = new Ant();
////        // OBJECT toString default implementation
////        public String toString() {
////            return getClass().getName() + "@" + Integer.toHexString(hashCode());
////        }
//        System.out.println(a.toString());
//        System.out.println( a.getHungerLevel());

//        System.out.print("Arguments passed to the program: ");
//        for (String arg : args) {
//            System.out.println(arg);
//        }

        if(args.length != 1) {
            System.out.println("No arguments provided. Please use 'without-inheritance' or 'with-inheritance'.");
            return;
        }
// Finds classes that implement the Simulation interface and creates instances of them.
        ServiceLoader<Simulation> serviceLoader = ServiceLoader.load(Simulation.class);

//        for( Simulation simulation : serviceLoader) {
//            simulations.put(simulation.getName(), simulation);
//        }

        // Using forEach to populate the simulations map with loaded simulations
        serviceLoader.forEach(simulation -> simulations.put(simulation.getName(), simulation));
        /*
          The simulation is tightly coupled to the AnimalSimulationWithInheritance and AnimalSimulationWithoutInheritance classes.
          This means that if we want to add a new simulation, we have to modify this class.
          This is not a good practice as it violates the Open/Closed Principle of SOLID principles.
          To avoid this, we can use a ServiceLoader to load the simulations dynamically.
          This way, we can add new simulations without modifying this class.
          The ServiceLoader will load all the implementations of the Simulation interface and we can use them dynamically.
*/
        String mode = (args.length == 1) ? args[0] : "with-inheritance";
        Simulation simulation = simulations.get(mode);
//      Tightly coupled because of hardcoded class names
//        Simulation simulation;
//        String mode = (args.length == 1) ? args[0] : "with-inheritance";
//        switch(mode) {
//            case "without-inheritance":
//                simulation = new AnimalSimulationWithoutInheritance();
//                break;
//            case "with-inheritance":
//            default:
//                simulation = new AnimalSimulationWithInheritance();
//                break;
//
//        }

//        if the simulation was never initialized, causing a NullPointerException.
//        The return prevents this by stopping execution when the input is invalid.

        simulation.initialize();
        simulation.simulate();
    }
}
