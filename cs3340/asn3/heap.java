/**
 * Heap data structure as priority queue used for extracting the edge with the minimum weight
 */

public class heap {

    private int[] min_heap;
    private int[] keys;
    private int n;

    public heap(int[] keys, int n){

        this.n = n;
        this.keys = new int[n + 1];
        System.arraycopy(keys, 0, this.keys, 0, this.keys.length);

        this.min_heap = new int[2 * n];

        for (int i = n; i <= 2 * n - 1; i++){
            this.min_heap[i] = i - n + 1;
        }

        for (int i = n - 1; i >= 1; i--){

            if (keys[this.min_heap[2 * i]] < keys[this.min_heap[2 * i + 1]]){
                this.min_heap[i] = this.min_heap[2 * i];
            } else {
                this.min_heap[i] = this.min_heap[2 * i + 1];
            }

        }

    }

    public boolean in_heap(int id){

        for (int i = this.n; i <= 2 * this.n - 1; i++){
            if (id == this.min_heap[i]) return true;
        }

        return false;

    }

    public int min_key(){
        return this.keys[this.min_heap[1]];
    }

    public int min_id(){
        return this.min_heap[1];
    }

    public int key(int id){
        return this.keys[id];
    }

    public void delete_min(){

        this.keys[0] = Integer.MAX_VALUE;
        this.min_heap[this.min_heap[1] + this.n - 1] = 0;

        int i = (this.min_heap[1] + this.n - 1) / 2;
        while (i >= 1){

            if (keys[this.min_heap[2 * i]] < keys[this.min_heap[2 * i + 1]]){
                this.min_heap[i] = this.min_heap[2 * i];
            } else {
                this.min_heap[i] = this.min_heap[2 * i + 1];
            }

            i = i / 2;

        }

    }

    public void decrease_key(int id, int new_key){

        if (new_key < this.keys[id]){

            this.keys[id] = new_key;

            id = (id + this.n - 1) / 2;
            while (id >= 1){

                if (keys[this.min_heap[2 * id]] < keys[this.min_heap[2 * id + 1]]){
                    this.min_heap[id] = this.min_heap[2 * id];
                } else {
                    this.min_heap[id] = this.min_heap[2 * id + 1];
                }

                id = id / 2;

            }

        }

    }

}
