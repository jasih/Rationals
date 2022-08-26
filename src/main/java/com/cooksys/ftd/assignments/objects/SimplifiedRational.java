package com.cooksys.ftd.assignments.objects;

import ognl.NumericExpression;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SimplifiedRational implements IRational {
    /**
     * Determines the greatest common denominator for the given values
     *
     * @param a the first value to consider
     * @param b the second value to consider
     * @return the greatest common denominator, or shared factor, of `a` and `b`
     * @throws IllegalArgumentException if a <= 0 or b < 0
     */
	
	// HINT: Look at the throws exception argument - 
	// It is possible to find the gcd of negative numbers, but this method should
	// throw the exception error IF either a or b is NEGATIVE
	// So, in the simplify() method, make use of Math.abs()
	// which calculates the absolute value of a number
	// When calculating the absolute value of a number, then it should not
	// throw an exception if the number is negative
	
	// Look into the Euclidean Algorithm to make this method
	
	
	private int num;
	private int denom;
	
	
    public static int gcd(int a, int b) throws IllegalArgumentException {
        
    	if (a <= 0 || b < 0) {
    		throw new IllegalArgumentException();
    	} else if (b == 0) {
    		return a;
    	} else {
    		return gcd(b, a % b);
    	}
    	
    }

    /**
     * Simplifies the numerator and denominator of a rational value.
     * <p>
     * For example:
     * `simplify(10, 100) = [1, 10]`
     * or:
     * `simplify(0, 10) = [0, 1]`
     *
     * @param numerator   the numerator of the rational value to simplify
     * @param denominator the denominator of the rational value to simplify
     * @return a two element array representation of the simplified numerator and denominator
     * @throws IllegalArgumentException if the given denominator is 0
     */
    
    // Hint: use gcd
    
    public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {			
 // Hint: gcd(Math.abs(numerator), Math.abs(denominator));
    	
    	int[] simplifiedRational;
    	
    	if (denominator == 0) {
    		throw new IllegalArgumentException();
    	} else {
    		simplifiedRational = new int[2];
    		for (int i : simplifiedRational) {
    			simplifiedRational[i] = gcd(Math.abs(numerator), Math.abs(denominator));
    		}
    	}
    	
    	return simplifiedRational;
    }

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * Simplification of numerator/denominator pair should occur in this method.
     * If the numerator is zero, no further simplification can be performed
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    
    // HINT: do not simplify fractions that have a numerator of 0
    
    public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {				
        
    	if (denominator == 0) {
    		throw new IllegalArgumentException();
    	} else if (numerator == 0) {
    		num = numerator;
    		denom = denominator;
    	}
    	
    	if (denom < 0) {
    		numerator *= -1;
    		denominator *= -1;
    	}
    	
    	num = numerator;
    	denom = denominator;
    	
    }

    /**
     * @return the numerator of this rational number
     */
    @Override
    public int getNumerator() {
        
    	return num;
    }

    /**
     * @return the denominator of this rational number
     */
    @Override
    public int getDenominator() {
        
    	return denom;
    }

    /**
     * Specializable constructor to take advantage of shared code between Rational and SimplifiedRational
     * <p>
     * Essentially, this method allows us to implement most of IRational methods directly in the interface while
     * preserving the underlying type
     *
     * @param numerator   the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value (specifically, a SimplifiedRational value)
     * @throws IllegalArgumentException if the given denominator is 0
     */
    
    // Hint: should create a new SimplifiedRational object
    
    @Override
    public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
       
    	if (denominator == 0) {
        	throw new IllegalArgumentException();
    	}
    
    	return new SimplifiedRational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a simplified rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
    
    // Hint: make use of the instanceof operator
    
    @Override
    public boolean equals(Object obj) {
// Hint: obj instanceof SimplifedRational;
    	if (obj == null) {
    		return false;
    	}
    	
    	if (!(obj instanceof SimplifiedRational)) {
    		return false;
    	}
    
    	SimplifiedRational that = (SimplifiedRational) obj;
    	return num == that.num && denom == that.denom;
    
    }

    /**
     * If this is positive, the string should be of the form `numerator/denominator`
     * <p>
     * If this is negative, the string should be of the form `-numerator/denominator`
     *
     * @return a string representation of this rational value
     */
    
    // There are 4 cases to determine fractions:
    // 1. If both numerator and denominator are negative, overall fraction is positive
    // 2. If both numerator and denominator are positive, overall fraction is positive
    // 3. If the numerator is negative and the denominator is positive, overall fraction is negative   
    // 4. If the denominator is negative and the numerator is positive, overall fraction is negative
  
    // Hint: if overall fraction is positive, print out `numerator/denominator`
    // if overall fraction is negative, print out `-numerator/denominator`
    // Make use of the Math.abs()
    
    @Override
    public String toString() {								
    	
    	if (gcd(Math.abs(num), Math.abs(denom)) > 0) {
    		return num + "/" + denom; 
    	} else if (gcd(Math.abs(num), Math.abs(denom)) < 0) {
    		return num + "/" + denom;
    	} else {
    		return num + "/" + denom;	
    	}
    	 
    }	
        	
}
