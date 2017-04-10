package com.crm.web.model;

public class JsonContact
{
	private Integer contactId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String telephoneNo;
	private String address1;
	private String address2;
	private String city;
	private String postCode;

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

}
