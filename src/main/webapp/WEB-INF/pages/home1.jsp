<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<script>
function doSubmitAction() {
    	
    	var userId = $('#userid').val();
    	
    			$.ajax({
    			url: userId,
    			type: "POST",
    			success: function(response) {
    				$('#dbInfo').html("DB Store set");
    			},
    			error: function(e) {
    				alert('Error: ' + e);
    			}
    		});
    }
</script>
<form name="user" action="">
User ID: <input type="text" name="userId" id="userid">

<input type="submit" id="userSubmit" onclick="doSubmitAction()" >
</form>
</body>
</html>