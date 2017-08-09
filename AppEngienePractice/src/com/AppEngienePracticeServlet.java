package com;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class AppEngienePracticeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// to persist the entity we need a datastore factory
		com.google.appengine.api.datastore.DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		// Creating an entity without identifier
		Entity en = new Entity("Person");
		en.setProperty("firstName", "Venkat");
		en.setProperty("lastname", "Naveen");
		en.setProperty("Email", "naveenyarramneedi@gmail.com");

		ds.put(en); // used to create an entity in the datastore..

		// ctreating an unique entity with the help of identifier

		Entity en1 = new Entity("User", 3141);
		en1.setProperty("Id", 1422);
		en1.setProperty("firstName", "venky");
		en1.setProperty("lastName", "yarramneedi");
		en1.setProperty("add", "Hyderabad");

		ds.put(en1);// used to create or updating an entity in the datastore..

		// Creating batch of entities
		Entity emp1 = new Entity("emp1");
		Entity emp2 = new Entity("emp2");
		Entity emp3 = new Entity("emp3");

		List<Entity> emp4 = Arrays.asList(emp1, emp2, emp3);

		ds.put(emp4);// used to create an entity in the datastore..
		
		Query q=new Query("User");
		PreparedQuery pq=ds.prepare(q);
		for(Entity e:pq.asIterable()){
			String id=e.getProperty("Id").toString();
			String fname=e.getProperty("firstName").toString();
			String lname=e.getProperty("lastName").toString();
			String add=e.getProperty("add").toString();
			System.out.println(id+ " "+fname+" "+" "+lname+" "+add);


		}
		
		
		
		/*
		 * =========== 
		 * // Creating key com.google.appengine.api.datastore.Key
		 * key = KeyFactory.createKey("User", 31);
		 * 
		 * // Retriving an entity try { Entity ret=ds.get(key);
		 * System.out.println(ret); } catch (EntityNotFoundException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * // Deleting an entity... ds.delete(key); // Creating an Entity group,
		 * here we are passing "en" key to the employee Entity so these two
		 * entities are in same group. Entity employee = new Entity("Employee",
		 * en.getKey()); ds.put(employee);// used to create an entity in the
		 * datastore..
		 * 
		 * // Creating ansister key com.google.appengine.api.datastore.Key key1
		 * = new KeyFactory.Builder("User", "GreatGrandPa") .addChild("User",
		 * "GrandPa").addChild("User", "Dad").getKey();
		 *  =========
		 */
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello Venkat");
	}
}
