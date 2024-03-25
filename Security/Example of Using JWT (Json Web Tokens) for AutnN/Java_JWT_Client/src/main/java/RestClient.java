import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    private static final String AUTH_URL = "http://localhost:8080/authenticate";
    private static final String PROTECTED_URL = "http://localhost:8080/hello";

    public static void main(String[] args) {
        try {
            // Authenticate and get JWT token
            String token = authenticate();
            if (token != null) {
                // Access protected resource with JWT token
                accessProtectedResource(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String authenticate() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(credentials)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Map<?, ?> body = objectMapper.readValue(response.body(), Map.class);
            System.out.println("Authentication successful.");
            return (String) body.get("jwt");
        } else {
            System.out.println("Authentication failed.");
            return null;
        }
    }

    private static void accessProtectedResource(String token) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PROTECTED_URL))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Protected resource accessed successfully:");
            System.out.println(response.body());
        } else {
            System.out.println("Failed to access protected resource.");
        }
    }
}
