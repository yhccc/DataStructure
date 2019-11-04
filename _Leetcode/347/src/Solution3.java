import java.util.*;
import java.util.PriorityQueue;

// Comparator使用匿名类
class Solution3 {
    private class Node{
        public int val;
        public int freq;

        public Node(int val, int freq){
            this.val = val;
            this.freq = freq;
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
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node a, Node b){
                return a.freq - b.freq;
            }
        });
        for (int key: map.keySet()){
            if (pq.size() < k)
                pq.add(new Node(key, map.get(key)));
            else if (map.get(key) > pq.peek().freq){
                pq.remove();
                pq.add(new Node(key, map.get(key)));
            }
        }
        while(!pq.isEmpty())
            res.add(pq.remove().val);
        return res;
    }
}