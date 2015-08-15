<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
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

</head>
<body>
	<div style="width: 40%; margin: 0 auto; text-align: center;">
		<div style="margin: 25px 0;">
			<h1>FPT UNIVERSITY</h1>
		</div>
		<div style="width: 100%;"></div>
		<button class="button" id="signinButton" style="width: 200px;">
			SIGN IN</button>
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
					url : 'http://localhost:8080/Timetabling/',
					contentType : 'application/octet-stream; charset=utf-8',
					success : function(result) {
						window.location = 'http://localhost:8080/Timetabling/'
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
