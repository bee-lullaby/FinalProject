<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page import="vn.edu.fpt.timetabling.utils.Const"%>
<html>
<head>
<title>Home</title>
<link rel="shortcut icon" href="resources/images/logo_fpt.png">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="https://apis.google.com/js/client:platform.js?onload=start"
	async defer></script>
<script>
	function start() {
		gapi
				.load(
						'auth2',
						function() {
							auth2 = gapi.auth2
									.init({
										client_id : '938120756299-8u3vpmb12jptc4jn8h5bdqftlurbfll9.apps.googleusercontent.com',
									// Scopes to request in addition to 'profile' and 'email'
									//scope: 'additional_scope'
									});
						});
	}
</script>
<link href="resources/css/metro.css" rel="stylesheet" type="text/css">
<link href="resources/css/metro-icons.css" rel="stylesheet"
	type="text/css">
<link href="resources/css/home.css" rel="stylesheet" type="text/css">
</head>
<body style="background: url('resources/images/bg.jpg'); background-repeat: no-repeat; background-size: cover;">
	<div class="fade-in"
		style="width: 40%; margin: 0 auto; text-align: center; padding: 25px; top: 100px; background: rgba(25, 25, 25, .2);">
		<div class="tile-area-title">
			<img src="resources/images/logo_fu_home.png" />
		</div>
		<div style="margin: 25px 0; color: white;">
			<h1>FU TIMETABLING</h1>
		</div>
		<div style="width: 100%;"></div>
		<button class="button" id="signinButton" style="width: 200px;">
			SIGN IN</button>

		<div id="bottom-bar">
			<div id="nav-bottom-bar" style="color: white;">
				<a href="http://fpt.edu.vn">FPT University</a><a href="#">Contact</a><a
					href="#">About Us</a>
			</div>
		</div>
	</div>
	<script>
		$('#signinButton').click(function() {
			// signInCallback defined in step 6.
			auth2.grantOfflineAccess({
				'redirect_uri' : 'postmessage'
			}).then(signInCallback);
		});
		function signInCallback(authResult) {
			if (authResult['code']) {
				// Send the code to the server
				$

						.ajax({
							type : 'POST',
							url : 'http://localhost:8080/FUTimetabling/',
							contentType : 'application/octet-stream; charset=utf-8',
							success : function(result) {
								window.location = 'http://localhost:8080/FUTimetabling/'
										+ result;
							},
							processData : false,
							data : authResult['code']
						});
			} else {
				// There was an error.
			}
		}
	</script>
</body>
</html>
