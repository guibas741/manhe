	var host = "http://localhost:8080/Manhe/services/";
	
	function listarSchedule() {  
			$.ajax({ 
				url : host + 'schedule',
				type : 'GET',
				 headers: {          
	                 Accept : 'application/json'   
	  			},    
				success : function(data) {
					$('#grid tr:gt(0)').remove();
					if(data.schedules != undefined){
						if ($.isArray(data.schedules.link)) {
						   for ( var i = 0; i < data.schedules.link.length; i++) {
						      var link = data.schedules.link[i]['@href'];
						      segueLinkSchedule(link);
						   }
						} else {
						   var link = data.schedules.link['@href'];
						   segueLinkSchedule(link);
						}
					}
			
				},
				error: function(data) {
					alert("Erro na invocação");
				}
			});
		}      
		
		
		function segueLinkSchedule(link) {
		   $.ajax({
		      url : host + link,
		      type : 'GET',
		      success : function(data) {
		         adicionaScheduleNovaAoGrid(data.schedule);
		      },
		      error : function(data) {
		         alert("Ocorreu um erro");
		      }
		   });
		}
		
		function adicionaScheduleNovaAoGrid(schedule) {

			 var data = "<tr>"
	  		      + "<td>" + schedule.name + "</td>"
	  		      + "<td>" + schedule.description + "</td>"
	  		      + "<td>" + schedule.status + "</td>"
	  		      + "<td><input type=\"button\" class=\"btn btn-danger\"  value=\"Apagar\" "
	  		      + "onclick=\"apagaSchedule('" + schedule.id + "');\" /> " 
	  		    + "<input type=\"button\" value=\"Editar\"  class=\"btn btn-primary\""
	  		      + "onclick=\"carregaSchedule('" + schedule.id + "');\" /></td>" 
	  		      + "</tr>";

	  		   $("#grid").append(data);
	  		}

		function adicionaSchedule() {
			var data = $("#criarScheduleForm").serializeJSON();

  			if(data.id){
  				atualizaSchedule(data);
  	  		}else {
  	  			criaSchedule(data);
  	  	  	}

  		    
  		}

  		function criaSchedule(data){
  			 data = "{\"schedule\":" + JSON.stringify(data) + "}";
   			$.ajax({
   			   url : host + 'schedule',
   			   type : 'POST',
   			   contentType : 'application/json',
   			   data : data,
   			   success : function(data) {
   				  alert("Incluído com sucesso!");
   				  $("#criarScheduleForm")[0].reset();
   				  listarSchedule();
   			   },
   			   error : function(data) {
   				 
 						console.log(data);
 						alert("Ocorreu um erro: " + data.status + " "
 								+ data.statusText);	
   			   }
   			 });
  		}


		function atualizaSchedule(data) {
			id = data.id;
  			data = "{\"schedule\":" + JSON.stringify(data) + "}";
  			$.ajax({
  			   url : host + 'schedule/'+id,
  			   type : 'PUT',
  			   contentType : 'application/json',
  			   data : data,
  			   success : function(data) {
  				  alert("Incluído com sucesso!");
  				  $("#criarScheduleForm")[0].reset();
  				  listarSchedule();
  			   },
  			   error : function(data) {
						console.log(data);
						alert("Ocorreu um erro: " + data.status + " "+ data.statusText);
					
  			   }
  			 });
  		}

		function apagaSchedule(id) {
  			$.ajax({
  						url : host + 'schedule/' + id,
  						type : 'DELETE',
  						success : function(data) {
  							listarSchedule();
  						},
  						error : function(data) {
  							console.log(data);
  							alert("Ocorreu um erro: " + data.status + " "
  									+ data.statusText);
  						}
  					});

  		}

		function carregaSchedule(id) {
					$.ajax({
						url : host + 'schedule/' + id,
						type : 'GET',
						success : function(data) {
							var frm =  $("#criarScheduleForm");
							 $.each(data.schedule, function(key, value){
							    $('[name='+key+']', frm).val(value);
							});
						},
						error : function(data) {
							console.log(data);
							alert("Ocorreu um erro: " + data.status + " "
									+ data.statusText);
						}
					});

		}
		
		