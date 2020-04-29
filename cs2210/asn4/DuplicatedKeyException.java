/**
 * The exception performed for the put() method of the BST
 * when the target data already exists in the tree
 * <p>
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class DuplicatedKeyException extends Exception{

    /**
     * Constructor
     * @param message Message extends the Exception class as default
     */
    public DuplicatedKeyException(String message){

        super(message);

    }

}
