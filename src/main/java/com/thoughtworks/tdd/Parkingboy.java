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

    public ParkingBoy() {

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


    //停车，聪明男孩，按空闲放
    public ParkingLot smartPark(Car car) throws NoPositionException, CarHasBeenParkedException, NullCarException {
        int bigCapacity = parkingLots.get(0).getParkingSpaceCount();
        ParkingLot parkingLotMax = parkingLots.get(0);
        for (ParkingLot parkingLot:parkingLots) {
            if (parkingLot.getParkingSpaceCount()>bigCapacity){
                bigCapacity = parkingLot.getParkingSpaceCount();
                parkingLotMax = parkingLot;
            }
        }
        Ticket ticket = parkingLotMax.park(car);
        ticketParkinglot.put(ticket,parkingLotMax);
        return parkingLotMax;
    }


    //停车，超级聪明男孩，按各自空闲率存放       问题：设置车库总容量，空闲车位数
    public ParkingLot superPark(Car car) throws NoPositionException, CarHasBeenParkedException, NullCarException {
        int bigCapacity = parkingLots.get(0).getParkingSpaceCount()/5;

        ParkingLot parkingLotMax = parkingLots.get(0);    //默认该车库Max率

        for (ParkingLot parkingLot:parkingLots) {
            if (parkingLot.getParkingSpaceCount()/5>bigCapacity){       //问题
                bigCapacity = parkingLot.getParkingSpaceCount();
                parkingLotMax = parkingLot;
            }
        }
        Ticket ticket = parkingLotMax.park(car);
        ticketParkinglot.put(ticket,parkingLotMax);
        return parkingLotMax;
    }






}
