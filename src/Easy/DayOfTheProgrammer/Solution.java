package Easy.DayOfTheProgrammer;

import java.io.*;

class Result {

    /*
     * Complete the 'dayOfProgrammer' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER year as parameter.
     */

    public static String dayOfProgrammer(int year) {
        int febCount = 28;

        if (1700 <= year && year <= 1917) {
            // julian
            if (year % 4 == 0) febCount = 29;

        } else if (year == 1918) {
            // transition
            febCount = 15;

        } else if (1919 <= year && year <= 2700) {
            // gregorian
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) febCount = 29;

        }

        int remainingDays = 41 - febCount;


        return "%d.09.%d".formatted(remainingDays, year);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.dayOfProgrammer(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
