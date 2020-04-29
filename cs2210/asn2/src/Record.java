/**
 * The data structure to store the configuration string
 * of the game board and its related feedback score
 */

public class Record {

    private String config;
    private int score;

    /**
     * Constructor
     * @param config The configuration string representing the game board
     * @param score The feedback score assigned to the game board
     */
    public Record(String config, int score){

        this.config = config;
        this.score = score;

    }

    /**
     * Get the configuration string representing the game board
     * @return The configuration string
     */
    public String getConfig(){
        return config;
    }

    /**
     * Get the feedback score assigned to the game board
     * @return The feedback score
     */
    public int getScore(){
        return score;
    }

}
