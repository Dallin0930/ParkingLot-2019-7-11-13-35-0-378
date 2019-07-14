package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
//private Object Ticket;

@Test    //停车取车，一辆
public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws FakeTicketException, UsedTicketException, NoTicketException, CarHasBeenParkedException, NullCarException, NoPositionException {
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

@Test  //停车取车，多辆
public void should_return_corresond_car_when_park_car_to_parking_lot_then_get_correspond_car_back() throws FakeTicketException, UsedTicketException, NoTicketException, CarHasBeenParkedException, NullCarException, NoPositionException {
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

@Test   //取车，票假
public void should_not_fetch_car_when_ticket_is_fake() throws Exception, FakeTicketException, NoTicketException, UsedTicketException, NullCarException, CarHasBeenParkedException {
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

@Test   //取车，票过期
public void should_not_fetch_car_when_ticket_is_used() throws FakeTicketException, Exception, NoTicketException, UsedTicketException, NullCarException, CarHasBeenParkedException {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        //then
        Assertions.assertThrows(UsedTicketException.class, ()->parkingBoy.fetch(ticket),"Unrecognized parking ticket.");
        }


    @Test   //取车，票为空
    public void should_not_fetch_car_when_no_ticket() throws Exception, FakeTicketException, NullCarException, CarHasBeenParkedException {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        Assertions.assertThrows(NoTicketException.class, ()->parkingBoy.fetch(null));
    }



@Test  //停车，车位不够
public void should_not_park_car_when_parking_space_count_is_less_than_0() throws Exception, NullCarException, CarHasBeenParkedException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingBoy.park(car1);
        parkingBoy.park(car2);
        parkingBoy.park(car3);
        parkingBoy.park(car4);
        parkingBoy.park(car5);
        //then
        Assertions.assertThrows(NoPositionException.class, ()->parkingBoy.park(car6));
        }


    @Test  //停车，车已经停过
    public void should_not_park_car_when_car_has_been_parked() throws CarHasBeenParkedException, NoPositionException, NullCarException {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        parkingBoy.park(car);
        //then
        Assertions.assertThrows(CarHasBeenParkedException.class, ()->parkingBoy.park(car));
    }

    @Test   //停车，车为空
    public void should_not_park_car_when_car_is_null() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        //then
        Assertions.assertThrows(NullCarException.class, ()->parkingBoy.park(null));
    }

    @Test  //停车，不聪明男孩按停车场顺序停放,车库容量5
    public void should_not_park_car_when_parking_space_count_is_less_than_0_with_two_parkinglots() throws Exception, FakeTicketException, NullCarException, CarHasBeenParkedException, NoTicketException, UsedTicketException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();


        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        parkingBoy.moreParkLots(car1);
        parkingBoy.moreParkLots(car2);
        parkingBoy.moreParkLots(car3);
        parkingBoy.moreParkLots(car4);
        parkingBoy.moreParkLots(car5);

        Ticket ticket = parkingBoy.moreParkLots(car6);
        //then
        Car fetchedCar = parkingBoy.fetchWithMoreParkingplots(ticket);
        //then
        assertSame(car6, fetchedCar);
    }


    @Test  //停车，聪明男孩，停车找空闲最多放
    public void should_not_park_car_when_parking_space_count_is_less_than_0_with_smart_parking_boy() throws Exception, FakeTicketException, NullCarException, CarHasBeenParkedException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        //set the bigger parking spaces
        parkingLot2.setParkingSpaceCount(7);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        parkingBoy.moreParkLots(car1);
        ParkingLot parkingLotMax = parkingBoy.smartPark(car2);
        //then
        assertSame(parkingLot2, parkingLotMax);
    }

    @Test   //停车，超级聪明男孩，找各自车库空闲率最大的放
    public void should_not_park_car_when_parking_space_count_is_less_than_0_with_super_parking_boy() throws Exception, FakeTicketException, NullCarException, CarHasBeenParkedException {
        //given
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        parkingLot2.setParkingSpaceCount(7);

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();

        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);


        //when
        parkingBoy.superPark(car1);
        ParkingLot parkingLotMax = parkingBoy.superPark(car2);
        //then
        assertSame(parkingLot2, parkingLotMax);
    }

    @Test    ///经理添加男孩，获取男孩列表
    public void should_return_parkingboys_when_manager_add_parkingboy(){
        //given
        Manager manager = new Manager();
        ParkingBoy parkingBoy =new ParkingBoy();
        //when

        //then
        assertSame(manager.getParkingBoys(),manager.addParkingboys(parkingBoy));
    }


    @Test /////经理1、管理停车场；  2、停车
    public void should_return_parkingLots_when_manager_add_parkingLot() throws CarHasBeenParkedException, NullCarException, NoPositionException, FakeTicketException, UsedTicketException, NoTicketException {
         //Given
        Manager manager = new Manager();
        ParkingLot parkingLot=new ParkingLot();

        Car car = new Car();

        //When
         ArrayList<ParkingLot> result=manager.addParkingLot(parkingLot);

         Ticket ticket =manager.moreParkLots(car);
         Car fetchedCar=manager.fetchWithMoreParkingplots(ticket);
        //Then
        assertSame(manager.getParkingLots(),result);      //管理停车场
        assertSame(car,fetchedCar);                       //停车取车
    }



    @Test   //经理命令，男孩未执行，返回错误信息
    public void should_return_worong_message_when_parkingboy_failed_parking_car() throws CarHasBeenParkedException, NullCarException, NoPositionException {
        //given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingSpaceCount(2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Manager manager = new Manager();
        Car car = new Car();
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        ArrayList<ParkingBoy> parkingBoys = manager.addParkingboys(parkingBoy);
        parkingBoys.get(parkingBoys.size()-1).park(car);
        parkingBoys.get(parkingBoys.size()-1).park(car1);
        //then
        //exist confusing problems
        Assertions.assertThrows(Exception.class,()->parkingBoys.get(parkingBoys.size()-1).park(car2));
    }

}


