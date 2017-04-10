package com.crm.dao;

import java.util.List;

import com.crm.model.Activity;

public interface ActivityDao
{
	List<Activity> getAllActivities();

	List<Activity> getAllActivitiesForContact( Integer contactId );

	Activity getActivityById( Integer activityId );

	void addActivity( Activity activity );

	void updateActivity( Activity activity );

	void deleteActivity( Integer activityId );

	void deleteAllActivitiesForContactId( Integer contactId );
}
