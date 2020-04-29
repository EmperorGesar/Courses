/**
 * Union-find structure to find connected components
 */

public class uandf {

    private int[] parent, rank;
    private boolean valid;

    public uandf(int n) {

        this.parent = new int[n];
        this.rank = new int[n];
        this.valid = true;

        for (int i = 0; i < n; i++){
            make_set(i);
        }

    }

    public void make_set(int i){

        if (this.valid){
            this.parent[i] = i;
            this.rank[i] = 0;
        } else {
            System.out.println("Could not make sets. Structure has already been finalized.");
        }

    }

    public void union_sets(int i, int j){

        if (this.valid){

            int x = find_set(i), y = find_set(j);

            if (this.rank[x] > this.rank[y]) {
                this.parent[y] = x;
            } else if (this.rank[x] < this.rank[y]) {
                this.parent[x] = y;
            } else if (x != y) {
                this.parent[x] = y;
                this.rank[y] = this.rank[y] + 1;
            }

        } else {
            System.out.println("Could not union sets. Structure has already been finalized.");
        }

    }

    public int find_set(int i){

        if (i != this.parent[i]) this.parent[i] = find_set(this.parent[i]);
        return this.parent[i];

    }

    public int final_sets(){

        int total = 0;

        for (int i = 0; i < this.parent.length - 1; i++){
            if (i == find_set(i)) total = total + 1;
        }

        int[] list = new int[total];
        int k = 0;

        for (int i = 0; i < this.parent.length - 1; i++){

            if (i == find_set(i)){
                list[k] = i;
                k = k + 1;
            }

        }

        int[] temp = new int[this.parent.length - 1];
        int cur;

        for (int i = 0; i < this.parent.length - 1; i++){

            k = 0;
            cur = find_set(i);

            while (k != total && cur != list[k]){
                k = k + 1;
            }

            if (k < total) temp[i] = k + 1;
            else temp[i] = 0;

        }

        this.parent = new int[temp.length];
        System.arraycopy(temp, 0, this.parent, 0, temp.length);
        this.valid = false;

        return total;

    }

    public int[] getParent(){
        return this.parent;
    }

}
