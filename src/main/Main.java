package main;


import java.io.IOException;
import utils.WebClient;


public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		WebClient reg = new WebClient();
		reg.register("test2@3il.fr", "matteo", "test3");
		
		WebClient log = new WebClient();
        String response = log.login("test2@3il.fr", "test3");
        System.out.println("Réponse du serveur : " + response);
	}
		
}