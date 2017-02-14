allControllers.controller('addResourceCtrl', ['$scope','$http','currentAuthService','$route','$templateCache','$location','modalService','selectListService','$routeParams',
                                                    function ($scope,$http,currentAuthService,$route,$templateCache,$location,modalService,selectListService,$routeParams) {
	
  $scope.getResourceType = selectListService.getResourceType();
  $scope.getMaterialType = selectListService.getMaterialType();
  $scope.getEpsg = selectListService.getEpsg();
  $scope.getIdentifierType = selectListService.getIdentifierType();
  $scope.getContributorType = selectListService.getContributorType();
  $scope.getRelationType = selectListService.getRelationType();
  $scope.registeredObjectType = selectListService.registeredObjectType();
  $scope.getTrueFalse = selectListService.getTrueFalse();
  
  
  
  
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
	  $scope.resource.eventType="registered"
	  
  }
  
  reset();
  
  $scope.addContributor = function(){
	  $scope.resource.contributorses.push({}); 
  }
  
  $scope.removeContributor = function(index){
	  if($scope.resource.contributorses.length > 1){
		  $scope.resource.contributorses.splice(index,1);
	  }
  }
  
  $scope.addRelatedResource = function(){
	  $scope.resource.relatedResourceses.push({}); 
  }
  
  $scope.removeRelatedResource = function(index){
	  if($scope.resource.relatedResourceses.length > 1){
		  $scope.resource.relatedResourceses.splice(index,1);
	  }
  }
	
  $scope.mintResource = function(){	  
	  $http.post('web/mintJson.do', 
			  $scope.resource
      ,{
    	  headers: {
    	        'Content-Type': 'application/json'
    	    }
      }).success(function(data,status) {
      	
      	 if(data[0].mintStatusCode != 200){
      		modalService.showModal({}, {    	            	           
       		 headerText: data[0].mintStatus ,
   	         bodyText: "FAILURE:" + data[0].mintLog
      		});
      	 }else if(data[0].databaseStatusCode != 200){
      		modalService.showModal({}, {    	            	           
       		 headerText: data[0].databaseStatus ,
   	         bodyText: "FAILURE:" + data[0].databaseLog + "<br>CAUSED:" + data[0].databaseExceptionCause
      		});
      	 }else{
      		modalService.showModal({}, {    	            	           
      		  headerText: "IGSN Minted" ,
      	      bodyText: "IGSN HANDLE: <a href='"+data[0].handle+"'>" + data[0].handle + "</a>",
      	      redirect: "/meta/"+data[0].sampleId
         	}).then(function (result) {	            
	              $location.path('/addresource');	            
	         });;
      	 }
      	
      	
      	
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
	  document.getElementById("form-resource-identifier").focus();
  }
  
  
  
  var getResource = function(igsn){
	 $http.get('public/getResource.do', {
		 	params: {		 		 	
		 		resourceIdentifier: igsn,
		 		}
     }).success(function(data,status) {
    	if(data.inputMethod != "form"){
    		modalService.showModal({}, {    	            	           
	    		 headerText: "Resource uneditiable" ,
		         bodyText: "Only resource that have been entered by this web form and not altered by any other means can be edited."		        	   
	    	 });
    		$location.path("/addresource");
    		
    	}else{
    		$scope.resource = data; 
         	$scope.resource.eventType="updated"
    	}     	 
     	
     }).error(function(response,status) {
    	if(status == 401){
    		$location.path("/login/" + $routeParams.igsn);
    	}else{
    		modalService.showModal({}, {    	            	           
	    		 headerText: response.header ,
		           bodyText: "FAILURE:" + response.message
	    	 }).then(function (result) {	            
	              $location.path('/addresource');	            
	         });
    	}
    	
     });	  
   }
	 
  
  if($routeParams.igsn){
	  $scope.resource.eventType="updated"
	  getResource($routeParams.igsn); 
  }
  
}]);