## Todays Snippets :point_down:
1. [Maven Dependencies](#dependencies)
2. [Product.java](#productjava) 
3. [ProductService.java](#productservicejava) 
4. [allProducts.html](#allProductshtml)
5. [addProduct.html](#addproducthtml) 

## Dependencies
```XML
 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf</artifactId>
            <version>3.1.1.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-springsecurity6</artifactId>
        </dependency>
    </dependencies>
```
## Product.java
```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    
    private String code;
    private String name;
    private String description;
    private double buyPrice;
    private double sellPrice;
    private int quantityInStock;

}
```
## ProductService.java
```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lit.sd4.model.Product; //you may have to change this import depending on where you store the Product class
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    static List<Product> productList = new ArrayList();

    static {
        productList.add(new Product("XG809", "Contemporary Office Desk", "Concentrating on the job is a lot easier when everyone has a place that’s comfortable to work at.", 25.00, 69.00, 18));
        productList.add(new Product("BG565", "Bed Frame With Storage", "With the comfort and quality you get from our sturdy single beds, you’ll wake up refreshed and ready to roll. ", 139.00, 175.00, 5));
        productList.add(new Product("PO262", "TV Stand", "Our TV stands and TV cabinets are there to cut the clutter and get things organised.", 69.99, 89.99, 120));
        productList.add(new Product("MC342", "Kitchen Unit", "They make the most of your wall by giving you extra storage, and the right kitchen shelf can boost the style of your decor too", 23.00, 65.99, 89));
        productList.add(new Product("WS341", "Folding Chair", "You can fold the chair, so it takes less space when you're not using it.", 12.00, 35.99, 30));
        productList.add(new Product("TF875", "Berkant Kitchen", "Express yourself in the place where all of life’s daily activities take place.in our stylish, yet personalised kitchen..", 8900.00, 12200.99, 4));

    }

    public List<Product> getAllProducts() {
        return productList;
    }// end getAllProducts

    
    public boolean addAProduct(Product p) {
        return productList.add(p);
    }
    
    public void deleteAProduct(String code) {
        
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getCode().equalsIgnoreCase(code)) {
                iterator.remove();
            }
        }
    }//end deleteAProduct

}//end class ProductService
```


## allProducts.html
```html
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Products</title>
</head>
<body>
<table style="width:100%">
    <tr>
        <th align="left">Code</th>
        <th align="left">Name</th>
        <th align="left">Description</th>
        <th align="left">Buy Price</th>
        <th align="left">Sell Price</th>
        <th align="left">Qty</th>
        <th align="left">Actions</th>
    </tr>
    <tr th:each="product: ${productList}">
        <td><span th:text="${product.code}"></span></td>
        <td><span th:text="${product.name}"></span></td>
        <td><span th:text="${product.description}"></span></td>
        <td><span th:text="${product.buyPrice}"></span></td>
        <td><span th:text="${product.sellPrice}"></span></td>
        <td><span th:text="${product.quantityInStock}"></span></td>
        <td></td>
    </tr>
</table>
</body>
</html>
```


## addProduct.html
```html
<!DOCTYPE html>
<html>
<head><title>Add a Product</title></head>
<body>


<form th:action="@{/addProduct}" th:object="${aProduct}" method="post">
    <table>
        <tr>
            <td>Code</td>
            <td><input type="text" th:field="*{code}" name="title"/></td>
            <td style="color:red"><span th:if="${#fields.hasErrors('code')}" th:errors="*{code}" /></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" th:field="*{name}" name="title"/></td>
            <td style="color:red"><span th:if="${#fields.hasErrors('name')}" th:errors="*{name}" /></td>
        </tr>
        <tr>
            <td>Description</label></td>
            <td><input type="text" th:field="*{description}" name="title"/></td>
            <td style="color:red"><span th:if="${#fields.hasErrors('description')}" th:errors="*{description}" /></td>
        </tr>
        <tr>
            <td>Buy Price</label></td>
            <td><input type="text" th:field="*{buyPrice}" name="title"/></td>
            <td style="color:red"><span th:if="${#fields.hasErrors('buyPrice')}" th:errors="*{buyPrice}" /></td>
        </tr>
        <tr>
            <td>Sell Price</label></td>
            <td><input type="text" th:field="*{sellPrice}" name="title"/></td>
            <td style="color:red"><span th:if="${#fields.hasErrors('sellPrice')}" th:errors="*{sellPrice}" /></td>
        </tr>

        <tr>
            <td>Quantity In Stock</label></td>
            <td><input type="text" th:field="*{quantityInStock}" name="title"/></td>
            <td style="color:red"><span th:if="${#fields.hasErrors('quantityInStock')}" th:errors="*{quantityInStock}" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add Product"/></td>
        </tr>
    </table>
</form>
</body>
</html>
```

