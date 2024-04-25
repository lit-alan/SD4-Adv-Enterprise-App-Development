## Create a Spring Boot project and use todays snippets to build your solution. :neutral_face:

1. [Maven Dependencies](#dependencies)
2. [User.java](#userjava) 
3. [PublicController.java](#publiccontrollerjava) 
4. [UserController.java](#usercontrollerjava)
5. [Enable Security Debugging](#debugging) 

## Dependencies
```XML
    <dependencies>
      <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
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
    </dependencies>

```

## User.java
```java

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String username;
    private String password;
    private String roles;
    private String profilePic;
    private String bio;

}
```

## PublicController.java
```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
public class PublicController {


    @GetMapping("/welcome")
    public String publicWelcome() {
        return "Welcome to the public area";
    }

    @GetMapping("/common")
    public String publicCommonArea() {
         return "Welcome to the common area";
    }
}
```

## UserController.java
```java
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sd4.src.model.User;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/profile")
public class UserController {

    @GetMapping("")
    public String username() {
        return "username for " + getLoggedInUsername()  + " with a role(s) of " + getUserRoles();
    }

    @PatchMapping("")
    public String updateProfile(@RequestParam String newBio) {
        return "Change bio for " + getLoggedInUsername() + " to " + newBio  + " with a role(s) of " + getUserRoles();
    }

    @DeleteMapping("")
    public String deleteProfile() {
        return "Delete Profile " + getLoggedInUsername()  + " with a role(s) of " + getUserRoles();
    }

    @PutMapping("")
    public String editProfile(@RequestBody User updatedUser) {
        return updatedUser + "\n\nProfile edited by " + getLoggedInUsername() + " with a role(s) of " + getUserRoles();
    }

    @PostMapping("")
    public String createProfile(@RequestBody User newUser) {
     return newUser + "\n\nProfile created by " + getLoggedInUsername() + " with a role(s) of " + getUserRoles();
    }

    @GetMapping("/picture")
    public String getProfilePicture() {
        return "Get Profile Picture for " + getLoggedInUsername() + " with a role(s) of " + getUserRoles();
    }

    @PostMapping("/picture")
    public String uploadProfilePicture() {
        return "Upload Profile Picture for " + getLoggedInUsername() + " with a role(s) of " + getUserRoles();
    }

    @GetMapping("/security")
    public String getSecuritySettings() {
        return "Get Security Settings for " + getLoggedInUsername() + " with a role(s) of " + getUserRoles();
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public String getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));
        return "Roles: " + roles;
    }
}
```

## Debugging
```properties
#Optionally add this line to your .properties file to enable Security Debugging in your application
logging.level.org.springframework.security=DEBUG
```
