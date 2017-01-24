allControllers.controller('registrantCtrl', ['$scope','$http','addRegistrantModalService','addPrefixModalService','modalService',
                                                    function ($scope,$http,addRegistrantModalService,addPrefixModalService,modalService) {
	

	$scope.addRegistrant = function(){
		addRegistrantModalService.open();
	}
	
	$scope.addPrefix = function(){
		addPrefixModalService.open();
	}
	
	$scope.setSelected = function(registrant){
		$scope.selectedRegistrant = registrant;
		checkAssignedRegistrant();
	}
	
	$scope.allocate = function(prefix,username){
		 $http.get('web/allocatePrefix.do', {
			 	params: {
			 		prefix: prefix,
			 		registrant : username
			 		}
	        }).success(function(data,status) {
	        	$scope.prefixList = data;
	       	 	checkAssignedRegistrant();
		    }).error(function(response) {
		    	modalService.showModal({}, {    	            	           
			           headerText: "Allocate prefix" ,
			           bodyText: "FAILURE: Please contact administrator:"
		    	 });
		    });	   
	}
	
	$scope.unAllocate = function(prefix,username){
		 $http.get('web/unAllocatePrefix.do', {
			 	params: {
			 		prefix: prefix,
			 		registrant : username
			 		}
	        }).success(function(data,status) {
	        	$scope.prefixList = data;
	       	 	checkAssignedRegistrant();
		    }).error(function(response) {
		    	modalService.showModal({}, {    	            	           
			           headerText: "Allocate prefix" ,
			           bodyText: "FAILURE: Please contact administrator:"
		    	 });
		    });	   
	}
	
	checkAssignedRegistrant = function(){
		for(var prefixIndex in $scope.prefixList){
			for(prefixRegistrantIndex in $scope.prefixList[prefixIndex].registrants){
				if($scope.prefixList[prefixIndex].registrants[prefixRegistrantIndex].registrantid ==$scope.selectedRegistrant.registrantid){
					$scope.prefixList[prefixIndex].isAssignedToSelectedRegistrant=true;
					break;
				}else{
					$scope.prefixList[prefixIndex].isAssignedToSelectedRegistrant=false;
				}
			}
			
		}
		
	}
	
	$http.get('web/listRegistrants.do')     
     .success(function(data) {
    	 $scope.registrantList = data;
    	 $scope.setSelected($scope.registrantList[0]);
    	 
     })
     .error(function(data, status) {    	
    	 modalService.showModal({}, {    	            	           
	           headerText: "Retrieve registrant" ,
	           bodyText: "FAILURE: Please contact administrator"
    	 });			       
     })
     
     $http.get('web/listPrefix.do')     
     .success(function(data) {
    	 $scope.prefixList = data;
    	 checkAssignedRegistrant();
     })
     .error(function(data, status) {    	
    	 modalService.showModal({}, {    	            	           
	           headerText: "Retrieve prefix" ,
	           bodyText: "FAILURE: Please contact your administrator"
    	 });			       
     })
	
	
	
}]);