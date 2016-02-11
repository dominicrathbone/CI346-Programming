package com.company;

/**
 * Created by drathbone on 15/01/16.
 */
public class Calculator {

    public static boolean isFactor(int factor, int number) {
        if(number % factor == 0 && factor != 1 && factor != number) {
            Debug.log(factor + " is a factor of " + number);
            return true;
        }
        return false;
    }

    public static boolean isPerfectNumber(int number){
        int temp = 0;
        for(int i=1;i<=number/2;i++){
            if(number%i == 0){
                temp += i;
            }
        }
        if(temp == number) {
            Debug.log(number + " is a perfect number");
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPrimeNumber(int number) {
        if (number >= 4) {
            if (number % 2 == 0) {
                return false;
            }
            for (long i=3; i*i<= number; i+=2)
            {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        } else {
            if (number >= 2) {
                Debug.log(number + " is a prime number");
                return true;
            }
            else {
                return false;
            }
        }
    }

    public static boolean isMersenne(int number) {
        int powerOfTwo = 2;
        while (powerOfTwo <= number) {
            powerOfTwo *= 2;
        }
        if(number == (powerOfTwo - 1)) {
            Debug.log(number + " is a mersenne number");
            return true;
        }
        return false;
    }

}
