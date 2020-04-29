/**
 * The exception performed for the remove() method of the BST
 * when the target data does not found in the tree
 * <p>
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class InexistentKeyException extends Exception{

    /**
     * Constructor
     * @param message Message extends the Exception class as default
     */
    public InexistentKeyException(String message){

        super(message);

    }

}
