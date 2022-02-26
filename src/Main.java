import java.util.ArrayList;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Population population = new Population();
        Knapsack knapsack = new Knapsack(100, 50);
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
        Population offsprings = Evolution.newGeneration(population);
        System.out.println("generation " + 0);
        System.out.println(offsprings.toString());
        int count = 0;
        int generationCount = 1;
        int fittestValue = offsprings.findFittest().getFitness();
        while(true) {
            offsprings = Evolution.newGeneration(offsprings);
            System.out.println("generation " + generationCount);
            System.out.println(offsprings.toString());
            if (fittestValue == offsprings.findFittest().getFitness()){
                count++;
                if(count == 100){
                    break;
                }
            }
            else {
                count = 0;
                fittestValue = offsprings.findFittest().getFitness();
            }
            generationCount++;

        }
        int[] chromosomes = offsprings.findFittest().getGenes();
        System.out.println("Knapsack items are "+"\n");
        for (int i = 0; i < chromosomes.length; i++ ){
            if(chromosomes[i] == 1){
                System.out.println(knapsack.getValueAt(i) +"    "+ knapsack.getWeightAt(i));
            }
        }
        System.out.println(offsprings.toString());






        //        System.out.println("Probabilities of all individuals to be selected = "+Arrays.toString(population.calculateProbabilities()));
//        evolution.onePointCrossover();
//        population.mutation();

    }
}