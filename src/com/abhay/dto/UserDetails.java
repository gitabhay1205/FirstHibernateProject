package com.abhay.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@NamedQuery(name="UserDetails.byId" , query="from UserDetails where userId=?") //uses HQL query
@NamedNativeQuery(name="UserDetails.byId1" , query="select * from user_details where user_Id= ?", resultClass=UserDetails.class) //uses Sql query
@Table(name="USER_DETAILS") 
public class UserDetails {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int userId;
	@Column(name="USER_NAME")
	private String userName;
	@Temporal(TemporalType.DATE)
	private Date joinedDate;
//	@Embedded
//	@AttributeOverrides({
//	@AttributeOverride(name="street", column= @Column(name="HOME_STREET_NAME")),
//	@AttributeOverride(name="city", column= @Column(name="HOME_CITY_NAME")),
//	@AttributeOverride(name="state", column= @Column(name="HOME_STATE_NAME")),
//	@AttributeOverride(name="pincode", column= @Column(name="HOME_PINCODE"))})
//	private Address homeAddress;
//	@Embedded
//	private Address officeAddress;
	
	//Now here we are seeing how Collection is embbedded in db
	@ElementCollection
	@JoinTable(name="USER_ADDRESS", //this annotation is not neccessary,it only ask hibernate to create address table with name "USER_ADDRESS"
	     joinColumns=@JoinColumn(name="USER_ID")) //JoinColumn ask hibernate to create membervariable name of userdetailsuserid in table with name "USER_ID"
	@GenericGenerator(name="hilo-gen", strategy="hilo") //for automatic generation of primary keys values
	@CollectionId(columns= {@Column(name="ADDRESS_ID")}, generator="hilo-gen",type=@Type(type="long")) //This annotation provides primary key to table USER_ADDRESS
	private Collection<Address> listOfAddress = new ArrayList<>();
	
	@Lob //to tell hibernate that this column would require large size
	private String desciption;
	
	
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public Collection<Address> getListOfAddress() {
		return listOfAddress;
	}
	public void setListOfAddress(Collection<Address> listOfAddress) {
		this.listOfAddress = listOfAddress;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
