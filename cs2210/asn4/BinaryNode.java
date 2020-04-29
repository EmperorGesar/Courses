/**
 * The class to operate pixels as nodes which are performed in the BST
 */

public class BinaryNode {

    private Pixel data;
    private BinaryNode left, right, parent;

    /**
     * Constructor
     * @param value The data of the pixel
     * @param left The left child of the node
     * @param right The right child of the node
     * @param parent The parent of the node
     */
    public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent){

        this.data = value;
        this.left = left;
        this.right = right;
        this.parent = parent;

    }

    /**
     * Constructor with no parameters
     */
    public BinaryNode(){

        this.data = null;
        this.left = null;
        this.right = null;
        this.parent = null;

    }

    /**
     * Get the parent of the node
     * @return The parent node
     */
    public BinaryNode getParent(){
        return this.parent;
    }

    /**
     * Set the parent of the node
     * @param parent The target node to set as the parent
     */
    public void setParent(BinaryNode parent){
        this.parent = parent;
    }

    /**
     * Set the left child of the node
     * @param p The target node to set as the left child
     */
    public void setLeft(BinaryNode p){
        this.left = p;
    }

    /**
     * Set the right child of the node
     * @param p The target node to set as the right child
     */
    public void setRight(BinaryNode p){
        this.right = p;
    }

    /**
     * Set the data of the node
     * @param value The target pixel to set in the node
     */
    public void setData(Pixel value){
        this.data = value;
    }

    /**
     * Determine whether the node is a leaf node
     * @return When both of the children are null return true; otherwise false
     */
    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    /**
     * Get the data of the node
     * @return The pixel stored in the node
     */
    public Pixel getData(){
        return this.data;
    }

    /**
     * Get the left child of the node
     * @return The left child node
     */
    public BinaryNode getLeft(){
        return this.left;
    }

    /**
     * Get the right child of the node
     * @return The right child
     */
    public BinaryNode getRight(){
        return this.right;
    }

}
