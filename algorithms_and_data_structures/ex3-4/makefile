# Path settings
SRC_DIR = src
TEST_DIR = test
LIB_DIR = lib
BIN_DIR = bin
MAIN_CLASS = graph.Prim
CSV_FILE = example.csv

# Libraries
JUNIT_JAR = $(LIB_DIR)/junit-4.12.jar
HAMCREST_JAR = $(LIB_DIR)/hamcrest-core-1.3.jar
CLASSPATH = .:$(JUNIT_JAR):$(HAMCREST_JAR)

# Source files
GRAPH_SRC_FILES = $(SRC_DIR)/graph/AbstractGraph.java $(SRC_DIR)/graph/AbstractEdge.java $(SRC_DIR)/graph/Graph.java $(SRC_DIR)/graph/Prim.java
PRIORITYQUEUE_SRC_FILES = $(SRC_DIR)/priorityqueue/AbstractQueue.java $(SRC_DIR)/priorityqueue/PriorityQueue.java $(SRC_DIR)/priorityqueue/PriorityQueueException.java
SRC_FILES = $(GRAPH_SRC_FILES) $(PRIORITYQUEUE_SRC_FILES)

# Test files
GRAPH_TEST_FILES = $(TEST_DIR)/graph/GraphTests.java
PRIORITYQUEUE_TEST_FILES = $(TEST_DIR)/priorityqueue/PriorityQueueTests.java
TEST_FILES = $(GRAPH_TEST_FILES) $(PRIORITYQUEUE_TEST_FILES)

# Compiled class files
CLASS_FILES = $(SRC_FILES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class) $(TEST_FILES:$(TEST_DIR)/%.java=$(BIN_DIR)/%.class)

# Default target
all: compile run

# Compile source and test files
compile:
	mkdir -p $(BIN_DIR)
	javac -cp $(CLASSPATH) -d $(BIN_DIR) $(SRC_FILES) $(TEST_FILES)

# Run the main program
run:
	java -cp $(BIN_DIR):$(CLASSPATH) $(MAIN_CLASS) $(CSV_FILE)

# Clean build artifacts
clean:
	rm -rf $(BIN_DIR)

# Phony targets
.PHONY: all compile run clean
