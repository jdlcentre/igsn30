allControllers.controller('metaCtrl', ['$scope','$http','currentAuthService','$templateCache','$location','modalService','$routeParams',
                                                    function ($scope,$http,currentAuthService,$templateCache,$location,modalService,$routeParams) {
	
	var mymap = L.map('igsn-map').setView([-27.7, 133], 4);
	
	L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
	    maxZoom: 18	   
	}).addTo(mymap);
	
	
	$scope.resource={};
	if($routeParams.igsn){
		$http.get('public/getResource.do', {
		 	params: {
		 		resourceIdentifier: $routeParams.igsn,		 		
		 		}
        }).success(function(data,status) {
        	$scope.resource = data; 
        	
        	if(data.location.wkt!=null){
	        	var wkt = new Wkt.Wkt();        	
	        	wkt.read(data.location.wkt);        	
	        	wkt.toObject();	        	
	        	var myLayer = L.geoJSON().addTo(mymap);
	        	myLayer.addData(wkt.toJson());
	        	if(wkt.type=="point"){
	        		mymap.panTo(L.latLng(wkt.components[0].y, wkt.components[0].x));
	        	}else if(wkt.components[0][0]){
	        		var arr = [];
	        		for(var i = 0; i<wkt.components[0].length;i++){
	        			arr.push(L.latLng(wkt.components[0][i].y, wkt.components[0][i].x));
	        		}
	        		var polygon = L.polygon(arr);
	        		mymap.panTo(polygon.getBounds().getCenter());
	        	}
	        	
        	}
        	
        	
	    }).error(function(response,status) {
	    	if(status == 401){
	    		$location.path("/login/" + $routeParams.igsn);
	    	}else{
	    		modalService.showModal({}, {    	            	           
		    		 headerText: response.header ,
			           bodyText: "FAILURE:" + response.message
		    	 });
	    	}
	    	
	    });	  
	}
	
	
	
   
}]);