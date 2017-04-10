package com.crm.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact
{
	@Id
	@Column(name = "contact_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "telephone_no")
	private String telephoneNo;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "city")
	private String city;

	@Column(name = "post_code")
	private String postCode;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
	private List<Activity> activities;

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId( Integer contactId ) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress( String emailAddress ) {
		this.emailAddress = emailAddress;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo( String telephoneNo ) {
		this.telephoneNo = telephoneNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1( String address1 ) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2( String address2 ) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode( String postCode ) {
		this.postCode = postCode;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities( List<Activity> activities ) {
		this.activities = activities;
	}

}
