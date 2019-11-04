import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 使用自己定义的优先队列
class Solution {
    private class Node implements Comparable<Node>{
        public int val;
        public int freq;

        public Node(int val, int freq){
            this.val = val;
            this.freq = freq;
        }

        @Override
        public int compareTo(Node o) {
            if (this.freq < o.freq)
                return 1;
            else if (this.freq > o.freq)
                return -1;
            else
                return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for(int num:nums){
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int key: map.keySet()){
            if (pq.getSize() < k)
                pq.enqueue(new Node(key, map.get(key)));
            else if (map.get(key) > pq.getFront().freq){
                pq.dequeue();
                pq.enqueue(new Node(key, map.get(key)));
            }
        }
        while(!pq.isEmpty())
            res.add(pq.dequeue().val);
        return res;
    }
}