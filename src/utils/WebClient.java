package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebClient {

	private String url = "http://localhost/website/projet%20dev%20java/";
	private HttpClient client;
	private HttpRequest request;
	private final String PATH_REGISTER = "/api/register.php";
	private final String PATH_LOGIN = "/api/login.php";
	
	public WebClient() {
		client = HttpClient.newHttpClient();
	}
	
	public int register(String email, String name , String password) throws IOException, InterruptedException {
		String data = "nom=" + URLEncoder.encode(name, StandardCharsets.UTF_8) +
        			  "&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8) +
        			  "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);
		
		
		request = HttpRequest.newBuilder()
				.uri(URI.create(this.url+this.PATH_REGISTER))
				.header("Content-Type", "application/x-www-form-urlencoded")
				.POST(BodyPublishers.ofString(data))
				.build();
		
		 HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		 
		 System.out.print(response.statusCode());
		 
		 return 0;
	}
	
	public String login(String email, String password) throws IOException, InterruptedException {
        String data = "email=" + URLEncoder.encode(email, StandardCharsets.UTF_8) +
                      "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url + this.PATH_LOGIN))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.body();
    }
	
	
}
