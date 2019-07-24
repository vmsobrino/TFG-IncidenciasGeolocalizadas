<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="icon" href="./img/favicon.ico">
	    
	    <title>Incidencias Geo-Localizadas</title>
	    
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
		<script src="https://apis.google.com/js/platform.js" async defer></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<script>
      		// google callback. This function will redirect to our login struts server
      		function onSignIn(googleUser) {
         		var profile = googleUser.getBasicProfile();
         		var redirectUrl = 'login';
         </script>
		<meta name="google-signin-scope" content="profile email">
		<meta name="google-signin-client_id" content="1051037700045-ibeuv0apliff5qem557abkunqbilh230.apps.googleusercontent.com">
	    <!-- Custom styles for this template -->
	    <link href="./css/signin.css" rel="stylesheet">
		<!-- style>
			.g-signin2 {
				width: 100%;
			}
			.g-signin2 > div {
				margin: 0 auto;
			}
		</style-->
	</head>
	<body>
		<div class="container">
	        <h1 class="form-signin-heading" align="center">GIMT</h1>
	        <br>
	        <h3 class="form-signin-heading" align="center">Gestión de Incidencias geolocalizadas</h3>
	        <br>
			<div align="center"
				class="g-signin2"
			 	data-onsuccess="onSignIn"
			 	data-theme="light"
			 	data-width="150"
			 	data-height="50"
			 	data-scope="https://www.googleapis.com/auth/plus.login"
				data-accesstype="offline"
			 	data-redirecturi="http://localhost:8080/gimt-web/loginAction.action">button
			 </div>
	        <br>
	        <h6 class="form-signin-heading" align="center">Utilice su cuenta Google para el acceso</h6>
	        <br>
	        <% if (null != request.getAttribute("error")) { %>
	        <div class="alert alert-danger">
	        	<strong>Error.</strong> <% out.print(request.getAttribute("error")); %>
			</div>
			<% } %>
	    </div> <!-- /container -->
	</body>
</html>