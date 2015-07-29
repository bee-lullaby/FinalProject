<!DOCTYPE html>
<html>
<head lang="en">

<!--     <link rel='shortcut icon' type='image/x-icon' href="resources/images/favicon.ico" /> -->
<title>FPT Timetabling</title>

<link href="resources/css/metro.css" rel="stylesheet" type="text/css">
<link href="resources/css/metro-icons.css" rel="stylesheet"
	type="text/css">
<link href="resources/css/flaticon.css" rel="stylesheet" type="text/css">
<script src="resources/js/jquery-2.1.3.min.js"></script>
<script src="resources/js/metro.js"></script>
<script src="resources/js/staff.js"></script>

<style>
.tile-area-controls {
	position: fixed;
	right: 40px;
	top: 40px;
}

.tile-group {
	left: 100px;
}

.tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big,
	.tile-super {
	opacity: 0;
	-webkit-transform: scale(.8);
	transform: scale(.8);
}

.charm.right-side {
	width: 300px;
	right: -300px;
}

#charmSettings .button {
	margin: 5px;
}

@media screen and (max-width: 640px) {
	.tile-area {
		overflow-y: scroll;
	}
	.tile-area-controls {
		display: none;
	}
}

@media screen and (max-width: 320px) {
	.tile-area {
		overflow-y: scroll;
	}
	.tile-area-controls {
		display: none;
	}
}

.glyph {
	display: inline-block;
	width: 120px;
	color: white;
	top: 50%;
	margin-top: -40px;
	left: 50%;
	margin-left: -32px;
	text-align: center;
}

.glyph .glyph-icon {
	padding: 10px;
	display: block;
	font-family: "Flaticon";
	font-size: 64px;
	line-height: 1;
}

.glyph .glyph-icon:before {
	font-size: 64px;
	color: #666;
	margin-left: 0;
}
</style>
<script>
	(function($) {
		$.StartScreen = function() {
			var plugin = this;
			var width = (window.innerWidth > 0) ? window.innerWidth
					: screen.width;

			plugin.init = function() {
				setTilesAreaSize();
				if (width > 640)
					addMouseWheel();
			};

			var setTilesAreaSize = function() {
				var groups = $(".tile-group");
				var tileAreaWidth = 80;
				$.each(groups, function(i, t) {
					if (width <= 640) {
						tileAreaWidth = width;
					} else {
						tileAreaWidth += $(t).outerWidth() + 80;
					}
				});
				$(".tile-area").css({
					width : tileAreaWidth
				});
			};

			var addMouseWheel = function() {
				$("body").mousewheel(function(event, delta, deltaX, deltaY) {
					var page = $(document);
					var scroll_value = delta * 50;
					page.scrollLeft(page.scrollLeft() - scroll_value);
					return false;
				});
			};

			plugin.init();
		}
	})(jQuery);

	$(function() {
		$.StartScreen();

		var tiles = $(".tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super");

		$.each(tiles, function() {
			var tile = $(this);
			setTimeout(function() {
				tile.css({
					opacity : 1,
					"-webkit-transform" : "scale(1)",
					"transform" : "scale(1)",
					"-webkit-transition" : "0.5s",
					"transition" : "0.5s"
				});
			}, Math.floor(Math.random() * 500));
		});

		$(".tile-group").animate({
			left : 0
		});
	});
</script>

</head>
<body>
	<div class="tile-area fg-black" style="width: 80%; margin: 0 auot;">
		<div class="tile-area-title">
			<img src="resources/images/logo.png" style="width: 150px; top: 40px;" />
		</div>
		<div class="tile-area-controls" style="top: 30px;">
			<button
				class="image-button icon-right bg-transparent bg-hover-dark no-border">
				<span class="sub-header no-margin text-light">You are logged
					in as ${accountType}</span> <span class="icon mif-user"></span>
			</button>
			<a href="../tiles.html"
				class="square-button bg-transparent bg-hover-dark no-border"> <span
				class="mif-switch"></span>
			</a>
		</div>

		<div class="tile-group double">
			<span class="tile-group-title" style="color: black">ACCOUNT</span>

			<div class="tile-container">
				<div class="tile-large fg-white" data-role="tile"
					style="background: url(resources/images/testAvatar.jpg) top left no-repeat; background-size: cover">
					<div class="tile-content">
						<div class="padding10">
							<h1>Name</h1>
							<h5>Account</h5>
							<h5>Position</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tile-group double">
			<span class="tile-group-title" style="color: black">ACTIVITIES</span>
			<div class="tile-container">
				<div class="tile-wide bg-cyan fg-white" data-role="tile">
					<div class="tile-content iconic">
						<div class="icon flaticon-question42"></div>
					</div>
					<span class="tile-label">Classes Arrangement</span>
				</div>
				<div class="tile-wide bg-olive fg-white" data-role="tile">
					<div class="tile-content iconic">
						<div class="icon flaticon-calendar130"></div>
					</div>
					<span class="tile-label">Schedule</span>
				</div>
			</div>
		</div>
		<div class="tile-group triple">
			<span class="tile-group-title" style="color: black">MANAGEMENT</span>
			<div class="tile-container">
				<div class="tile bg-teal fg-white" data-role="tile" id="manage-teachers">
					<div class="tile-content iconic">
						<div class="icon flaticon-teacher4"></div>
					</div>
					<span class="tile-label">Teachers</span>
				</div>
				<div class="tile bg-darkMagenta fg-white" data-role="tile" id="manage-students">
					<div class="tile-content iconic">
						<span class="icon flaticon-student39"></span>
					</div>
					<span class="tile-label">Students</span>
				</div>
				<div class="tile bg-lime fg-white" data-role="tile" id="manage-specialized">
					<div class="tile-content iconic">
						<span class="icon flaticon-books30"></span>
					</div>
					<div class="tile-label">Specialized</div>
				</div>
				<div class="tile bg-red fg-white" data-role="tile" id="manage-classFPTs">
					<div class="tile-content iconic">
						<span class="icon flaticon-class6"></span>
					</div>
					<div class="tile-label">Classes</div>
				</div>
				<div class="tile bg-orange fg-white" data-role="tile" id="manage-courses">
					<div class="tile-content iconic">
						<span class="icon flaticon-open-book1"></span>
					</div>
					<span class="tile-label">Courses</span>
				</div>
				<div class="tile bg-darkViolet fg-white" data-role="tile" id="manage-departments">
					<div class="tile-content iconic">
						<span class="icon flaticon-businessman276"></span>
					</div>
					<div class="tile-label">Departments</div>
				</div>
				<div class="tile bg-darkBlue fg-white" data-role="tile" id="manage-rooms">
					<div class="tile-content iconic">
						<span class="icon flaticon-school1"></span>
					</div>
					<span class="tile-label">Rooms</span>
				</div>
			</div>
		</div>

	</div>
</body>
</html>