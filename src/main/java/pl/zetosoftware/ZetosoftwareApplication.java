package pl.zetosoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class ZetosoftwareApplication {


	public static void main(String[] args)
	{
		SpringApplication.run(ZetosoftwareApplication.class, args);
	}
}