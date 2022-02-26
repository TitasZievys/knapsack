import java.util.ArrayList;
import java.util.Random;


public class Population {
    private double[] probability;
    private int[] fitness;
    private Individual Parent1, Parent2;
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
        getFitnessOfAllIndividuals();
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
    public void selection(){
        int Parent1Index = getParentIndex();
        int Parent2Index = getParentIndex();
        while(Parent1Index == Parent2Index){
            Parent2Index = getParentIndex();
        }
        Parent1 = individuals.get(Parent1Index);
        Parent2 = individuals.get(Parent2Index);
    }

    public void swap(int index){
        int temp;
        temp = Parent1.gene[index];
        Parent1.gene[index] = Parent2.gene[index];
        Parent2.gene[index] = temp;
    }

    public void onePointCrossover(){
        selection();
        int crossoverIndex = Knapsack.getRandomNumberInRange(1, Parent1.numberOfGenes-1);
        for (int i = 0; i <crossoverIndex; i++){
            swap(i);
        }
    }

    public void twoPointCrossover(){
        selection();
        int crossoverIndex1 = Knapsack.getRandomNumberInRange(1, Parent1.numberOfGenes-2);
        int crossoverIndex2 = Knapsack.getRandomNumberInRange(crossoverIndex1+1, Parent2.numberOfGenes-1);
        for(int i = 0; i <crossoverIndex1; i++){
            swap(i);
        }
        for(int j = crossoverIndex2; j < Parent1.numberOfGenes; j++){
            swap(j);
        }
    }

    public void uniformCrossover(){
        selection();
        for(int i = 0; i < Parent1.numberOfGenes; i++){
            boolean j = Math.random() > 0.5;
            System.out.println(j);
            if (j==true){
                swap(i);
            }
        }
    }

    public static boolean mutationProbability() {
        return Math.random() > 0.999; // probability = 1/1000
    }

    public void mutation(){
        onePointCrossover();
        System.out.println(Parent1);
        System.out.println(Parent2);
        for (int i = 0; i <Parent1.numberOfGenes; i++){ // every gene has a chance of mutation
            int temp1 = Knapsack.getRandomNumberInRange(0, Parent1.numberOfGenes-1);
            int temp2 = Knapsack.getRandomNumberInRange(0, Parent2.numberOfGenes-1);
            int parent1RandomGene = Parent1.gene[temp1];
            int parent2RandomGene = Parent2.gene[temp2];
            if(mutationProbability()){
                if(parent1RandomGene == 1){
                    Parent1.gene[temp1] = 0;
                }
                else{
                    Parent1.gene[temp1] = 1;
                }

            }
            if(mutationProbability()){
                if(parent2RandomGene == 1){
                    Parent2.gene[temp2] = 0;
                }
                else{
                    Parent2.gene[temp2] = 1;
                }

            }
        }
        System.out.println(Parent1);
        System.out.println(Parent2);
    }


}
