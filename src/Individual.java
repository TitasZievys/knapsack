public class Individual {

    private Knapsack knapsack;
    public int[] gene;
    public int numberOfGenes;

    public int[] getGene() {
        return gene;
    }


    public Individual(Knapsack knapsack) {
        this.knapsack = knapsack;
        this.numberOfGenes = knapsack.getNumberOfItems();
        gene = new int[knapsack.getNumberOfItems()];
        // fill an individual with genes 1 or 0
        for (int i = 0; i < knapsack.getNumberOfItems(); i++) {
            boolean j = Math.random() > 0.5;
            if (j) {
                gene[i] = 1;
            } else gene[i] = 0;
        }
        validate();
    }


    public int getFitness() {
        int fitness = 0;
        int weight = 0;
        for (int i = 0; i < numberOfGenes; i++) {
            if (gene[i] == 1) {
                fitness += knapsack.getValueAt(i);
                weight += knapsack.getWeightAt(i);
            }
        }
        if (weight > knapsack.getCapacity()){
            return 0;
        } else {
            return fitness;
        }
    }

    private void validate(){
        // If an individual is generated with all genes = 0, change a random gene from 0 to 1
        int geneCount = 0;
        for(int i=0; i < numberOfGenes; i++){
            geneCount += gene[i];
        }
        if (geneCount == 0){
            int randomGene0 = Knapsack.getRandomNumberInRange(0, numberOfGenes-1);
            gene[randomGene0] = 1;
        }
        // If fitness is 0 because weight is over the limit, change a random gene from 1 to 0
        if (getFitness() == 0){
            // loops while fitness is 0
            while(true){
                int randomGene = Knapsack.getRandomNumberInRange(0, numberOfGenes-1);
                if(gene[randomGene] == 0){
                    continue;
                }
                gene[randomGene] = 0;
                if(getFitness() > 0){
                    break;
                }
            }
        }
    }


    @Override
    public String toString() {
        String fittest = "";
        for (int i = 0; i < gene.length; i++) {
            fittest += String.valueOf(gene[i]) + " ";
        }
        return  fittest;
    }

}