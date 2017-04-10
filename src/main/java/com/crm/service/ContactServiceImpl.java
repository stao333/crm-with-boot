package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.ActivityDao;
import com.crm.dao.ContactDao;
import com.crm.model.Contact;

@Service
public class ContactServiceImpl implements ContactService
{
	@Autowired
	private ContactDao contactDao;

	@Autowired
	private ActivityDao activityDao;

	@Override
	@Transactional(readOnly = true)
	public List<Contact> getAllContacts() {
		return contactDao.getAllContacts();
	}

	@Override
	@Transactional(readOnly = true)
	public Contact getContactById( Integer contactId ) {
		return contactDao.getContactById( contactId );
	}

	@Override
	@Transactional(readOnly = true)
	public Contact getContactByEmailAddress( String email ) {
		return contactDao.getContactByEmailAddress( email );
	}

	@Override
	@Transactional
	public void addContact( Contact contact ) {
		contactDao.addContact( contact );
	}

	@Override
	@Transactional
	public void updateContact( Contact contact ) {
		contactDao.updateContact( contact );
	}

	@Override
	@Transactional
	public void deleteContact( int contactId ) {
		activityDao.deleteAllActivitiesForContactId( contactId );
		contactDao.deleteContact( contactId );
	}

}
