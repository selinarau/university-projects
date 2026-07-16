/*
 * This is a header file of excersize 2 of ASD course in spring 2024.
 *
 * Creator: Selina Rautakoski (selina.rautakoski@edu.unito.it)
 * 
 */

#define MAX_WORDS 1000         // Maximum number of words to read
#define MAX_LENGTH 50          // Maximum length of each word
#define MAX_DICT 1000000       // Maximum number of words in dictionary
#define MAX_SIMILAR_WORDS 1000 // Maximum number of similar words

int read_file(FILE *file, char *words[], int l);
char* to_lower(char* s);
int min(int a, int b);
int edit_distance_dyn(const char *s1, const char *s2);
void correct_text(FILE *text, FILE *dictionary, int min_distance);