package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    private Object Ticket;

    @Test
      public void should_return_origincar_when_parkcar_and_fetchcar_by_ticket() {
          //Given
          Car car=new Car();
          Parkingboy pkBoy=new Parkingboy();

          //When
          Ticket ticket=pkBoy.parkCarToParkingLot(car);     // 停车,得到票
          Car fetchedCar=pkBoy.fetchCarWithTicket(ticket);   // 根据票取车

          //Then
          assertEquals(car,fetchedCar);
      }




    @Test
    public void should_rteurn_correspondcar_when_parkcar_and_fetch_correspondcar_by_ticket(){
          //Given
         Car firstCar=new Car();
         Car secondCar=new Car();
         Parkingboy pkBoy=new Parkingboy();
        //When
        Ticket firstticket=pkBoy.parkCarToParkingLot(firstCar);     // 停车,得到票1
        Car firstfetchedCar=pkBoy.fetchCarWithTicket(firstticket);   // 根据票1取车

        Ticket secondticket=pkBoy.parkCarToParkingLot(secondCar);     // 停车,得到票2
        Car secondfetchedCar=pkBoy.fetchCarWithTicket(secondticket);   // 根据票2取车

        //Then
        assertEquals(firstCar,firstfetchedCar);
        assertEquals(secondCar,secondfetchedCar);
    }



    @Test    //错误车票，无车可取，抛出异常，显示提示信息（story1 & story2）
    public void should_return_no_car_be_fetched_and_send_message_when_given_a_wrong_ticket(){
          //Given
          Car car=new Car();
          Parkingboy pkboy=new Parkingboy();
          //When
        Exception exception = assertThrows(Exception.class, () -> {
            // when
            Ticket ticket = new Ticket();
            pkboy.fetchCarWithTicket(ticket);
        });
        //Then
        assertEquals("Unrecognized parking ticket",exception.getMessage());
    }



    @Test   //无票，无车可取，抛出异常，显示提示信息（story1 & story2）
    public void should_return_no_car_be_fetch_and_send_message_when_dosenot_provid_ticket(){

    }

    @Test  //过期车票，无车可取，抛出异常，显示提示信息（story1 & story2）
    public void should_return_no_car_be_fetch_and_send_message_when_provided_a_used_ticket(){

    }

    @Test
    public void should_return__no_car_be_fetch_and_send_message_when_parkinglot_is_full(){

    }







}
