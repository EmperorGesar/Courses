import java.util.Random;

/**
 * The client code for testing
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class Client {

    public static void main(String[] args){

        // construction of a new sequence
        int size1 = 2000, size2 = 200;
        Obj[] sequence1 = new Obj[size1];
        Obj[] sequence2 = new Obj[size2];

        // randomize the sequence by its attribute
        randomize(sequence1, SequenceType.aSequenceType, size1);
        randomize(sequence2, SequenceType.bSequenceType, size2);

        // selection of algorithm
        SortingAlgorithm sortingAlgorithm1 = AlgorithmSelector.select(sequence1[0].getSequenceType(), size1);
        SortingAlgorithm sortingAlgorithm2 = AlgorithmSelector.select(sequence2[0].getSequenceType(), size2);

        // sort
        sortingAlgorithm1.sort(sequence1);
        sortingAlgorithm2.sort(sequence2);

        // output testing
        // output(sequence1);
        // output(sequence2);

    }

    private static void randomize(Obj[] sequence, SequenceType sequenceType, int size){

        Random random = new Random();
        int index;
        int[] source = new int[size];

        for (int i = 0; i < size; i++){
            source[i] = i;
        }

        int range = size;

        for (int i = 0; i < size; i++){
            index = Math.abs(random.nextInt() % range--);
            sequence[i] = new Obj(sequenceType, i, source[index]);
            source[index] = source[range];
        }

    }

    private static void output(Obj[] sequence){

        for (Obj obj : sequence) {
            System.out.println("Id: " + obj.getId() + "   \tAttribute: " + obj.getAttribute());
        }

    }

}
