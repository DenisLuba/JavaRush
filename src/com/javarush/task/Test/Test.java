package com.javarush.task.Test;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p = new Person();
        p.setAge(20);
        p.setName("Joe");

        Address a = new Address();
        a.setHouseNumber(1);

        Employee e = new Employee();
        e.setPerson(p);
        e.setAddress(a);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(e);
        objectOutputStream.flush();
        objectOutputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Employee e2 = (Employee) objectInputStream.readObject();
        objectInputStream.close();

        assertTrue(
                e2.getPerson().getAge() == e.getPerson().getAge());
        assertTrue(
                e2.getAddress().getHouseNumber() == e.getAddress().getHouseNumber());
    }

    public static class Employee implements Serializable {
        private transient Address address;
        private Person person;

        // setters and getters


        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.defaultWriteObject();
            oos.writeObject(address.getHouseNumber());
        }

        private void readObject(ObjectInputStream ois)
                throws ClassNotFoundException, IOException {
            ois.defaultReadObject();
            Integer houseNumber = (Integer) ois.readObject();
            Address a = new Address();
            a.setHouseNumber(houseNumber);
            this.setAddress(a);
        }
    }
    public static class Person implements Serializable {
        private int age;
        private String name;
        private Address country; // must be serializable too

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getCountry() {
            return country;
        }

        public void setCountry(Address country) {
            this.country = country;
        }
    }

    public static class Address {
        private int houseNumber;

        public int getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
        }

        // setters and getters
    }
}