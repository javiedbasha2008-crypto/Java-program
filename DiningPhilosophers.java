import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    
    // There are 5 forks, each represented by a lock
    private final ReentrantLock[] forks = new ReentrantLock[5];

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        
        // Determine the fork indices for the current philosopher
        int leftFork = philosopher;
        int rightFork = (philosopher + 1) % 5;
        
        // Enforce the asymmetry rule to prevent deadlocks
        if (philosopher % 2 == 0) {
            // Even philosophers: Left then Right
            forks[leftFork].lock();
            forks[rightFork].lock();
        } else {
            // Odd philosophers: Right then Left
            forks[rightFork].lock();
            forks[leftFork].lock();
        }
        
        try {
            // Standard sequence once both forks are acquired
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
        } finally {
            // Release both locks in the finally block to prevent leaks
            forks[leftFork].unlock();
            forks[rightFork].unlock();
        }
    }
}