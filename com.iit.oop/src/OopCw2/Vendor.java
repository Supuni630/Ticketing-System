package OopCw2;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;            //variable for ticketPool
    private final int releaseRate;                  //variable for releaseRate
    private final int totalTickets;                //variable for totalTiclkets

    public Vendor(TicketPool ticketPool, int releaseRate,int totalTickets) {      //constructor for vendor
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.totalTickets = totalTickets;
    }

    @Override
    public void run() {


        int addTickets = 0;
        while(addTickets < totalTickets){
            int addedTickets = Math.min(releaseRate,totalTickets - addTickets);
            if(ticketPool.areAllTicketsReleased()){
                System.out.println(Thread.currentThread().getName()+ "All tickets over.Finish adding tickets.");
                break;
            }
            ticketPool.addTickets(addedTickets);
            addTickets += addedTickets;

            try {
                Thread.sleep(1000); // Simulate 1-second interval between releases
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}


