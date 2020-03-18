package Sleeping_Barber;
import java.util.concurrent.atomic.AtomicInteger;

public class Barber implements Runnable {
    private static final AtomicInteger idGenerator = new AtomicInteger();
    private final int id;
    private final Shop.WaitingRoom waiting;
    public Barber(Shop.WaitingRoom waitingRoom) {
        this.waiting = waitingRoom;
        this.id = idGenerator.incrementAndGet();
    }
/*
     */

    @Override
    public void run() {
        while(true)
        {
            waiting.cutHair(id);
        }

    }

}
