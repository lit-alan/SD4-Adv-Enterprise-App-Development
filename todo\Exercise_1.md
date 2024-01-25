## Create a Search Facility for the Books Database :smiley:

### Introduction
This guide outlines the steps to create a search facility for the books table in the Books database, which you are familiar with from class.

### Steps to Follow

**1. Project Setup**
   - Create a **new project** using the Spring Initializr. Ensure the packaging is a **Jar** file. Include dependencies such as Spring Boot Dev Tools, Lombok, Spring Web, Thymeleaf, H2 Database, and Spring Data JPA. For example:
   ![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/8b8d18aa-323c-49d1-81d6-852f05840cfd)
 

**2. Additional Dependencies**
   - Manually add the following dependencies to the POM (as they can't be added using the Initializr):

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
 ```

**3. Configure the application.properties file** with the following:
   
```properties
spring.datasource.url=jdbc:h2:mem:books;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=alan.ryan
spring.datasource.password=password

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.path=/h2-console
spring.h2.console.enabled=true
spring.mvc.validation.enabled=true
```

**4. Class and Package Setup**
   - Add classes such as [Book entity](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Lectures/L2/L2_Validation/src/main/java/sd4/com/model/Book.java), [Repository](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Lectures/L2/L2_Validation/src/main/java/sd4/com/repository/BookRepository.java), [Service](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Lectures/L2/L2_Validation/src/main/java/sd4/com/service/BookService.java), and [validators](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/tree/master/Lectures/L2/L2_Validation/src/main/java/sd4/com/validators) from the source code for Lecture 2.
   - Update the application class with an [autowired reference to the BookService class](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Lectures/L2/L2_Validation/src/main/java/sd4/com/application/L2_Validation.java). Implement the CommandLineRunner interface and add the following code to override the run method:
   ```java
   @Override
    public void run(String... args) throws Exception {
        Date startDate = new GregorianCalendar(2007, Calendar.JANUARY, 1).getTime();
        Date today = new Date();
        Faker faker = new Faker();

        /add 100 Book objects with random/fake data to database
        for (int i = 1; i <=100 ; i++) {
            bookService.saveBook(new Book(Long.valueOf(i), faker.book().title(), faker.book().publisher(),faker.date().between(startDate, today) , faker.number().randomDouble(2,5,35), faker.code().isbn13(true) ,faker.number().numberBetween(1,15)));
        }

    }

   ```
**5. Create a Controller**
   - Implement the necessary endpoints for a search facility in the books table.

**6. Design the Views**
   - Create the necessary view(s) for the search facility.

**7. Styling**
   - A suggestion would be to use the [CSS](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/blob/master/Lectures/L2/L2_Validation/src/main/resources/static/assets/css/style.css) that I used for L1 and L2. 

**Final Notes**
   - Ensure to scan (in the main class) the packages where the controller, entity class, service, and repository are located.
