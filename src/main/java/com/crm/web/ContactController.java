package com.crm.web;

import static com.crm.util.JsonUtil.convertToJson;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.Contact;
import com.crm.service.ContactService;
import com.crm.web.model.JsonContact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/contacts")
public class ContactController
{

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<JsonContact> getAllContacts() throws JsonProcessingException {

		List<Contact> contacts = contactService.getAllContacts();
		List<JsonContact> jsonContacts = new ArrayList<JsonContact>();

		for( Contact contact : contacts ) {
			jsonContacts.add( convertToJson( contact ) );
		}

		return jsonContacts;
	}

	@RequestMapping(value = "/getByEmailAddress/{email}", method = RequestMethod.GET)
	@ResponseBody
	public String getContactByEmailAddess( @PathVariable String email ) throws JsonProcessingException {

		Contact contact = contactService.getContactByEmailAddress( email );

		ObjectMapper mapper = new ObjectMapper();
		String contactStr = mapper.writeValueAsString( contact );

		return contactStr;

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addContact( @RequestParam String emailAddress, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam(required = false) String address1, @RequestParam(required = false) String address2,
			@RequestParam(required = false) String city, @RequestParam(required = false) String postCode,
			@RequestParam(required = false) String telephoneNo ) {

		Contact contact = new Contact();
		contact.setEmailAddress( emailAddress );
		contact.setFirstName( firstName );
		contact.setLastName( lastName );
		contact.setAddress1( address1 );
		contact.setAddress2( address2 );
		contact.setCity( city );
		contact.setPostCode( postCode );
		contact.setTelephoneNo( telephoneNo );

		contactService.addContact( contact );

		return "{}";

	}

	@RequestMapping(value = "/update/{contactId}", method = RequestMethod.POST)
	@ResponseBody
	public String updateContact( @PathVariable Integer contactId, @RequestParam(required = false) String emailAddress,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
			@RequestParam(required = false) String address1, @RequestParam(required = false) String address2,
			@RequestParam(required = false) String city, @RequestParam(required = false) String postCode,
			@RequestParam(required = false) String telephoneNo ) {

		Contact contact = contactService.getContactById( contactId ); // new Contact();
		contact.setEmailAddress( emailAddress );
		contact.setFirstName( firstName );
		contact.setLastName( lastName );
		contact.setAddress1( address1 );
		contact.setAddress2( address2 );
		contact.setCity( city );
		contact.setPostCode( postCode );
		contact.setTelephoneNo( telephoneNo );

		contactService.updateContact( contact );

		return "{}";

	}

	@RequestMapping(value = "/delete/{contactId}", method = RequestMethod.POST)
	@ResponseBody
	public String deleteContact( @PathVariable Integer contactId ) {

		contactService.deleteContact( contactId );

		return "{}";

	}
}