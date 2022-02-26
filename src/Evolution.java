import java.util.ArrayList;

public class Evolution {
    //This method selects 2 random different individuals with roulette wheel selection as parents
    public static ArrayList<Individual> selection(Population population){
        int parentIndex1 = population.getParentIndex();
        int parentIndex2 = population.getParentIndex();
        while(parentIndex1 == parentIndex2){
            parentIndex2 = population.getParentIndex();
        }
        Individual parent1 = population.getIndividuals().get(parentIndex1);
        Individual parent2 = population.getIndividuals().get(parentIndex2);
        ArrayList<Individual> parents = new ArrayList<>();
        parents.add(parent1);
        parents.add(parent2);
        return parents;
    }

    public static ArrayList<Individual> onePointCrossover(ArrayList<Individual> parents){
        Individual parent1 = parents.get(0);
        Individual parent2 = parents.get(1);
        int[] parent1Genes = parent1.getGenes();
        int[] parent2Genes = parent2.getGenes();
        int crossoverIndex = Knapsack.getRandomNumberInRange(0, parent1Genes.length);
        int[] child1Genes = new int[parent1Genes.length];
        int[] child2Genes = new int[parent1Genes.length];
        for (int i = 0; i <crossoverIndex; i++){
            child1Genes[i] = parent1Genes[i];
            child2Genes[i] = parent2Genes[i];
        }
        for (int i = crossoverIndex; i < parent1Genes.length; i++){
            child1Genes[i] = parent1Genes[i];
            child2Genes[i] = parent2Genes[i];
        }
        Individual child1 = new Individual(parent1.getKnapsack(), child1Genes);
        Individual child2 = new Individual(parent2.getKnapsack(), child2Genes);
        ArrayList<Individual> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        return children;
    }

    public static ArrayList<Individual> twoPointCrossover(ArrayList<Individual> parents){
        Individual parent1 = parents.get(0);
        Individual parent2 = parents.get(1);
        int[] parent1Genes = parent1.getGenes();
        int[] parent2Genes = parent2.getGenes();
        int crossoverIndex1 = Knapsack.getRandomNumberInRange(0, parent1Genes.length-2);
        int crossoverIndex2 = Knapsack.getRandomNumberInRange(crossoverIndex1, parent2Genes.length);
        int[] child1Genes = new int[parent1Genes.length];
        int[] child2Genes = new int[parent2Genes.length];
        for (int i = 0; i <crossoverIndex1; i++){
            child1Genes[i] = parent1Genes[i];
            child2Genes[i] = parent2Genes[i];
        }
        for (int i = crossoverIndex1; i < crossoverIndex2; i++){
            child1Genes[i] = parent1Genes[i];
            child2Genes[i] = parent2Genes[i];
        }
        for (int i =crossoverIndex2; i < parent1Genes.length; i++){
            child1Genes[i] = parent1Genes[i];
            child2Genes[i] = parent2Genes[i];
        }
        Individual child1 = new Individual(parent1.getKnapsack(), child1Genes);
        Individual child2 = new Individual(parent2.getKnapsack(), child2Genes);
        ArrayList<Individual> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        return children;
    }

    public static ArrayList<Individual> uniformCrossover(ArrayList<Individual> parents) {
        Individual parent1 = parents.get(0);
        Individual parent2 = parents.get(1);
        int[] parent1Genes = parent1.getGenes();
        int[] parent2Genes = parent2.getGenes();
        int[] childGenes = new int[parent1Genes.length];
        for(int i = 0; i < parent1Genes.length; i++){
            boolean j = Math.random() > 0.5;
            if(j){
                childGenes[i] = parent1Genes[i];
            }
            else {
                childGenes[i] = parent2Genes[i];
            }
        }
        Individual child = new Individual(parent1.getKnapsack(), childGenes);
        ArrayList<Individual> children = new ArrayList<>();
        children.add(child);
        return children;
    }

    public static boolean mutationProbability() {
        return Math.random() > 0.999; // probability = 1/1000
    }

//    public void mutation(){
//
//        System.out.println(parent1);
//        System.out.println(parent2);
//        for (int i = 0; i < parent1.numberOfGenes; i++){ // every gene has a chance of mutation
//            int temp1 = Knapsack.getRandomNumberInRange(0, parent1.numberOfGenes-1);
//            int temp2 = Knapsack.getRandomNumberInRange(0, parent2.numberOfGenes-1);
//            int parent1RandomGene = parent1.genes[temp1];
//            int parent2RandomGene = parent2.genes[temp2];
//            if(mutationProbability()){
//                if(parent1RandomGene == 1){
//                    parent1.genes[temp1] = 0;
//                }
//                else{
//                    parent1.genes[temp1] = 1;
//                }
//
//            }
//            if(mutationProbability()){
//                if(parent2RandomGene == 1){
//                    parent2.genes[temp2] = 0;
//                }
//                else{
//                    parent2.genes[temp2] = 1;
//                }
//
//            }
//        }
//        System.out.println(parent1);
//        System.out.println(parent2);
//    }

    //    public static void swap(int index){
//        int temp;
//        temp = genes1[index];
//        parent1.gene[index] = parent2.gene[index];
//        parent2.gene[index] = temp;
//    }
}