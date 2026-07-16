/*
 * This is a main function to sort records.
 * 
 * This file is part of the exercise 1 of ASD course in spring 2024.
 * Creator: Selina Rautakoski (selina.rautakoski@edu.unito.it)
 */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "sort_records.h"

int main(int argc, char *argv[]) {
    FILE *input, *output;
    int field;
    int algo;
    clock_t start;
    clock_t end;
    double responce_time;

    if(argc<5)
        printf("Some information is missing from the command line");
    // Open a file in read mode
    input = fopen(argv[1], "r");
    // Open file tin writinf mode
    output = fopen(argv[2], "w");
    if (!input) {
        printf("Can't open the input file\n");
        return 1;
    } else if (!output) {
        printf("Can't open the output file\n");
        return 1;
    } else {
        field = atoi(argv[3]);
        if(field < 1 || field > 3){
            printf("Invalid field\n");
            return 1;
        }
        algo = atoi(argv[4]);
        if(algo < 1 || algo > 2){
            printf("Invalud algo\n");
            return 1;
        }

        start = clock(); // start time 
        sort_records(input, output, field, algo);
        end = clock(); //end time
        responce_time = ((double)(end - start)) / CLOCKS_PER_SEC;
        printf("Responce time with field %d and algorithm %d took %f seconds.\n", field, algo, responce_time);
    }
    fclose(input);
    fclose(output);
    return 0;
}