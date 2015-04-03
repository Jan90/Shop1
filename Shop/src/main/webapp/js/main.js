/**
 * 
 */
function changeBackgrndLoadPage() {
			var currentColor = document.getElementById('menu').style.backgroundColor;
			if (currentColor != "") {
				var count = document.getElementById("content")
						.getElementsByTagName("div").length;
				alert(count);
				for (var i = 0; count > i; i++) {
					document.getElementById("content").getElementsByTagName(
							"div")[i].style.backgroundColor = currentColor;
				}

			} else {
			}

		}
		function changeBackgrnd(id) {
			var elem = document.getElementById(id).value;
			var cook = elem;
			var count = document.body.getElementsByTagName("div").length;
			for (var i = 0; count > i; i++) {
				document.getElementsByTagName("div")[i].style.backgroundColor = cook;

			}
		}

		function nextPage(buttonId) {
			var buttonValue = document.getElementById(buttonId).value;
			switch (buttonValue) {
			case "Main":
				location.reload();
				break;
			case "Catalogue":
				var url = "getAllProductList.htm";
				$.ajax({
					url : url,
					type : "get",
					success : function(data) {
						$('#content').html(data);
						changeBackgrndLoadPage();
					}
				})
				break;

			case "Shopping cart":
				var url = "getShoppingCart.htm";
				$.ajax({
					url : url,
					type : "get"
				}).done(function(data) {
					$("#content").html(data);
					changeBackgrndLoadPage();
				});
				break;
			}
		}