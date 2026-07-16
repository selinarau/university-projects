/*
 * This is a unit test file to test the functions of sort records
 * 
 * This is a part of the exercise 1 of ASD course in spring 2024.
 * Creator: Selina Rautakoski (selina.rautakoski@edu.unito.it)
 */

#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include "sort_records.h"


// Test compare_records functions
void test_compare_records_field1() {
    Record a = {1, "dog", 39892, 10231.538};
    Record b = {2, "cat", 2, 5.7};

    // compare by field 1 (string)
    int result = (compare_records(&a, &b, 1));
    assert(result > 0);
}
void test_compare_records_field2() {
    Record a = {1, "dog", 39892, 10231.538};
    Record b = {2, "cat", 2, 5.7};

    // compare by field 2 (int)
    int result = (compare_records(&a, &b, 2));
    assert(result > 0);
}
void test_compare_records_field3() {
    Record a = {1, "dog", 39892, 10231.538};
    Record b = {2, "cat", 2, 5.7};

    // compare by field 3 (string)
    int result = (compare_records(&a, &b, 3));
    assert(result > 0);
}

// Test merge_sort function
void test_merge_sort() {
    Record records[] = {
        {1, "dog", 39892, 10231.538},
        {2, "cat", 2, 5.7},
        {3, "horse", 15, 1.2}
    };
    size_t count = sizeof(records) / sizeof(records[0]);
    merge_sort(records, 0, count - 1, 1);

    assert(strcmp(records[0].field1, "cat") == 0 || strcmp(records[1].field1, "dog") == 0 || strcmp(records[2].field1, "horse") == 0);
}

// Test quick_sort function
void test_quick_sort() {
    Record records[] = {
        {1, "dog", 39892, 10231.538},
        {2, "cat", 2, 5.7},
        {3, "horse", 15, 1.2}
    };
    size_t count = sizeof(records) / sizeof(records[0]);
    quick_sort(records, 0, count - 1, 1);
    assert(strcmp(records[0].field1, "cat") == 0 || strcmp(records[1].field1, "dog") == 0 || strcmp(records[2].field1, "horse") == 0);
}

// Test read_records and write_records functions
void test_read_write_records() {
    const char *test_data = "1,dog,39892,10231.538\n2,cat,2,5.7\n3,horse,15,1.2\n";
    FILE *infile = fmemopen((void *)test_data, strlen(test_data), "r");
    Record records[3];
    size_t count = read_records(infile, records, 3);
    fclose(infile);

    FILE *outfile = tmpfile();
    write_records(outfile, records, count);
    rewind(outfile);

    char buffer[256];
    fread(buffer, sizeof(char), sizeof(buffer) - 1, outfile);
    buffer[255] = '\0'; // Null-terminate buffer
    fclose(outfile);

    assert(strcmp(buffer, test_data)!=0);
}

int main() {
    test_compare_records_field1();
    test_compare_records_field2();
    test_compare_records_field3();
    test_merge_sort();
    test_quick_sort();
    test_read_write_records();
    return 0;
}