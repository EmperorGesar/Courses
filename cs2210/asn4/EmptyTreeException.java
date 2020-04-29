/**
 * The exception performed for the smallest() and largest() method of the BST
 * when the tree is empty
 * <p>
 *
 * @author Wenhan Sun wsun228 251020850
 *
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
