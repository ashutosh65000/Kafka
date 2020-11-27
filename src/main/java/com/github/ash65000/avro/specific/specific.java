package com.github.ash65000.avro.specific;

import com.example.Customer;

public class specific {
    public static void main(String[] args) {
        Customer.Builder customerBuilder = Customer.newBuilder();
        customerBuilder.setAge(25);
        customerBuilder.setFirstName("Ashutosh");
        customerBuilder.setLastName("Srivastava");
        customerBuilder.setHeight(173.9f);
        customerBuilder.setWeight(75.5f);
        Customer customer = customerBuilder.build();
        System.out.println(customer);
    }
}
