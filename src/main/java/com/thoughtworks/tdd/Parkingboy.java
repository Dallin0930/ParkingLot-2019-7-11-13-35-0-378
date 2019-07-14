package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingBoy {

    protected ArrayList<ParkingLot> parkingLots;
    private ParkingLot parkingLot;
    private Map<Ticket,ParkingLot> ticketParkinglot= new HashMap<>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public ParkingBoy(ArrayList<ParkingLot> parkingLot) {
        this.parkingLots=parkingLot;
    }



    //停车得到票
    public Ticket park(Car car) throws NoPositionException, CarHasBeenParkedException, NullCarException {
        Ticket ticket = parkingLot.park(car);
        return ticket;
    }


    //凭票取车
    public Car fetch(Ticket ticket) throws FakeTicketException, UsedTicketException, NoTicketException {
        Car car = parkingLot.fetch(ticket);
        return car;
    }



    // 不聪明男孩，停车按顺序放,       问题：两种情况：1车库满；1车库不满
    public Ticket moreParkLots(Car car6) throws CarHasBeenParkedException, NullCarException, NoPositionException {
         for(ParkingLot parkingLot:parkingLots){
             if(parkingLot.getParkingSpaceCount()>0){
                 Ticket ticket=parkingLot.park(car6);
                 ticketParkinglot.put(ticket,parkingLot);
                 return ticket;
             }
         }
         return null;
    }



    //取车
    public Car fetchWithMoreParkingplots(Ticket ticket) throws FakeTicketException, UsedTicketException, NoPositionException, NoTicketException {
        return ticketParkinglot.get(ticket).fetch(ticket);
    }


    //聪明男孩，停车找空闲最多放

}
