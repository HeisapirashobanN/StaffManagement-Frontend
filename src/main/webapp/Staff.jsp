<%@ page import = "com.staff" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.6.0.min.js"></script>
        <script src="Components/Staff.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
			<div class="row">
				<div class="col">
					<h1>Staff Management</h1>
					<form id="formStaff" name="formStaff" method="POST" action="Staff.jsp">
						Staff name: 
						<input 
							id="name" 
							name="name" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Staff Job Title: 
						<input 
							id="title"
							name="title" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Staff Mail: 
						<input 
							id="mail" 
							name="mail" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Staff Contact: 
						<input 
							id="contact" 
							name="contact" 
							type="text" 
							class="form-control form-control-sm"
						><br>
						
						Staff Gender: 
						<input 
							id="gender" 
							name="gender" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						<input 
							id="btnSave" 
							name="btnSave" 
							type="button" 
							value="Save" 
							class="btn btn-primary"
						>

						<input type="hidden" name="hidStaffIDSave" id="hidStaffIDSave" value="">
					</form>
				
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<div id="divStaffGrid">
						<%
							staff staff = new staff(); 
							out.print(staff.readStaff());
						%>
					</div>
				</div>
			</div>
		</div>

</body>
</html>