package dev.redbeans.bootcamp202506eirzarog.springapi.controllers;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    // http://localhost:8080/search?q=java+spring+boot+hello+world+example
    // http://localhost:8080/hello?name=Eirini&param2=value2
    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name){

        String pageContent = """
                                <h1>Hello, ${name}!</h1>
                                <p>Welcome to the Spring Boot API!</p>
                                <ul>
                                    <li><a href="/hello/bootcamp">Bootcamp Greeting</a></li>
                                    <li><a href="/hello?q=java+spring+boot+hello+world+example">Query Example</a></li>
                                    <li><a href="/hello?name=John">Personalized Greeting</a></li>
                                </ul>
                                """;
        String greetingName = "World"; // Default name if none provided
        if (name == null || name.isEmpty()) {
            name = greetingName;
        }
        return pageContent.replace("${name}", name);
    }

    @GetMapping("/hello/bootcamp")
    public String helloBootcamp(){
        return "Hello, Bootcamp!";
    }


}
