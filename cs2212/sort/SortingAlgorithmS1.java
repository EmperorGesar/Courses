/**
 * The concrete implementation of {@link SortingAlgorithm} algorithm S1 (ex. Quick Sort)
 */

public class SortingAlgorithmS1 implements SortingAlgorithm {

    @Override
    public void sort(Obj[] sequence) {
        quickSort(sequence, 0, sequence.length - 1);
        System.out.println("Sequence sorted by algorithm S1.");
    }

    private void quickSort(Obj[] sequence, int p, int r){

        if (p < r){

            int x = sequence[r].getAttribute();
            int q = p - 1;
            Obj temp;

            for (int i = p; i < r; i++){

                if (sequence[i].getAttribute() <= x){

                    q = q + 1;
                    temp = sequence[q];
                    sequence[q] = sequence[i];
                    sequence[i] = temp;

                }

            }

            temp = sequence[q + 1];
            sequence[q + 1] = sequence[r];
            sequence[r] = temp;

            q = q + 1;
            quickSort(sequence, p, q - 1);
            quickSort(sequence, q + 1, r);

        }

    }

}
