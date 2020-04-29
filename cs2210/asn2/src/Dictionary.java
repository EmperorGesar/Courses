import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * The hash dictionary storing objects of the Record class
 * <p>
 *
 * @author Wenhan Sun wsun228 251020850
 *
 */

public class Dictionary implements DictionaryADT{

    private RecordNode[] table;
    private int count;

    /**
     * Constructor
     * @param size The target length to create the dictionary
     */
    public Dictionary(int size){

        this.table = new RecordNode[size];
        this.count = 0;

    }

    /**
     * Convert the configuration string of the target record to the key of the hash table and store its data
     * @param pair The target record representing the target game board
     * @return 1 if there exists collision, otherwise return 0
     * @throws DictionaryException The target record already exists in the hash table
     */
    public int insert(Record pair) throws DictionaryException {

        String config = pair.getConfig();
        int hashKey = hashCompute(config);

        if (table[hashKey] != null) {

            RecordNode last = table[hashKey];
            if (last.getRecord().getConfig().equals(pair.getConfig())){
                throw new DictionaryException();
            }

            while (last.getNext() != null){

                last = last.getNext();

                if (last.getRecord().getConfig().equals(pair.getConfig())){
                    throw new DictionaryException();
                }

            }

            RecordNode target = new RecordNode(pair);
            last.setNext(target);
            target.setPrevious(last);

            count = count + 1;
            return 1;

        } else {

            table[hashKey] = new RecordNode(pair);
            count = count + 1;
            return 0;

        }

    }

    /**
     * Delete the target record from the hash table
     * @param config The configuration string of the target record
     * @throws DictionaryException The target record does not exist in the hash table
     */
    public void remove(String config) throws DictionaryException {

        int hashKey = hashCompute(config);

        if (table[hashKey] != null){

            RecordNode target;

            // Determine collision
            for (target = table[hashKey]; target.getNext() != null; target = target.getNext()){
                if (target.getRecord().getConfig().equals(config)){

                    // Delete by re-linking the nodes
                    if (target.getNext() != null){
                        target.getNext().setPrevious(target.getPrevious());
                    }

                    if (target.getPrevious() != null){
                        target.getPrevious().setNext(target.getNext());
                    } else {
                        table[hashKey] = target.getNext();
                    }

                    target.setPrevious(null);
                    target.setNext(null);

                    count = count - 1;
                    break;

                }
            }

        } else {
            throw new DictionaryException();
        }

    }

    /**
     * Get the feedback score of the target record stored in the hash table
     * @param config The configuration string of the target record
     * @return -1 if the target record does not exist, otherwise return its feedback score
     */
    public int get(String config) {

        int hashKey = hashCompute(config);

        if (table[hashKey] != null){

            RecordNode target;

            for (target = table[hashKey]; target.getNext() != null; target = target.getNext()){
                if (target.getRecord().getConfig().equals(config)) return target.getRecord().getScore();
            }

            return target.getRecord().getScore();

        } else {
            return -1;
        }

    }

    /**
     * Get the number of total records stored in the hash table
     * @return The the total count
     */
    public int numElements() {
        return count;
    }

    /**
     * Private method to convert the configuration string of the record to the key of the hash table
     * @param config The configuration string of the target record
     * @return The key of the hash table
     */
    private int hashCompute(String config){

        char[] alpha = config.toCharArray();
        int hashKey = 0;

        // Hash function
        // Config: "c0c1...ck"
        // M is the chosen dictionary size 7993
        // Assume x is 37
        // Thus, h(config) = ((((c0x^0 mod M) + c1x^1) mod M) + ... + ckx^k) mod M
        for (int i = 0; i < config.length(); i++){
            hashKey = hashKey + ((int) alpha[i]) * ((int) Math.pow(37, i));
            hashKey = abs(hashKey % 7993);
        }

        return hashKey;

    }

    /**
     * Private class to operate configuration records as nodes that stored in the dictionary
     */
    private class RecordNode{

        private Record record;
        private RecordNode previous, next;

        private RecordNode(Record record){

            this.record = record;
            this.previous = null;
            this.next = null;

        }

        private Record getRecord(){
            return this.record;
        }

        private RecordNode getPrevious(){
            return this.previous;
        }

        private RecordNode getNext(){
            return this.next;
        }

        private void setPrevious(RecordNode previous){
            this.previous = previous;
        }

        private void setNext(RecordNode next){
            this.next = next;
        }

    }

}
