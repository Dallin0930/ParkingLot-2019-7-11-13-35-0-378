package com.thoughtworks.tdd;

import java.util.ArrayList;

public class Manager extends ParkingBoy{

    ArrayList<ParkingBoy>  parkingBoys =new ArrayList<>();

    ArrayList<ParkingLot> parkingLots =new ArrayList<>();


    public ArrayList<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public ArrayList<ParkingBoy> addParkingboys(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
        return parkingBoys;
    }




    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }


    public ArrayList<ParkingLot> addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
        ArrayList<ParkingLot>  parkingLotsResult=parkingLots;
        return parkingLotsResult;
    }
}
