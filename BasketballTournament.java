import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BasketballTournament {
    public static final Semaphore insideT = new Semaphore(20);
    public static final Semaphore inDressingRoom = new Semaphore(10);
    public static final AtomicInteger dressedP = new AtomicInteger(0);
    public static final Semaphore readyP = new Semaphore(0, true);
    public static final Semaphore gameDone = new Semaphore(0, true);
    static Lock lock = new ReentrantLock();

    public static int getDressedP() {
        lock.lock();
        int val = dressedP.get();
        lock.unlock();
        return val;
    }

    public static void incrementDressedPlayer() {
        lock.lock();
        dressedP.incrementAndGet();
        lock.unlock();
    }

    public static void resetDressedPlayers() {
        lock.lock();
        dressedP.set(0);
        lock.unlock();
    }


    public static void main(String[] args) {
        HashSet<Player> threads = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Player p = new Player();
            threads.add(p);
        }
        threads.forEach(Thread::start);

        for(Thread t: threads) {
            try {
                t.join(5000);
            } catch (InterruptedException e) {
                System.out.println("Possible deadlock!");
                return;
            }
        }

        System.out.println("Tournament finished.");
    }

}


class Player extends Thread {

    @Override
    public void run(){
        try{
            execute();
        }catch (InterruptedException e) {
            System.out.println("Possible deadlock!");
            System.exit(0);
        }
    }

    public void execute() throws InterruptedException {
        // at most 20 players should print this in parallel
        BasketballTournament.insideT.acquire();
        System.out.println("Player inside.");
        BasketballTournament.inDressingRoom.acquire();
        // at most 10 players may enter in the dressing room in parallel

        System.out.println("In dressing room.");

        Thread.sleep(10);// this represent the dressing time
        // after all players are ready, they should start with the game together
        BasketballTournament.inDressingRoom.release();
        BasketballTournament.incrementDressedPlayer();
        if (BasketballTournament.getDressedP() == 20) {
            BasketballTournament.resetDressedPlayers();
            BasketballTournament.readyP.release(20);
        }

        BasketballTournament.readyP.acquire();
        System.out.println("Game started.");
        Thread.sleep(100);// this represent the game duration
        System.out.println("Player done.");
        BasketballTournament.gameDone.release();

        if (BasketballTournament.gameDone.availablePermits() == 20) {
            System.out.println("Game finished.");
            BasketballTournament.gameDone.acquire(20);
            BasketballTournament.insideT.release(20);
        }
        // only one player should print the next line, representing that the game has finished

    }
}

