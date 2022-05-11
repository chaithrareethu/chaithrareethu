package com.core.main.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class Viewusersrestcontroller {

	@Autowired
	Usersrepository usersrepo;
	
	//URL:http://localhost:8080/users/getallusers
	//tested
	@GetMapping("/getallusers")
	public @ResponseBody List<Users> getallUsers()
	{
		return usersrepo.getAllUsers();
	}
	
	//URL:http://localhost:8080/users/getusername/janicea
	//tested
	@GetMapping("/getusername/{username}") 
	public @ResponseBody Users getUsername(@PathVariable("username") String strusername ) throws AuthorizationException
	{
		return usersrepo.getUser(strusername);
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> Validate(@RequestBody Users user) throws AuthorizationException{
		String username = user.getUsername();
		Users u  = usersrepo.getUser(username);
		try {
			if(username.equals(u.getUsername())) {
				System.out.println(username);
				System.out.println(u.getUsername());
				//return ResponseEntity.ok();
				return new ResponseEntity<>("Valid Username id" , HttpStatus.OK);
			}
		}
		catch(Exception e) {
			System.out.println(username);
			return new ResponseEntity<>("Invalid Username id" , HttpStatus.NOT_FOUND);
		}
		return null;

}
	

	
	
	@PostMapping("/adduser") 
	public @ResponseBody Users addUser(@RequestBody Users userobj)
	{
		return usersrepo.saveUsers(userobj);
	}
	
	
	
	@PutMapping("/updateuser") 
	public @ResponseBody Users updateUser(@RequestBody Users userobj)
	{
		return usersrepo.saveUsers(userobj);
	}
	
	@DeleteMapping("/deleteuser") 
	public String deleteUser(String username_emailid)
	{
		return "";
	}
	
}
