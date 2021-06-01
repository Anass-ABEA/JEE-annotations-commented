package ma.ac.emi.g.info.EmiApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class EmiApplication {
	@Autowired
	PersonneRepo pr;

	public static void main(String[] args) {
		SpringApplication.run(EmiApplication.class, args);
	}

	@Bean
	public CommandLineRunner console(){
		return args-> {
			System.out.println("COUCOU");
			pr.save(new Student(1234L,"Kabbaj","Mohammed Issam", LocalDate.of(1990,1,1),1,false));
			pr.save(new Personne(1235L,"Belouadha", "Fatima-Zahra",LocalDate.of(1992,1,1)));
			System.out.println(pr.findAll());
			System.out.println("findByNom");
//			System.out.println(pr.findByNom("Kabbaj"));
			new Scanner(System.in).next();
		};
	}

}
