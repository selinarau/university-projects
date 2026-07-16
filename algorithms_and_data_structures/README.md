# Algorithms and Data structures Exercises (Spring 2024)

These exercises were part of **Algorithms and Data Structures** course during my Erasmus student exchange in Turin. The exercises were implemented individually, following the specifications and examples provided in the course material.

To practice different programming languages, exercises 1–2 are implemented in **C**, while exercises 3–4 are implemented in **Java**. Unit tests are included for all exercises to verify the correctness of the implemented operations.


---

## Exercise 1: Record Sorting Program

The **main** function serves as the entry point of the record sorting application. Its purpose is to handle command-line arguments, open input and output files, validate user options, execute the selected sorting algorithm (**merge sort** or **quick sort**), and measure the execution time.

The program expects four command-line arguments:

*./program input_file output_file field algorithm*

where:

* **input_file** is the file containing the records that will be sorted.
* **output_file** is the file where the sorted records will be saved.
* **field** specifies which record field should be used for sorting. The program accepts values from 1 to 3.
* **algorithm** specifies which sorting algorithm should be used: **1** for merge sort and **2** for quick sort.

The program records the start time using the C **clock()** function. The performance results are reported separately in a PDF file.

The sorting function **sort_record()** defined in **sort_records.h** performs the actual record sorting and writes the results to the output file.

---

## Exercise 2: Text Correction Program

The **main** function is the entry point of the text correction application. Its purpose is to read a text file and a dictionary file, process the text using a correction algorithm, and apply corrections based on a specified similarity threshold.

The program uses command-line arguments to receive the required input files and correction settings:

*./program text_file dictionary_file minimum_distance*

where:
* **text_file** is the input file containing the text that needs to be checked and corrected.
* **dictionary_file** contains valid words that are used as references during the correction process.
* **minimum_distance** defines the allowed distance between words when searching for possible corrections. It determines how similar a word must be to a dictionary entry to be considered a possible match.

The programs calls **correct_text()** function defined in **correct_text.h** which performs the actual text analysis and correction by comparing words from the input text against words in the dictionary.

---

## Exercises 3-4: 

### Priority Queue

This program implements a generic **Priority Queue** data structure in Java. The queue stores elements according to their priority, defined by a given **Comparator**, and supports an arbitrary number of elements.

The implementation provides the main priority queue operations:

* Checking whether the queue is empty.
* Adding elements while maintaining priority order.
* Accessing and removing the highest-priority element.
* Checking whether an element exists and removing specific elements.

The priority queue is implemented using a **heap-based structure**, which allows efficient insertion and removal operations with a time complexity of **O(logN)**, while accessing the top element is performed in **O(1)** time. Additional data structures may be used to optimize search and removal operations.

### Sparse Graph and Minimum Spanning Forest Implementation

This program implements a generic **Graph** data structure in Java, optimized for sparse graphs (graphs where the number of edges is significantly smaller than the maximum possible number of edges).

The graph implementation supports:

* Generic node types and edge labels.
* Directed and undirected graphs.
* Labelled and unlabelled edges.
* Adding, removing, and searching nodes and edges.
* Retrieving neighbours, nodes, and edges.

The graph is implemented using an **adjacency-based representation**, which provides efficient operations for sparse data. The implementation follows the required complexity constraints, including constant-time access for common operations such as checking nodes and edges and retrieving neighbour nodes.

The project also includes support for representing edges separately and is designed to allow graph algorithms, such as minimum spanning forest algorithms, to be implemented on top of the graph structure.
