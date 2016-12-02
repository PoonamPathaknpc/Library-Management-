<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.dbproject.model.*"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="firstStyle.css">

<title>University of Texas at Dallas Online Library Management</title>
<meta name="author" content="Poonam Pathak">

<header class="header">
<h1>Official Library Desk Management for UTD Staff</h1>

</header>


</head>

<body>

<script type="text/javascript">

function ValidationSearch() {
	var isbn = $("#searchBox").val();
	if (jQuery.trim(isbn).length == 0) {
		alert("Empty Search not allowed");
		return false;
	}
	
	return true;
}
</script>

	<blockquote id="quote">Books Wash away from the Soul the
		Dust of Everydays Life!!</blockquote>
<nav>
	<div class="navmenu">
		<div class="leftnavmenu">
			<ul>
				<li><a href="#">Borrower Management</a>
				<ul class="sub-menu">
					<li><a href="Register.jsp">Register Borrower</a></li>
					<li><a href="BorrowerDetails.jsp">View Borrower Details</a></li>
				</ul>
                </li>
                
				<li><a href="#">Book Management </a>
				<ul class="sub-menu">					
					<li><a href="BookCheckout.jsp">Book (Pay Fine/Checkin/Checkout Book)</a></li>
					
				</ul>
				</li>
				<li><form action="SearchBooks" method="post">
				<button  type="submit"  name="UpdateFines">Update fines for the day</button>
				</form></li>
				<li><form action="Hellotest" method="get">
				<button  type="submit"  name="try">try this for fun!!</button>
				</form></li>
			</ul>
		</div>
	</div> 
	
	</nav>
	
	<br>
	<br>
	<br>
	
	<div  class="image">
		<form class="form-horizontal" action="SearchBooks" method="post"
					onsubmit="return ValidationSearch()"> 

			<div class="form-group">
				<label>Search</label> <input id="searchBox" type="text" id="input"
					name="isbn" placeholder="10-Digit to be entered if ISBN"
					value="SearchBook"> <input type="submit" name="Search"
					value="Search">

			</div>

		</form>
		
	</div>

</body>


</html>