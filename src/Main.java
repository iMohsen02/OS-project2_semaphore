import java.util.Scanner;

/**
 * For the interview, Accenture has assigned person X to be responsible for this job and given him a room and a
 * A seat is provided. The interview procedure is like this, from among the volunteers, the person who came earlier
 * entered the room and meanwhile the rest of the people have to wait in the waiting room. After the interview
 * When the person is finished, he leaves the room and the next volunteer enters the room from the waiting room.
 * If there is no one in the waiting room during office hours, person X will take a nap.
 * A) Get the number of interview volunteers from the user and this process using thread 2 and guides
 * simulate
 * b) The waiting hall has 3 seats and if the seats are full in the waiting hall, people should enter
 * Wait outside the company (capacity is unlimited outside the company).
 *
 * @since 2022
 * @author Mohsen Gholammi | iMohsen02
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Project 2 - Accenture - Semaphore");

        int numberOfParticipant = new Scanner(System.in).nextInt();
        for (int i = 0; i < numberOfParticipant; i++) {
            new Participant(String.valueOf(i + 1)).start();
        }
    }
}
