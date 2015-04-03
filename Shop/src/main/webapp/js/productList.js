/**
 * 
 */
function showPaginProductList(page) {
		var pagelink = "getProductList/page/";
		var url = pagelink + page + ".htm";
		$.ajax({
			url : url,
			method : "get"
		}).done(function(data) {
			$("#content").html(data);
			changeBackgrndLoadPage();
		});
	}

	function showProductList() {
		var type;
		var genre;
		var xmlhttp;
		type = document.getElementById("prodType");
		type = type.options[type.selectedIndex].value;
		genre = document.getElementById("genre");
		genre = genre.options[genre.selectedIndex].text;
		var pagelink = "getProductList/type/";
		var url = pagelink + type + "/genre/" + genre + ".htm";
		$.ajax({
			url : url,
			method : "get"
		}).done(function(data) {
			$("#content").html(data);
			changeBackgrndLoadPage();
		});
	}
	
	function showProductListByName(name) {
		var pagelink = "getProductList/name/";
		var url = pagelink + name + ".htm";
		$.ajax({
			url : url,
			method : "get"
		}).done(function(data) {
			$("#content").html(data);
			changeBackgrndLoadPage();
		});
	}
	
	function addToShoppingCart() {
		var type = "";
		var genre = "";
		var name = "";
		var products = [];
		$("input:checkbox[id=product]:checked").each(
				function() {
					{
						var selectedProducts = $(this).closest('tr')
								.find('input[id=product]').prop('checked',
										false);
						$(selectedProducts).each(function(index, value) {

							if (index == 0) {
							}
							if (index == 1) {
								type = $(this).val();
							} else if (index == 2) {
								genre = $(this).val();
							} else if (index == 3) {
								name = $(this).val();
							} else {
								"Wrong index"
							}
						})
						products.push(type, genre, name);
					}
				})
		var jsonProducts = JSON.stringify(products);
		url="addToCart.htm"
			$.ajax({
				url : url,
				method : "post",
				contentType : "application/json",
				dataType : "json",
				data : jsonProducts,
				success : function() {
					alert("Product(s) was(re) successfully added to your cart");
				}
			});
	}

	function getGenres(type) {
		var pagelink = "populateDropDownList/page/";
		var url = pagelink + type + ".htm";
		$.ajax({
			url : url,
			method : "get",
			contentType : "application/json",
			dataType : "json",
			async : false,
			success : function(genres) {
				removeAllGenres();
				$.each(genres, function(index, value) {
					$("#genre").append($("<option />").text(value.genre));
				});
			}
		});
	}
	
	function removeAllGenres() {
		var genres = document.dropDown.genre.length;
		for (var i = genres; i >= 0; i--) {
			document.dropDown.genre.options[i] = null;
		}
	}
	
	jQuery.cookie = function(name, value, options) {
		if (typeof value != 'undefined') {
			options = options || {};
			if (value === null) {
				value = '';
				options.expires = -1;
			}
			var expires = '';
			if (options.expires) {
				var date;
				if (typeof options.expires == 'number') {
					date = new Date();
					date.setTime(date.getTime()
							+ (options.expires * 24 * 60 * 60 * 1000));
				} else {
					date = options.expires;
				}
				expires = '; expires=' + date.toUTCString(); 
			}
			var path = options.path ? '; path=' + (options.path) : '';
			var domain = options.domain ? '; domain=' + (options.domain) : '';
			var secure = options.secure ? '; secure' : '';
			document.cookie = [ name, '=', encodeURIComponent(value), expires,
					path, domain, secure ].join('');
		} else {
			var cookieValue = null;
			if (document.cookie) {
				var cookies = document.cookie.split(';');
				for (var i = 0; cookies.length > i; i++) {
					var cookie = jQuery.trim(cookies[i]);
					if (cookie.substring(0, name.length + 1) == (name + '=')) {
						cookieValue = decodeURIComponent(cookie
								.substring(name.length + 1));
						break;
					}
				}
			}
			return cookieValue;
		}
	};
	
	if ($.cookie('remember_genres') != null) {
		$("#genre").append(
				$("<option />").text($.cookie('remember_genres')).attr(
						'selected', 'selected'));
	}
	if ($.cookie('remember_prodType') != null) {
		$('.prodType option[value="' + $.cookie('remember_prodType') + '"]')
				.attr('selected', 'selected');
	}
	$('.prodType').change(function() {
		$.cookie('remember_prodType', $('.prodType option:selected').val(), {
			expires : 90,
			path : '/'
		});
	});
	$('.genre').change(function() {
		$.cookie('remember_genres', $('.genre option:selected').val(), {
			expires : 90,
			path : '/'
		});
	});