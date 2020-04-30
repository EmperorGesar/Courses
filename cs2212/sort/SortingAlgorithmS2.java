/**
 * The concrete implementation of {@link SortingAlgorithm} algorithm S2 (ex. Merge Sort)
 */

public class SortingAlgorithmS2 implements SortingAlgorithm {

    @Override
    public void sort(Obj[] sequence) {
        this.temp = new Obj[sequence.length];
        mergeSort(sequence, 0, sequence.length - 1);
        System.out.println("Sequence sorted by algorithm S2.");
    }

    private Obj[] temp;

    private void mergeSort(Obj[] sequence, int p, int r){

        if (p < r){

            int q = p + (r - p) / 2;

            mergeSort(sequence, p, q);
            mergeSort(sequence, q + 1, r);

            int i = p, j = q + 1;

            for (int k = p; k <= r; k++){
                temp[k] = sequence[k];
            }

            for (int k = p; k <= r; k++){

                if (i > q){
                    sequence[k] = temp[j++];
                } else if (j > r){
                    sequence[k] = temp[i++];
                } else if (temp[j].getAttribute() < temp[i].getAttribute()){
                    sequence[k] = temp[j++];
                } else {
                    sequence[k] = temp[i++];
                }

            }

        }

    }

}
