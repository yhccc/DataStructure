import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
//        for(int i = 0; i < 5; i++)
//        {
//            listQueue.enqueue(i);
//            System.out.println(listQueue);
//        }
//
//        listQueue.dequeue();
//        System.out.println(listQueue);

        int opCount = 100000;
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
//        double time1 = testQueue(arrayQueue, opCount);
//        System.out.println(time1);
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println(time2);
        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
        double time3 = testQueue(listQueue, opCount);
        System.out.println(time3);
    }

    // 测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
    private static  double testQueue(Queue<Integer> q, int opCount){
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
