/*
 * This is the header file to sort records that includes functions to sort records dynamically with QuickSort and MergeSort.
 * 
 * This file is part of the exercise 1 of ASD course in spring 2024.
 * Creator: Selina Rautakoski (selina.rautakoski@edu.unito.it)
 * 
 */

#include <stdio.h>

// Definition of a record structure
typedef struct {
    int id;
    char field1[1000];
    int field2;
    double field3;

} Record;

size_t read_records(FILE *infile, Record *records, size_t max_records);
void write_records(FILE *outfile, Record *records, size_t count);
int compare_records(const Record *a, const Record *b, size_t field);
void merge(Record *records, size_t left, size_t mid, size_t right, size_t field);
void merge_sort(Record *records, size_t left, size_t right, size_t field);
size_t partition(Record *records, size_t low, size_t high, size_t field);
void quick_sort(Record *records, int low, int high, size_t field);
void sort_records(FILE *infile, FILE *outfile, size_t field, size_t algo);
