package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.ActivityDao;
import com.crm.model.Activity;

@Service
public class ActivityServiceImpl implements ActivityService
{
	@Autowired
	private ActivityDao activityDao;

	@Override
	@Transactional(readOnly = true)
	public List<Activity> getAllActivities() {

		List<Activity> activities = activityDao.getAllActivities();
		return activities;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Activity> getActivitiesByContactId( Integer contactId ) {

		List<Activity> activities = activityDao.getAllActivitiesForContact( contactId );
		return activities;
	}

	@Override
	@Transactional(readOnly = true)
	public Activity getActivityById( Integer activityId ) {

		Activity activity = activityDao.getActivityById( activityId );
		return activity;
	}

	@Override
	@Transactional
	public void addActivity( Activity activity ) {

		activityDao.addActivity( activity );

	}

	@Override
	@Transactional
	public void updateActivity( Activity activity ) {

		activityDao.updateActivity( activity );

	}

	@Override
	@Transactional
	public void deleteActivity( Integer activityId ) {

		activityDao.deleteActivity( activityId );

	}

	@Override
	@Transactional
	public void deleteAllActivitiesForContactId( Integer contactId ) {

		activityDao.deleteAllActivitiesForContactId( contactId );

	}

}
