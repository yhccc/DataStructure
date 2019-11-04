import java.util.*;
import java.util.PriorityQueue;

// 使用Java自带的优先队列 + Comparator优先队列比较器实现
class Solution2 {
    private class Node{
        public int val;
        public int freq;

        public Node(int val, int freq){
            this.val = val;
            this.freq = freq;
        }

    }

    // 可以写成匿名类
    private class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.freq - o2.freq;
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
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
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