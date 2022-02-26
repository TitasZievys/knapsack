import java.util.ArrayList;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Population population = new Population();
        Evolution evolution = new Evolution();
        Knapsack knapsack = new Knapsack(100, 10);
        knapsack.displayKnapsack();


        for(int i = 0; i<10; i++){
            Individual in = new Individual(knapsack);
            population.addIndividual(in);
        }



        ArrayList<Individual> individuals = population.getIndividuals();

        for(Individual in : individuals){
            System.out.println("Individual " + Arrays.toString(in.getGenes()));
        }
        System.out.println("Fittest individual = " + population.findFittest().toString());
        System.out.println("Fitness of all individuals = "+Arrays.toString(population.getFitnessOfAllIndividuals()));
        population.getParentIndex();






        //        System.out.println("Probabilities of all individuals to be selected = "+Arrays.toString(population.calculateProbabilities()));
//        evolution.onePointCrossover();
//        population.mutation();

    }
}