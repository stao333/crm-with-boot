package com.crm.util;

import com.crm.model.Activity;
import com.crm.model.Contact;
import com.crm.web.model.JsonActivity;
import com.crm.web.model.JsonContact;

public class JsonUtil
{
	public static JsonActivity convertToJson( Activity activity ) {
		JsonActivity jsonActivity = new JsonActivity();

		jsonActivity.setActivityId( activity.getActivityId() );
		jsonActivity.setActivityType( activity.getActivityType() );
		jsonActivity.setContactId( activity.getContact().getContactId() );
		jsonActivity.setContactName( activity.getContact().getFirstName() + " " + activity.getContact().getLastName() );
		jsonActivity.setDueDate( activity.getDueDate() );
		jsonActivity.setNotes( activity.getNotes() );
		jsonActivity.setTitle( activity.getTitle() );

		return jsonActivity;
	}

	public static JsonContact convertToJson( Contact contact ) {

		JsonContact jsonContact = new JsonContact();

		jsonContact.setAddress1( contact.getAddress1() );
		jsonContact.setAddress2( contact.getAddress2() );
		jsonContact.setCity( contact.getCity() );
		jsonContact.setContactId( contact.getContactId() );
		jsonContact.setEmailAddress( contact.getEmailAddress() );
		jsonContact.setFirstName( contact.getFirstName() );
		jsonContact.setLastName( contact.getLastName() );
		jsonContact.setPostCode( contact.getPostCode() );
		jsonContact.setTelephoneNo( contact.getTelephoneNo() );

		return jsonContact;
	}
}
