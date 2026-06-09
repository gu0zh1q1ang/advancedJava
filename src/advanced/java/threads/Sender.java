package advanced.java.threads;

public class Sender implements Runnable{
    private final Data2 data;

    public Sender(Data2 data) {
        this.data = data;
    }

    @Override
    public void run() {
        synchronized (data){
            data.send("hello-world");
            data.notifyAll();
        }
    }
}
