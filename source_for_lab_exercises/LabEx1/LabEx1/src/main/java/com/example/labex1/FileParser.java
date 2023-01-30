package com.example.labex1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/*

Class to parse the file

Uses StreamTokenizer - StringTokenizer could also be used

*/
public class FileParser {

    //We will create a FileReader to the file and wrap it with a StreamTokenizer
    static FileReader frs = null;
    static StreamTokenizer in = null;

    //store the max number of days in each month
    static int[] daysInMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //var to determine where each value which is read from the file is stored in the array
    static int day = 0;


    //array to store each ranking wich is read from the file 366 days in a leap year,
    //no value stored in days[0] so array needs to be sized at 367
    static int[] days = new int[367];



    public static int[] fillArray() {

        readFile(days); // read contents of file into array

        return days; //return array

    }//end fillArray()

    // method to read contents of file into array
    private static void readFile(int[] days) {

        try {
            // Create file input and output streams
            frs = new FileReader("days.txt");

            // Create a stream tokenizer wrapping file input stream
            in = new StreamTokenizer(frs);

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }//end catch

        try {
            //loop to read past the col headings
            for (int i = 0; i < 31; i++) {
                in.nextToken();
            }//end for

            //read the 2nd row 1st col value (i.e "Jan")
            in.nextToken();

            //while not EOF
            while (in.ttype != StreamTokenizer.TT_EOF) {

                //loop through the 12 months of the year
                for (int month = 0; month < 12; month++) {
                    //read the popularity rankings for each day in the current month
                    readDaysInMonth(days, month);

                    //read next token => this line will read the remainder of the row headings
                    //E.G. "Feb", "Mar"...."Dec"
                    in.nextToken();

                }//end for

            }//end while

        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(0);
        }//end catch

    }//end readFile()

    //method to read the rankings for a given month
    private static void readDaysInMonth( int[] days, int month) {

        for (int i = 0; i < daysInMonths[month]; i++) {
            day++; //day stores the current day of the year => 01/01 is 1, 31/12 is 366 etc
            try {
                in.nextToken(); //read the next token
            } catch (IOException ex) {
                System.out.println(ex);
            }

            int ranking = (int) in.nval; //read ranking from the stream

            days[day] = ranking; //store the ranking in the array at index day


        }//end for

    }//end readDaysInMonth()

}//end class