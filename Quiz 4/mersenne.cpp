#include <iostream>
#include <cmath> 	// could also do this with bit shifts
#include <time.h>
#include <stdio.h>

// checks whether the given number is prime
bool isPrime(int n) {
	// look for a factor of n and if found, return false
	for (int factor = 2; factor < n; factor++) {
		if (n % factor == 0) {
			return false;
		}
	}
	return true;
}


// finds and returns the nth prime
int nthPrime(int n) {
	int currentValue = 1;
	int numFound = 0;
	
	while (numFound < n) {
		currentValue++;
			
		// if currentValue is prime, we have found another prime,
		// so can increment numFound
		if (isPrime(currentValue)) {
				numFound++;
		}	
	}
	return currentValue;
}


// finds and returns the nth mersenne prime
int nthMersenne(int n) {
	int currentPrimeNum = 0;
	int currentPrime;
	int numFound = 0;
	int currentValue;
	
	while (numFound < n) {
		
		currentPrimeNum++;
		currentPrime = nthPrime(currentPrimeNum);
		currentValue = pow(2, currentPrime) - 1;

		if (isPrime(currentValue)) {
			numFound++;
		}
	}
	return currentValue;
}


// find and print the 8th Mersenne prime number
int main() {
	clock_t before;
	double elapsed;
	before = clock();

	int n = 8;
	int result = nthMersenne(n);
	std::cout << "The " << n << "th Mersenne prime is: " << result;

	elapsed = clock() - before;
	printf("Function used %.3f seconds\n", elapsed/CLOCKS_PER_SEC);

}