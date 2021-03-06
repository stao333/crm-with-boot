package com.crm.service;

import java.util.List;

import com.crm.model.Activity;

public interface ActivityService
{
	List<Activity> getAllActivities();

	List<Activity> getActivitiesByContactId( Integer contactId );

	Activity getActivityById( Integer activityId );

	void addActivity( Activity activity );

	void updateActivity( Activity activity );

	void deleteActivity( Integer activityId );

	void deleteAllActivitiesForContactId( Integer contactId );
}
