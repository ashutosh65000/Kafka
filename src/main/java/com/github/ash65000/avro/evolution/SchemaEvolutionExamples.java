package com.github.ash65000.avro.evolution;

import java.io.File;
import java.io.IOException;

import com.example.CustomerV1;
import com.example.CustomerV2;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class SchemaEvolutionExamples {
    public static void main(String[] args) {

        // BACKWARD COMPATIBILITY READ
        // deal with v1 of our customer
        CustomerV1 customerv1 = CustomerV1.newBuilder().setFirstName("XY").setLastName("Z").setAge(22).setWeight(75.6f)
                .setHeight(180f).build();
        System.out.println("Customer V1 = " + customerv1.toString());
        // writing data to a file
        final DatumWriter<CustomerV1> datumWriter = new SpecificDatumWriter<>(CustomerV1.class);
        try (DataFileWriter<CustomerV1> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customerv1.getSchema(), new File("customer-v1.avro"));
            dataFileWriter.append(customerv1);
            System.out.println("Written customer-v1.avro");
            dataFileWriter.close();
        } catch (IOException e) {
            System.out.println("Couldn't write file");
            e.printStackTrace();
        }
        // reading data from the file using the v2 version of schema
        System.out.println("Reading customerv1.avro using v2 schema");
        final File file = new File("customer-v1.avro");
        final DatumReader<CustomerV2> datumReader = new SpecificDatumReader<>(CustomerV2.class);
        CustomerV2 customerv2read;
        try (DataFileReader<CustomerV2> dataFileReader = new DataFileReader<>(file, datumReader)) {
            while (dataFileReader.hasNext()) {
                customerv2read = dataFileReader.next();
                System.out.println("Successfully read avro file");
                System.out.println("Customer V2= " + customerv2read.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("#********************** Backward Compatibility success!!! **********************#");
    }
}
