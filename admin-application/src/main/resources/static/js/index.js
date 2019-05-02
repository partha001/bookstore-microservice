//$(document).ready(function() {
//	
//	//$("#message").hide();
//	
//	$("#login").click(function(){		
//		var flag= true;
//		var message = "";
//		console.log('hello');
//		if($("#username").val()==""){
//			flag = false;
//			message = "username cant be left blank !";
//		}else if($("#password").val()==""){
//			flag = false;
//			message = "password cant be left blank !";
//		}
//		
//		if(flag){
//			$("#message").hide();
//			$("#message").text("");
//			
//			var postData = {};
//			postData["username"] = $("#username").val();
//			postData["password"] = $("#password").val();
//			console.log(postData);
//			$.post("/login",postData);
//			
////			$.get("/test1", function(data, status){
////				   console.log('get called');
////				 });
//		}else{
//			$("#message").show();
//			$("#message").text(message);
//		}	
//	});
//	
//});
