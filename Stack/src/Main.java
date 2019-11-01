import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        LinkedListStack<Integer> stack = new LinkedListStack<>();
//        for (int i = 0; i < 10; i++) {
//            stack.push(i);
//            System.out.println(stack);
//        }
//        stack.pop();
//        System.out.println(stack);

        int opCount = 10000000;
        ArrayStack<Integer> arrayQueue = new ArrayStack<>();
        double time1 = testStack(arrayQueue, opCount);
        System.out.println(time1);
        LinkedListStack<Integer> loopQueue = new LinkedListStack<>();
        double time2 = testStack(loopQueue, opCount);
        System.out.println(time2);
    }

    // 测试使用s运行opCount个push和pop操作所需要的时间，单位：秒
    private static  double testStack(Stack<Integer> s, int opCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            s.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            s.pop();

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
