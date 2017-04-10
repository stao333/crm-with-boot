package com.crm.web;

import static com.crm.util.JsonUtil.convertToJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.Activity;
import com.crm.model.Contact;
import com.crm.service.ActivityService;
import com.crm.service.ContactService;
import com.crm.web.model.JsonActivity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/activities")
public class ActivityController
{
	@Autowired
	private ActivityService activityService;

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<JsonActivity> getAllActivities() throws JsonProcessingException {

		List<Activity> activities = activityService.getAllActivities();
		List<JsonActivity> jsonActivities = new ArrayList<JsonActivity>();

		for( Activity activity : activities ) {
			jsonActivities.add( convertToJson( activity ) );
		}

		return jsonActivities;
	}

	@RequestMapping(value = "/byContactId/{contactId}", method = RequestMethod.GET)
	@ResponseBody
	public List<JsonActivity> getActivitiesByContactId( @PathVariable Integer contactId ) throws JsonProcessingException {

		List<Activity> activities = activityService.getActivitiesByContactId( contactId );

		List<JsonActivity> jsonActivities = new ArrayList<JsonActivity>();

		for( Activity activity : activities ) {
			jsonActivities.add( convertToJson( activity ) );
		}

		return jsonActivities;
	}

	@RequestMapping(value = "/byId/{activityId}", method = RequestMethod.GET)
	@ResponseBody
	public String getActivityById( @PathVariable Integer activityId ) throws JsonProcessingException {

		Activity activity = activityService.getActivityById( activityId );

		ObjectMapper mapper = new ObjectMapper();
		String activityStr = mapper.writeValueAsString( activity );

		return activityStr;

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addActivity( @RequestParam Integer contactId, @RequestParam String activityType, @RequestParam String title,
			@RequestParam(required = false) String notes, @RequestParam(required = false) String dueDate ) {

		Contact contact = contactService.getContactById( contactId );
		Activity activity = new Activity();
		activity.setActivityType( activityType );
		activity.setContact( contact );
		activity.setNotes( notes );
		activity.setTitle( title );
		activity.setDueDate( convertToDate( dueDate ) );

		activityService.addActivity( activity );

		return "{}";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateActivity( @RequestParam Integer activityId, @RequestParam String activityType, @RequestParam String title,
			@RequestParam(required = false) String notes, @RequestParam(required = false) String dueDate ) {

		Activity activity = activityService.getActivityById( activityId );
		activity.setActivityId( activityId );
		activity.setActivityType( activityType );
		activity.setNotes( notes );
		activity.setTitle( title );
		activity.setDueDate( convertToDate( dueDate ) );

		activityService.updateActivity( activity );

		return "{}";

	}

	@RequestMapping(value = "/delete/{activityId}", method = RequestMethod.POST)
	@ResponseBody
	public String deleteActivity( @PathVariable Integer activityId ) {

		activityService.deleteActivity( activityId );

		return "{}";

	}

	@RequestMapping(value = "/delete/byContactId/{contactId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteAllActivitiesForContact( @PathVariable Integer contactId ) {

		activityService.deleteAllActivitiesForContactId( contactId );

		return "{}";

	}

	@InitBinder
	@ResponseBody
	public void initBinder( WebDataBinder webDataBinder ) {
		DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		webDataBinder.registerCustomEditor( Date.class, new CustomDateEditor( dateFormat, false ) );
	}

	private Date convertToDate( String dateStr ) {
		DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		try {
			return dateFormat.parse( dateStr );
		} catch( ParseException e ) {
			return null;
		}
	}
}
