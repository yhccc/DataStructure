import java.util.*;
import java.util.PriorityQueue;

// lambda表达式替代匿名类
class Solution5 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for(int num:nums){
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> map.get(a) - map.get(b)
        );
        for (int key: map.keySet()){
            if (pq.size() < k)
                pq.add(key);
            else if (map.get(key) > map.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }
        while(!pq.isEmpty())
            res.add(pq.remove());
        return res;
    }
}