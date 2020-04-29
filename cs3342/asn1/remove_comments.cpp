/* 
 * Removes comments from a given C program
 */

#include <iostream>
#include <fstream>

using namespace std;

int main(int argc, char **argv) {

    if (argv[1] == NULL) exit(EXIT_FAILURE);

    FILE *input = fopen(argv[1], "r");
    ofstream output("input_C_source_rem.cpp");

    if (input == NULL) exit(EXIT_FAILURE);

    char current;
    bool isLineComment = false, isStarComment = false, isSQuote = false, isDQuote = false;

    while (!feof(input)){

        current = fgetc(input);

        if (current == '\'' && !isDQuote && !isLineComment && !isStarComment) isSQuote = !isSQuote;
        if (current == '\"' && !isSQuote && !isLineComment && !isStarComment) isDQuote = !isDQuote;

        if (current == '/' && !isLineComment && !isStarComment && !isSQuote && !isDQuote) {

            current = fgetc(input);

            if (current == '/') {
                isLineComment = true;
            } else if (current == '*') {
                isStarComment = true;
            } else {
                ungetc(current, input);
            }

        }

        if (current == '\n' && isLineComment) isLineComment = false;

        if (current == '*' && isStarComment) {

            current = fgetc(input);

            if (current == '/'){
                isStarComment = false;
                current = fgetc(input);
            }

        }

        if (!isLineComment && !isStarComment && !feof(input)) output << current;

    }

    fclose(input);
    output.close();

}
