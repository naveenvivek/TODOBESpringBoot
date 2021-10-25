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

		Todo todo = new Todo();
		todo.setTitle("Todo Item");
		todo.setDescription("Complete Assignment");
		todo.setLastUpdatedTime(LocalDateTime.now());


		Todo todo1 = new Todo();
		todo1.setTitle("Todo Item 1");
		todo1.setDescription("Complete Assignment 2");
		todo1.setLastUpdatedTime(LocalDateTime.now());

		Todo todo2 = new Todo();
		todo2.setTitle("Todo Item 1");
		todo2.setDescription("Complete Assignment 2");
		todo2.setLastUpdatedTime(LocalDateTime.now());

		Todo todo3 = new Todo();
		todo3.setTitle("Todo Item 1");
		todo3.setDescription("Complete Assignment 2");
		todo3.setLastUpdatedTime(LocalDateTime.now());

		todoRepository.save(todo);
		todoRepository.save(todo1);
		todoRepository.save(todo2);
		todoRepository.save(todo3);


		user.setTodoList(Arrays.asList(todo,todo1,todo2,todo3));

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
