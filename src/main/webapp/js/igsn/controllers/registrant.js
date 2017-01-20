allControllers.controller('registrantCtrl', ['$scope','$http','addRegistrantModalService','addPrefixModalService','modalService',
                                                    function ($scope,$http,addRegistrantModalService,addPrefixModalService,modalService) {
	

	$scope.addRegistrant = function(){
		addRegistrantModalService.open();
	}
	
	$scope.addPrefix = function(){
		addPrefixModalService.open();
	}
	
	$http.get('listRegistrants.do')     
     .success(function(data) {
    	 $scope.registrantList = data;
    	 
     })
     .error(function(data, status) {    	
    	 modalService.showModal({}, {    	            	           
	           headerText: "Retrieve registrant" ,
	           bodyText: "FAILURE: Please contact administrator"
    	 });			       
     })
     
     $http.get('listPrefix.do')     
     .success(function(data) {
    	 $scope.prefixList = data;
    	 
     })
     .error(function(data, status) {    	
    	 modalService.showModal({}, {    	            	           
	           headerText: "Retrieve prefix" ,
	           bodyText: "FAILURE: Please contact your administrator"
    	 });			       
     })
	
	
	
}]);