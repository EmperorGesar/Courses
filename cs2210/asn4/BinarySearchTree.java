/**
 * The BST formed with nodes to store the data of pixels of the figure
 * <p>
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class BinarySearchTree implements BinarySearchTreeADT{

    private BinaryNode root;

    /**
     * Constructor
     */
    public BinarySearchTree(){
        this.root = null;
    }

    /**
     * Get the root of the BST
     * @return The root node
     */
    public BinaryNode getRoot() {
        return this.root;
    }

    /**
     * Get the pixel with the target key coordinates from the BST
     * @param r The node performed in recursion to access the sub-tree, starting from the root
     * @param key The target key coordinates
     * @return The data of the target pixel when found, otherwise null
     */
    public Pixel get(BinaryNode r, Location key) {

        if (r != null && r.getData() != null){

            if (key.compareTo(r.getData().getLocation()) == -1){
                return get(r.getLeft(), key);
            } else if (key.compareTo(r.getData().getLocation()) == 1){
                return get(r.getRight(), key);
            } else {
                return r.getData();
            }

        } else {
            return null;
        }

    }

    /**
     * Insert a pixel data into the BST
     * @param r The node performed in recursion to access the sub-tree, starting from the root
     * @param data The target pixel data
     * @throws DuplicatedKeyException When the target pixel already exists in the BST
     */
    public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {

        if (get(r, data.getLocation()) == null){

            if (r == null){
                this.root = new BinaryNode(data, null, null, null);
            } else if (r.getData() != null){

                if (data.getLocation().compareTo(r.getData().getLocation()) == -1){

                    if (r.getLeft() == null){
                        r.setLeft(new BinaryNode(data, null, null, r));
                    } else {
                        put(r.getLeft(), data);
                    }

                } else if (data.getLocation().compareTo(r.getData().getLocation()) == 1){

                    if (r.getRight() == null){
                        r.setRight(new BinaryNode(data, null, null, r));
                    } else {
                        put(r.getRight(), data);
                    }

                }

            }

        } else {
            throw new DuplicatedKeyException("Target data already exists.");
        }

    }

    /**
     * Delete a pixel data from the BST
     * @param r The node performed in recursion to access the sub-tree, starting from the root
     * @param key The target key coordinates
     * @throws InexistentKeyException When the target does not found in the BST
     */
    public void remove(BinaryNode r, Location key) throws InexistentKeyException {

        if (get(r, key) != null){

            if (key.compareTo(r.getData().getLocation()) == -1){
                remove(r.getLeft(), key);
            } else if (key.compareTo(r.getData().getLocation()) == 1){
                remove(r.getRight(), key);
            } else {

                if (r.isLeaf()){

                    if (r == this.root){
                        this.root = null;
                    } else {

                        if (r.getParent().getLeft() == r){

                            r.getParent().setLeft(null);
                            r.setParent(null);

                        } else if (r.getParent().getRight() == r){

                            r.getParent().setRight(null);
                            r.setParent(null);

                        }

                    }

                } else if (r.getLeft() == null){

                    r.getRight().setParent(r.getParent());

                    if (r == this.root){

                        this.root = r.getRight();
                        r.setRight(null);

                    } else {

                        if (r.getParent().getLeft() == r){

                            r.getParent().setLeft(r.getRight());
                            r.setRight(null);
                            r.setParent(null);

                        } else if (r.getParent().getRight() == r){

                            r.getParent().setRight(r.getRight());
                            r.setRight(null);
                            r.setParent(null);

                        }

                    }

                } else if (r.getRight() == null){

                    r.getLeft().setParent(r.getParent());

                    if (r == this.root){

                        this.root = r.getLeft();
                        r.setLeft(null);

                    } else {

                        if (r.getParent().getLeft() == r){

                            r.getParent().setLeft(r.getLeft());
                            r.setLeft(null);
                            r.setParent(null);

                        } else if (r.getParent().getRight() == r){

                            r.getParent().setRight(r.getLeft());
                            r.setLeft(null);
                            r.setParent(null);

                        }

                    }

                } else {

                    boolean isLeft = false;
                    if (r.getParent() != null){
                        if (r.getParent().getLeft() != null){
                            if (r.getParent().getLeft() == r) isLeft = true;
                        }
                    }

                    BinaryNode temp = r;
                    r = smallestNode(temp.getRight());

                    if (r.getRight() != null){
                        r.getRight().setParent(r.getParent());
                        r.getParent().setLeft(r.getRight());
                    }

                    r.setRight(temp.getRight());
                    r.setLeft(temp.getLeft());
                    r.setParent(temp.getParent());

                    if (r.getParent() != null){
                        if (isLeft) r.getParent().setLeft(r);
                        else r.getParent().setRight(r);
                    } else {
                        this.root = r;
                    }

                }

            }

        } else {
            throw new InexistentKeyException("Target data does not found.");
        }

    }

    /**
     * Find the pixel with the smallest value larger than the target key coordinates
     * @param r The node performed in recursion to access the sub-tree, starting from the root
     * @param key The target key coordinates
     * @return The next pixel data
     */
    public Pixel successor(BinaryNode r, Location key) {

        if (r == null) {
            return null;
        } else if (key.compareTo(r.getData().getLocation()) == 1){
            return successor(r.getRight(), key);
        } else if (key.compareTo(r.getData().getLocation()) == 0){

            if (r.getRight() != null) {

                try {
                    return smallest(r.getRight());
                } catch (EmptyTreeException e) {
                    e.printStackTrace();
                }

            } else {
                return null;
            }

        }

        Pixel temp = successor(r.getLeft(), key);

        if (temp != null) return temp;
        else return r.getData();

    }

    /**
     * Find the pixel with the largest value smaller than the target key coordinates
     * @param r The node performed in recursion to access the sub-tree, starting from the root
     * @param key The target key coordinates
     * @return The previous pixel data
     */
    public Pixel predecessor(BinaryNode r, Location key) {

        if (r == null){
            return null;
        } else if (key.compareTo(r.getData().getLocation()) == -1){
            return predecessor(r.getLeft(), key);
        } else if (key.compareTo(r.getData().getLocation()) == 0){

            if (r.getLeft() != null) {

                try {
                    return largest(r.getLeft());
                } catch (EmptyTreeException e) {
                    e.printStackTrace();
                }

            } else {
                return null;
            }

        }

        Pixel temp = predecessor(r.getRight(), key);

        if (temp != null) return temp;
        else return r.getData();

    }

    /**
     * Find the pixel with the smallest coordinates
     * @param r The node performed in recursion to access the sub-tree, starting from the root
     * @return The pixel data with the smallest key
     * @throws EmptyTreeException When the tree is empty
     */
    public Pixel smallest(BinaryNode r) throws EmptyTreeException {

        if (r != null){

            if (r.getLeft() == null) return r.getData();
            else return smallest(r.getLeft());

        } else {
            throw new EmptyTreeException("The tree is empty.");
        }

    }

    /**
     * Find the pixel with the largest coordinates
     * @param r The node performed in recursion to access the sub-tree, starting from the root
     * @return The pixel data with the largest key
     * @throws EmptyTreeException When the tree is empty
     */
    public Pixel largest(BinaryNode r) throws EmptyTreeException {

        if (r != null){

            if (r.getRight() == null) return r.getData();
            else return largest(r.getRight());

        } else {
            throw new EmptyTreeException("The tree is empty.");
        }

    }

    /**
     * Private method used by the remove() method to find the node with the smallest coordinates
     * @param r The node performed in recursion to access the sub-tree, starting from the root
     * @return The node with the smallest key
     */
    private BinaryNode smallestNode(BinaryNode r){

        if (r.getLeft() == null) return r;
        else return smallestNode(r.getLeft());

    }

}
