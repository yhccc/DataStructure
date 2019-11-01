public class Sum {
    /*
        递归：本质上，将原来的问题，转化为更小的同一问题
        递归的代价：函数调用 + 系统栈空间
        递归写法：
            求解最基本问题
            把原问题转化为更小的问题
     */

    public static int sum(int[] arr){
        return sum(arr, 0);
    }

    // 计算arr[l…n)这个区间内所有数字的和
    private static int sum(int[] arr, int l){
        if (l == arr.length) return 0;
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(sum(nums));
    }
}
