package com.thoughtworks.tdd;
import com.thoughtworks.tdd.Ticket;
public class Parkingboy {

    protected  ParkingLot parkinglot;

    public Ticket parkCarToParkingLot(Car car) {          //停车获取票
        Ticket ticket = new Ticket();
        parkinglot.getTicketForcar().put(ticket,car);
        return ticket;  
    }



    public Car fetchCarWithTicket(Ticket ticket) {        //凭票取车(票错误/票过期/无票)
        Car car=parkinglot.getTicketForcar().get(ticket);
        return car;
    }


}
