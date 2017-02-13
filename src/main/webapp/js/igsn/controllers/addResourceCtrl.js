allControllers.controller('addResourceCtrl', ['$scope','$http','currentAuthService','$route','$templateCache','$location','modalService','selectListService',
                                                    function ($scope,$http,currentAuthService,$route,$templateCache,$location,modalService,selectListService) {
	
  $scope.getResourceType = selectListService.getResourceType();
  $scope.getMaterialType = selectListService.getMaterialType();
  $scope.getEpsg = selectListService.getEpsg();
  $scope.getIdentifierType = selectListService.getIdentifierType();
  $scope.getContributorType = selectListService.getContributorType();
  $scope.getRelationType = selectListService.getRelationType();
  $scope.registeredObjectType = selectListService.registeredObjectType();
  $scope.getTrueFalse = selectListService.getTrueFalse();
  
  
  $scope.update = false;
  
  var reset = function(){
	  $scope.resource={};  
	  $scope.resource.contributorses=[];
	  $scope.resource.contributorses[0] = {};	  
	  $scope.resource.relatedResourceses=[];
	  $scope.resource.relatedResourceses[0] ={};
	  $scope.resource.materialTypeses=[];
	  $scope.resource.materialTypeses[0] ={};
	  $scope.resource.resourceTypeses=[];
	  $scope.resource.resourceTypeses[0] ={};
	  $scope.resource.curationDetailses=[];
	  $scope.resource.curationDetailses[0]={};
	  
	  
	  $scope.resource.classificationses=[];
	  $scope.resource.classificationses[0]={};
	  $scope.resource.alternateIdentifierses=[];
	  $scope.resource.alternateIdentifierses[0] ={};
	  $scope.resource.sampledFeatureses=[];
	  $scope.resource.sampledFeatureses[0]={};
	  
	  
  }
  
  reset();
  
  $scope.addContributor = function(){
	  $scope.resource.contributors.push({}); 
  }
  
  $scope.removeContributor = function(index){
	  $scope.resource.contributors.splice(index,1);
  }
  
  $scope.addRelatedResource = function(){
	  $scope.resource.relatedResources.push({}); 
  }
  
  $scope.removeRelatedResource = function(index){
	  $scope.resource.relatedResources.splice(index,1);
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
      }).error(function(response,status) {
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
		  if(wkt.type.toUpperCase()!="POINT" || wkt.type.toUpperCase()!="POLYGON" || wkt.type.toUpperCase()!="LINESTRING" || 
				  wkt.type.toUpperCase()!="CURVE" || wkt.type.toUpperCase()!="MULTIPOLYGON" || wkt.type.toUpperCase()!="TRIANGLE"
					  || wkt.type.toUpperCase()!="MULTICURVE" || wkt.type.toUpperCase()!="CURVEPOLYGON"){
			  $scope.location.wkt.$invalid = true;  
		  }else{
			  $scope.location.wkt.$invalid = false;
		  }
		  
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
	  $scope.resource.resourceIdentifier=$scope.resource.prefix;
  }
  
  
  
  var getResource = function(){
	 $http.get('public/getResource.do', {
		 	params: {
		 		resourceIdentifier: 'CSTST-Demo3',		 		
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
	 
  
  if($scope.update){
	 getResource(); 
  }
  
}]);