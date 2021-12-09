package dany.fmi.master.BooksApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
@ComponentScan
public class BooksAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksAppApplication.class, args);
	}

}
