// wrote this code in java useing a Dijkstra Algorithm, past 3 cases [n=4 k=2] [n=2 k=1] [n=2 k=2] runtime= 28ms  

import java.util.*;

class Solution {

    private void dijkstra(int[] signalReceivedAt, int source, Map<Integer, List<Pair<Integer, Integer>>> adj) {
        Queue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        pq.add(new Pair<>(0, source));

        signalReceivedAt[source] = 0;

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> topPair = pq.remove();

            int currNode = topPair.getValue();
            int currNodeTime = topPair.getKey();

            if (currNodeTime > signalReceivedAt[currNode]) {
                continue;
            }

            if (!adj.containsKey(currNode)) {
                continue;
            }

            for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                int time = edge.getKey();
                int neighborNode = edge.getValue();

                if (signalReceivedAt[neighborNode] > currNodeTime + time) {
                    signalReceivedAt[neighborNode] = currNodeTime + time;
                    pq.add(new Pair<>(signalReceivedAt[neighborNode], neighborNode));
                }
            }
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

        for (int[] time : times) {
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];

            adj.putIfAbsent(source, new ArrayList<>());
            adj.get(source).add(new Pair<>(travelTime, dest));
        }

        int[] signalReceivedAt = new int[n + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

        dijkstra(signalReceivedAt, k, adj);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}