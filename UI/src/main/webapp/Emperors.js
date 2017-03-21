function Page(url, title, request, callback){
	this.urlRegExp = new RegExp("^"+url+"$");
	this.title = title;
	this.request = request;
	this.callback = callback;
};
Page.prototype.suitable = function(url){
	return this.urlRegExp.test(url);
};
Page.prototype.load = function(data, url, push=true){
	console.log("loaded " + url + " data: " + data);
	var title = "";
	if (typeof this.title === 'function') {
		title = this.title(this, data);
	}else{
		title = this.title;
	}
	document.title = title;
	if(push)
		window.history.pushState(data, title, url);
	$("#items").empty();
	this.callback(this, data);
};
Page.prototype.go = function(url){
	console.log("go to " + url);
	if(this.request) {
		var request = this.request;
		var matches = this.urlRegExp.exec(url);
		if(matches.length > 1){
			request += matches[1];
		}
		if(matches.length > 2){
			request += "/" + matches[2];
		}
		if(matches.length > 3){
			request += "/" + matches[3];
		}
		var self = this;
		$.getJSON(request, function (data) {
			self.load(data, url);
		});
	}
	else
		this.load(null, url);
};
var pager = {
	pages: [],
	add: function(page){
		this.pages.push(page);
	},
	getPageByUrl: function(url){
		for(var i in this.pages)
			if(this.pages[i].suitable(url))
				return this.pages[i];
		return this.pages[0];
	},
	go: function(url){
		this.getPageByUrl(url).go(url);
	}
};
window.onpopstate = function(e){
	pager.getPageByUrl(document.location).load(e.state, document.location, false);
};
pager.add(new Page(
	"/404",
	"Not found",
	undefined,
	function(page, data){
		$("#items").text("Error: not found");
	}
));
pager.add(new Page(
	".*//[^/]+/",
	"Categories",
	"/Shop/rest/category/",
	function(page, data){
		for(var i in data){
			$("#items").append('<article class="z1"><a class="category" href="http://localhost:8080/category/'+data[i].id+'">'+
			'<h2>'+data[i].name+'</h2>'+
			'</a></article>');
			$(".category").each(function() {
				$(this).click(function() {
					pager.go($(this).attr('href'));
					return false;
				});
			});
		}
	}
));
pager.add(new Page(
	".*//[^/]+/category/([0-9]*)",
	function(page, data){
		return "Category " + data.name;
	},
	"/Shop/rest/category/get/",
	function(page, data){
		$.getJSON("/Shop/rest/product/getbycategory/" + data.id, function (data) {
			for(var i in data){
				$("#items").append('<article class="z1">'+
				'<a class="product" href="http://localhost:8080/product/'+data[i].id+'"><h2>'+data[i].name+'</h2></a>'+
				'<desc>'+data[i].description+'</desc>'+
				'<price>'+data[i].price+'</price>'+
				'</article>');
				$(".product").each(function() {
					$(this).click(function() {
						pager.go($(this).attr('href'));
						return false;
					});
				});
			}
		});
	}
));
pager.add(new Page(
	".*//[^/]+/product/([0-9]*)",
	function(page, data){
		return "Product " + data.name;
	},
	"/Shop/rest/product/get/",
	function(page, data){
		$("#items").append('<article class="z1">'+
		'<h2>'+data.name+'</h2>'+
		'<desc>'+data.description+'</desc>'+
		'<price>'+data.price+'</price>'+
		'</article>');
	}
));

pager.go(document.location);
