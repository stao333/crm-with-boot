package com.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "activity")
public class Activity
{
	@Id
	@Column(name = "activity_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_id", nullable = false)
	private Contact contact;

	@Column(name = "title")
	private String title;

	@Column(name = "notes")
	private String notes;

	@Column(name = "due_date")
	private Date dueDate;

	@Column(name = "activity_type")
	private String activityType;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId( Integer activityId ) {
		this.activityId = activityId;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact( Contact contact ) {
		this.contact = contact;
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

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType( String activityType ) {
		this.activityType = activityType;
	}

}
