# Compiler
CC = gcc

# Compiler flags
CFLAGS = -Wall -Wextra -Werror -Iinclude

# Directories
INCDIR = include
SRCDIR = src
TESTDIR = test
BINDIR = bin
RECORDS_FILE = records.csv
OUTPUT_FILE = output.csv

# Output executables
MAIN_EX1 = $(BINDIR)/main_ex1
TEST_EX1 = $(BINDIR)/test_ex1

# Source files
MAIN_SRCS = $(SRCDIR)/sort_records.c $(SRCDIR)/main.c
TEST_SRCS = $(SRCDIR)/sort_records.c $(TESTDIR)/test_sort_records.c

# Object files
MAIN_OBJS = $(MAIN_SRCS:%.c=%.o)
TEST_OBJS = $(TEST_SRCS:%.c=%.o)

# Header files
HEADERS = $(INCDIR)/sort_records.h

# Default target
all: $(BINDIR) $(MAIN_EX1) $(TEST_EX1) run_main

# Create bin directory if it does not exist
$(BINDIR):
	mkdir -p $(BINDIR)

# Compile main_ex1
$(MAIN_EX1): $(MAIN_OBJS)
	$(CC) $(CFLAGS) -o $@ $^

# Compile test_ex1
$(TEST_EX1): $(TEST_OBJS)
	$(CC) $(CFLAGS) -o $@ $^

# Compile source files into object files
$(SRCDIR)/%.o: $(SRCDIR)/%.c $(HEADERS)
	$(CC) $(CFLAGS) -c $< -o $@

$(TESTDIR)/%.o: $(TESTDIR)/%.c $(HEADERS)
	$(CC) $(CFLAGS) -c $< -o $@

# Run main with parameters
run_main: $(MAIN_EX1)
	$(MAIN_EX1) $(RECORDS_FILE) $(OUTPUT_FILE) 3 2

# Clean up build files
clean:
	rm -f $(SRCDIR)/*.o $(TESTDIR)/*.o $(MAIN_EX1) $(TEST_EX1)

# Phony targets
.PHONY: all clean
