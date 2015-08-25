<!DOCTYPE html>
<html>
<head lang="en">

<title>FPT Timetabling</title>

<link rel="shortcut icon" href="resources/images/logo_fpt.png">
<link href="resources/css/metro.css" rel="stylesheet" type="text/css">
<link href="resources/css/metro-icons.css" rel="stylesheet"
	type="text/css">
<link href="resources/css/flaticon.css" rel="stylesheet" type="text/css">
<script src="resources/js/jquery-2.1.3.min.js"></script>
<script src="resources/js/metro.js"></script>

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
		
		
		$("[id^='activity-'").on("click", function() {
			var pageName = $(this).attr("id").split("-")[1];
			window.location = "teacherPage/" +pageName;
		});
	});
</script>

</head>
<body>
	<div class="tile-area fg-black" style="width: 80%; margin: 0 auto;">
		<div class="tile-area-title">
			<img src="resources/images/logo_fu_home.png" style="width: 150px; top: 40px;" />
		</div>
		<div class="tile-area-controls" style="top: 30px;">
			<button
				class="image-button icon-right bg-transparent bg-hover-dark no-border">
				<span class="sub-header no-margin text-light">You are logged
					in as <b>${account}</b></span> <span class="icon mif-user"></span>
			</button>
			<a href="/FUTimetabling/logout"
				class="square-button bg-transparent bg-hover-dark no-border"> <span
				class="mif-switch"></span>
			</a>
		</div>
		<div class="tile-group double">
			<span class="tile-group-title" style="color: black">ACTIVITIES</span>
			<div class="tile-container">
				<div class="tile bg-cyan fg-white" data-role="tile" id="activity-viewTimetable">
					<div class="tile-content iconic">
						<div class="icon flaticon-calendars16"></div>
					</div>
					<span class="tile-label">View Timetable</span>
				</div>
			</div>
		</div>
		
	</div>

</body>
</html>