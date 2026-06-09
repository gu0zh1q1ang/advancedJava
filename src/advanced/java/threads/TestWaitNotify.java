package advanced.java.threads;

import org.openjdk.jol.info.ClassLayout;

public class TestWaitNotify {

    public static void main(String[] args) {
        Data2 data = new Data2();

        Receiver receiverData = new Receiver(data);
        Thread receiver = new Thread(receiverData, "receiver");
        receiver.start();

        System.out.println(ClassLayout.parseInstance(receiver).toPrintable());

        Sender senderData = new Sender(data);
        Thread sender = new Thread(senderData, "sender");
        sender.start();

        try {
            sender.join(1000);
            receiver.join(1000);
            System.out.println(receiverData.getNotice());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
