
CRM_FUNCS = {
	retrieveAllContacts: function() {
        $.get("contacts/", {}).done(function (data) {
            
            CRM_DATA.contacts = data;
			console.log( CRM_DATA.contacts);

        }).fail(function (data) {
        	
            $.jnotify("Error retrieving data from server!", "error");

        });		
	},
	
	retrieveAllActivities: function() {
        $.get("activities/", {}).done(function (data) {
            
            CRM_DATA.activities = data;
			console.log( CRM_DATA.activities);

        }).fail(function (data) {
        	
            $.jnotify("Error retrieving data from server!", "error");

        });		
	},
	
	loadContactsTable: function() {
		
		CRM_FUNCS.clearTable('#contactsTable');
		
	    $('#contactsTable').DataTable( {
	    	"paging": true,
	    	"info": false,
	        "select": true,
	        "ajax": {url: "contacts/", dataSrc: ''},
	        "columns": [
	            { 
					title: "Name", 
					data: "lastName",
					render: function(data, type, row) {
						return row.firstName + " " + row.lastName;
					}
				}
	        ]
	    } );
	    
	    var table = $('#contactsTable').DataTable();
	 
	    table.off('select');
	    table
	        .on( 'select', function ( e, dt, type, indexes ) {
	            var rowData = table.rows( indexes ).data().toArray();
	            CRM_DATA.currentContact = rowData[0];
	            CRM_FUNCS.populateContactDetails();
	        } )
	        
	    //CRM_DATA.currentContact = null;
	},
	
	loadActivitiesTable: function() {
		
		CRM_FUNCS.clearTable('#activitiesTable');
		
	    $('#activitiesTable').DataTable( {
	    	"paging": true,
	    	"info": false,
	        "select": true,
	        "ajax": {url: "activities/", dataSrc: ''},
	        "columns": [
	            { 
					title: "Title", 
					data: "title"
				},
	            { 
					title: "Contact", 
					searchable: false,
					data: "contactName"
				}
	        ]
	    } );
	    
	    var table = $('#activitiesTable').DataTable();
	 
	    table.off('select');
	    table
	        .on( 'select', function ( e, dt, type, indexes ) {
	            var rowData = table.rows( indexes ).data().toArray();
	            CRM_DATA.currentActivity = rowData[0];
	            CRM_FUNCS.populateActivityDetails();
	        } )
	},
	
	loadContactActivitiesTable: function( contactId ) {
		
		CRM_FUNCS.clearTable('#contactActivitiesTable');
		
	    $('#contactActivitiesTable').DataTable( {
	    	"paging": false,
	    	"info": false,
	        "ajax": {url: "activities/byContactId/" + contactId, dataSrc: ''},
	        "columns": [
	            { 
					title: "Title", 
					data: "title"
				},
	            { 
					title: "Type", 
					data: "activityType"
				},
				{
					title: "Due Date",
					data: "dueDate",
					render: function(data, type, row) {
						if( data === null || data === undefined ) {
							return "";
						}						
						return new Date( data ).toISOString().substring(0, 10);
					}
				}
	        ]
	    } );
	},
	
	populateContactDetails: function() {
		$('#firstName').val(CRM_DATA.currentContact.firstName);
		$('#lastName').val(CRM_DATA.currentContact.lastName);
		$('#emailAddress').val(CRM_DATA.currentContact.emailAddress);
		$('#telephoneNo').val(CRM_DATA.currentContact.telephoneNo);
		$('#address1').val(CRM_DATA.currentContact.address1);
		$('#address2').val(CRM_DATA.currentContact.address2);
		$('#city').val(CRM_DATA.currentContact.city);
		$('#postCode').val(CRM_DATA.currentContact.postCode);
		
		CRM_FUNCS.loadContactActivitiesTable( CRM_DATA.currentContact.contactId );
		
		$('#fullName').val(CRM_DATA.currentContact.firstName + " " + CRM_DATA.currentContact.lastName);
	},
	
	clearContactDetails: function() {
		$('#firstName').val("");
		$('#lastName').val("");
		$('#emailAddress').val("");
		$('#telephoneNo').val("");
		$('#address1').val("");
		$('#address2').val("");
		$('#city').val("");
		$('#postCode').val("");
		
		CRM_FUNCS.clearTable( "#contactActivitiesTable" );
		
		$('#fullName').val("");
	},
	
	updateContactDetails: function() {
		
		if ( CRM_DATA.currentContact === undefined || CRM_DATA.currentContact === null ) {
			$.jnotify("No contact has been selected!", "error");
			return;
		}
		
		if ( !CRM_FUNCS.contactIsValid() ) {
			return;
		}
		
		var contactId = CRM_DATA.currentContact.contactId;
		
		var args = {};
		args.emailAddress = $('#emailAddress').val();
		args.firstName = $('#firstName').val();
		args.lastName = $('#lastName').val();
		args.address1 = $('#address1').val();
		args.address2 = $('#address2').val();
		args.city = $('#city').val();
		args.postCode = $('#postCode').val();
		args.telephoneNo = $('#telephoneNo').val();
		
        $.post("contacts/update/" + contactId, args).done(function (data) {            
        	CRM_FUNCS.loadContactsTable();
        	CRM_FUNCS.clearContactDetails();
        }).fail(function (data) {
        	
            $.jnotify("Error sending contact update to server!", "error");

        });				
	},
	
	convertMillsToFormattedDate: function( data ) {
		if( data === null || data === undefined ) {
			return "";
		}						
		return new Date( data ).toISOString().substring(0, 10);
	},
	
	populateActivityDetails: function() {
		$('#fullName').val(CRM_DATA.currentActivity.contactName);
		$('#activityType').val(CRM_DATA.currentActivity.activityType);
		$('#title').val(CRM_DATA.currentActivity.title);
		$('#dueDate').val( CRM_FUNCS.convertMillsToFormattedDate( CRM_DATA.currentActivity.dueDate) );
		$('#notes').val(CRM_DATA.currentActivity.notes);
		
		CRM_DATA.currentContact.contactId = CRM_DATA.currentActivity.contactId;
	},
	
	clearActivityDetails: function() {
		$('#fullName').val("");
		$('#title').val("");
		$('#notes').val("");
		$('#dueDate').val("");
		
		CRM_DATA.currentActivity = null;
	},
	
	addContact: function() {
		
		if ( !CRM_FUNCS.contactIsValid() ) {
			return;
		}
		
		var args = {};
		args.emailAddress = $('#emailAddress').val();
		args.firstName = $('#firstName').val();
		args.lastName = $('#lastName').val();
		args.address1 = $('#address1').val();
		args.address2 = $('#address2').val();
		args.city = $('#city').val();
		args.postCode = $('#postCode').val();
		args.telephoneNo = $('#telephoneNo').val();
		
        $.post("contacts/add", args).done(function (data) {            
        	CRM_FUNCS.loadContactsTable();
        	CRM_FUNCS.clearContactDetails();
        }).fail(function (data) {       	
            $.jnotify("Error adding contact to server!", "error");
        });				
	},
	
	deleteContact: function() {
		
		if ( CRM_DATA.currentContact === undefined || CRM_DATA.currentContact === null ) {
			$.jnotify("No contact has been selected!", "error");
			return;
		}
		
		CRM_FUNCS.showDeleteContactModal();
		
		/*
		var retValue = confirm("Are you sure you want to delete this contact?");
		if ( retValue !== true ) {
			return;
		}
		
        $.post("contacts/delete/" + CRM_DATA.currentContact.contactId, {}).done(function (data) {            
        	CRM_FUNCS.loadContactsTable();
        	CRM_FUNCS.clearContactDetails();
        }).fail(function (data) {       	
            $.jnotify("Error deleting contact from server!", "error");
        });	
        */
	},
	
	deleteActivity: function() {
		
		if ( CRM_DATA.currentActivity === undefined || CRM_DATA.currentActivity === null ) {
			$.jnotify("No activity has been selected!", "error");
			return;
		}
		
		CRM_FUNCS.showDeleteActivityModal();
		
		/*
		var retValue = confirm("Are you sure you want to delete this activity?");
		if ( retValue !== true ) {
			return;
		}
		
        $.post("activities/delete/" + CRM_DATA.currentActivity.activityId, {}).done(function (data) {            
        	CRM_FUNCS.loadActivitiesTable();
        	CRM_FUNCS.clearActivityDetails();
        }).fail(function (data) {       	
            $.jnotify("Error deleting activity from server!", "error");
        });	
        */			
	},
	
    clearTable: function (tableId) {

        if ($.fn.DataTable.isDataTable(tableId)) {
            $(tableId).DataTable().destroy();
            $(tableId + ' thead').html('');
        }
        $(tableId + ' tbody').empty();
        //$(tableId).removeClass('large_text_handling');
    },
    
    isEmpty: function (str) {

        if (str === undefined || str === null) {
            return true;
        }
        str = str.trim();
        if (str.length === 0) {
            return true;
        } else {
            return false;
        }
    },
    
    contactIsValid: function() {
    	if ( CRM_FUNCS.isEmpty($('#emailAddress').val()) ) {
    		$.jnotify("Email must be provided!", "error");
    		return false;
    	}
    	if ( CRM_FUNCS.isEmpty($('#firstName').val()) ) {
    		$.jnotify("First name must be provided!", "error");
    		return false;
    	}
    	if ( CRM_FUNCS.isEmpty($('#lastName').val()) ) {
    		$.jnotify("Last name must be provided!", "error");
    		return false;
    	}
    	return true;
   },

   activityIsValid: function() {
   	if ( CRM_FUNCS.isEmpty($('#fullName').val()) ) {
   		$.jnotify("Contact must be provided!", "error");
   		return false;
   	}
   	if ( CRM_FUNCS.isEmpty($('#activityType').val()) ) {
   		$.jnotify("Activity type name must be provided!", "error");
   		return false;
   	}
   	if ( CRM_FUNCS.isEmpty($('#title').val()) ) {
   		$.jnotify("Subject must be provided!", "error");
   		return false;
   	}
   	return true;
  },

	addActivity: function() {
		
		if ( !CRM_FUNCS.activityIsValid() ) {
			return;
		}
		
		var args = {};
		args.contactId = CRM_DATA.currentContact.contactId;
		args.title = $('#title').val();
		args.activityType = $('#activityType').find(":selected").text();;
		args.dueDate = $('#dueDate').val();
		args.notes = $('#notes').val();

        $.post("activities/add", args).done(function (data) {            
        	CRM_FUNCS.loadActivitiesTable();
        	//CRM_FUNCS.clearContactDetails();
        }).fail(function (data) {       	
            $.jnotify("Error adding activity to server!", "error");
        });				

	},
	
	updateActivityDetails: function( contactId ) {
		
		if ( !CRM_FUNCS.activityIsValid() ) {
			return;
		}
		
		var args = {};
		args.activityId = CRM_DATA.currentActivity.activityId;
		args.title = $('#title').val();
		args.activityType = $('#activityType').find(":selected").text();;
		args.dueDate = $('#dueDate').val();
		args.notes = $('#notes').val();
		
        $.post("activities/update", args).done(function (data) {            
        	CRM_FUNCS.loadActivitiesTable();
        }).fail(function (data) {
        	
            $.jnotify("Error sending contact update to server!", "error");

        });				
	},
	
	showDeleteActivityModal: function() {
        $('#deleteActivityModal').modal('show');
        $('#deleteActivityModal').off('click', '#deleteActivityYesBtn').on('click', '#deleteActivityYesBtn', function (event) {
            $.post("activities/delete/" + CRM_DATA.currentActivity.activityId, {}).done(function (data) {            
            	CRM_FUNCS.loadActivitiesTable();
            	CRM_FUNCS.clearActivityDetails();
            }).fail(function (data) {       	
                $.jnotify("Error deleting activity from server!", "error");
            });				

            $('#deleteActivityModal').modal('hide');
        });		
	},
	
	showDeleteContactModal: function() {
        $('#deleteContactModal').modal('show');
        $('#deleteContactModal').off('click', '#deleteContactYesBtn').on('click', '#deleteContactYesBtn', function (event) {
            $.post("contacts/delete/" + CRM_DATA.currentContact.contactId, {}).done(function (data) {            
            	CRM_FUNCS.loadContactsTable();
            	CRM_FUNCS.clearContactDetails();
            	CRM_FUNCS.loadActivitiesTable();
            }).fail(function (data) {       	
                $.jnotify("Error deleting contact from server!", "error");
            });				

            $('#deleteContactModal').modal('hide');
        });		
	},
	
	addListeners: function() {
		$('#addContactBtn').click( function() {
			CRM_FUNCS.addContact();
		});
		
		$('#deleteContactBtn').click( function() {
			CRM_FUNCS.deleteContact();
		});
		
		$('#updateContactBtn').click( function() {
			CRM_FUNCS.updateContactDetails();
		});
		
		$('#addActivityBtn').click( function() {
			CRM_FUNCS.addActivity();
		});
		
		$('#deleteActivityBtn').click( function() {
			CRM_FUNCS.deleteActivity();
		});
		
		$('#updateActivityBtn').click( function() {
			CRM_FUNCS.updateActivityDetails( CRM_DATA.currentActivity.activityId );
		});
	}
}

CRM_DATA = {
	contacts: null,
	activities: null,
	currentContact: null,
	currentActivity: null
}

$( document ).ready(function() {
	
	//CRM_FUNCS.retrieveAllContacts();
	//CRM_FUNCS.retrieveAllActivities();
	
	CRM_FUNCS.loadContactsTable();
	CRM_FUNCS.loadActivitiesTable();
	
	$( '#dueDate' ).datepicker({ dateFormat: 'yy-mm-dd' });
	
	CRM_FUNCS.addListeners();
	
    
});