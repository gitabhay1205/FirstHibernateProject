package com.abhay.main;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.abhay.dto.Address;
import com.abhay.dto.FourWheeler;
import com.abhay.dto.TwoWheeler;
import com.abhay.dto.UserDetails;
import com.abhay.dto.Vechile;

public class HibernateTest {
	
	public static void main(String args[]) {
		
		UserDetails user = new UserDetails();
		
//		Address addr = new Address();
//		addr.setCity("Lucknow");
//		addr.setStreet("Yahiyaganj");
//		addr.setState("UP");
//		addr.setPincode("411057");
//		
//		Address addr2 = new Address();
//		addr2.setCity("kanpur");
//		addr2.setStreet("Chowk");
//		addr2.setState("UP");
//		addr2.setPincode("226003");
		
//		user.getListOfAddress().add(addr);
//		user.getListOfAddress().add(addr2);
		
//		user.setUserId(3);
		user.setUserName("Aayushi");
		user.setJoinedDate(new Date());
//		user.setHomeAddress(addr);
//		user.setOfficeAddress(addr2);
		user.setDesciption("User descriptin here");
		
//		Vechile v = new Vechile();
//		v.setVechileName("Car");
//	
//		TwoWheeler tw = new TwoWheeler();
//		tw.setSteeringHandle("bike Handle");
//		tw.setVechileName("Honda");
//		
//		FourWheeler fw = new FourWheeler();
//		fw.setSteeringWheel("car steering");
//		tw.setVechileName("BMW");
		
		//Getting session from sessionfactory to save model objects
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
//		session.save(v);
//		session.save(tw);
//		session.save(fw);
		 //int q= (int) session.save(user); //save method return serializable integer value of id
		session.save(user);
		
		//Retrieving all user object details using HQL 
 Query query = session.createQuery("from UserDetails where userId>2 ");
 
 //Retrieving userDetails object from table using ParameterBindin
String mUserId="5";
String userNam="Aayushi";
Query query2 = session.createQuery("from UserDetails where userId > ? and userName = ? ");
query2.setInteger(0,Integer.parseInt(mUserId));
query2.setString(1, userNam);
List<UserDetails> userIn =(List<UserDetails>)query2.list();

//Retrieving userDetails object from table using NamedQuery defined in UserDetails.java
String mUserId2="5";
Query query3 = session.getNamedQuery("UserDetails.byId");
query3.setInteger(0,Integer.parseInt(mUserId2));
List<UserDetails> userIn1 =(List<UserDetails>)query3.list();

//Retrieving userDetails object from table using NamedNativeQuery defined in UserDetails.java
String mUserId3="1";
Query query4 = session.getNamedQuery("UserDetails.byId1");
query4.setInteger(0,Integer.parseInt(mUserId3));
List<UserDetails> userIn2 =(List<UserDetails>)query4.list();

//Retrieving data from db using criteria API another way as compared to retrieving data from HQl
Criteria criteria = session.createCriteria(UserDetails.class);//this will fetch all records from table of UserDetails
criteria.add(Restrictions.eq("userName","Abhay"))//this will fetch only those records from table where userName is Abhay
         .add(Restrictions.gt("userId", 5));//this will fetch only those records from table where userName is Abhay and userId greater than 5(gt means greater than)
List<UserDetails> userIn3 =(List<UserDetails>)criteria.list();

//Retrieving data from data with help of Query by example(it is helpful when u have too many search criteria like userId username userage then it is not good to wite .add again and again
UserDetails userExample = new UserDetails();
userExample.setUserId(1);
userExample.setUserName("Abhay");
Example example = Example.create(userExample);
Criteria criteria1 = session.createCriteria(UserDetails.class)//this will fetch all records from table of UserDetails
                             .add(example);
List<UserDetails> userIn4 =(List<UserDetails>)criteria1.list();



//Retrieving only UserName of user object details using HQL 
Query  query1 = session.createQuery("select userName from UserDetails");

//Retrieving description and UserName column of user object details using HQL 
//Query  query2 = session.createQuery("select description,userName from UserDetails");
 
//returns list of all of user object query
 List<UserDetails> userInfo =(List<UserDetails>)query.list();
 
//returns list of userName of user object query1
 List<String> userNames =(List<String>)query1.list(); 
 
//returns list of list of String userName,description of user object query1
//List<List<String>> userNamesDescription =(List<List<String>>)query1.list(); 
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println("Size of userInfo list is "+ userInfo.size());
		
		for(String s:userNames) {
			System.out.println(s);
		}
		
		for(UserDetails s:userIn) {
			System.out.println("Example for Parameter Binding " + s.getUserName() + "  " + s.getDesciption());
		}
		
		for(UserDetails s:userIn1) {
			System.out.println("Example for NamedQuery "+s.getUserName() + "  " + s.getDesciption());
		}
		for(UserDetails s:userIn2) {
			System.out.println("Example for NamedNativeQuery "+s.getUserName() + "  " + s.getDesciption());
		}
		
		for(UserDetails s:userIn3) {
			System.out.println("Example for Criteria API "+s.getUserName() + "  " + s.getDesciption());
		}
		
		for(UserDetails s:userIn4) {
			System.out.println("Example of QueryByexample "+s.getUserName() + "  " + s.getDesciption());
		}
		
		//System.out.println("object is" + q);
		//retrieve user object from database
//		user = null;
//	    session = sessionFactory.openSession();
//		session.beginTransaction();	
//		user = (UserDetails)session.get(UserDetails.class, 1);
//		System.out.println("User Name from user_details is : "+ user.getUserName());
	}

}
