	var host = "http://localhost:8080/Manhe/services/";
	
	function listarUser() {  
			$.ajax({ 
				url : host + 'user',
				type : 'GET',
				 headers: {          
	                 Accept : 'application/json'   
	  			},    
				success : function(data) {
					$('#grid tr:gt(0)').remove();
					if(data.users != undefined){
						if ($.isArray(data.users.link)) {
						   for ( var i = 0; i < data.users.link.length; i++) {
						      var link = data.users.link[i]['@href'];
						      segueLinkUser(link);
						   }
						} else {
						   var link = data.users.link['@href'];
						   segueLinkUser(link);
						}
					}
			
				},
				error: function(data) {
					alert("Erro na invocação");
				}
			});
		}      
		
		
		function segueLinkUser(link) {
		   $.ajax({
		      url : host + link,
		      type : 'GET',
		      success : function(data) {
		         adicionaUserNovaAoGrid(data.user);
		      },
		      error : function(data) {
		         alert("Ocorreu um erro");
		      }
		   });
		}
		
		function adicionaUserNovaAoGrid(user) {

			 var data = "<tr>"
	  		      + "<td>" + user.name + "</td>"
	  		      + "<td>" + user.description + "</td>"
	  		      + "<td>" + user.status + "</td>"
	  		      + "<td><input type=\"button\" class=\"btn btn-danger\"  value=\"Apagar\" "
	  		      + "onclick=\"apagaUser('" + user.id + "');\" /> " 
	  		    + "<input type=\"button\" value=\"Editar\"  class=\"btn btn-primary\""
	  		      + "onclick=\"carregaUser('" + user.id + "');\" /></td>" 
	  		      + "</tr>";

	  		   $("#grid").append(data);
	  		}

		function adicionaUser() {
			var data = $("#criarUserForm").serializeJSON();

  			if(data.id){
  				atualizaUser(data);
  	  		}else {
  	  			criaUser(data);
  	  	  	}

  		    
  		}

  		function criaUser(data){
  			 data = "{\"user\":" + JSON.stringify(data) + "}";
   			$.ajax({
   			   url : host + 'user',
   			   type : 'POST',
   			   contentType : 'application/json',
   			   data : data,
   			   success : function(data) {
   				  alert("Incluído com sucesso!");
   				  $("#criarUserForm")[0].reset();
   				  listarUser();
   			   },
   			   error : function(data) {
   				 
 						console.log(data);
 						alert("Ocorreu um erro: " + data.status + " "
 								+ data.statusText);	
   			   }
   			 });
  		}


		function atualizaUser(data) {
			id = data.id;
  			data = "{\"user\":" + JSON.stringify(data) + "}";
  			$.ajax({
  			   url : host + 'user/'+id,
  			   type : 'PUT',
  			   contentType : 'application/json',
  			   data : data,
  			   success : function(data) {
  				  alert("Incluído com sucesso!");
  				  $("#criarUserForm")[0].reset();
  				  listarUser();
  			   },
  			   error : function(data) {
						console.log(data);
						alert("Ocorreu um erro: " + data.status + " "+ data.statusText);
					
  			   }
  			 });
  		}

		function apagaUser(id) {
  			$.ajax({
  						url : host + 'user/' + id,
  						type : 'DELETE',
  						success : function(data) {
  							listarUser();
  						},
  						error : function(data) {
  							console.log(data);
  							alert("Ocorreu um erro: " + data.status + " "
  									+ data.statusText);
  						}
  					});

  		}

		function carregaUser(id) {
					$.ajax({
						url : host + 'user/' + id,
						type : 'GET',
						success : function(data) {
							var frm =  $("#criarUserForm");
							 $.each(data.user, function(key, value){
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
		
		