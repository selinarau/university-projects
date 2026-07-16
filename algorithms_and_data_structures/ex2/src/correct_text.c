/*
 * This file includes functions to compute edit distance of two strings.
 * To compute the distance is needed a function to remove the first character of a string
 * and a function to count the min value of three integers.
 * 
 * This file is part of the exercise 2 of ASD course in spring 2024.
 * Creator: Selina Rautakoski (selina.rautakoski@edu.unito.it)
 * 
 */
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "correct_text.h"

// Function to read the words from the file
int read_file(FILE *file, char *words[], int l) {
    int count = 0;
    char line[l];
    char delim[] = " .,?!:;\n"; // characters to end the word (delimiter)

    while (fgets(line, sizeof(line), file) != NULL){ // Gets the content of the file to a string
        char *word = strtok(line, delim); // pointer to the word from the file
        while (word != NULL) {
            words[count] = strdup(word); // save to list of words a new pointer to the word
            
            if (words[count] == NULL) { // Check if memory allocation succeed
                perror("Memory allocation error");
                exit(EXIT_FAILURE);
            }
            word = strtok(NULL, delim); // initializre the pointer 
            count++;
        }
    }
    return count;
}

// Function to lowercase a string.
char* to_lower(char* s) {
    size_t l = strlen(s);
    size_t i = 0;
    for(i = 0; i < l; i++)
        s[i] = tolower(s[i]);
    s[l] = '\0';
    return s;
}

// Returns the smaller of two integers
int min(int a, int b) {
    if(a<b) return a;
    else return b;
}

// Function to compute the edit distance (how many edits has to be done to get s1 from s2), 
// when only possible edits are cancellation and insertion.
int edit_distance_dyn(const char *s1, const char *s2) {
    int len1 = strlen(s1);
    int len2 = strlen(s2);

    // create a 2D array (table) to store results of substrings
    int **dp = (int **)malloc((len1 + 1) * sizeof(int *));
    // the table has the size len1 x len2
    for (int i = 0; i <= len1; i++) {
        dp[i] = (int *)malloc((len2 + 1) * sizeof(int));
    }

    // The base cases
    for (int i = 0; i <= len1; i++) {
        dp[i][0] = i; // insert characters into an empty s2 to match s1
    }
    for (int j = 0; j <= len2; j++) {
        dp[0][j] = j; // delete all characters from s2 to match an empty s1
    }

    // go through the table and count the edit distance for each substring
    for (int i = 1; i <= len1; i++) {
        for (int j = 1; j <= len2; j++) {
            if (s1[i - 1] == s2[j - 1]) { 
                dp[i][j] = dp[i - 1][j - 1]; // the letter are the same so the distance doesn't increase
            } else {
                dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1]); // letters are not the same so the distance is 1 + the minimum of insertion and deletion
            }
        }
    }
    int result = dp[len1][len2]; // whole strings handled in the last cell

    // free allocated memory
    for (int i = 0; i <= len1; i++) {
        free(dp[i]);
    }
    free(dp);

    return result;
}

// Function to correct the given text according to the given dictionary and minimum distance.
void correct_text(FILE *text, FILE *dictionary, int min_distance){
    char *text_words[MAX_WORDS]; // list of words of text
    char *dict_words[MAX_DICT]; // list of words of dictionary
    int text_n = read_file(text, text_words, MAX_WORDS*MAX_LENGTH); // number of words in text
    int dict_n = read_file(dictionary, dict_words, MAX_LENGTH); // number of words in dictionary
    int i = 0;
    int j = 0;

    // go through all the words of the texts and find the words whose edit distance from the word is less than equal to the minimum edit distance.
    for (i = 0; i < text_n; i++) {
        int found = 0;
        char *word = to_lower(text_words[i]); // ignore upper cases
        // check first if the word is found from the dictionary (if it is already there it is probably written right).
        for (j = 0; j < dict_n; j++) {
            if (strcmp(word,dict_words[j])==0){
                found = 1;
                break;
            }
        }
        // if the word is not in the dictionary, go through all the words of the texts and 
        // find the words whose edit distance from the word is less than equal to the minimum edit distance.
        if(found == 0)  {
            printf("Word '%s' might mean words: \n", text_words[i]);
            for(j = 0; j < dict_n; j++) {
                int l1 = strlen(word); // length of s1
                int l2 = strlen(dict_words[j]); // length of s2
                int diff = l1-l2; // difference between the lengths of the words
                // if the difference between the length of words is greater than minimum distance, the edit distance is greater too
                if((diff<=min_distance&& diff>=0) || (-diff<=min_distance && diff <=0)){
                    if(edit_distance_dyn(word, dict_words[j]) <= min_distance){ // compute the edit distance and check if it is smaller than the given minimum
                        printf("%s ", dict_words[j]);
                    }
                }
            }
            printf("\n");
        }
    }
    // Free allocated memory
    for (i = 0; i < text_n; i++) {
        free(text_words[i]);
    }
    for (i = 0; i < dict_n; i++) {
        free(dict_words[i]);
    }
}