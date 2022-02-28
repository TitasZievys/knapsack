public class Knapsack{
    private final int capacity;
    private final int numberOfItems;
    private final int[] weights;
    private final int[] values;

    public Knapsack(int capacity, int numberOfItems){
        this.capacity = capacity;
        this.numberOfItems = numberOfItems;

        weights = new int[numberOfItems];
        values = new int[numberOfItems];

        for(int i = 0; i < numberOfItems; i++){
            weights[i] = getRandomNumberInRange(1, capacity/2);
            values[i] = getRandomNumberInRange(1, 50);
        }
    }
    public static int getRandomNumberInRange(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }
    public int getCapacity(){
        return capacity;
    }
    public void displayKnapsack(){
        for(int i = 0; i < numberOfItems; i++){
            System.out.println("Item "+ (i+1) + "\t Weight " + weights[i] + "\t Value " + values[i]);
        }
    }
    public int getWeightAt(int index){
        return weights[index];
    }
    public int getValueAt(int index){
        return values[index];
    }
}