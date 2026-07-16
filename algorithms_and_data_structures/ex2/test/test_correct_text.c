/*
 * This is a unit test file to test the functions of correct text
 * 
 * This is a part of the exercise 2 of ASD course in spring 2024.
 * Creator: Selina Rautakoski (selina.rautakoski@edu.unito.it)
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "correct_text.h"

// Test reading_file function
void test_read_file() {
    FILE *file = fopen("edit_distance_test/test.txt", "w"); // open file to write
    char *words[10] = {0}; // array for words that are going to be read from file
    char *expected[] = {"dog", "cat", "horse"}; // expected words for comparing
    int count; // integer for return value from read_file function
    int i; // index for for-loop
    fprintf(file, "dog, cat:\nhorse!\n"); // write to file something that should give the expected words
    fclose(file);
    
    file = fopen("edit_distance_test/test.txt", "r"); //open for reading
    count = read_file(file, words, 10); // read_file
    fclose(file);
    assert(count == 3);
    for(i = 0; i < count; i++) { // compare the real and expected words
        assert(strcmp(expected[i],words[i])==0);
    }

    for (int i = 0; i < count; i++) {
        free(words[i]); // free allocated memory
    }
}



// Test to_lower function
void test_to_lower() {
    char *s = "DoG";
    char *r = to_lower(s);
    assert(strcmp(r,"dog")==0);
    free(r);
}

// Test min function
void test_min() {
    int a = 6;
    int b = 1;
    assert(min(a,b)== b);
}

// Test edit_distance_dyn function
void test_edit_distance_dyn() {
    char *s1 = "casa";
    char *s2 = "cassa";
    assert(edit_distance_dyn(s1,s2)== 1);
}

int main () {
    test_read_file();
    test_to_lower();
    test_min();
    test_edit_distance_dyn();
    return 0;
}