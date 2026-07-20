
  import java.util.concurrent.Semaphore;
  import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    private int currentNumber = 1;
    
    // Semaphores to synchronize the 4 distinct threads
    private Semaphore fizzSema = new Semaphore(0);     
    private Semaphore buzzSema = new Semaphore(0);     
    private Semaphore fizzbuzzSema = new Semaphore(0); 
    private Semaphore numberSema = new Semaphore(1);   // Starts unblocked

    public FizzBuzz(int n) {
        this.n = n;
    }

    // Thread A calls fizz() -> prints "fizz"
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            fizzSema.acquire();
            if (currentNumber > n) break;
            
            printFizz.run();
            
            currentNumber++;
            numberSema.release();
        }
    }

    // Thread B calls buzz() -> prints "buzz"
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            buzzSema.acquire();
            if (currentNumber > n) break;
            
            printBuzz.run();
            
            currentNumber++;
            numberSema.release();
        }
    }

    // Thread C calls fizzbuzz() -> prints "fizzbuzz"
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            fizzbuzzSema.acquire();
            if (currentNumber > n) break;
            
            printFizzBuzz.run();
            
            currentNumber++;
            numberSema.release();
        }
    }

    // Thread D calls number() -> prints the integer digits
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (currentNumber <= n) {
            numberSema.acquire();
            
            if (currentNumber > n) {
                break;
            }

            if (currentNumber % 15 == 0) {
                fizzbuzzSema.release();
            } else if (currentNumber % 3 == 0) {
                fizzSema.release(); 
            } else if (currentNumber % 5 == 0) {
                buzzSema.release(); 
            } else {
                printNumber.accept(currentNumber);
                currentNumber++;
                numberSema.release(); 
            }
        }

        // Release blocked threads so they can exit their loops gracefully
        fizzSema.release();
        buzzSema.release();
        fizzbuzzSema.release();
    }
}