package com.crm.web.model;

import java.util.Date;

public class JsonActivity
{
	private Integer activityId;
	private String activityType;
	private Integer contactId;
	private String contactName;
	private String title;
	private String notes;
	private Date dueDate;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId( Integer activityId ) {
		this.activityId = activityId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType( String activityType ) {
		this.activityType = activityType;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId( Integer contactId ) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName( String contactName ) {
		this.contactName = contactName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes( String notes ) {
		this.notes = notes;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate( Date dueDate ) {
		this.dueDate = dueDate;
	}

}
