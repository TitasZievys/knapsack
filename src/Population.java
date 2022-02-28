import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Population {
    private int[] fitness;
    ArrayList<Individual> individuals = new ArrayList<>();

    public Population() {
    }


    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public void addIndividual(Individual individual) {
        individuals.add(individual);
    }

    public Individual getFittest() {
        Individual fittest = individuals.get(0);
        for (Individual i : individuals) {
            if (fittest.getFitness() <= i.getFitness()) {
                fittest = i;
            }
        }
        return fittest;
    }
    public Individual getLeastFittest() {
        Individual leastFittest = individuals.get(0);
        for (Individual i : individuals) {
            if (leastFittest.getFitness() >= i.getFitness()) {
                leastFittest = i;
            }
        }
        return leastFittest;
    }

    public int[] getFitnessOfAllIndividuals() {
        fitness = new int[getIndividuals().size()];
        for (int i = 0; i < getIndividuals().size(); i++) {
            fitness[i] = getIndividuals().get(i).getFitness();
        }
        return fitness;
    }

    // roulette wheel selection - gets the index of the parent chosen by a probability
    public int getParentIndex() {
        ArrayList<Integer> cumulativeSumArray = new ArrayList<>();
        int sumOfArray = 0;

        for (int i = 0; i < individuals.size(); i++) {
            sumOfArray += fitness[i];
            cumulativeSumArray.add(i, sumOfArray);
        }
        Random random = new Random();
        int index = 0;
        int indexOfParent;
        int number = random.nextInt(sumOfArray);
        while (true) {
            if (number > cumulativeSumArray.get(index)) {
                index += 1;
                continue;
            }
            int parentFitness = cumulativeSumArray.get(index);
            indexOfParent = cumulativeSumArray.indexOf(parentFitness);
            index = 0;
            break;
        }
        //returns the index of the parent individual for the selection process
        return indexOfParent;
    }

    @Override
    public String toString() {
        StringBuilder populationMembers = new StringBuilder();
        for(Individual in : individuals){
            populationMembers.append("Individual ").append(Arrays.toString(in.getGenes())).append("\n");

        }
        return populationMembers.toString();

    }


}
