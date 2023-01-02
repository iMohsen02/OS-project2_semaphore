import java.nio.file.Watchable;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Accenture is a company contains a waiting room and interview room which an interviewer is waiting for participants to join and start an interview.
 * but in the implementation interview room and waiting room has a capacity and is critical section, which is controlled by the semaphore.
 * waiting room has 4 capacity of participants only a person can attend interview in interview room.
 *
 * while there is no person in waiting room and interview room, interviewer is relaxing.
 * when the company capacity other people should wait out of the company.
 *
 * @since  2022
 * @author Mohsen Gholami | iMohsen02
 */
public class Accenture {

    // set 44 people because of 3 people are in waiting room and a person is in interview room
    public Accenture() {
        wrSemaphore = new Semaphore(4, true);
    }

    private static class Waitingroom {
        private InterviewRoom interviewRoom;
        private Semaphore irSemaphore;

        Waitingroom(Interviewer interviewer) {
            interviewer.start();
            this.interviewRoom = new InterviewRoom(interviewer);
            this.irSemaphore = new Semaphore(1);
        }

        public void comeIntoWaitingRoom(Participant participant) throws InterruptedException {

            System.out.println("\033[91m" + participant + " is in waiting room\033[m");
            irSemaphore.acquire();

            interviewRoom.startNewInterview(participant);

            irSemaphore.release();

            Thread.sleep(100);
            System.out.println("\033[91m" + participant + " out of waiting room\033[m");

        }

        public int getAvailablePermit()
        {
            return this.irSemaphore.availablePermits();
        }

    }

    private static class InterviewRoom {
        private Interviewer interviewer;
        private Participant participant;

        InterviewRoom(Interviewer interviewer) {
            this.interviewer = interviewer;
        }

        InterviewRoom(Interviewer interviewer, Participant participant) {
            this(interviewer);
            this.participant = participant;
        }

        public void startNewInterview(Participant participant) throws InterruptedException {
            this.participant = participant;
            System.out.println("\033[96mstart interview -> " + this.participant + "\033[0m");

            // interview
            Thread.sleep(1000);

            System.out.println("\033[96mend of interview -> " + this.participant + "\033[0m");
        }
    }


    private static Interviewer interviewer;
    private static Waitingroom waitingroom;

    static {
        interviewer = new Interviewer("X");
        waitingroom = new Waitingroom(interviewer);
    }

    private Semaphore wrSemaphore;
    public void SendToInterview(Participant participant) throws InterruptedException {

        System.out.println("\033[93m" + participant + " is out of company\033[m");
        // because they are waiting out of the company, a random wait is generated
        Thread.sleep(new Random().nextInt(15000));

        // set 3 people can enter the company
        wrSemaphore.acquire();

        waitingroom.comeIntoWaitingRoom(participant);

        // send people out of the company and let other enter
        wrSemaphore.release();

        // check if company is empty person X is relaxing
        if (wrSemaphore.availablePermits() == 4)
            interviewer.rest();

        System.out.println("\033[93m" + participant + " went out of company\033[m");
    }


}
