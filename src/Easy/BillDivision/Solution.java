package Easy.BillDivision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'bonAppetit' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY bill - prices of items
     *  2. INTEGER k - item anna did not eat
     *  3. INTEGER b - charged amount
     */

    public static void bonAppetit(List<Integer> bill, int k, int b) {
        int annaCharge = 0;

        for (int i = 0; i < bill.size(); i++) {
            if (i == k) continue;

            annaCharge += bill.get(i);
        }
        annaCharge /= 2;

        if (b == annaCharge) {
            System.out.println("Bon Appetit");
        } else {
            System.out.println(b - annaCharge);
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> bill = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int b = Integer.parseInt(bufferedReader.readLine().trim());

        Result.bonAppetit(bill, k, b);

        bufferedReader.close();
    }
}
