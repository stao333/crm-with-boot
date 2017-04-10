<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title>Technical Test CRM</title>

    <script type="application/javascript" src="resources/js/external/jquery-3.1.0.min.js"></script>
    <script type="application/javascript" src="resources/js/external/jquery-ui.min.js"></script>
    <script type="application/javascript" src="resources/js/external/jquery-ui-timepicker-addon.min.js"></script>
    <script type="application/javascript" src="resources/js/external/jquery.jnotify.min.js"></script>
    <script type="application/ecmascript" src="resources/js/external/datatables.min.js"></script>
	<script type="application/javascript" src="resources/js/external/bootstrap.min.js"></script>

	<script type="application/javascript" src="resources/js/external/dataTables.buttons.min.js"></script>
	<script type="application/javascript" src="resources/js/external/buttons.html5.min.js"></script>
	<script type="application/javascript" src="resources/js/external/buttons.flash.min.js"></script>
	<script type="application/javascript" src="resources/js/external/dataTables.select.min.js"></script>

	<script type="application/javascript" src="resources/js/crm.js"></script>

    <link rel="stylesheet" href="resources/css/external/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/external/jquery-ui.min.css">
    <link rel="stylesheet" href="resources/css/external/jquery-ui-timepicker-addon.min.css">
    <link rel="stylesheet" href="resources/css/external/jquery.jnotify.min.css">
    <link rel="stylesheet" href="resources/css/external/datatables.min.css">

    <link rel="stylesheet" href="resources/css/crm.css">

	<link rel="stylesheet" href="resources/css/external/buttons.dataTables.min.css">

	<link rel="stylesheet" href="resources/css/external/select.dataTables.min.css">
</head>

<body>
<div class="container">

  <div class="row">
    <div class="col-md-4" ><b>Technical Test CRM</b></div>
    <div class="col-md-4" >
    <ul class="nav nav-tabs">
    	<li class="active"><a data-toggle="tab" href="#contactsPanel">Contacts</a></li>
    	<li><a data-toggle="tab" href="#activitiesPanel">Activities</a></li>
    </ul>
    </div>
    <div class="col-md-4" ><h6><a href="<c:url value='j_spring_security_logout'/>">Logout</a></h6></div>
  </div>
	
	<div class="tab-content">
	
	  <div id="contactsPanel"  class="tab-pane fade in active">
	   <div class="row" class="row">
		<div class="col-md-3" >
        
            <table id="contactsTable" class="display"></table>
            <p></p>
            <div><button id="addContactBtn">Add Contact</button></div>
		
		</div>
		<div class="col-md-9" style="border-style: ridge; border-width: 1px;border-radius: 3px;" >
		
			<div class="row">
				<div class="col-md-6" ></div>
				<div class="col-md-6" ><button id="deleteContactBtn">Delete Contact</button> <button id="updateContactBtn">Edit/Save Contact</button></div>
			</div>
			<div class="row">
				<div class="col-md-6" >First Name * <input id="firstName" type="text"> </div>
				<div class="col-md-6" >Last Name * <input id="lastName" type="text"> </div>
			</div>
			<div class="row">
				<div class="col-md-6" >Email * <input id="emailAddress" type="text"> </div>
				<div class="col-md-6" >Telephone <input id="telephoneNo" type="text"> </div>
			</div>
			<div class="row">
				<div class="col-md-6" >Address 1 <input id="address1" type="text"> </div>
				<div class="col-md-6" ></div>
			</div>
			<div class="row">
				<div class="col-md-6" >Address 2 <input id="address2" type="text"> </div>
				<div class="col-md-6" ></div>
			</div>
			<div class="row">
				<div class="col-md-6" >City <input id="city" type="text"> </div>
				<div class="col-md-6" ></div>
			</div>
			<div class="row">
				<div class="col-md-6" >Post Code <input id="postCode" type="text"> </div>
				<div class="col-md-6" ></div>
			</div>
			<div class="row">
				<div class="col-md-6" ><p> </p></div>
				<div class="col-md-6" ><p> </p></div>
			</div>
			<div class="row">
				<div class="col-md-6" >Activities </div>
				<div class="col-md-6" ><button id="addActivityBtnFalse">Add Activity</button></div>
			</div>
			<div class="row">
				<div class="col-md-12" >
					<table id="contactActivitiesTable" class="display"></table>
				</div>
			</div>
		
		</div>	
	   </div>
	  </div>
		
	  <div id="activitiesPanel" class="tab-pane fade">
	   <div class="row">
		<div class="col-md-3" >
        
            <table id="activitiesTable" class="display"></table>
            <p></p>
            <div><button id="addActivityBtn">Add Activity</button></div>
		
		</div>
		
		<div class="col-md-9" style="border-style: ridge; border-width: 1px;border-radius: 3px;" >
		
			<div class="row">
				<div class="col-md-8" ></div>
				<div class="col-md-4" ><button id="deleteActivityBtn">Delete Activity</button> <button id="updateActivityBtn">Edit/Save Activity</button></div>
			</div>
			<div class="row">
				<div class="col-md-5" >Contact <input id="fullName" type="text" disabled="disabled" size="30"> </div>
				<div class="col-md-3" >Type <select id="activityType">
					  <option value="Email">Email</option>
					  <option value="Call">Call</option>
					  <option value="Meeting">Meeting</option>
					</select>
			    </div>
				<div class="col-md-4" >Due Date <input id="dueDate" type="text"> </div>
			</div>
			<div class="row">
				<div class="col-md-12" >Subject <input id="title" type="text" size="50"></div>
			</div>
			<div class="row">
				<div class="col-md-12" >Notes <textarea id="notes" rows="10" cols="50"></textarea></div>
			</div>
			
		</div>
		
	   </div>
	  </div>
	  
	</div>


	<div class="modal fade" id="deleteActivityModal" role="dialog">
	    <div class="modal-dialog">
	    	<div class="modal-content" style="max-width: 400px;">
	    		<div class="modal-header">
	    		<button type="button" class="close" data-dismiss="modal">|Close &times;</button>
		        	<p class="modal-title">Delete Activity</p>
		        </div>
		        <div class="modal-body">
		        	<p>Are you sure you want to delete this activity?</p>
					<div id="deleteActivityBtns">
						<input type="button" id="deleteActivityNoBtn" class="btn" data-dismiss="modal" value="No"></input>
						<input type="button" id="deleteActivityYesBtn" class="btn" value="Yes"></input>
					</div>
		        </div>
	    	</div>
	    </div>
	</div>
	
	<div class="modal fade" id="deleteContactModal" role="dialog">
	    <div class="modal-dialog">
	    	<div class="modal-content" style="max-width: 400px;">
	    		<div class="modal-header">
	    		<button type="button" class="close" data-dismiss="modal">|Close &times;</button>
		        	<p class="modal-title">Delete Contact</p>
		        </div>
		        <div class="modal-body">
		        	<p>Are you sure you want to delete this contact?</p>
					<div id="deleteContactBtns">
						<input type="button" id="deleteContactNoBtn" class="btn" data-dismiss="modal" value="No"></input>
						<input type="button" id="deleteContactYesBtn" class="btn" value="Yes"></input>
					</div>
		        </div>
	    	</div>
	    </div>
	</div>
	

</div>
</body>
</html>