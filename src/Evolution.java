import java.util.ArrayList;

public class Evolution {
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

    public void onePointCrossover(){
        int crossoverIndex = Knapsack.getRandomNumberInRange(1, parent1.numberOfGenes-1);
        for (int i = 0; i <crossoverIndex; i++){
            swap(i);
        }
    }
//    public static void swap(int index){
//        int temp;
//        temp = genes1[index];
//        parent1.gene[index] = parent2.gene[index];
//        parent2.gene[index] = temp;
//    }
}
