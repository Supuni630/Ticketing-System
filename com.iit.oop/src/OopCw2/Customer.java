package OopCw2;

public class Customer implements Runnable {
    private final TicketPool ticketPool;              //variable for ticketPool
    private final int retrievalRate;                  //variable for retrievalRate

    public Customer(TicketPool ticketPool, int retrievalRate) {              //constructor for costomer
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        while (true) {
            ticketPool.retrieveTickets(retrievalRate);

            if(Thread.currentThread().isInterrupted()){
                break;
            }
            try {
                Thread.sleep(1000); // Simulate 1-second interval for each customer action
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}



