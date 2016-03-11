package com.company;

/**
 * Created by drathbone on 15/01/16.
 */
public class Calculator {

    public static boolean isFactor(long factor, long number) {
        if(number % factor == 0 && factor != 1 && factor != number) {
            return true;
        }
        return false;

    }

    public static boolean isPrimeNumber(long n) {
        if ( n >= 4 )
        {
            if ( n%2 == 0 ) return false;
            for ( long i=3; i*i<=n; i+=2 )
            {
                if ( n%i == 0 ) return false;
            }
            return true;
        } else {
            if ( n >= 2 )
                return true;
            else
                return false;
        }
    }

    public static boolean isMersenne(long number) {
        int powerOfTwo = 2;
        while (powerOfTwo <= number) {
            powerOfTwo *= 2;
        }
        if(number == (powerOfTwo - 1)) {
            return true;
        }
        return false;
    }

}
