/**
 * interviewer is a person work in {@link Accenture} company and provided interview.
 * when the waiting room and interview is empty interviewer is relaxing.
 *
 * @since 2022
 * @author Mohsen Gholami | iMohsen02
 */
public class Interviewer extends Thread{

    String name;
    Interviewer(String name) {
        this.name = name;
        this.setName("Thread[" + name + ']');
    }

    @Override
    public void run() {
        this.rest();
        super.run();
    }

    public void rest() {
        System.out.println("X is sleeping...");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
