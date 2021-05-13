<%@ page import = "com.dao.PurchaseDao" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/productPurchase.js"></script>

</head>
<body>

<form id="formItem" name="formItem">

date <input type="text" name="date" id="date" />
total <input type="text" name="total" id="total" />
<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave"
 name="hidItemIDSave" value="">
 
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 PurchaseDao purchase = new PurchaseDao();
 out.print(purchase.items());
 %>
</div>

</body>
</html>