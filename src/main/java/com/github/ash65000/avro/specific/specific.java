package com.github.ash65000.avro.specific;

import java.io.File;
import java.io.IOException;

import com.example.Customer;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

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

        // writing to a file
        final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);
        try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-specific.avro"));
            dataFileWriter.append(customer);
            System.out.println("Written customer-specific.avro");
            dataFileWriter.close();
        } catch (IOException e) {
            System.out.println("Couldn't write file");
            e.printStackTrace();
        }

        // reading from a file
        final File file = new File("customer-specific.avro");
        final DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
        Customer customerRead;
        try (DataFileReader<Customer> dataFileReader = new DataFileReader<>(file, datumReader)) {
            while (dataFileReader.hasNext()) {
                customerRead = dataFileReader.next();
                System.out.println("Successfully read avro file");
                System.out.println(customerRead.toString());

                // Step 4: interpret as a generic record
                // get the data from the generic record
                System.out.println("First name: " + customerRead.get("first_name"));
                // float height = customerRead.getHeight();
                // System.out.println(height);
                Float height = customerRead.getHeight();
                System.out.println(Float.toString(height));
                // read a non existent field
                // System.out.println("Non existent field: " + customerRead.get("not_here"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
