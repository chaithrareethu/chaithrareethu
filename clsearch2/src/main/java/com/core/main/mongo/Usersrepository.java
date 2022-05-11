package com.core.main.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class Usersrepository {

	@Autowired
	MongoOperations mongoops;
	
	@Autowired
	MongoTemplate mongotemplate;
	
	public List<Users> getAllUsers()
	{
		return mongoops.findAll(Users.class, "users");
	}
	
	public Users getUser(String usernameoremail) throws AuthorizationException
	{
		try {
			if (usernameoremail.contains("@"))
				return mongoops.findOne(Query.query(Criteria.where("emailid").is(usernameoremail)), Users.class, "users");
			else
				return mongoops.findOne(Query.query(Criteria.where("username").is(usernameoremail)), Users.class, "users");
		}
		catch(Exception e) {
			throw new  AuthorizationException("Invalid User name");
		}
		
	}
	
	
	 /*NOTE:
	  * This method uses MongoTemplate.save() or insert().Both these methods auto-generate ID in the DB for new record.	  
	 *The limitation here is both these methods create a key-value as "_class": "com.core.main.mongo.Customer". This is not
	 *fetched in a get() or findOne() hence not an issue.
	 *Difference bw save() and insert():save() inserts if record does not exist else updates the record.insert(): inserts
	 *if record does not exist, else Exception thrown as duplicate key exists
	 **/
	public Users saveUsers(Users users)
	{
		Users usersObj = mongotemplate.findOne(Query.query(Criteria.where("username")
				.is(users.getUsername())), Users.class, "users");
		if (usersObj == null)
			usersObj = new Users();
		//else
			//do nothing
		usersObj.setUsername(users.getUsername());
		usersObj.setEmailid(users.getEmailid());
		usersObj.setPassword(users.getEmailid());
		usersObj.setIsadmin(users.getIsadmin());
		return mongotemplate.save(usersObj);
		
	}
	
	
}
