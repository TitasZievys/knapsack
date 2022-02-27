import java.util.ArrayList;
import java.util.Arrays;

public class Evolution {
    //This method selects 2 random different individuals with roulette wheel selection as parents
    public static ArrayList<Individual> selection(Population population) { //different method for uniformCrossover
        int parentIndex1 = population.getParentIndex();
        int parentIndex2 = population.getParentIndex();

        while (parentIndex1 == parentIndex2) {
            parentIndex2 = population.getParentIndex();
        }
        Individual parent1 = population.getIndividuals().get(parentIndex1);
        Individual parent2 = population.getIndividuals().get(parentIndex2);
        ArrayList<Individual> parents = new ArrayList<>();
        parents.add(parent1);
        parents.add(parent2);
        printIndividuals(parents, "Parents");
        return parents;
    }
    public static void printIndividuals(ArrayList<Individual> individuals, String name){
        System.out.println(name);
        for(Individual individual : individuals){
            System.out.println(Arrays.toString(individual.getGenes()));
        }
    }


    public static ArrayList<Individual> onePointCrossover(ArrayList<Individual> parents) {
        Individual parent1 = parents.get(0);
        Individual parent2 = parents.get(1);
        int[] parent1Genes = parent1.getGenes();
        int[] parent2Genes = parent2.getGenes();
        int crossoverIndex = Knapsack.getRandomNumberInRange(0, parent1Genes.length);
        System.out.println(crossoverIndex);
        int[] child1Genes = new int[parent1Genes.length];
        int[] child2Genes = new int[parent1Genes.length];
        for (int i = 0; i < crossoverIndex; i++) {
            child1Genes[i] = parent1Genes[i];
            child2Genes[i] = parent2Genes[i];
        }
        for (int i = crossoverIndex; i < parent1Genes.length; i++) {
            child1Genes[i] = parent2Genes[i];
            child2Genes[i] = parent1Genes[i];
        }
        Individual child1 = new Individual(parent1.getKnapsack(), child1Genes);
        Individual child2 = new Individual(parent2.getKnapsack(), child2Genes);
        ArrayList<Individual> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        return children;
    }

    public static ArrayList<Individual> twoPointCrossover(ArrayList<Individual> parents) {
        Individual parent1 = parents.get(0);
        Individual parent2 = parents.get(1);
        int[] parent1Genes = parent1.getGenes();
        int[] parent2Genes = parent2.getGenes();
        int crossoverIndex1 = Knapsack.getRandomNumberInRange(0, parent1Genes.length - 2);
        int crossoverIndex2 = Knapsack.getRandomNumberInRange(crossoverIndex1 + 1, parent2Genes.length);
        int[] child1Genes = new int[parent1Genes.length];
        int[] child2Genes = new int[parent2Genes.length];
        for (int i = 0; i < crossoverIndex1; i++) {
            child1Genes[i] = parent1Genes[i];
            child2Genes[i] = parent2Genes[i];
        }
        for (int i = crossoverIndex1; i < crossoverIndex2; i++) {
            child1Genes[i] = parent2Genes[i];
            child2Genes[i] = parent1Genes[i];
        }
        for (int i = crossoverIndex2; i < parent1Genes.length; i++) {
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
        for (int i = 0; i < parent1Genes.length; i++) {
            boolean j = Math.random() > 0.5;
            if (j) {
                childGenes[i] = parent1Genes[i];
            } else {
                childGenes[i] = parent2Genes[i];
            }
        }
        Individual child = new Individual(parent1.getKnapsack(), childGenes);
        ArrayList<Individual> children = new ArrayList<>();
        children.add(child);
        return children;
    }

    public static boolean mutationProbability() {
        return Math.random() > 0.99; // probability = 1/1000
    }

    public static Individual mutation(Individual child) {
        int[] genes = new int[child.getGenes().length];
        for (int i = 0; i < child.getGenes().length; i++) {
            if (!mutationProbability()) {
                genes[i] = child.getGenes()[i];
            } else {
                if (child.getGenes()[i] == 1) {
                    genes[i] = 0;
                } else {
                    genes[i] = 1;
                }
            }
        }
        Individual mutatedChild = new Individual(child.getKnapsack(), genes);
        return mutatedChild;
    }


        public static Population newGeneration (Population population){
            Population newGen = new Population();
            while (newGen.getIndividuals().size() < population.getIndividuals().size()) {
                ArrayList<Individual> parents = selection(population);
                ArrayList<Individual> children = twoPointCrossover(parents);
                Individual mutatedChild1 = mutation(children.get(0));
                Individual mutatedChild2 = mutation(children.get(1));
                newGen.addIndividual(mutatedChild1);
                newGen.addIndividual(mutatedChild2);
                printIndividuals(children, "Children");

            }

            newGen.getFitnessOfAllIndividuals();
            return newGen;
        }

    public static Population newGenerationUniform (Population population){
        Population newGenUniform = new Population();
        while (newGenUniform.getIndividuals().size() < population.getIndividuals().size()) {
            ArrayList<Individual> parents = selection(population);
            ArrayList<Individual> children = uniformCrossover(parents);
            Individual mutatedChild = mutation(children.get(0));
            newGenUniform.addIndividual(mutatedChild);
            printIndividuals(children, "Children");

        }

        newGenUniform.getFitnessOfAllIndividuals();
        return newGenUniform;
    }

    public static void  oneMemberElitism(Population previousGeneration, Population thisGeneration){ // fittest individual always copied into population
        ArrayList<Individual> arraylisttemp = thisGeneration.getIndividuals();
        Individual leastFittestChild = thisGeneration.getLeastFittest();
        Individual fittestParentIndividual = previousGeneration.getFittest();
        if (fittestParentIndividual.getFitness() >= leastFittestChild.getFitness()){
            arraylisttemp.remove(leastFittestChild);
            arraylisttemp.add(fittestParentIndividual);
        }

    }

    public static Population copyOfPopulation(Population oldPopulation){
        Population newPopulation = new Population();
        for(Individual i : oldPopulation.getIndividuals()){
            Individual newIndividual = new Individual(i.getKnapsack(), i.getGenes());
            newPopulation.addIndividual(newIndividual);
        }
        return newPopulation;
    }
}
