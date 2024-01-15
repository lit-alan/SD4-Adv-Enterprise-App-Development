## Snippets for 'Introduction To Spring' Live Coding Session: :point_down:

1. [Controller Method With Path Params](#controller-method-with-path-params)

2. [Messages For List](#messages-for-list)

3. [Greetings.html](#greetingshtml)

4. [Person.java](#personjava)

5. [REST Controller Method](#rest-controller-method)


### Controller Method With Path Params
```java
@GetMapping("/nice/{fname}/{age}")
@ResponseBody
public String saySomethingNice(@PathVariable(required = false) String fname,
                               @PathVariable(required = false) Integer age) {
    return "Hello " + fname + " you look well for someone aged " + age;
}

```

### Messages For List
```java
List<String> messages = new ArrayList();
messages.add("Dia duit domhan");
messages.add("Hello World");
messages.add("Hallo Welt");
messages.add("Bonjour le monde");
messages.add("Hola Mundo");
messages.add("Hej Verden");
messages.add("Привет, мир");
messages.add("Witaj świecie");
messages.add("Hallo Wereld");
messages.add("Olá Mundo");
```

### Greetings.html
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Welcome!</h1>
<tr th:each="message: ${list}">
    <td th:text="${message}" /> <br>
</tr>
</body>
</html>
```


### Person.java
```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    
    private String name;
    private String city;
    private int age;
}
```

### REST Controller Method
```java
public List<Person> getThePeople() {

   List<Person> peeps = new ArrayList();
   peeps.add(new Person("Dave", "Limerick", 25));
   peeps.add(new Person("Siobhan", "Limerick", 35));
   peeps.add(new Person("Aoife", "Cork", 45));

   return peeps;
}
```