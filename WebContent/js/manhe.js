	var host = "http://localhost:8080/manhe/services/";
	
	function listarLayette() {  
			$.ajax({ 
				url : host + 'layette',
				type : 'GET',
				 headers: {          
	                 Accept : 'application/json'   
	  			},    
				success : function(data) {

					$('#grid tr:gt(0)').remove();
					if ($.isArray(data.cervejas.link)) {
					   for ( var i = 0; i < data.cervejas.link.length; i++) {
					      var link = data.cervejas.link[i]['@href'];
					      segueLinkCerveja(link);
					   }
					} else {
					   var link = data.cervejas.link['@href'];
					   segueLinkCerveja(link);
					}
			
				},
				error: function(data) {
					alert("Erro na invocação");
				}
			});
		}      
		
		
		function segueLinkCerveja(link) {
		   $.ajax({
		      url : host + link,
		      type : 'GET',
		      success : function(data) {
		         adicionaCervejaNovaAoGrid(data.cerveja);
		      },
		      error : function(data) {
		         alert("Ocorreu um erro");
		      }
		   });
		}
		
		function adicionaCervejaNovaAoGrid(cerveja) {

			 var data = "<tr onmouseover='verCerveja(\"" + cerveja.nome + "\")' >"
	  		      + "<td>" + cerveja.nome + "</td>"
	  		      + "<td>" + cerveja.cervejaria + "</td>"
	  		      + "<td>" + cerveja.descricao + "</td>"
	  		      + "<td>" + cerveja.tipo + "</td>"
	  		      + "<td><input type=\"button\" class=\"btn btn-danger\"  value=\"Apagar\" "
	  		      + "onclick=\"apagaCerveja('" + cerveja.nome + "');\" /> " 
	  		    + "<input type=\"button\" value=\"Editar\"  class=\"btn btn-primary\""
	  		      + "onclick=\"carregaCerveja('" + cerveja.nome + "');\" /></td>" 
	  		      + "</tr>";

	  		   $("#grid").append(data);
	  		}

		function adicionaCerveja() {
			var data = $("#criarCervejaForm").serializeJSON();

  			if(data.id){
  				atualizaCerveja(data);
  	  		}else {
  	  			criaCerveja(data);
  	  	  	}

  		    
  		}

  		function criaCerveja(data){
  			 data = "{\"cerveja\":" + JSON.stringify(data) + "}";
   			$.ajax({
   			   url : host + 'cervejas',
   			   type : 'POST',
   			   contentType : 'application/json',
   			   data : data,
   			   success : function(data) {
   				  alert("Incluído com sucesso!");
   				  $("#criarCervejaForm")[0].reset();
   				  listarCervejas();
   			   },
   			   error : function(data) {
   				 
 						console.log(data);
 						alert("Ocorreu um erro: " + data.status + " "
 								+ data.statusText);	
   			   }
   			 });
  		}


		function atualizaCerveja(data) {
			id = data.nome;
  			data = "{\"cerveja\":" + JSON.stringify(data) + "}";
  			$.ajax({
  			   url : host + 'cervejas/'+id,
  			   type : 'PUT',
  			   contentType : 'application/json',
  			   data : data,
  			   success : function(data) {
  				  alert("Incluído com sucesso!");
  				  $("#criarCervejaForm")[0].reset();
  				  listarCervejas();
  			   },
  			   error : function(data) {
						console.log(data);
						alert("Ocorreu um erro: " + data.status + " "+ data.statusText);
					
  			   }
  			 });
  		}

		function apagaCerveja(id) {
  			$.ajax({
  						url : host + 'cervejas/' + id,
  						type : 'DELETE',
  						success : function(data) {
  							listarCervejas();
  						},
  						error : function(data) {
  							console.log(data);
  							alert("Ocorreu um erro: " + data.status + " "
  									+ data.statusText);
  						}
  					});

  		}

		function carregaCerveja(id) {
					$.ajax({
						url : host + 'cervejas/' + id,
						type : 'GET',
						success : function(data) {
							var frm =  $("#criarCervejaForm");
							 $.each(data.cerveja, function(key, value){
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
		
		function verCerveja(nome) {
  			$('#images').html('Loading...');
			$.ajax({
				url: '/handson-20/services/cervejas/' + nome,
				method: 'GET',
				headers: {
					Accept: 'image/*'
				},
				processData: false,
				cache: false,
				success: function (data) {
					console.info(data);
					$('#images').html('<img width=300 height=500 src="data:image/png;base64,' + data + '" />');
				},
				failure: function (data) {
					console.info('ERRO');
					$('#images').html('');
				}
			});
 	  	}
		