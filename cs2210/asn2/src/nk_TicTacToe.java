/**
 * The game board that is stored in a 2-dimensional character array
 * which will be executed dynamically during the process of the game
 */

public class nk_TicTacToe {

    private char[][] gameBoard;
    private int size, minAdj, level;

    /**
     * Constructor
     * @param board_size The side length of the game board
     * @param inline The minimum adjacent same symbol to win the game
     * @param max_levels The depth of the game tree
     */
    public nk_TicTacToe(int board_size, int inline, int max_levels){

        this.size = board_size;
        if (board_size >= inline) this.minAdj = inline;
        else this.minAdj = board_size;
        this.level = max_levels;
        this.gameBoard = new char[board_size][board_size];

        // Initialize the game board
        for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                this.gameBoard[i][j] = ' ';
            }
        }

    }

    /**
     * Create a new hash dictionary with a chose size 7993
     * @return The empty hash dictionary created
     */
    public Dictionary createDictionary(){
        return new Dictionary(7993);
    }

    /**
     * Get the feedback score of the game board to check whether it is in the dictionary
     * @param configurations The hash dictionary that stores the records of the game board
     * @return -1 if the configuration of the game board does not exist, otherwise return its feedback score
     */
    public int repeatedConfig(Dictionary configurations){
        return configurations.get(configBuild());
    }

    /**
     * Store the game board configuration string and its score into the hash dictionary
     * @param configurations The hash dictionary that stores the records of the game board
     * @param score The feedback score assigned to the game board
     */
    public void insertConfig(Dictionary configurations, int score){

        Record pair = new Record(configBuild(), score);

        try {
            int collision = configurations.insert(pair);
        } catch (DictionaryException e) {
            e.printStackTrace();
        }

    }

    /**
     * Store the target symbol representing the player into the target position of the game board
     * @param row The row of the game board
     * @param col The column of the game board
     * @param symbol The character representation of the player:
     *               'O' for the computer and 'X' for the human
     */
    public void storePlay(int row, int col, char symbol){

        if (row != -1 && col != -1) {
            gameBoard[row][col] = symbol;
        }

    }

    /**
     * Determine whether the target position is not occupied by players
     * @param row The row of the game board
     * @param col The column of the game board
     * @return True if the position is empty, otherwise false
     */
    public boolean squareIsEmpty (int row, int col){
        return gameBoard[row][col] == ' ';
    }

    /**
     * Determine whether the target player wins the game
     * @param symbol The character representation of the player:
     *               'O' for the computer and 'X' for the human
     * @return True if the target player wins, otherwise false
     */
    public boolean wins (char symbol){

        // Determine the validity in rows and columns
        int countRow;
        int countCol;

        for (int i = 0; i < size; i++){

            countRow = 0;
            countCol = 0;

            for (int j = 0; j < size; j++){

                if (gameBoard[i][j] == symbol){
                    countRow = countRow + 1;
                } else {
                    if (countRow < minAdj) countRow = 0;
                }

                if (gameBoard[j][i] == symbol){
                    countCol = countCol + 1;
                } else {
                    if (countCol < minAdj) countCol = 0;
                }

            }

            if (countRow >= minAdj || countCol >= minAdj) return true;

        }

        // Determine the validity in both diagonals
        int countDiagL;
        int countDiagR;

        for (int r = 0; r <= (size - minAdj); r++){
            for (int c = 0; c <= (size - minAdj); c++){

                countDiagL = 0;
                countDiagR = 0;

                for (int i = 0; i < minAdj; i++){

                    if (gameBoard[r + i][c + i] == symbol){
                        countDiagL = countDiagL + 1;
                    } else {
                        if (countDiagL < minAdj) countDiagL = 0;
                    }

                    if (gameBoard[r + i][size - 1 - c - i] == symbol){
                        countDiagR = countDiagR + 1;
                    } else {
                        if (countDiagR < minAdj) countDiagR = 0;
                    }

                }

                if (countDiagL >= minAdj || countDiagR >= minAdj) return true;

            }
        }

        return false;

    }

    /**
     * Determine whether the game is a draw
     * @return True if no player wins and no empty positions remaining, otherwise false
     */
    public boolean isDraw(){

        boolean empty = false;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (squareIsEmpty(i, j)) empty = true;
            }
        }

        return !wins('O') && !wins('X') && !empty;

    }

    /**
     * Check the status of the game board and determine its feedback score
     * @return The feedback score in different scenarios
     */
    public int evalBoard(){

        if (wins('O')) return 3;
        else if (wins('X')) return 0;
        else if (isDraw()) return 2;
        else return 1;

    }

    /**
     * Private method to convert the game board into the configuration string
     * @return The configuration string of the game board
     */
    private String configBuild(){

        String config = "";

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                config = config + gameBoard[i][j];
            }
        }

        return config;

    }

}
