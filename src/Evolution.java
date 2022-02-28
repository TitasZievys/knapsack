import java.util.ArrayList;


public class Evolution {
    //This method selects 2 random different individuals with roulette wheel selection as parents
    public static ArrayList<Individual> selection(Population population) {
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
        return parents;
    }

    // one-point crossover implementation
    public static ArrayList<Individual> onePointCrossover(ArrayList<Individual> parents) {
        Individual parent1 = parents.get(0);
        Individual parent2 = parents.get(1);
        int[] parent1Genes = parent1.getGenes();
        int[] parent2Genes = parent2.getGenes();
        int crossoverIndex = Knapsack.getRandomNumberInRange(0, parent1Genes.length);
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
    // two-point crossover implementation
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
    // uniform crossover implementation
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
        return Math.random() > 0.999; // probability = 1/1000
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
        return new Individual(child.getKnapsack(), genes);
    }

    // with this method we create a new generation with either two one of two point crossover strategies
    public static Population newGeneration(Population population) {
        Population newGen = new Population();
        while (newGen.getIndividuals().size() < population.getIndividuals().size()) {
            ArrayList<Individual> parents = selection(population);
            ArrayList<Individual> children = twoPointCrossover(parents); // here we include the strategy
            Individual mutatedChild1 = mutation(children.get(0));
            Individual mutatedChild2 = mutation(children.get(1));
            newGen.addIndividual(mutatedChild1);
            newGen.addIndividual(mutatedChild2);

        }

        newGen.getFitnessOfAllIndividuals();
        return newGen;
    }
    // this method creates a new generation with uniformCrossover strategy
    public static Population newGenerationUniform(Population population) {
        Population newGenUniform = new Population();
        while (newGenUniform.getIndividuals().size() < population.getIndividuals().size()) {
            ArrayList<Individual> parents = selection(population);
            ArrayList<Individual> children = uniformCrossover(parents);
            Individual mutatedChild = mutation(children.get(0));
            newGenUniform.addIndividual(mutatedChild);
        }

        newGenUniform.getFitnessOfAllIndividuals();
        return newGenUniform;
    }

    public static void replacingLowestElitism(Population previousGeneration, Population thisGeneration) {
        ArrayList<Individual> arrayOfThisGeneration = thisGeneration.getIndividuals();
        Individual leastFittestChild = thisGeneration.getLeastFittest();
        Individual fittestParentIndividual = previousGeneration.getFittest();
        Individual fittestChildIndividual = thisGeneration.getFittest();
        if (fittestParentIndividual.getFitness() >= fittestChildIndividual.getFitness()) {
            arrayOfThisGeneration.remove(leastFittestChild);
            arrayOfThisGeneration.add(fittestParentIndividual);
        }

    }


    public static void replacingFittestElitism(Population previousGeneration, Population thisGeneration) {
        ArrayList<Individual> thisGenerationArray = thisGeneration.getIndividuals();
        Individual fittestParentIndividual = previousGeneration.getFittest();
        Individual fittestChildIndividual = thisGeneration.getFittest();
        if (fittestParentIndividual.getFitness() >= fittestChildIndividual.getFitness()) {
            thisGenerationArray.remove(fittestChildIndividual);
            thisGenerationArray.add(fittestParentIndividual);
        }
    }

    public static Population copyOfPopulation(Population oldPopulation) {
        Population newPopulation = new Population();
        for (Individual i : oldPopulation.getIndividuals()) {
            Individual newIndividual = new Individual(i.getKnapsack(), i.getGenes());
            newPopulation.addIndividual(newIndividual);
        }
        return newPopulation;
    }
}
