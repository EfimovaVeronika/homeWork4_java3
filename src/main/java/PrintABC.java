public class PrintABC {
    private final Object mon = new Object();
    private volatile char currentSymbol = 'A';

    public static void main(String[] args) {
        final PrintABC printABC = new PrintABC();
        Thread thread1 = new Thread(() -> printABC.printA());
        Thread thread2 = new Thread(() -> printABC.printB());
        Thread thread3 = new Thread(() -> printABC.printC());
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentSymbol != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    currentSymbol = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentSymbol != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    currentSymbol = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentSymbol != 'C') {
                        mon.wait();
                    }
                    System.out.print("C");
                    currentSymbol = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

