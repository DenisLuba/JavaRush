package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    /*
    expected:
    {
        "className" : ".ParkingLot",
        "name" : "Super ParkingLot",
        "city" : "Kyiv",
        "vehicles" : [ {
            "className" : "com.javarush.task.task33.task3305.RacingBike",
            "name" : "Simba",
            "owner" : "Peter",
            "age" : 2
          }, {
            "className" : "com.javarush.task.task33.task3305.Motorbike",
            "name" : "Manny",
            "owner" : null
           }, {
            "className" : "com.javarush.task.task33.task3305.Car"
           } ]
     }
     */

    public static void main(String[] args) throws IOException {
        ParkingLot parkingLot = new ParkingLot("Super ParkingLot", "Kyiv");
        RacingBike racingBike = new RacingBike("Simba", "Peter", 2);
        Motorbike motorbike = new Motorbike("Manny");
        Car car = new Car();
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(racingBike);
        vehicles.add(motorbike);
        vehicles.add(car);
        parkingLot.setVehicles(vehicles);
        convertToJson(parkingLot);
    }

    public static void convertToJson(ParkingLot parkingLot) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, parkingLot);
        System.out.println(writer.toString());
    }
}
