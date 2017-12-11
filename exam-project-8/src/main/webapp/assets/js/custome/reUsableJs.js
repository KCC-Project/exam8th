
//url1 for search Param ,url2 for get getting data with the help of selected id, fxn calling datatable load 
function select2Function(url1,url2,method1,method2,placeholder,fxn){
		$("select").select2({
			theme : "bootstrap",
			width : "auto"
		});

		$("#sel1").select2({
			theme : "bootstrap",
			width : "100%",
			//width:auto,
			height : "10px",
			minimumInputLength : 3,
			placeholder : "Search "+placeholder,
			ajax : {
				url :url1 ,
				dataType : 'json',
				type : method1,
				delay : 400,
				data : function(params) {
					//console.log("params="   + params.term);
					return {
						val : params.term,
						page : params.page
					};
				},
				processResults : function(data, params) {
					console.log("returned data from server =" + JSON.stringify(data));
					//   console.log("full name = " + data.first_name + " "+data.last_name);
					//.log("id= " + data.s_id);
					params.page = params.page || 1;
					return {
						results : data,
						pagination : {
							more : (params.page * 30) < data.total_count
						}
					};
				},
				cache : true
			},
			escapeMarkup : function(markup) {
				return markup;
			},
			templateResult : formatRepo,
			templateSelection : formatRepoSelection
		}).on("change", function(e) {
			var selected_element = $(e.currentTarget);
			//console.log(selected_element);
			var select_val = selected_element.val();
			//console.log("program Id=" + select_val);
			//alert(select_val);
			var url3 = url2 +"/"+select_val;
			var method3 = method2;
			var data3 = "";
			fxn(url3, method3, data3);
		});

		function formatRepo(repo) {
			// console.log("formated repo=" + JSON.stringify(repo));
			if (repo.loading)
				return repo.text;
			var markup = '<option value='+repo.id+'>' + repo.name + '</option>';
			return markup;
		}
		function formatRepoSelection(repo) {
			return repo.name || repo.text;
		}
	}

function load_faculty(e, target) {
		$.ajax({
			url : window.context + "/ApiFaculty/GetAllFaculty",
			method : "GET",
			dataType : 'json',
			cache : true,
			success : function(data) {
				var json = data;
				//console.log("json size=" + data.length);
				var content = '';
				content += "<option selected='true' disabled> Select Faculty </option>"
				for (var i = 0; i < data.length; i++) {
					var facultyName = data[i].faculty_name;
					var facultyId = data[i].faculty_id;
					//console.log("faculty name =" + facultyName);

					content += '<option value='+facultyId+'>' + facultyName + '</option>';
				}
				$('#' + target).html(content);
			},
			error : function() {
				alert("Error...!!!");
			}
		});
	}
	function load_program(e, target) {
		var getid = e.target.id;
		var id = $('#' + getid).find(":selected").val();
		$.ajax({
			url : window.context + "/ApiProgram/GetProgramByFacultyId/" + id,
			method : "GET",
			dataType : 'json',
			cache : true,
			success : function(data) {

				//console.log("program size=" + JSON.stringify(data));
				var content = '';
				content += "<option selected='true' disabled> Select Program </option>"
				for (var i = 0; i < data.length; i++) {
					var programeName = data[i].program_name;
					var programeId = data[i].program_id;
					//console.log("faculty name =" + programeName);

					content += '<option value='+programeId+'>' + programeName + '</option>';
				}
				$('#' + target).html(content);
			},
			error : function() {
				alert("Error...!!!");
			}
		});
	}
	

	
	function load_all_program(target) {
        $.ajax({
            url : window.context + "/ApiProgram/GetAllProgram",
            method : "GET",
            dataType : 'json',
            cache : true,
            success : function(data) {

                //console.log("program size=" + JSON.stringify(data));
                var content = '';
                for (var i = 0; i < data.length; i++) {
                    var programeName = data[i].program_name;
                    var programeId = data[i].program_id;
                    //console.log("faculty name =" + programeName);

                    content += '<option value='+programeId+'>' + programeName + '</option>';
                }
                $('#' + target).html(content);
            },
            error : function() {
                alert("Error...!!!");
            }
        });
    }
	
	
	function load_all_examType(target) {
        $.ajax({
            url : window.context + "/ApiExam_type/GetAllExam_type",
            method : "GET",
            dataType : 'json',
            cache : true,
            success : function(data) {

                var content = '';
                for (var i = 0; i < data.length; i++) {
                    var exam_type_id = data[i].exam_type_id;
                    var type_name = data[i].type_name;

                    content += '<option value='+exam_type_id+'>' + type_name + '</option>';
                }
                $('#' + target).html(content);
            },
            error : function() {
                alert("Error...!!!");
            }
        });
    }
	
	
	function search_subject(data, target) {
        $.ajax({
            url : window.context + "/ApiSubject/GetSubjectByParameters",
            method : "POST",
            data: data,
            dataType : 'json',
            cache : true,
            success : function(data) {

                var content = '';
                for (var i = 0; i < data.length; i++) {
                    var subject_id = data[i].subject_id;
                    var subject_name = data[i].subject_name;

                    content += '<option value='+subject_id+'>' + subject_name + '</option>';
                }
                $('#' + target).html(content);
            },
            error : function() {
                alert("Error...!!!");
            }
        });
    }
	
	
