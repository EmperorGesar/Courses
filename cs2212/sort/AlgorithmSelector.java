/**
 * The class for the implementation of factory design pattern which chooses the algorithms
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class AlgorithmSelector {

    public static SortingAlgorithm select(SequenceType sequenceType, int sequenceSize){

        if (sequenceType == SequenceType.aSequenceType && sequenceSize > 1000){
            return new SortingAlgorithmS1();
        } else {
            return new SortingAlgorithmS2();
        }

    }

}
