	var host = "http://localhost:8080/Manhe/services/";
	
	function listarLayette() {  
			$.ajax({ 
				url : host + 'layette',
				type : 'GET',
				 headers: {          
	                 Accept : 'application/json'   
	  			},    
				success : function(data) {
					$('#grid tr:gt(0)').remove();
					if(data.layettes != undefined && data.layettes.length > 0){
						if ($.isArray(data.layettes.link)) {
						   for ( var i = 0; i < data.layettes.link.length; i++) {
						      var link = data.layettes.link[i]['@href'];
						      segueLinkLayette(link);
						   }
						} else {
						   var link = data.layettes.link['@href'];
						   segueLinkLayette(link);
						}
					}
			
				},
				error: function(data) {
					alert("Erro na invocação");
				}
			});
		}      
		
		
		function segueLinkLayette(link) {
		   $.ajax({
		      url : host + link,
		      type : 'GET',
		      success : function(data) {
		         adicionaLayetteNovaAoGrid(data.layette);
		      },
		      error : function(data) {
		         alert("Ocorreu um erro");
		      }
		   });
		}
		
		function adicionaLayetteNovaAoGrid(layette) {

			 var data = "<tr>"
	  		      + "<td>" + layette.name + "</td>"
	  		      + "<td>" + layette.description + "</td>"
	  		      + "<td>" + layette.status + "</td>"
	  		      + "<td><input type=\"button\" class=\"btn btn-danger\"  value=\"Apagar\" "
	  		      + "onclick=\"apagaLayette('" + layette.id + "');\" /> " 
	  		    + "<input type=\"button\" value=\"Editar\"  class=\"btn btn-primary\""
	  		      + "onclick=\"carregaLayette('" + layette.id + "');\" /></td>" 
	  		      + "</tr>";

	  		   $("#grid").append(data);
	  		}

		function adicionaLayette() {
			var data = $("#criarLayetteForm").serializeJSON();

  			if(data.id){
  				atualizaLayette(data);
  	  		}else {
  	  			criaLayette(data);
  	  	  	}

  		    
  		}

  		function criaLayette(data){
  			 data = "{\"layette\":" + JSON.stringify(data) + "}";
   			$.ajax({
   			   url : host + 'layette',
   			   type : 'POST',
   			   contentType : 'application/json',
   			   data : data,
   			   success : function(data) {
   				  alert("Incluído com sucesso!");
   				  $("#criarLayetteForm")[0].reset();
   				  listarLayettes();
   			   },
   			   error : function(data) {
   				 
 						console.log(data);
 						alert("Ocorreu um erro: " + data.status + " "
 								+ data.statusText);	
   			   }
   			 });
  		}


		function atualizaLayette(data) {
			id = data.nome;
  			data = "{\"layette\":" + JSON.stringify(data) + "}";
  			$.ajax({
  			   url : host + 'layettes/'+id,
  			   type : 'PUT',
  			   contentType : 'application/json',
  			   data : data,
  			   success : function(data) {
  				  alert("Incluído com sucesso!");
  				  $("#criarLayetteForm")[0].reset();
  				  listarLayettes();
  			   },
  			   error : function(data) {
						console.log(data);
						alert("Ocorreu um erro: " + data.status + " "+ data.statusText);
					
  			   }
  			 });
  		}

		function apagaLayette(id) {
  			$.ajax({
  						url : host + 'layettes/' + id,
  						type : 'DELETE',
  						success : function(data) {
  							listarLayettes();
  						},
  						error : function(data) {
  							console.log(data);
  							alert("Ocorreu um erro: " + data.status + " "
  									+ data.statusText);
  						}
  					});

  		}

		function carregaLayette(id) {
					$.ajax({
						url : host + 'layettes/' + id,
						type : 'GET',
						success : function(data) {
							var frm =  $("#criarLayetteForm");
							 $.each(data.layette, function(key, value){
								    $('[id='+key+']', frm).val(value);
							});
						},
						error : function(data) {
							console.log(data);
							alert("Ocorreu um erro: " + data.status + " "
									+ data.statusText);
						}
					});

		}
		
		