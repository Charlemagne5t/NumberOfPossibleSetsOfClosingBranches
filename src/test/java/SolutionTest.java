import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        int n = 3;
        int maxDistance = 5;
        int[][] roads = {
                {0, 1, 2},
                {1, 2, 10},
                {0, 2, 10}
        };
        int expected = 5;
        int actual = new Solution().numberOfSets(n, maxDistance, roads);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        int n = 3;
        int maxDistance = 5;
        int[][] roads = {
                {0, 1, 20},
                {0, 1, 10},
                {1, 2, 2},
                {0, 2, 2}
        };
        int expected = 7;
        int actual = new Solution().numberOfSets(n, maxDistance, roads);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test3() {
        int n = 1;
        int maxDistance = 10;
        int[][] roads = {};
        int expected = 2;
        int actual = new Solution().numberOfSets(n, maxDistance, roads);

        Assert.assertEquals(expected, actual);
    }
}
