$("ul").on("click", "li", function () {
	$(this).toggleClass("completed");
	//if ($(this).css("color") === "rgb(0, 0, 0)") {
	// 	$(this).css({
	// 		color : "gray",
	// 		textDecoration: "line-through"
	// 	});
	// }
	// else {
	// 	$(this).css({
	// 		color : "black",
	// 		textDecoration: "none"
	// 	});
	// }
});


$("ul").on("click", "span.icon", function() {
	$(this).parent().fadeOut(400);
});

$("input[type=text]").on("keypress", function (event) {
	if(event.which === 13) {
		var todoText = $(this).val();
		$(this).val("");
		$("ul").append("<li><span class = 'icon'><i class='far fa-trash-alt'></i></span>" + "<span class = 'task'>" + todoText + "</span>" + "</li>");
	}
});

$(".plus").on("click", function() {
	$("input").fadeToggle(200);
});
