/**
 * The exception performed for the smallest() and largest() method of the BST
 * when the tree is empty
 */

public class EmptyTreeException extends Exception{

    /**
     * Constructor
     * @param message Message extends the Exception class as default
     */
    public EmptyTreeException(String message){
        super(message);
    }

}
