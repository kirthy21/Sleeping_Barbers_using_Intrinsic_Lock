package Sleeping_Barber;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {

    private static final AtomicInteger idGenerator = new AtomicInteger();

    private final int id;

    private final Shop.WaitingRoom waiting;

    private volatile boolean shaved;

    public Customer(Shop.WaitingRoom waitingRoom) {
        this.id = idGenerator.incrementAndGet();
        this.waiting = waitingRoom;
    }



    @Override
    public void run() {
        try {
            waiting.add(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public boolean isShaved() {
//        return shaved;
//    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
