import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Population {
    private double[] probability;
    private int[] fitness;
    private Individual parent1, parent2;
    ArrayList<Individual> individuals = new ArrayList<>();


    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public void addIndividual(Individual individual) {
        individuals.add(individual);
    }

    public Individual findFittest() {
        Individual fittest = individuals.get(0);
        for (Individual i : individuals) {
            if (fittest.getFitness() <= i.getFitness()) {
                fittest = i;
            }
        }
        return fittest;
    }

    public int[] getFitnessOfAllIndividuals() {
        fitness = new int[getIndividuals().size()];
        for (int i = 0; i < getIndividuals().size(); i++) {
            fitness[i] = getIndividuals().get(i).getFitness();
        }
        return fitness;
    }

    public double[] calculateProbabilities() {
        int[] fitness = getFitnessOfAllIndividuals();
        double denominator = 0;
        for (int i = 0; i < fitness.length; i++) {
            denominator += fitness[i];
        }
        probability = new double[getFitnessOfAllIndividuals().length];
        for (int i = 0; i < fitness.length; i++) {
            probability[i] = fitness[i] / denominator;
        }
        return probability;
    }

    // Roulette wheel selection of an individual
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
    //This method selects 2 random different individuals with roulette wheel selection as parents
//    public void selection(){
//        int Parent1Index = getParentIndex();
//        int Parent2Index = getParentIndex();
//        while(Parent1Index == Parent2Index){
//            Parent2Index = getParentIndex();
//        }
//        parent1 = individuals.get(Parent1Index);
//        parent2 = individuals.get(Parent2Index);
//    }





//    public void twoPointCrossover(){
//        int crossoverIndex1 = Knapsack.getRandomNumberInRange(1, parent1.numberOfGenes-2);
//        int crossoverIndex2 = Knapsack.getRandomNumberInRange(crossoverIndex1+1, parent2.numberOfGenes-1);
//        for(int i = 0; i <crossoverIndex1; i++){
//            swap(i);
//        }
//        for(int j = crossoverIndex2; j < parent1.numberOfGenes; j++){
//            swap(j);
//        }
//    }

//    public void uniformCrossover(){
//        for(int i = 0; i < parent1.numberOfGenes; i++){
//            boolean j = Math.random() > 0.5;
//            System.out.println(j);
//            if (j==true){
//                swap(i);
//            }
//        }
//    }

    public static boolean mutationProbability() {
        return Math.random() > 0.999; // probability = 1/1000
    }

    public void mutation(){

        System.out.println(parent1);
        System.out.println(parent2);
        for (int i = 0; i < parent1.numberOfGenes; i++){ // every gene has a chance of mutation
            int temp1 = Knapsack.getRandomNumberInRange(0, parent1.numberOfGenes-1);
            int temp2 = Knapsack.getRandomNumberInRange(0, parent2.numberOfGenes-1);
            int parent1RandomGene = parent1.genes[temp1];
            int parent2RandomGene = parent2.genes[temp2];
            if(mutationProbability()){
                if(parent1RandomGene == 1){
                    parent1.genes[temp1] = 0;
                }
                else{
                    parent1.genes[temp1] = 1;
                }

            }
            if(mutationProbability()){
                if(parent2RandomGene == 1){
                    parent2.genes[temp2] = 0;
                }
                else{
                    parent2.genes[temp2] = 1;
                }

            }
        }
        System.out.println(parent1);
        System.out.println(parent2);
    }

    @Override
    public String toString() {
        String populationMembers = "";
        for(Individual in : individuals){
            populationMembers += "Individual " + Arrays.toString(in.getGenes()) + "\n";

        }
        return populationMembers;

    }


}
