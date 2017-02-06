allControllers.controller('addResourceCtrl', ['$scope','$http','currentAuthService','$route','$templateCache','$location','modalService','selectListService',
                                                    function ($scope,$http,currentAuthService,$route,$templateCache,$location,modalService,selectListService) {
	
  $scope.getResourceType = selectListService.getResourceType();
  $scope.getMaterialType = selectListService.getMaterialType();
  $scope.getEpsg = selectListService.getEpsg();
  $scope.getIdentifierType = selectListService.getIdentifierType();
  $scope.getContributorType = selectListService.getContributorType();
  $scope.getRelationType = selectListService.getRelationType();
  $scope.registeredObjectType = selectListService.registeredObjectType();
  
  $scope.form={};  
  $scope.form.contributors=[];
  $scope.form.contributors[0] = {};
  $scope.form.relatedResources=[];
  $scope.form.relatedResources[0] = {};

  $scope.update = true;
  
  $scope.addContributor = function(){
	  $scope.form.contributors.push({}); 
  }
  
  $scope.removeContributor = function(index){
	  $scope.form.contributors.splice(index,1);
  }
  
  $scope.addRelatedResource = function(){
	  $scope.form.relatedResources.push({}); 
  }
  
  $scope.removeRelatedResource = function(index){
	  $scope.form.relatedResources.splice(index,1);
  }
	
  $scope.mintResource = function(){
	  	
	  
	  $http.post('web/mintJson.do', 
			  $scope.resource
      ,{
    	  headers: {
    	        'Content-Type': 'application/json'
    	    }
      }).success(function(data,status) {
      	//On success, do something here.
      }).error(function(response) {
    	modalService.showModal({}, {    	            	           
    		 headerText: response.header ,
	           bodyText: "FAILURE:" + response.message
    	 });
      });	  
  }
  
  $scope.testIsWKT = function(wktString){
	  if(!wktString){
		  $scope.location.wkt.$invalid = false;
		  return;
	  }
	  try{		  
		  var wkt = new Wkt.Wkt();        	
		  wkt.read(wktString);        			 
		  $scope.location.wkt.$invalid = false;
	  }catch(error){
		  $scope.location.wkt.$invalid=true;
	  }
	  
  }
  
  
  
  //getAllocatedPrefix.do
   
  var getAllocatedPrefix = function(){
      $http.get('web/getAllocatedPrefix.do', {}).success(function(response) {
		 $scope.allocatedPrefixes = response;	  
	  }).error(function(data) {
		  modalService.showModal({}, {    	            	           
	           headerText: "Retrieve prefix" ,
	           bodyText: "FAILURE: Please contact your administrator"
    	 });	
	  });
  }
  getAllocatedPrefix();
  
  
  $scope.changePrefix = function(){
	  $scope.form.resourceIdentifier=$scope.form.prefix;
  }
  
  
  
  var getResource = function(){
	 $http.get('public/getResource.do', {
		 	params: {
		 		resourceIdentifier: 'CSTST1',		 		
		 		}
     }).success(function(data,status) {
     	$scope.resource = data;      	     	
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
  //getResource();
  
}]);