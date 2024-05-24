package View;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bài tập 1,2 chương 4. lập trình đồng thời");
        System.out.println("Main gọi Thread1 !");

        // Gọi hai thread với độ ưu tiên khác nhau
        callTwoThread();

        System.out.println("Main kết thúc gọi Thread1 !");
        System.out.println("\n\n------------------Main gọi Thread2 !");

        // Gọi hai thread với độ ưu tiên khác nhau, thread1 chạy trước thread2
        callTwoThread02();

        System.out.println("Main kết thúc gọi Thread2 !");
    }

    static void callOne() {
        MyThread1 m1 = new MyThread1();
        m1.run();
    }

    static void callOneWithThread() {
        MyThread1 m1 = new MyThread1();
        Thread t1 = new Thread(m1);
        t1.setName("Thread so 1");
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
    }

    static void callTwoThread() {
        MyThread1 m1 = new MyThread1();
        Thread t1 = new Thread(m1);
        t1.setName("Thread1 so 1");
        t1.setPriority(Thread.MAX_PRIORITY);

        MyThread1 m2 = new MyThread1();
        Thread t2 = new Thread(m2);
        t2.setName("Thread1 so 2");
        t2.setPriority(Thread.MIN_PRIORITY);

        // Bắt đầu hai thread
        t1.start();
        t2.start();
    }

    static void callTwoThread02() {
        MyThread2 m1 = new MyThread2(0);
        Thread t1 = new Thread(m1);
        t1.setName("Thread2 so 1");
        t1.setPriority(Thread.MAX_PRIORITY);

        MyThread2 m2 = new MyThread2(1);
        Thread t2 = new Thread(m2);
        t2.setName("Thread2 so 2");
        t2.setPriority(Thread.MIN_PRIORITY);

        // Bắt đầu thread1
        t1.start();

        // Chờ thread1 kết thúc
        try {
            t1.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // Bắt đầu thread2
        t2.start();
    }
}