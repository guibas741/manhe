	var host = "http://localhost:8080/Manhe/services/";
	
	function listarMood() {  
			$.ajax({ 
				url : host + 'mood',
				type : 'GET',
				 headers: {          
	                 Accept : 'application/json'   
	  			},    
				success : function(data) {
					$('#grid tr:gt(0)').remove();
					if(data.moods != undefined){
						if ($.isArray(data.moods.link)) {
						   for ( var i = 0; i < data.moods.link.length; i++) {
						      var link = data.moods.link[i]['@href'];
						      segueLinkMood(link);
						   }
						} else {
						   var link = data.moods.link['@href'];
						   segueLinkMood(link);
						}
					}
			
				},
				error: function(data) {
					alert("Erro na invocação");
				}
			});
		}      
		
		
		function segueLinkMood(link) {
		   $.ajax({
		      url : host + link,
		      type : 'GET',
		      success : function(data) {
		         adicionaMoodNovaAoGrid(data.mood);
		      },
		      error : function(data) {
		         alert("Ocorreu um erro");
		      }
		   });
		}
		
		function adicionaMoodNovaAoGrid(mood) {

			 var data = "<tr>"
	  		      + "<td>" + mood.name + "</td>"
	  		      + "<td>" + mood.description + "</td>"
	  		      + "<td>" + mood.status + "</td>"
	  		      + "<td><input type=\"button\" class=\"btn btn-danger\"  value=\"Apagar\" "
	  		      + "onclick=\"apagaMood('" + mood.id + "');\" /> " 
	  		    + "<input type=\"button\" value=\"Editar\"  class=\"btn btn-primary\""
	  		      + "onclick=\"carregaMood('" + mood.id + "');\" /></td>" 
	  		      + "</tr>";

	  		   $("#grid").append(data);
	  		}

		function adicionaMood() {
			var data = $("#criarMoodForm").serializeJSON();

  			if(data.id){
  				atualizaMood(data);
  	  		}else {
  	  			criaMood(data);
  	  	  	}

  		    
  		}

  		function criaMood(data){
  			 data = "{\"mood\":" + JSON.stringify(data) + "}";
   			$.ajax({
   			   url : host + 'mood',
   			   type : 'POST',
   			   contentType : 'application/json',
   			   data : data,
   			   success : function(data) {
   				  alert("Incluído com sucesso!");
   				  $("#criarMoodForm")[0].reset();
   				  listarMood();
   			   },
   			   error : function(data) {
   				 
 						console.log(data);
 						alert("Ocorreu um erro: " + data.status + " "
 								+ data.statusText);	
   			   }
   			 });
  		}


		function atualizaMood(data) {
			id = data.id;
  			data = "{\"mood\":" + JSON.stringify(data) + "}";
  			$.ajax({
  			   url : host + 'mood/'+id,
  			   type : 'PUT',
  			   contentType : 'application/json',
  			   data : data,
  			   success : function(data) {
  				  alert("Incluído com sucesso!");
  				  $("#criarMoodForm")[0].reset();
  				  listarMood();
  			   },
  			   error : function(data) {
						console.log(data);
						alert("Ocorreu um erro: " + data.status + " "+ data.statusText);
					
  			   }
  			 });
  		}

		function apagaMood(id) {
  			$.ajax({
  						url : host + 'mood/' + id,
  						type : 'DELETE',
  						success : function(data) {
  							listarMood();
  						},
  						error : function(data) {
  							console.log(data);
  							alert("Ocorreu um erro: " + data.status + " "
  									+ data.statusText);
  						}
  					});

  		}

		function carregaMood(id) {
					$.ajax({
						url : host + 'mood/' + id,
						type : 'GET',
						success : function(data) {
							var frm =  $("#criarMoodForm");
							 $.each(data.mood, function(key, value){
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
		
		