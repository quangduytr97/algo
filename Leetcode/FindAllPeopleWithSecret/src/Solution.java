import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//BFS
class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] meeting : meetings) {
            int x = meeting[0], y = meeting[1], t = meeting[2];
            // If map contains key x, return the value of x, otherwise return a new ArrayList
            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{t, y});
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{t, x});
        }

        int[] vis = new int[n];
        Arrays.fill(vis, Integer.MAX_VALUE);
        vis[0] = 0;
        vis[firstPerson] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        q.add(new int[]{firstPerson, 0});

        while (!q.isEmpty()) {
            int[] personTime = q.poll();
            int person = personTime[0];
            int time = personTime[1];

            for (int[] nextPersonTime : graph.getOrDefault(person, new ArrayList<>())) {
                int t = nextPersonTime[0];
                int nextPerson = nextPersonTime[1];

                if (t >= time && vis[nextPerson] > t) {
                    vis[nextPerson] = t;
                    q.add(new int[]{nextPerson, t});
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (vis[i] != Integer.MAX_VALUE) {
                ans.add(i);
            }
        }
        return ans;
    }
}