/**
 * 
 */
function removeProduct(index) {
		var pagelink = "removeProduct/";
		var url = pagelink + index + ".htm";
		$.ajax({
			url : url,
			type : "get"
		}).done(function(data) {
			$("#content").html(data);
		});
	}