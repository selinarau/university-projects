/*
 * This is a main function to correct text.
 *
 * This file is part of excersize 2 of ASD course in spring 2024.
 * Creator: Selina Rautakoski (selina.rautakoski@edu.unito.it)
 * 
 */

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "correct_text.h"

int main(int argc, char *argv[]) {
    FILE *text, *dictionary;
    int min_distance;
    if(argc<3)
        printf("Some information is missing from the command line");

    text = fopen(argv[1], "r");
    if(!text) {
        printf("Can't open correctme.txt\n");
        return 1;
    }
    dictionary = fopen(argv[2], "r");
    if (!dictionary){
        printf("Can't open dictionary\n");
        return 1; 
    }

    min_distance = atoi(argv[3]);
    correct_text(text, dictionary, min_distance);

    fclose(text);
    fclose(dictionary);
    return 0;
}