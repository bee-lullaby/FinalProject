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
		gapi.load('auth2', function() {
			auth2 = gapi.auth2.init({
				client_id : '938120756299-8u3vpmb12jptc4jn8h5bdqftlurbfll9.apps.googleusercontent.com',
				// Scopes to request in addition to 'profile' and 'email'
				//scope: 'additional_scope'
			});
		});
	}
</script>
<meta name="google-signin-scope" content="profile email">
</head>
<body>
	<h1>Hello world!</h1>
	<P>The time on the server is ${serverTime}.</P>
	<button id="signinButton">Sign in with Google</button>
	<label id="email"></label>
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
				$.ajax({
					type : 'POST',
					url : 'http://localhost:8080/fpt/',
					contentType : 'application/octet-stream; charset=utf-8',
					success : function(result) {
						if(result === undefined || result === null || result === "") {
							document.getElementById('email').innerHTML = "Error, login again";
						} else {
							var temp = JSON.parse(result);
							if(temp !== undefined && temp !== null && 
									temp.email !== undefined && temp.email !== null) {
								// Hide the sign-in button now that the user is authorized, for example:
								$('#signinButton').attr('style', 'display: none');
								document.getElementById('email').innerHTML = temp.email;
							} else {
								document.getElementById('email').innerHTML = "Error, login again";
							}
						}
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
