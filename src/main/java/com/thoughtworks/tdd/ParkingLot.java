package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {

    private HashMap<Ticket,Car> ticketForcar;

    ParkingLot(){
        ticketForcar=new HashMap<>();
    }

    public HashMap<Ticket, Car> getTicketForcar() {
        return ticketForcar;
    }

}
