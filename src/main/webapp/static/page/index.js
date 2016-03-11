require.config({
	baseUrl : 'static', // 相对于index.html页面文件的地址
	paths : { // 这里配置的地址，都是相对于上方的baseUrl的
		jquery : 'jquery/jquery-1.12.1.min',
		avalon : 'avalon/avalon.modern',
		domReady : 'domReady',
		mmHistory : 'avalon/mmHistory',
		mmRouter : 'avalon/mmRouter'
	},
	shim : {
		avalon : {
			exports : "avalon"
		},
		mmHistory : {
			deps : [ 'avalon' ]
		},
		mmRouter : {
			deps : [ 'avalon' ]
		}
	}
});

require([ 'avalon', "domReady!" ], function() {
	var vm = avalon.define({
		$id : "hyjj",
		contentUrl : "static/templates/welcome.html",
		showHome : function(){
			console.log("showHome");
			$("li.hyjj-top-nav").removeClass('active');
			$("#nav-home").addClass('active');
		},
		showDs : function(){
			$("li.hyjj-top-nav").removeClass('active');
			$("#nav-ds").addClass('active');
			console.log("showDs");
		}
	});
	avalon.scan();
});
