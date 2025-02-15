package OopCw2;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private int tickets;                            //variable for tickets
    private final int maxPoolTickets;               //variable for maxPoolTickets
    private int ticketReleaseRate;                 //variable for ticketReleaseRate
    private final int totalTickets;              //variable for totalTickets


    public TicketPool(int maxPoolTickets, int totalTickets) {                 //constructor for TicketPool
        this.maxPoolTickets = maxPoolTickets;
        this.totalTickets = totalTickets;
        this.tickets = 0;
        this.ticketReleaseRate = 0;
    }


    public synchronized int getTickets(){
        return tickets;
    }                  //getter for getTickets

    public synchronized int getMaxPoolTickets(){
        return maxPoolTickets;
    }         //getter for getMaxPoolTickets

    public synchronized boolean areAllTicketsReleased(){
        return ticketReleaseRate >= totalTickets;
    }

    public synchronized void addTickets(int count) {                   //add tickets to the pool.
        while (tickets  + count > maxPoolTickets) {
            System.out.println(Thread.currentThread().getName() + " Waiting to add ticket.");        //waiting if the pool is full
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        if(ticketReleaseRate + count > totalTickets){
            count = totalTickets - ticketReleaseRate;
        }

        tickets += count;
        ticketReleaseRate += count;
        System.out.println(Thread.currentThread().getName() + "added" + count + "tickets.current tickets:" + tickets);
        notifyAll();
    }

    public synchronized void retrieveTickets(int count) {              //retrieves tickets from the pool.
        while (tickets < count) {

            if(areAllTicketsReleased() && tickets == 0){
                System.out.println(Thread.currentThread().getName() + "waiting to buy tickets.No more tickets available.");               //waiting if sufficient tickets are available
            }
            System.out.println(Thread.currentThread().getName() + "waiting to buy tickets.No more enough tickets.");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }


        if(tickets >= count){
            tickets -= count;
            System.out.println(Thread.currentThread().getName() + "retrived " + count + "tickets.remaining tickets:" + tickets);
        }



    }


}



