import javafx.util.converter.IntegerStringConverter;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class MyBigIntegers {

    static String ResultsFolderPath = "/home/diana/Results/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;

    static int numberOfTrials = 100;
    static int MAXINPUTSIZE = (int) Math.pow(1.5, 22);
    static int MININPUTSIZE = 1;
    static int Nums = 90;
    static long fibResult = 0;
    static int Base = 10;
    static String Added = "";
    static String FibResult = "";
    static int inputSize = 2;
    public static class bigIntegers {
        int BigNum1[] = new int[inputSize];
        public int sign;

    }

    public static class bigInt{
        public String num1;
        public String num2;
    }


    public static bigIntegers setBigNumber(bigIntegers bigNumber) {
        bigIntegers BI = new bigIntegers();
        bigIntegers BI2 = new bigIntegers();
        int i = 0;

        for (i = 0; i < BI.BigNum1.length; i++) {
            BI.BigNum1[i] = (int) (0 + Math.random() * (9 - 0));
        }
        BI.sign = 1;


        return BI;
    }

    public static String ToString(bigIntegers BI) {
        StringBuilder stringBuilder = new StringBuilder();
        String s1 = "";
        int xx = 0;
        int x = 0;
        int count = 0;
        for (xx = 0; xx < BI.BigNum1.length; xx++) {
            if(BI.BigNum1[xx] == 0){
                count++;
            }
            else{
                break;
            }

        }
        if(BI.sign == -1){
            stringBuilder.append("-");
        }
        for (x = count; x < BI.BigNum1.length; x++) {
            stringBuilder.append(BI.BigNum1[x]);

        }
        s1 = s1 + stringBuilder;
        //System.out.println("Final string: " + s1);

        return s1;
    }

    public static void main(String[] args) {
        //bigIntegers testBI = new bigIntegers();

        //bigIntegers BI1 = setBigNumber(testBI);
        //System.out.println("Original Array: " + Arrays.toString(BI1.BigNum1));
        //bigIntegers BI2 = setBigNumber(testBI);
        //ToString(BI1);
        //ToString(BI2);
        //bigIntegers BI3 =  add(BI1, BI2, Base);
        //ToString(BI3);



        // run the whole experiment at least twice, and expect to throw away the data from the earlier runs, before java has fully optimized

        System.out.println("Running first full experiment...");
        runFullExperiment("MyBigIntegersTimes-Exp1-ThrowAway.txt");
        System.out.println("Running second full experiment...");
        runFullExperiment("MyBigIntegersTimes-Exp2.txt");
        System.out.println("Running third full experiment...");
        runFullExperiment("MyBigIntegersTimes-Exp3.txt");
    }

    static void runFullExperiment(String resultsFileName) {


        //declare variables for doubling ratio
        double[] averageArray = new double[1000];
        double currentAv = 0;
        double doublingTotal = 0;
        int y = 1;

        //num1 = "99999999999999999999999999999999999999999999999999999999";
        //num2 = "-1";
        //System.out.println("num 1 = " + num1);
        //System.out.println("num 2 = " + num2);




        //set up print to file
        try {
            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);
            resultsWriter = new PrintWriter(resultsFile);
        } catch (Exception e) {
            System.out.println("*****!!!!!  Had a problem opening the results file " + ResultsFolderPath + resultsFileName);
            return; // not very foolproof... but we do expect to be able to create/open the file...
        }

        //declare variables for stop watch
        ThreadCpuStopWatch BatchStopwatch = new ThreadCpuStopWatch(); // for timing an entire set of trials
        ThreadCpuStopWatch TrialStopwatch = new ThreadCpuStopWatch(); // for timing an individual trial

        //add headers to text file
        resultsWriter.println("#X(Value)  AverageTime   NumberOfTrials  Doubling Ratio"); // # marks a comment in gnuplot data
        resultsWriter.flush();

        /* for each size of input we want to test: in this case starting small and doubling the size each time */
        for (inputSize = MININPUTSIZE; inputSize <= MAXINPUTSIZE; inputSize*=2) {
            //test run for fibonacci numbers
            //verifyFib(inputSize);

            // progress message...
            System.out.println("Running test for input size "+inputSize+" ... ");

            /* repeat for desired number of trials (for a specific size of input)... */
            long batchElapsedTime = 0;
            // generate a list of randomly spaced integers in ascending sorted order to use as test input
            // In this case we're generating one list to use for the entire set of trials (of a given input size)
            // but we will randomly generate the search key for each trial
            //System.out.print("    Generating test data...");

            //generate random integer list
            //long resultFib = Fib(x);
            bigIntegers testBI = new bigIntegers();
            bigIntegers testBI2 = new bigIntegers();
            testBI.BigNum1 = new int[inputSize];
            testBI2.BigNum1 = new int[inputSize];

            bigIntegers BI = setBigNumber(testBI);
            bigIntegers BI2 = setBigNumber(testBI2);
            //bigIntegers BI = new bigIntegers();
            //bigIntegers BI2 = new bigIntegers();
            //BI.BigNum1 = new int[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8,8,8,8,8,8,8,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3};
            //BI2.BigNum1 = new int[]{3,3,3,3,3,3,3,4,4,4,4,8,8,8,8,8,8,8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,6,6,6,6,6};
            BI.sign = 1;
            BI2.sign = 1;
            /*String number1 = "";
            String number2 = "";
            int p = 0;
            for(p=0; p<BI.BigNum1.length;p++){
               number1 += Integer.toBinaryString(BI.BigNum1[p]);
            }
            for(p=0; p<BI2.BigNum1.length;p++){
                number2+= Integer.toBinaryString(BI2.BigNum1[p]);
            }*/
            String num1 = ToString(BI);
            String num2 = ToString(BI2);
            //String num1 = "3333333333444444444477777777779999999999";
            //String num2 = "4444444444555555555522222222228888888888";
            //System.out.println("String " + num1);
            //System.out.println("String2 " + num2);
            //bigInt n1 = new bigInt(){};
            //print progress to screen
            System.out.println("...done.");
            System.out.print("    Running trial batch...");

            /* force garbage collection before each batch of trials run so it is not included in the time */
            System.gc();


            // instead of timing each individual trial, we will time the entire set of trials (for a given input size)
            // and divide by the number of trials -- this reduces the impact of the amount of time it takes to call the
            // stopwatch methods themselves
            BatchStopwatch.start(); // comment this line if timing trials individually

            // run the trials
            for (long trial = 0; trial < numberOfTrials; trial++) {
                // generate a random key to search in the range of a the min/max numbers in the list
                //long testSearchKey = (long) (0 + Math.random() * (testList[testList.length-1]));
                /* force garbage collection before each trial run so it is not included in the time */
                // System.gc();

                //TrialStopwatch.start(); // *** uncomment this line if timing trials individually
                /* run the function we're testing on the trial input */


               /* if ((num1.charAt(0) == '-' || num2.charAt(0) == '-') && (num1.charAt(0) != '-' || num2.charAt(0) != '-')) {
                    System.out.print("-");
                }

                if (num1.charAt(0) == '-' &&
                        num2.charAt(0) != '-')
                {
                    num1 = num1.substring(1);
                }
                else if (num1.charAt(0) != '-' &&
                        num2.charAt(0) == '-')
                {
                    num2 = num2.substring(1);
                }
                else if (num1.charAt(0) == '-' &&
                        num2.charAt(0) == '-')
                {
                    num1 = num1.substring(1);
                    num2 = num2.substring(1);
                }*/
                //bigIntegers bigIntegersAdded = add(BI, BI2, Base);
                //Added = ToString(bigIntegersAdded);
                //System.out.println("Sum = " + Added);
                //String BI4 =String.valueOf(karatsuba(number1, number2));
                String BI4 = times(num1, num2, Base);
                //FibResult = Added;
                FibResult = BI4;
                //System.out.println("total = " + BI4);
                //fibResult = Fib(inputSize);
                //System.out.println(result);
                // batchElapsedTime = batchElapsedTime + TrialStopwatch.elapsedTime(); // *** uncomment this line if timing trials individually
            }
            batchElapsedTime = BatchStopwatch.elapsedTime(); // *** comment this line if timing trials individually
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials; // calculate the average time per trial in this batch

            //put current average time in array of average times. We will be able to use this to calculate the doubling ratio
            averageArray[y] = averageTimePerTrialInBatch;

            //skip this round if this is the first one (no previous average for calculation)
            if (inputSize != 0) {
                doublingTotal = averageTimePerTrialInBatch / averageArray[y - 1]; //Calculate doubling ratio

            }
            y++;

            //System.out.println("total = " + FibResult);
            //int countingbits = countBits(inputSize);
            /* print data for this size of input */
            resultsWriter.printf("%6d %15.2f %4d %15.2f\n", inputSize, averageTimePerTrialInBatch, numberOfTrials, doublingTotal); // might as well make the columns look nice
            resultsWriter.flush();
            //System.out.println(" ....done.");
        }
    }

    public static bigIntegers add(bigIntegers B1, bigIntegers B2, int Base) {
        //declare variables
        int[] total = new int[B1.BigNum1.length+1];
        int[] num1 = new int[]{};
        int[] num2 = new int[]{};
        int temp = 0;
        int drop = 0;
        int carry = 0;
        bigIntegers Result = new bigIntegers();

        //determine which number is bigger and make num1 the largest number
        for (int i = 0; i < B1.BigNum1.length; i++) {
            if (B1.BigNum1[i] == 0) {
                if (B2.BigNum1[i] != 0) {
                    num1 = B2.BigNum1;
                    num2 = B1.BigNum1;
                    break;
                }
            } else {
                num1 = B1.BigNum1;
                num2 = B2.BigNum1;
                break;
            }
        }

        //starting at the farthest right, loop through both arrays and add the two numbers together one digit at a time, find the carry and save it to add to next two
        //numbers added together.
        for(int x = num1.length - 1; x >= 0; x--){
          //add the two numbers and carry together
          temp = num1[x] + num2[x] + carry;
          //calculate the carry by dividing the result by the base number (10)
          carry =(int) Math.floor(temp/Base);
          //calculate the number to drop to the total result by temp result modulo base number (10)
          drop = temp % Base;
            //add the drop number to the total number array
            total[x+1] = drop;

        }
        //put the last carry in the first index of the array
        total[0] = carry;
        //set the total array to the bigInteger array that is returned
        Result.BigNum1 = total;

        return Result;
    }

    public static String times(String num1String, String num2String, int Base){
        //I was able to get the karatsuba algorithm to work with smaller numbers but was unable to get it to work with
        //big integers
        //long num1 = Long.parseLong(num1String.trim());
        //long num2 = Long.valueOf(num2String.trim());
        //long total = karatsuba(num1, num2, Base);

        int length1 = num1String.length();
        int length2 = num2String.length();
        //if either array length is 0, return 0
        if(length1 == 0 || length2 == 0){
            return "0";
        }
        //declare result array to be the length of both arrays combined
        int result[] = new int[length1 + length2];
        //declare variables
        int index1 = 0;
        int index2 = 0;
        int i,j = 0;
        //loop through string 1
        for( i = length1 - 1; i >=0; i--){
            //declare variables
            int carry = 0;
            //set 1st number to farthest right character
            int n1 = num1String.charAt(i) - '0';
            //set index2 to 0
            index2 = 0;
            //loop through second string
            for(j = length2 - 1; j>= 0; j--){
                //set 2nd number to the farthest right character of string 2
                int n2 = num2String.charAt(j) - '0';
                //calculate the total by multiplying the two numbers adding the last calclated total and the carry
                int total = n1 * n2 + result[index1 + index2] + carry;
                //calculate the carry
                carry = total/Base;
                //set the next number in the total array
                result[index1 + index2] = total %Base;
                //increment the index value
                index2++;
            }
            //if carry does not = 0, add the carry to the last digit in the total array
            if(carry > 0 )
            {
                result[index1 + index2] += carry;
            }
            //increment index 1
            index1++;
        }
        //loop through the result array and find the index of the last 0 on the right
        i = result.length-1;
        while(i >= 0 && result[i] == 0){
            i--;
        }
        //if there are no 0's return 0
        if(i == -1){
            return "0";
        }
        //declare the string to return
        String s = "";
        //add all characters in result array to s and return s
        while(i >=0){
            s += (result[i--]);
        }

        return s;
    }

    /*public static long  karatsuba(String num1, String num2) {

        // Find the maximum of lengths of x and Y and make length
        // of smaller string same as that of larger string
        int n = makeEqualLength(num1, num2);

        // Base cases
        if (n == 0) return 0;
        if (n == 1) return multiplyiSingleBit(num1, num2);

        int fh = n/2;   // First half of string, floor(n/2)
        int sh = (n-fh); // Second half of string, ceil(n/2)

        // Find the first half and second half of first string.
        // Refer http://goo.gl/lLmgn for substr method
        String Xl =num1.substring(0, fh);
        String Xr =num1.substring(fh, sh);

        // Find the first half and second half of second string
        String Yl = num2.substring(0, fh);
        String Yr = num2.substring(fh, sh);

        // Recursively calculate the three products of inputs of size n/2
       long P1 = karatsuba(Xl, Yl);
       long P2 = karatsuba(Xr, Yr);
       long P3 = karatsuba(addBitStrings(Xl, Xr), addBitStrings(Yl, Yr));

        // Combine the three products to get the final result.
        return P1*(1<<(2*sh)) + (P3 - P1 - P2)*(1<<sh) + P2;
    }

    public static int makeEqualLength(String num1, String num2){

        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 < len2)
        {
            for (int i = 0 ; i < len2 - len1 ; i++)
                num1 = '0' + num2;
            return len2;
        }
        else if (len1 > len2)
        {
            for (int i = 0 ; i < len1 - len2 ; i++)
                num2 = '0' + num2;
        }
        return  len1;
    }

    public static String addBitStrings(String num1, String num2){

        String result = "";  // To store the sum bits

        // make the lengths same before adding
        int length = makeEqualLength(num1, num2);
        int carry = 0;  // Initialize carry

        // Add all bits one by one
        for (int i = length-1 ; i >= 0 ; i--)
        {
            int firstBit = num1.charAt(i) - '0';
            int secondBit = num2.charAt(i) - '0';

            // boolean expression for sum of 3 bits
            int sum = (firstBit ^ secondBit ^ carry)+'0';

            result = (char)sum + result;

            // boolean expression for 3-bit addition
            carry = (firstBit&secondBit) | (secondBit&carry) | (firstBit&carry);
        }

        // if overflow, then add a leading 1
        if (carry != 0)  result = '1' + result;

        return result;
    }

    public static int multiplyiSingleBit(String a, String b)
    {
        int result = (a.charAt(0) - '0')*(b.charAt(0) - '0');
        return result;
    }*/
}

