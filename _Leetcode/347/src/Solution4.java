import java.util.*;
import java.util.PriorityQueue;

// Comparator使用匿名类, 改变内置Integer类的比较方式
class Solution4 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for(int num:nums){
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
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