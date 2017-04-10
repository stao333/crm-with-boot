package com.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crm.model.Contact;

@Repository
public class ContactDaoImpl implements ContactDao
{
	private static final Logger logger = LoggerFactory.getLogger( ContactDaoImpl.class );

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public void setSessionFactory( SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Contact> getAllContacts() {
		Session session = sessionFactory.getCurrentSession();
		List<Contact> contacts = session.createQuery( "from Contact" ).list();
		for( Contact contact : contacts ) {
			logger.info( "Contact List::" + contact );
		}
		return contacts;
	}

	@Override
	public Contact getContactById( Integer contactId ) {
		Session session = sessionFactory.getCurrentSession();
		Contact contact = (Contact) session.get( Contact.class, contactId );
		return contact;
	}

	@Override
	public Contact getContactByEmailAddress( String email ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addContact( Contact contact ) {
		Session session = sessionFactory.getCurrentSession();
		session.save( contact );
	}

	@Override
	public void updateContact( Contact contact ) {
		Session session = sessionFactory.getCurrentSession();
		session.update( contact );
	}

	@Override
	public void deleteContact( int contactId ) {
		Session session = sessionFactory.getCurrentSession();
		Contact contact = (Contact) session.load( Contact.class, contactId );
		session.delete( contact );
	}

}
