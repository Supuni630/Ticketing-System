package OopCw2;

public class Configuration {
    private int totalTickets;  //variable for totalTickets
    private int maxPoolTickets;   //variable for maxPoolTickets
    private int ticketReleaseRate;   //variable for ticketReleaseRate
    private int customerRetrievalRate;      //variable for customerRetrievalRate

    public Configuration(int totalTickets, int maxPoolTickets, int ticketReleaseRate, int customerRetrievalRate) {
        this.totalTickets = totalTickets;
        this.maxPoolTickets = maxPoolTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }                  //getter for totalTickets

    public int getMaxPoolTickets() {
        return maxPoolTickets;
    }              //getter for maxPoolTickets

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }           //getter for ticketReleaseRate

    public int getCustomerRetrievalRate() {         //getter for customerRetrievalRate
        return customerRetrievalRate;
    }
}


