	var host = "http://localhost:8080/Manhe/services/";
	
	function listarMedicalRecommendation() {  
			$.ajax({ 
				url : host + 'medical-recommendation',
				type : 'GET',
				 headers: {          
	                 Accept : 'application/json'   
	  			},    
				success : function(data) {
					$('#grid tr:gt(0)').remove();
					if(data.medicalRecommendations != undefined){
						if ($.isArray(data.medicalRecommendations.link)) {
						   for ( var i = 0; i < data.medicalRecommendations.link.length; i++) {
						      var link = data.medicalRecommendations.link[i]['@href'];
						      segueLinkMedicalRecommendation(link);
						   }
						} else {
						   var link = data.medicalRecommendations.link['@href'];
						   segueLinkMedicalRecommendation(link);
						}
					}
			
				},
				error: function(data) {
					alert("Erro na invocação");
				}
			});
		}      
		
		
		function segueLinkMedicalRecommendation(link) {
		   $.ajax({
		      url : host + link,
		      type : 'GET',
		      success : function(data) {
		         adicionaMedicalRecommendationNovaAoGrid(data.medicalRecommendation);
		      },
		      error : function(data) {
		         alert("Ocorreu um erro");
		      }
		   });
		}
		
		function adicionaMedicalRecommendationNovaAoGrid(medicalRecommendation) {

			 var data = "<tr>"
	  		      + "<td>" + medicalRecommendation.name + "</td>"
	  		      + "<td>" + medicalRecommendation.description + "</td>"
	  		      + "<td>" + medicalRecommendation.status + "</td>"
	  		      + "<td><input type=\"button\" class=\"btn btn-danger\"  value=\"Apagar\" "
	  		      + "onclick=\"apagaMedicalRecommendation('" + medicalRecommendation.id + "');\" /> " 
	  		    + "<input type=\"button\" value=\"Editar\"  class=\"btn btn-primary\""
	  		      + "onclick=\"carregaMedicalRecommendation('" + medicalRecommendation.id + "');\" /></td>" 
	  		      + "</tr>";

	  		   $("#grid").append(data);
	  		}

		function adicionaMedicalRecommendation() {
			var data = $("#criarMedicalRecommendationForm").serializeJSON();

  			if(data.id){
  				atualizaMedicalRecommendation(data);
  	  		}else {
  	  			criaMedicalRecommendation(data);
  	  	  	}

  		    
  		}

  		function criaMedicalRecommendation(data){
  			 data = "{\"medical-recommendation\":" + JSON.stringify(data) + "}";
   			$.ajax({
   			   url : host + 'medical-recommendation',
   			   type : 'POST',
   			   contentType : 'application/json',
   			   data : data,
   			   success : function(data) {
   				  alert("Incluído com sucesso!");
   				  $("#criarMedicalRecommendationForm")[0].reset();
   				  listarMedicalRecommendation();
   			   },
   			   error : function(data) {
   				 
 						console.log(data);
 						alert("Ocorreu um erro: " + data.status + " "
 								+ data.statusText);	
   			   }
   			 });
  		}


		function atualizaMedicalRecommendation(data) {
			id = data.id;
  			data = "{\"medical-recommendation\":" + JSON.stringify(data) + "}";
  			$.ajax({
  			   url : host + 'medicalRecommendation/'+id,
  			   type : 'PUT',
  			   contentType : 'application/json',
  			   data : data,
  			   success : function(data) {
  				  alert("Incluído com sucesso!");
  				  $("#criarMedicalRecommendationForm")[0].reset();
  				  listarMedicalRecommendation();
  			   },
  			   error : function(data) {
						console.log(data);
						alert("Ocorreu um erro: " + data.status + " "+ data.statusText);
					
  			   }
  			 });
  		}

		function apagaMedicalRecommendation(id) {
  			$.ajax({
  						url : host + 'medical-recommendation/' + id,
  						type : 'DELETE',
  						success : function(data) {
  							listarMedicalRecommendation();
  						},
  						error : function(data) {
  							console.log(data);
  							alert("Ocorreu um erro: " + data.status + " "
  									+ data.statusText);
  						}
  					});

  		}

		function carregaMedicalRecommendation(id) {
					$.ajax({
						url : host + 'medical-recommendation/' + id,
						type : 'GET',
						success : function(data) {
							var frm =  $("#criarMedicalRecommendationForm");
							 $.each(data.medicalRecommendation, function(key, value){
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
		
		