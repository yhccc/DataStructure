import java.util.ArrayList;
import java.util.TreeSet;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        ArrayList<Integer> res = new ArrayList<>();

        for (int num:nums1)
            set.add(num);
        for (int num:nums2){
            if (set.contains(num))
            {
                res.add(num);
                set.remove(num);
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}