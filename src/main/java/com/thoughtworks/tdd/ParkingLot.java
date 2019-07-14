package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {

    private HashMap<Ticket,Car>  ticketAndcar = new HashMap<>();

   //停车入库，返回车票，并存入HashMap中
    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        ticketAndcar.put(ticket,car);
        return ticket;
    }

    // 无票/过期票/假票/真票 ，取车
    public Car fetch(Ticket ticket) throws FakeTicketException, UsedTicketException, NoTicketException {
        if (ticket!=null) {
            if (ticketAndcar.containsKey(ticket)) {
                if (ticketAndcar.get(ticket) != null) {
                    Car car = ticketAndcar.get(ticket);
                    ticketAndcar.put(ticket, null);
                    return car;
                } else {
                    throw new UsedTicketException();
                }
            } else {
                throw new FakeTicketException("Unrecognized parking ticket.");
            }
        }else {
            throw new NoTicketException("Please provide your parking ticket.");
        }
    }
}
