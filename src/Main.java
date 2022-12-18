import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Population population = new Population();
        Knapsack knapsack = new Knapsack(100, 20); // (capacity, numberOfItems) - change to your liking
        knapsack.displayKnapsack();
        System.out.println("\n");
        for(int i = 0; i<50; i++){ // i < your selected population size
            Individual in = new Individual(knapsack);
            population.addIndividual(in);
        }
        Arrays.toString(population.getFitnessOfAllIndividuals());
        Population offsprings = Evolution.newGenerationUniform(population); // newGenerationUniform or newGeneration can be chosen
        Evolution.replacingFittestElitism(population, offsprings); // replacingFittestElitism or replacingLowestElitism can be chosen
        int count = 0;
        int generationCount = 1;
        int fittestValue = offsprings.getFittest().getFitness();
        while(true) {
            Population oldPopulation = Evolution.copyOfPopulation(offsprings);
            offsprings = Evolution.newGenerationUniform(offsprings); // newGenerationUniform or newGeneration can be chosen
            Evolution.replacingFittestElitism(oldPopulation, offsprings); // replacingFittestElitism or replacingLowestElitism can be chosen
            // If running for 100 generations and no new fittest member found - break the loop
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
        int totalWeight = 0;
        int totalValue = 0;
        System.out.println("Knapsack items are: "+"\n");
        for (int i = 0; i < chromosomes.length; i++ ){
            // if the item is in the knapsack, its value and weight is displayed
            if(chromosomes[i] == 1){
                totalWeight += knapsack.getWeightAt(i);
                totalValue += knapsack.getValueAt(i);

                System.out.println("Value "+knapsack.getValueAt(i) +"    "+"Weight "+ knapsack.getWeightAt(i));
            }
        }

        System.out.println("\n");
        System.out.println("Total weight = "+totalWeight);
        System.out.println("Total value = "+totalValue);
        System.out.println("generation " + generationCount);
    }
}