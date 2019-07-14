package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private Object Ticket;

    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws FakeTicketException, UsedTicketException, NoTicketException {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);
        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_corresond_car_when_park_car_to_parking_lot_then_get_correspond_car_back() throws FakeTicketException, UsedTicketException, NoTicketException {
        //given
        Car firstcar = new Car();
        Car secondcar = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        Ticket firstticket = parkingBoy.park(firstcar);
        Car fetchedfirstCar = parkingBoy.fetch(firstticket);

        Ticket secondticket = parkingBoy.park(secondcar);
        Car fetchedsecondCar = parkingBoy.fetch(secondticket);
        //then
        assertSame(firstcar, fetchedfirstCar);
        assertSame(secondcar, fetchedsecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_fake() throws Exception, FakeTicketException, NoTicketException, UsedTicketException {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Ticket fakeTicket = new Ticket();
        //when
        Ticket trueticket = parkingBoy.park(car);
        Car truefetchedticket=parkingBoy.fetch(trueticket);
        //then
        Assertions.assertThrows(FakeTicketException.class, () -> parkingBoy.fetch(fakeTicket), "Unrecognized parking ticket.");
    }
}