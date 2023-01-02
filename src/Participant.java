import java.util.Random;

/**
 * participants are the people who want to attend interview in {@link Accenture} company.
 * they are waiting out of the company and when there is less than 3 person in company people can enter the company.
 *
 * @since 2022
 * @author Mohsen Gholami | iMohsen02
 */
public class Participant extends Thread {

    String name;
    private static final Accenture ACCENTURE;

    static {
        ACCENTURE = new Accenture();
    }

    Participant(String name) {
        this.name = name;
        this.setName("Thread[" + name + ']');
    }

    @Override
    public void run() {
        System.out.println("\033[0m" + this + " started");

        try {

            // start thread
            ACCENTURE.SendToInterview(this);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
