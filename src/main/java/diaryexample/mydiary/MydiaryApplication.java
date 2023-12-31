package diaryexample.mydiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MydiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydiaryApplication.class, args);
	}

}
