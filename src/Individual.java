public class Individual {

    private Knapsack knapsack;
    public int[] genes;
    public int numberOfGenes;

    public int[] getGenes() {
        return genes;
    }


    public Individual(Knapsack knapsack) {
        this.knapsack = knapsack;
        this.numberOfGenes = knapsack.getNumberOfItems();
        genes = new int[knapsack.getNumberOfItems()];
        // fill an individual with genes 1 or 0
        for (int i = 0; i < knapsack.getNumberOfItems(); i++) {
            boolean j = Math.random() > 0.5;
            if (j) {
                genes[i] = 1;
            } else genes[i] = 0;
        }
        validate();
    }

    public Individual(Knapsack knapsack, int[] genes){
        this.numberOfGenes = genes.length;
        this.knapsack = knapsack;
        this.genes = genes;
        validate();
    }

    public Knapsack getKnapsack() {
        return knapsack;
    }

    public int getFitness() {
        int fitness = 0;
        int weight = 0;
        for (int i = 0; i < numberOfGenes; i++) {
            if (genes[i] == 1) {
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
            geneCount += genes[i];
        }
        if (geneCount == 0){
            int randomGene0 = Knapsack.getRandomNumberInRange(0, numberOfGenes-1);
            genes[randomGene0] = 1;
        }
        // If fitness is 0 because weight is over the limit, change a random gene from 1 to 0
        if (getFitness() == 0){
            // loops while fitness is 0
            while(true){
                int randomGene = Knapsack.getRandomNumberInRange(0, numberOfGenes-1);
                if(genes[randomGene] == 0){
                    continue;
                }
                genes[randomGene] = 0;
                if(getFitness() > 0){
                    break;
                }
            }
        }
    }


    @Override
    public String toString() {
        String fittest = "";
        for (int i = 0; i < genes.length; i++) {
            fittest += String.valueOf(genes[i]) + " ";
        }
        return  fittest;
    }

}