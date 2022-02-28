import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Population population = new Population();
        Knapsack knapsack = new Knapsack(100, 20); // the number of items changes
        knapsack.displayKnapsack();
        for(int i = 0; i<100; i++){ // population size changes
            Individual in = new Individual(knapsack);
            population.addIndividual(in);
        }
        System.out.println("Fittest individual = " + population.getFittest().toString());
        System.out.println("Fitness of all individuals = "+Arrays.toString(population.getFitnessOfAllIndividuals()));
        Population offsprings = Evolution.newGenerationUniform(population);
        Evolution.replacingFittestElitism(population, offsprings);
        System.out.println("generation " + 0);
        System.out.println(offsprings.toString());
        int count = 0;
        int generationCount = 1;
        int fittestValue = offsprings.getFittest().getFitness();
        while(true) {
            Population oldPopulation = Evolution.copyOfPopulation(offsprings);
            offsprings = Evolution.newGenerationUniform(offsprings);
            Evolution.replacingFittestElitism(oldPopulation, offsprings);
            System.out.println("generation " + generationCount);
            System.out.println("Fittest value" + offsprings.getFittest().getFitness());
            if (fittestValue == offsprings.getFittest().getFitness()){
                count++;
                if(count == 100){
                    break;
                }
            }
            else {
                count = 0;
                fittestValue = offsprings.getFittest().getFitness();
            }
            generationCount++;

        }
        int[] chromosomes = offsprings.getFittest().getGenes();
        knapsack.displayKnapsack();
        int totalWeight = 0;
        int totalValue = 0;
        System.out.println("Knapsack items are "+"\n");
        for (int i = 0; i < chromosomes.length; i++ ){
            if(chromosomes[i] == 1){
                totalWeight += knapsack.getWeightAt(i);
                totalValue += knapsack.getValueAt(i);

                System.out.println("Value "+knapsack.getValueAt(i) +"    "+"Weight "+ knapsack.getWeightAt(i));
            }
        }


        System.out.println(offsprings.toString());
        System.out.println("Total weight = "+totalWeight);
        System.out.println("Total value = "+totalValue);
        System.out.println("generation " + generationCount);
    }
}