package Medium.QueenAttackII;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        int paths = 0;

        r_q -= 1;
        c_q -= 1;

//        2d array won't work. OOM.
//        String[][] board = new String[n][n];

        Map<String, Integer> board = new HashMap<>();

        int[][] directions = new int[][]{
                {1, 0}, // down
                {-1, 0}, // up
                {0, 1}, // right
                {0, -1}, // left
                {1, 1}, // bottom right
                {-1, -1}, // top left
                {-1, 1}, // top right
                {1, -1}, // bottom left
        };


        // queen's position marked as 1
        board.put("%d,%d".formatted(r_q, c_q), 1);

        // obstacles marked as -1
        for (List<Integer> obstacle : obstacles) {
            int x = obstacle.get(0) - 1;
            int y = obstacle.get(1) - 1;

            board.put("%d,%d".formatted(x, y), -1);
        }

        for (int[] row : directions) {
            int next_x = row[0];
            int next_y = row[1];

            int r_q_moved = r_q + next_x;
            int c_q_moved = c_q + next_y;

            while (0 <= r_q_moved && r_q_moved < n &&
                    0 <= c_q_moved && c_q_moved < n &&
                    board.getOrDefault("%d,%d".formatted(r_q_moved, c_q_moved), 0) != -1) {

                paths++;

                r_q_moved += next_x;
                c_q_moved += next_y;
            }
        }

        return paths;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
