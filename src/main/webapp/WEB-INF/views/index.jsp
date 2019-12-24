<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Keep-Board</title>
</head>
<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->
	<form action="add" method="post">
		Note title: <input type=text name="notetitle" /> <br /> <br /> Note
		Content: <input type=text name="notecontent"/ > <br /> <br />
		Note Status: <select name="notestatus">
			<option>Select</option>
			<option value="completed">Completed</option>
			<option value="ongoing">Ongoing</option>
		</select> <br /> <br /> <input type="submit" value="submit" />
	</form>
	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
	<%-- <table border=2>
		<tr>
			<th>ID</th>
			<th>Note Title</th>
			<th>Note Content</th>
			<th>Note Status</th>
			<th>Created At</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${list }" var="l">
			<tr>
				<td>${l.id}</td>
				<td>${l.notetitle}</td>
				<td>${l.notecontent }</td>
				<td>${l.notestatus }</td>
				<td>${l.createdat }</td>
				<td><a href="deletecontact?id=${l.id }"> Delete </a></td>
				<td><a href="editcontactpage?id=${l.id }"> Edit </a></td>
			</tr>
		</c:forEach>
	</table> --%>
</body>
</html>