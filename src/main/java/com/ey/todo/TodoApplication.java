package com.ey.todo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;


import com.ey.todo.domain.Todo;
import com.ey.todo.domain.User;
import com.ey.todo.persistence.TodoRepository;
import com.ey.todo.persistence.UserRepository;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TodoApplication implements CommandLineRunner{

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository todoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setPassword("must be hashed");
		user.setUsername("EY_TEST_USER1");

		Todo todo1 = new Todo();
		todo1.setTitle("Todo Item");
		todo1.setDescription("Meet Laura");
		todo1.setLastUpdatedTime(LocalDateTime.now());


		Todo todo2 = new Todo();
		todo2.setTitle("Todo Item");
		todo2.setDescription("Design a Prototype");
		todo2.setLastUpdatedTime(LocalDateTime.now());

		Todo todo3 = new Todo();
		todo3.setTitle("Todo Item");
		todo3.setDescription("Schedule Meeting with Stakeholder");
		todo3.setLastUpdatedTime(LocalDateTime.now());

		Todo todo4 = new Todo();
		todo4.setTitle("Todo Item");
		todo4.setDescription("Finish Task Before deadline");
		todo4.setLastUpdatedTime(LocalDateTime.now());

		todoRepository.save(todo1);
		todoRepository.save(todo2);
		todoRepository.save(todo3);
		todoRepository.save(todo4);


		user.setTodoList(Arrays.asList(todo1,todo2,todo3,todo4));

		userRepository.save(user);
	}



	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2).select().
				 apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))).
				apis(RequestHandlerSelectors.basePackage("com.ey")).
				build().
				apiInfo(apiDetails());
	}
	
	
	private ApiInfo apiDetails() {
		
		return new ApiInfo(
				"Online TODO List",
				"APIs for Todo",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Naveen Kumar", "https://github.com/naveenvivek", "naveenvivek@outlook.com"),
				"API License",
				"https://github.com/naveenvivek",
				Collections.emptyList());
		
	}

}
