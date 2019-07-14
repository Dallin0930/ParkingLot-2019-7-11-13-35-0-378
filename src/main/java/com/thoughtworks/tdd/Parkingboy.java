package com.thoughtworks.tdd;
import com.thoughtworks.tdd.Ticket;
public class ParkingBoy {

    protected  ParkingLot parkinglot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkinglot=parkingLot;
    }

    //停车得到票
    public Ticket park(Car car) {
     Ticket ticket = parkinglot.park(car);
     return  ticket;
    }


    //凭票取车
    public Car fetch(Ticket ticket) throws FakeTicketException, UsedTicketException, NoTicketException {
        Car car = parkinglot.fetch(ticket);
        return car;
    }
}
