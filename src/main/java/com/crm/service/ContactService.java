package com.crm.service;

import java.util.List;

import com.crm.model.Contact;

public interface ContactService
{
	List<Contact> getAllContacts();

	Contact getContactById( Integer contactId );

	Contact getContactByEmailAddress( String email );

	void addContact( Contact contact );

	void updateContact( Contact contact );

	void deleteContact( int contactId );
}
