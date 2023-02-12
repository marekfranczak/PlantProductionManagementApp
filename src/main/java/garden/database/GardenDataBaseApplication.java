package garden.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class. It contains the method responsible for launching the application.
 */
@SpringBootApplication
public class GardenDataBaseApplication {

	/**
	 * Main method of application.
	 * @param args Args from main method.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GardenDataBaseApplication.class, args);
	}

}
