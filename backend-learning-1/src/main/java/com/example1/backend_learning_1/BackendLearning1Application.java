package com.example1.backend_learning_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication is the main annotation for a Spring Boot app.
//		It tells Spring Boot to:
//		Start the application
//		Automatically configure required settings
//		Scan and find your components/classes
//It combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.

@SpringBootApplication
public class BackendLearning1Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendLearning1Application.class, args);
		System.out.println("Server running....");
	}

	//	Controller -> the entry point  for incoming web requests
	//	Browser sends request to /hello
	//	Controller receives the request
	//	Controller returns Hello World as a response
	@RestController
    static class HelloController {
//		@GetMapping("")
//		public String sayRoot(){
//			return "Hello Root page";
//		}
		@GetMapping({"", "/"}) //To map more than one same endpoint
			public String sayRoot(){
			return "<h1>Hello Root Page \"/\"</h1>";
		}
		@GetMapping("home")
		public String sayHome(){
			return "<h2>Hello Home Page</h2>";
		}
	}
	// @RestController tells Spring that a class handles web requests and sends data back, usually as JSON.
	// It is used to create REST APIs, such as:
	//	- Getting data with GET
	//	- Adding data with POST
	//	- Updating data with PUT
	//	- Deleting data with DELETE
	// It combines @Controller and @ResponseBody

	// @GetMapping -> get/read data
	// @PostMapping -> create/send new data
	// @PutMapping -> update all data
	// @PatchMapping -> update part of data (partial update)
	// @DeleteMapping -> delete data

	//	1. How does the browser talk to Java?
	//		- Through HTTP requests, handled by a web server
	//	2. How does Java understand URLs?
	//		- The web server/Spring processes the URL and Spring uses URL mappings
	//	3. Who starts the server?
	//		- `SpringApplication.run()` starts Spring Boot, which starts the embedded server
	//	4. Who listens on port 8080?
	//		- Usually embedded Tomcat
	//	5. How does `/hello` connect to our Java method?
	//		- `@GetMapping("/hello")` tells Spring to call that method
}


// COMPLETE FLOW
//	Step 1: Browser sends request to localhost:8080/hello
//	Step 2: Request reaches embedded Tomcat
//	Step 3: Spring MVC checks the available mappings
//			M = Model -> our data, like users, product etc
//			V = View -> what the user sees, such as HTML page or JSON response
//			C = Controller -> receives browser/API requests, runs logic, and returns a view or data
//	Step 4: /hello is matched with sayHello()
//	Step 5: sayHello() returns "Hello World"\
//	Step 6: Response goes back to browser
//	Step 7: Browser displays Hello World


// IF BROWSER SHOWS "404 Not Found", CHECK -
// 1. did spring detect my controller?
// 2. is the package structure correct?
// 3. did component scanning happen?
// 4. was the bean created?
// 5. did Spring MVC registered the mapping?
// 6. is the url correct?
// 7. did Tomcat receive the request?

// IF APPLICATION STARTS SLOWLY, could be -
//	1. Too many BEANs being created
//	2. Database connection taking time
//	3. Auto Configuration doing extra work
//	4. Heavy startup logic running
//	5. External service call blocking startup

// CONTROLLER DOES NOT RECEIVE REQUEST, possible reasons:
//	1. Wrong port
//	2. Wrong url
//	3. Application not running
//	4. Controller not detected
//	5. Incorrect annotation
//	6. context path configured
//	7. Request blocked by security

// BEAN is an object that Spring creates and manages for us.
// Spring can create it, configure it, and give it to other classes when needed.

// COMMON ANNOTATIONS that create Spring Beans are:
//	@Component — general-purpose bean
//	@Service — bean for business logic
//	@Repository — bean for database logic
//	@Controller — bean for web pages
//	@RestController — bean for REST APIs
//	@Configuration with @Bean — manually creates a bean
//	@Service, @Repository, @Controller, and @RestController are specialized forms of @Component.

//DONE LEC2