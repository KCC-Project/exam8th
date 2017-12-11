$(document).ready(function() {

	//////  Edit table for student information  ///////

	$('#tblStudent').editable({
		container : 'body',
		selector : 'td.fname',
		title : 'First Name',
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});

	
	$('#tblStudent').editable({
		container : 'body',
		selector : 'td.mname',
		title : 'Middle Name',
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});

	$('#tblStudent').editable({
		container : 'body',
		selector : 'td.lname',
		title : 'Last Name',
		
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});

	$('#tblStudent').editable({
		container : 'body',
		selector : 'td.phone',
		title : 'Phone',
		validate : function(value) {
			var regex = /^[0-9]+$/;
			if ($.trim(value) == '') {
				return 'This field is required';
			}
			else if (!regex.test(value)) {
				return 'Numbers only!';
			} 
			},
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	$('#tblStudent').editable({
		container : 'body',
		selector : 'td.address',
		title : 'Address',
	
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	$('#tblStudent_additional').editable({
		container : 'body',
		selector : 'td.district',
		title : 'District',
	
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	$('#tblStudent_additional').editable({
		container : 'body',
		selector : 'td.city',
		title : 'City',
		
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});

	$('#tblStudent_additional').editable({
		container : 'body',
		selector : 'td.email',
		
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	$('#tblStudent_additional').editable({
		container : 'body',
		selector : 'td.dob',
		title : 'Date Of Birth',
		
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	$('#tblStudent_additional').editable({
		container : 'body',
		selector : 'td.zip',
		title : 'Zip Code',
		validate : function(value) {
			var regex = /^[0-9]+$/;
			if ($.trim(value) == '') {
				return 'This field is required';
			}
			else if (!regex.test(value)) {
				return 'Numbers only!';
			} 
		},
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	$('#tblStudent_additional').editable({
		container : 'body',
		selector : 'td.regdate',
		title : 'Registration Number',
		
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});

	$('#tblStudent_additional').editable({
		container : 'body',
		selector : 'td.status',
		title : 'Status',
		validate : function(value) {
			var regex = /^[0-1]+$/;
			if ($.trim(value) == '') {
				return 'This field is required';
			}
			else if (!regex.test(value)) {
				return 'Numbers only!';
			} 
			
		},
		validate : function(value) {
			if ($.trim(value) == '') {
				return 'This field is required';
			}
		}
	});
	//////////////// Student Info Edit table Ends Here //////////////
});
