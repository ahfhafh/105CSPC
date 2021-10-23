#include <stdio.h>
#include <time.h>
#include <stdlib.h>

// original code
void original(int n) {
	static int matrix[100][10000][10] = {};
	
	for (int i=0; i<n; i++) {
		// run loop initializing matrix to 0s
		
		for (int b = 0; b < 10000; b++) {
			for (int a = 0; a < 100; a++) {
				for (int c = 0; c < 10; c++) { 
					matrix[a][b][c] = 0;
				}
			}
		}
		
	}	
}

// modified to minimize the number of loop operations
void loopOpt(int n) {
	static int matrix[100][10000][10] = {};
	
	for (int i=0; i<n; i++) {
		// run loop initializing matrix to 0s
		
		for (int c = 0; c < 10; c++) {
			for (int a = 0; a < 100; a++) {
				for (int b = 0; b < 10000; b++) { 
					matrix[a][b][c] = 0;
				}
			}
		}
		
	}
}

// modified to give sequential memory access
void matrixOpt(int n) {
	static int matrix[100][10000][10] = {};
	
	for (int i=0; i<n; i++) {
		// run loop initializing matrix to 0s
		
		for (int a = 0; a < 100; a++) {
			for (int b = 0; b < 10000; b++) {
				for (int c = 0; c < 10; c++) { 
					matrix[a][b][c] = 0;
				}
			}
		}
		
	}
}


int main() {
	int numTimes = 100; //number of times to run each function per loop
	int numLoops = 10;	//number of outer loops
	
	srand(time(NULL)); //initialize random seed
	double totalOriginal = 0;
	double totalLoopOpt = 0;
	double totalMatrixOpt = 0;
	clock_t before;

	for (int i = 0; i < numLoops; i++) {
		before = clock();
		original(numTimes);
		totalOriginal += clock() - before;
		
		before = clock();
		loopOpt(numTimes);
		totalLoopOpt += clock() - before;	
		
		before = clock();
		matrixOpt(numTimes);
		totalMatrixOpt += clock() - before;	
	}	
	
	printf("Total time for original: %.3f seconds\n", totalOriginal/CLOCKS_PER_SEC);
	printf("Total time for loop order optimization: %.3f seconds\n", totalLoopOpt/CLOCKS_PER_SEC);
	printf("Total time for matrix order optimization: %.3f seconds\n", totalMatrixOpt/CLOCKS_PER_SEC);
	
}