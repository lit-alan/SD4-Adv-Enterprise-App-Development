# Lab Exercise Two :sunglasses:

1. Test all the CRUD operations from the code listing for Lecture Two. Remember only _**GET**_ requests can be tested through the browser. The others will have to be tested using [Postman](https://www.postman.com/downloads/) or an equilavent.

2. Read the following [tutorial](https://www.baeldung.com/spring-data-derived-queries) on derived queries and then add the following functionality.
    
    :heavy_check_mark:  Add functionality that will retrieve a [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) of _Author_ objects where each object has a _firstName_ that starts with a specific **_prefix_**. For example:
    
      ![](/images/prefix.JPG) 
      
      --------------
    
    :heavy_check_mark:   Add functionality that will retrieve a [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) of _Author_ objects where each object has a _firstName_ that ends with a specific **_suffix_**. For example:
    
      ![](/images/suffix.JPG)

    -------------- 

    :heavy_check_mark:  Add functionality that will retrieve a [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) of _Author_ objects where each object has a _firstName_ that contains a specific **_infix_**. For example:
    
      ![](/images/infix.JPG)
      
      --------------
      
    :heavy_check_mark:     Add functionality that will retrieve a [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) of _Author_ objects who were _**born between**_ a specified year range. The list should be _**ordered by firstName**_. For example:
     
      ![](/images/bornBetween.JPG)
      
    --------------
    
    :heavy_check_mark:  Add functionality that will retrieve a [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) of _Author_ objects who were _**born prior**_ to a specified year. For example:
    
      ![](/images/bornBefore.JPG)
      
    --------------

    :heavy_check_mark:     Add functionality that will determine and retrieve the _**average yearnBorn**_ for all Authors. For example:
     
      ![](/images/avgYearBorn.JPG)
      

    
