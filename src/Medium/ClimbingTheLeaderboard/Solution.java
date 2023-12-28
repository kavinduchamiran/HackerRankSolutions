package Medium.ClimbingTheLeaderboard;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked - current score board
     *  2. INTEGER_ARRAY player - player's scorecard
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> ranks = new ArrayList<>();

        List<Integer> filteredRanked = new ArrayList<>();

        int lastRank = -1;
        for (Integer rank : ranked) {
            if (rank == lastRank) continue;

            filteredRanked.add(rank);
            lastRank = rank;
        }

        int playerRank = filteredRanked.size() - 1;

        for (int score : player) {
            for (int i = playerRank; i >= 0; i--) {
                if (score >= filteredRanked.get(i)) {
                    playerRank--;
                } else {
                    break;
                }
            }

            ranks.add(playerRank + 2);
        }

        return ranks;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
