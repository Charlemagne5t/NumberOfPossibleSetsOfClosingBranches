import java.util.*;

public class Solution {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int[] cities = new int[n];
        for (int i = 0; i < n; i++) {
            cities[i] = i;
        }
        List<List<Integer>> sets = generateVariations(cities);
        int count = 0;
        for (int i = 0; i < sets.size(); i++) {
            if(floydWarshall(n, maxDistance, roads, sets.get(i))){
                count++;
            }
        }
        return count;
    }

    private List<List<Integer>> generateVariations(int[] array) {
        List<List<Integer>> possibleSets = new ArrayList<>();
        generateVariationsHelper(array, 0, new ArrayList<>(), possibleSets);
        return possibleSets;
    }

    private void generateVariationsHelper(int[] array, int index, List<Integer> currentVariation, List<List<Integer>> result) {
        result.add(new ArrayList<>(currentVariation));

        for (int i = index; i < array.length; i++) {
            currentVariation.add(array[i]);
            generateVariationsHelper(array, i + 1, currentVariation, result);
            currentVariation.remove(currentVariation.size() - 1);
        }
    }

    private boolean floydWarshall(int n, int maxDistance, int[][] roads, List<Integer> closed){
        int defaultDistance = 1_000_007;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], defaultDistance);
        }
        for (int i = 0; i < roads.length; i++) {
            if(!closed.contains(roads[i][0]) && !closed.contains(roads[i][1])){
                dp[roads[i][0]][roads[i][1]] = Math.min(roads[i][2], dp[roads[i][0]][roads[i][1]]);
                dp[roads[i][1]][roads[i][0]] = Math.min(roads[i][2], dp[roads[i][1]][roads[i][0]]);
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(!closed.contains(i) && !closed.contains(j) && !closed.contains(k)){
                        if(i == j){
                            dp[i][j] = 0;
                        }else dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!closed.contains(i) && !closed.contains(j)){
                    if(dp[i][j] > maxDistance){
                        return false;
                    }
                }

            }

        }

        return true;
    }
}
