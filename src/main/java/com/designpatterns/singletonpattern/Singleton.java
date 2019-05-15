package src.main.java.com.designpatterns.singletonpattern;

/**
 * The singleton pattern is a design pattern that restricts the instantiation of a class to one "single" instance.
 * This is useful when exactly one object is needed to coordinate actions across the system. The term comes from the
 * mathematical concept of a singleton.
 * <p>
 * The key idea in this pattern is to make the class itself responsible for controlling its instantiation (only once).
 * The hidden constructor (declared private) ensures that the class can never be instantiated from outside the class.
 * The public static operation can be accessed easily by using the class name and function name(Singleton.getInstance())
 *
 * @see <a href="https://en.wikipedia.org/wiki/Singleton_pattern">Singleton Pattern</a>
 */
public class Singleton {
    private volatile static Singleton instance = null;

    private Singleton() {
    }

    /**
     * A singleton implementation may use lazy initialization, where the instance is created when the static method
     * is first invoked.
     * <p>
     * If the static method might be called from multiple threads simultaneously, measures may need
     * to be taken to prevent race conditions that could result in the creation of multiple instances of the class.
     * <p>
     * The following implementation is a thread-safe sample implementation, using lazy initialization with
     * double-checked locking.
     *
     * @return the single instance of the Singleton class
     */
    public static Singleton getInstance() {
        if (instance == null) {
            // First attempt to make thread safe
            synchronized (Singleton.class) {
                // Double Checked locking as multiple threads can reach the above step
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
