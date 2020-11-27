package com.github.ash65000.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.*;

class GenericRecordExamples {
    public static void main(String[] args) {
        // Step 0: define schema
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse("{\n" + "     \"type\": \"record\",\n" + "     \"namespace\": \"com.example\",\n"
                + "     \"name\": \"Customer\",\n" + "     \"fields\": [\n"
                + "       { \"name\": \"first_name\", \"type\": \"string\", \"doc\": \"First Name of Customer\" },\n"
                + "       { \"name\": \"last_name\", \"type\": \"string\", \"doc\": \"Last Name of Customer\" },\n"
                + "       { \"name\": \"age\", \"type\": \"int\", \"doc\": \"Age at the time of registration\" },\n"
                + "       { \"name\": \"height\", \"type\": \"float\", \"doc\": \"Height at the time of registration in cm\" },\n"
                + "       { \"name\": \"weight\", \"type\": \"float\", \"doc\": \"Weight at the time of registration in kg\" },\n"
                + "       { \"name\": \"automated_email\", \"type\": \"boolean\", \"default\": true, \"doc\": \"Field indicating if the user is enrolled in marketing emails\" }\n"
                + "     ]\n" + "}");
        // Step 1: create a generic record
        // we build our first customer
        GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema);
        customerBuilder.set("first_name", "John");
        customerBuilder.set("last_name", "Doe");
        customerBuilder.set("age", 26);
        customerBuilder.set("height", 175f);
        customerBuilder.set("weight", 70.5f);
        customerBuilder.set("automated_email", false);
        GenericData.Record myCustomer = customerBuilder.build();
        System.out.println(myCustomer);

        // default values
        GenericRecordBuilder customerBuilderWithDefault = new GenericRecordBuilder(schema);
        customerBuilderWithDefault.set("first_name", "John");
        customerBuilderWithDefault.set("last_name", "Doe");
        customerBuilderWithDefault.set("age", 26);
        customerBuilderWithDefault.set("height", 175f);
        customerBuilderWithDefault.set("weight", 70.5f);
        GenericData.Record customerWithDefault = customerBuilderWithDefault.build();
        System.out.println(customerWithDefault);

        // Step 2: write that generic record to a file

        // Step 3: read a generic record form a file

        // Step 4: interpret as a generic record
    }
}
