# Compiler
CC = gcc

# Compiler flags
CFLAGS = -Wall -Wextra -Werror -Iinclude

# Directories
INCDIR = include
SRCDIR = src
TESTDIR = test
DATA = edit_distance_test
TEXT_FILE = $(DATA)/correctme.txt
DICT_FILE = $(DATA)/dictionary.txt
BINDIR = bin

# Output executables
MAIN_EX2 = $(BINDIR)/main_ex2
TEST_EX2 = $(BINDIR)/test_ex2

# Source files
MAIN_SRCS = $(SRCDIR)/correct_text.c $(SRCDIR)/main.c
TEST_SRCS = $(SRCDIR)/correct_text.c $(TESTDIR)/test_correct_text.c

# Object files
MAIN_OBJS = $(MAIN_SRCS:%.c=%.o)
TEST_OBJS = $(TEST_SRCS:%.c=%.o)

# Header files
HEADERS = $(INCDIR)/correct_text.h

# Default target
all: $(BINDIR) $(MAIN_EX2) $(TEST_EX2) run_main

# Create bin directory if it does not exist
$(BINDIR):
	mkdir -p $(BINDIR)

# Compile main_ex2
$(MAIN_EX2): $(MAIN_OBJS)
	$(CC) $(CFLAGS) -o $@ $^

# Compile test_ex2
$(TEST_EX2): $(TEST_OBJS)
	$(CC) $(CFLAGS) -o $@ $^

# Compile source files into object files
$(SRCDIR)/%.o: $(SRCDIR)/%.c $(HEADERS)
	$(CC) $(CFLAGS) -c $< -o $@

$(TESTDIR)/%.o: $(TESTDIR)/%.c $(HEADERS)
	$(CC) $(CFLAGS) -c $< -o $@

# Run main_ex2 with parameters
run_main: $(MAIN_EX2)
	$(MAIN_EX2) $(TEXT_FILE) $(DICT_FILE) 3

# Clean up build files
clean:
	rm -f $(SRCDIR)/*.o $(TESTDIR)/*.o $(MAIN_EX1) $(TEST_EX1)

# Phony targets
.PHONY: all clean
