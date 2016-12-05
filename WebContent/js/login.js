var host = "http://localhost:8080/Manhe/services/";
function logar(e){
	var data = $("#Login_Form").serializeJSON();
	 data = "{\"user\":" + JSON.stringify(data) + "}";
	$.ajax({
	   url : host + 'user',
	   type : 'POST',
	   contentType : 'application/json',
	   data : data,
	   success : function(data) {
		   $("#Login_Form").submit();
	   },
	   error : function(data) {
		 
				console.log(data);
				alert("Ocorreu um erro: " + data.status + " "
						+ data.statusText);	
	   }
	 });
}

$("#Login_Form").submit(function(e){
    e.preventDefault();
    logar(e);
 });