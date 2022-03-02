<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		 <label >Insert: </label>

		<form action="addstudent">
			<input type="text" name="id"><br>
			<input type="text" name="name"><br>
			<input type="text" name="tech"><br>
			
			<input type="submit" ><br>
		
		
		</form>
				 <label >View: </label>
		
		<form action="getstudent">
			<input type="text" name="id"><br>
			
			<input type="submit" ><br>
		
		
		</form>
		 <label >Update: </label>
		<form action="updatestudent">
			<input type="text" name="id"><br>
			<input type="text" name="name"><br>
			<input type="text" name="tech"><br>
			<input type="submit" ><br>
		
		
		</form>
		
		 <label >Delete: </label>
		<form action="deletestudent">
			<input type="text" name="id"><br>
			
			<input type="submit" ><br>
		
		
		</form>
</body>
</html>