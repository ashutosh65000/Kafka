#### Use the avro-tools jar to perform operations. This jar is internally used by the Kafka for it's tasks and manipulations.

##### On MacOS/Linux -->
`wget https://repo1.maven.org/maven2/org/apache/avro/avro-tools/1.8.2/avro-1.8.2.jar`
##### On Windows -->
`curl https://repo1.maven.org/maven2/org/apache/avro/avro-tools/1.8.2/avro-1.8.2.jar -output avro-1.8.2.jar`

##### Commands
* to print data in JSON format          `java -jar ~/avro-1.8.2.jar tojson --pretty <avro-file>`
* to print the schema of the avro file  `java -jar ~/avro-1.8.2.jar getschema --pretty <avro-file>`
