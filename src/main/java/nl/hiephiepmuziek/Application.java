package nl.hiephiepmuziek;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	
	@RequestMapping("/resource")
	  public Map<String,Object> home() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userName = auth.getName(); //get logged in username
		
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", "Hello World");
	    model.put("user", userName);
	    return model;
	  }
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
