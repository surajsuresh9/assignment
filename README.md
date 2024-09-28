# Assignment

Software required:
-----------------
Java (JDK 17 or higher)
Maven (3.9.2 or higher)
Postman 

Getting Started
----------------
- Please download the zip/ clone the project
- Build the project (mvn clean install)
  <> You should see a similar output
```  
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  23.785 s
[INFO] Finished at: 2024-09-28T00:04:23+05:30
[INFO] ------------------------------------------------------------------------
```
- Go into the target directory and run ***java -jar assignment-0.0.1-SNAPSHOT.jar***
    The app runs on Tomcat port 8083 (default)
    If all goes well, you should see the server logs, something similar to this

  ```
      2024-09-28T00:07:33.833+05:30  INFO 40840 --- [ciValue-eCommerce] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8083 (http) with context path '/'
      2024-09-28T00:07:33.874+05:30  INFO 40840 --- [ciValue-eCommerce] [           main] c.i.InternalServiceApplication           : Started InternalServiceApplication in 6.852 seconds (process running for 7.632)
      2024-09-28T00:07:34.249+05:30  INFO 40840 --- [ciValue-eCommerce] [           main] c.i.InternalServiceApplication           : Database initialized with Products!
      2024-09-28T00:07:34.395+05:30  INFO 40840 --- [ciValue-eCommerce] [           main] c.i.InternalServiceApplication           : Database initialized with Shoppers!
  ```

- The database is populated with data from products.json and shelves.json

View H2 Database
----------------
- This database can be viewed through a web browser on the H2 console
  on http://localhost:8083/h2-console
  
- The following connection string can be used
  JDBC URL: jdbc:h2:mem:eCommerce
  username: root
  password: root

- The following Tables can be viewed from the console: *PRODUCT_TBL*, *SHELF_TBL*, *SHOPPER_TBL*
  
Open API Docs
-------------
- YAML: http://localhost:8083/api-docs.yml
- JSON: http://localhost:8083/api-docs

Swagger UI
----------
- http://localhost:8083/swagger-ui/index.html

Metrics
-------
- The API response time metrics can be found at 
```http://localhost:8083/actuator/metrics/{timer.name}```
- timer.name = "getProductsByShopper.timer", method = getProductsByShopper
- timer.name = "addProduct.timer", method = addProduct
- timer.name = "getShoppersByProduct.timer", method = getShoppersByProduct
- timer.name = "addShopper.timer", method = addShopper

Tools used
----------
- Java 17
- Spring Boot (3.x.x)
- Maven
- H2 database (Chose this instead of MySQL as its easier to setup with lesser overheads)
