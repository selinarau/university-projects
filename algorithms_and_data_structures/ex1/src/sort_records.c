/*
 * This file includes functions to sort records dynamically with QuickSort and MergeSort.
 * 
 * This file is part of the exercise 1 of ASD course in spring 2024.
 * Creator: Selina Rautakoski (selina.rautakoski@edu.unito.it)
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sort_records.h"

// Function to read records from a file 
size_t read_records(FILE *infile, Record *records, size_t max_records) {
    size_t count = 0;
    while (count < max_records && 
            fscanf(infile, "%d,%999[^,],%d,%lf", &records[count].id, records[count].field1, &records[count].field2, &records[count].field3) == 4) {
        count++;
    }
    return count;
}

// Function to write the records to a file
void write_records(FILE *outfile, Record *records, size_t count) {
    for (size_t i = 0; i < count; i++) {
        fprintf(outfile, "%d,%s,%d,%lf\n", records[i].id, records[i].field1, records[i].field2, records[i].field3);
    }
}


// Function to compare two records based on the specified field
// Returns negative value, when a is smaller than b, positive value when a is greater than b (0 when equal).
int compare_records(const Record *a, const Record *b, size_t field) {
    switch (field) {
        case 1:
            return strcmp(a->field1, b->field1);
        case 2:
            return a->field2 - b->field2;
        case 3:
            if (a->field3 < b->field3) return -1;
            else if (a->field3 > b->field3) return 1;
            else return 0;
    }
    return -1;
}

// Function to merge record two lists so that the smaller value of a list is always added to the first record of merged list.
void merge(Record *records, size_t left, size_t mid, size_t right, size_t field) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    
    // Memory allocation for left and right record lists
    Record *L = malloc(n1 * sizeof(Record));
    Record *R = malloc(n2 * sizeof(Record));
    
    // Copy the records to left and right record lists
    for (int i = 0; i < n1; i++) 
        L[i] = records[left + i];
    for (int j = 0; j < n2; j++)
        R[j] = records[mid + 1 + j];
    
    // merge according to the comparing
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (compare_records(&L[i], &R[j], field) <= 0) {
            records[k] = L[i];
            i++;
        } else {
            records[k] = R[j];
            j++;
        }
        k++;
    }
    
    // copy the remaining records to the two lists
    while (i < n1) {
        records[k] = L[i];
        i++;
        k++;
    }
    while (j < n2) {
        records[k] = R[j];
        j++;
        k++;
    }

    free(L);
    free(R);
}

// Function to sort records using MergeSort
void merge_sort(Record *records, size_t left, size_t right, size_t field) {
    if (left < right) {
        size_t mid = left + (right - left) / 2;
        merge_sort(records, left, mid, field);
        merge_sort(records, mid + 1, right, field);
        merge(records, left, mid, right, field);
    }
}

// Function to make the partition of a list of records so that values smaller than pivot are placed to the left side of pivot index 
// and greater values to its right side.
// Returns the pivot index.
size_t partition(Record *records, size_t low, size_t high, size_t field) {
    Record pivot = records[high]; // Pivot record
    size_t i = low - 1; // Index of smaller element
    Record temp; // temporary record for the swapping elements

    for (size_t j = low; j < high; j++) {
        // Check if current element is smaller than the pivot
        if (compare_records(&records[j], &pivot, field) <= 0) {
            i++; // Index of smaller element increases by 1
            // Swap records[i] with records[j]
            temp = records[i];
            records[i] = records[j];
            records[j] = temp;
        }
    }
    // Swap pivot and the element at the right position
    temp = records[i+1];
    records[i+1] = records[high];
    records[high] = temp;

    return i + 1;
}


// Function to sort records using QuickSort
void quick_sort(Record *records, int low, int high, size_t field) {
    if (low < high) {
        int pivot = partition(records, low, high, field);
        quick_sort(records, low, pivot - 1, field);
        quick_sort(records, pivot + 1, high, field);
    }
}


// Function to sort records according to the given input, output, algorithm and field from main.
void sort_records(FILE *infile, FILE *outfile, size_t field, size_t algo) {

    Record records[1000];
    // read records from the infile and count their number
    size_t n = read_records(infile, records, 1000);
    // Select sorting algorithm and sort the records according to the given field 
    if(algo == 1)
        merge_sort(records, 0, n-1, field);
    else if (algo  == 2) 
        quick_sort(records,0, n-1, field);
    else {
        fprintf(stderr, "Invalid algorithm");
        exit(EXIT_FAILURE);
    }
    // write ordered records to the outfile
    write_records(outfile, records, n);
}