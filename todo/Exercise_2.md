## Create and localise a drilldown feature for the Book entity 👇:

### Introduction
This guide outlines the steps to create and localise a drilldown for the books entity in the Books database, which you are familiar with from class. You will need to use the source code for [Lecture 4](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/tree/master/Lectures) as a starting point.

### Steps to Follow

**1. Replace the Book entity class with the following:**
   
```java
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@ValidIsbnForPublicationDate(message="{book.invalidISBNForDate}")
@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Long bookId;
    
    @NotBlank(message = "{book.titleNotEmpty}")
    @NonNull
    private String title;
   
    @NotBlank(message="{book.publisherNotEmpty}")
    @Size(min = 5, message="{book.publisherSize}")
    @NonNull
    private String publisher;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private Date publicationDate;
    
    @NotNull(message="{book.priceNull}")
    @DecimalMin("1.0")
    @NonNull
    private Double price;
    
    @NotBlank(message="{book.isbnNull}")
    @ValidISBN(message="{book.isbnNull}")
    @NonNull
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "authorID", referencedColumnName = "authorID")
    @ToString.Exclude
    private Author author; // Each book has one author

}
```
 

**2. Add an Author entity class to the project**
```java
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Author  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Long authorID;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private int yearBorn;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private List<Book> books; // One author can have many books
}
```
_note the relationships between the two entity classes_

**3. Create a repository and service for the Author entity**

**4. Autowire a reference to the author service into the Application class**

**5. Replace the _run_ method in the Application class with the following:**
   
```java
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        Author[] authors = new Author[5];

        //generate and persist the Author entities
        for (int i = 0; i <authors.length ; i++) {
            authors[i] = new Author(Long.valueOf(i), faker.name().firstName(), faker.name().lastName(), faker.number().numberBetween(1951, 2001));
            authorService.saveAuthor(authors[i]);
        }

        //generate and persist the Book entities
        Date startDate = new GregorianCalendar(2007, Calendar.JANUARY, 1).getTime();
        Date today = new Date();


        for (int i = 1; i <=10 ; i++) {
            Book b = new Book(Long.valueOf(i), faker.book().title(), faker.book().publisher(),faker.date().between(startDate, today) , faker.number().randomDouble(2,5,35), faker.code().isbn13(true));
            b.setAuthor(authors[faker.number().numberBetween(1, 5)]);
            bookService.saveBook(b);

        }
    }
```

**6. Add a method to the Book controller that will facilitate a drilldown**
   
**7. Add a button (in yellow below) to the home page (viewAll.html) to allow the user to drill down on a book**
![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/34e8d04a-a3f4-48e7-8e8a-40f9020cfee7)


**8. Create a new view that will not only display information about the selected book, but also details on the author who wrote it, I should be able to display this page in English/French/Spanish**

![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/d73a9316-fc7e-4749-a124-efc6e6a867f3)



**9. Update the three resource bundles with translations so that the new view will appear localised when the user switches languages/regions**

**10. As an added extra try to display in a list any of the other books that the selected author has written. Each book title should be a link that will drill down on the author who wrote it**
![image](https://github.com/lit-alan/SD4-Adv-Enterprise-App-Development/assets/4732629/2a3ced1e-87aa-4eef-a257-903d5d3b8484)

_This is the page localised in Spanish for the country of Spain_



**Final Note**
   - It is hugely important that you not only attempt but understand how this exercise works.
