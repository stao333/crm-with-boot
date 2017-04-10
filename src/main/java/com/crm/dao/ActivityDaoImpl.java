package com.crm.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crm.model.Activity;
import com.crm.model.Contact;

@Repository
public class ActivityDaoImpl implements ActivityDao
{

	private static final Logger logger = LoggerFactory.getLogger( ActivityDaoImpl.class );

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public void setSessionFactory( SessionFactory sessionFactory ) {
		this.sessionFactory = sessionFactory;
	}

	@PostConstruct
	public void init() {
		;
	}

	@Override
	public List<Activity> getAllActivities() {

		Session session = sessionFactory.getCurrentSession();
		List<Activity> activities = session.createQuery( "from Activity" ).list();
		for( Activity activity : activities ) {
			// Hibernate.initialize( activity.getContact() );
			logger.info( "Activity List::" + activity );
		}
		return activities;
	}

	@Override
	public List<Activity> getAllActivitiesForContact( Integer contactId ) {

		Session session = sessionFactory.getCurrentSession();
		Contact contact = (Contact) session.load( Contact.class, contactId );

		Criteria criteria = session.createCriteria( Activity.class );
		List<Activity> activities = criteria.add( Restrictions.eq( "contact", contact ) ).list();

		return activities;
	}

	@Override
	public Activity getActivityById( Integer activityId ) {
		Session session = sessionFactory.getCurrentSession();
		Activity activity = (Activity) session.get( Activity.class, activityId );
		return activity;
	}

	@Override
	public void addActivity( Activity activity ) {
		Session session = sessionFactory.getCurrentSession();
		session.save( activity );
	}

	@Override
	public void updateActivity( Activity activity ) {
		Session session = sessionFactory.getCurrentSession();
		session.update( activity );
	}

	@Override
	public void deleteActivity( Integer activityId ) {
		Session session = sessionFactory.getCurrentSession();
		Activity activity = (Activity) session.load( Activity.class, activityId );
		session.delete( activity );
	}

	@Override
	public void deleteAllActivitiesForContactId( Integer contactId ) {

		Session session = sessionFactory.getCurrentSession();
		Contact contact = (Contact) session.load( Contact.class, contactId );

		String hql = "delete from Activity where contact = :contact";
		int count = session.createQuery( hql ).setParameter( "contact", contact ).executeUpdate();
		logger.info( "A total of " + count + " activities have been deleted." );
		/*
		 * Contact contact = (Contact) session.load( Contact.class, contactId );
		 * 
		 * List<Activity> activityList = session.createCriteria( Activity.class ).add( Restrictions.eq( "contact", contact )
		 * ).list(); for( Activity activity : activityList ) { session.delete( activity ); }
		 */

	}

}
