allControllers.controller('addResourceCtrl', ['$scope','$http','currentAuthService','$route','$templateCache','$location','modalService','selectListService','$routeParams','$filter','$sce',
                                                    function ($scope,$http,currentAuthService,$route,$templateCache,$location,modalService,selectListService,$routeParams,$filter,$sce) {
	
  $scope.getResourceType = selectListService.getResourceType();
  $scope.getMaterialType = selectListService.getMaterialType();
  $scope.getEpsg = selectListService.getEpsg();
  $scope.getIdentifierType = selectListService.getIdentifierType();
  $scope.getContributorType = selectListService.getContributorType();
  $scope.getRelationType = selectListService.getRelationType();
  $scope.registeredObjectType = selectListService.registeredObjectType();
  $scope.getTrueFalse = selectListService.getTrueFalse();
  $scope.getMGAZone = selectListService.getMGAZone();
  $scope.loading=false;
 
  var parseOptionToHtmlList = function(arrayList){
	  var result = "<ul class='small' style='padding-left:10px'>";
	  for(var index in arrayList){
		  result += "<li><a target='_blank' href='"+arrayList[index]+"'>" + arrayList[index] + "</a></li>"
	  }
	  return result + "</ul>";
  }
  
  var isUndefinedOrNull =function(obj) {
      return !angular.isDefined(obj) || obj===null;
  }

  $scope.htmlResourceIdentifierPopover = $sce.trustAsHtml('<p>An example of a geosample code CSRWASC111. The first two characters must be [A-Z] and specify the code of an allocating agent. The CS code has been assigned to CSIRO. This is followed by 3 characters [A-Z] representing the project as designated by the allocating agent. The rest of the characters represent the local sample code specified by the project This can be a combination of characters, numbers and dash (-) and dot (.). See the xsd pattern constraint.</p>');
  $scope.htmlRegisteredObjectType = $sce.trustAsHtml("<p>Registered Object Type - Select the links below for definition:<br>"+parseOptionToHtmlList($scope.registeredObjectType)+"</p>")
  $scope.htmlResourceType = $sce.trustAsHtml("<p>The physical form of a resource, e.g. core, cuttings and grab.</p>" + parseOptionToHtmlList($scope.getResourceType));
  $scope.htmlMaterialType = $sce.trustAsHtml("<p>Type of material represented by the sample</p>" + parseOptionToHtmlList($scope.getMaterialType));
  $scope.htmlRelationType = $sce.trustAsHtml("<p>The relationship between the resource being registered and other entity (e.g., event, document, parent sample, etc.)</p>" + parseOptionToHtmlList($scope.getRelationType));
  $scope.htmlRelatedIdentifierType = $sce.trustAsHtml("<p>Identifier type</p>" + parseOptionToHtmlList($scope.getIdentifierType));
  $scope.htmlContributorType = $sce.trustAsHtml("<p>Contributor type</p>" + parseOptionToHtmlList($scope.getContributorType));
  $scope.htmlWellknowntext = $sce.trustAsHtml("<p>Well-known text (WKT) is a text markup language for representing vector geometry objects on a map, spatial reference systems of spatial objects and transformations between spatial reference systems(<a target='_BLANK' href='https://en.wikipedia.org/wiki/Well-known_text'>Wikipedia)</a></p>");
  $scope.htmlSRID = $sce.trustAsHtml("<p>This refers to the spatial referencing system identifier (SRID). Specify an EPSG code, e.g., '4326' is the EPSG code for the WGS84 referenced coordinate system.</p>" + parseOptionToHtmlList($scope.getEpsg))
  $scope.htmlGeographicCoordinates = $sce.trustAsHtml("<p>A geographic coordinate system that enables every location on Earth to be specified by the use of <a target='_BLANK' href='https://en.wikipedia.org/wiki/Latitude'>latitude</a> and <a target='_BLANK' href='https://en.wikipedia.org/wiki/Longitude'>longtitude</a></p>");
  $scope.htmlUTMCoordinates = $sce.trustAsHtml("<p><a target='_BLANK' href='http://www.dtpli.vic.gov.au/property-and-land-titles/geodesy/geocentric-datum-of-australia-1994-gda94'>GDA94</a> is the official geodetic datum adopted nationally across Australia on 1 January 2000. It replaced the Australian Geodetic Datum 1966 (AGD66) used in Victoria. Universal Transverse Mercator (UTM) projection coordinates (easting, northing and zone)</p>");
  
  var initDataStructure = function(){
	  if($scope.resource==null){
		  $scope.resource={};
	  };    
	  if( $scope.resource.contributorses==null){
		  $scope.resource.contributorses=[];
		
	  };
	  if($scope.resource.relatedResourceses==null){
		  $scope.resource.relatedResourceses=[];
		 
	  };
	  if($scope.resource.materialTypeses==null){
		  $scope.resource.materialTypeses=[];
		 
	  };
	  if($scope.resource.resourceTypeses==null){
		  $scope.resource.resourceTypeses=[];
	  };
	  if($scope.resource.curationDetailses==null){
		  $scope.resource.curationDetailses=[];
	  };	  
	  if($scope.resource.classificationses==null){
		  $scope.resource.classificationses=[];
	  };
	  if($scope.resource.alternateIdentifierses==null){
		  $scope.resource.alternateIdentifierses=[];
	  }
	  if($scope.resource.sampledFeatureses==null){
		  $scope.resource.sampledFeatureses=[];
	  };
	  if($scope.resource.logDate==null){
		  $scope.resource.logDate={};
		  $scope.resource.logDate.eventType="registered";
	  }	  
	  
	  $scope.resource.locationInputType = "wkt";
	  $scope.useDegree = false;
		    
	  if( $scope.resource.contributorses.length==0){
		  $scope.resource.contributorses[0] = {}; 
	  };
	  if($scope.resource.relatedResourceses.length==0){
		  $scope.resource.relatedResourceses[0] ={};
	  };
	  if($scope.resource.materialTypeses.length==0){
		  $scope.resource.materialTypeses[0] ={};  
	  };
	  if($scope.resource.resourceTypeses.length==0){
		  $scope.resource.resourceTypeses[0] ={};
	  };
	  if($scope.resource.curationDetailses.length==0){
		  $scope.resource.curationDetailses[0]={};
	  };	  
	  if($scope.resource.classificationses.length==0){
		  $scope.resource.classificationses[0]={};
	  };
	  if($scope.resource.alternateIdentifierses.length==0){
		  $scope.resource.alternateIdentifierses[0] ={};
	  }
	  if($scope.resource.sampledFeatureses.length==0){
		  $scope.resource.sampledFeatureses[0]={};  
	  };
	  
	  
  }
  
  initDataStructure();
  $scope.mode='register'
	  
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

  $scope.clearLandingPage = function(){
	  $scope.resource.landingPage="";
  }
	
  $scope.mintResource = function(){	  
	  $scope.loading=true;
	  if($scope.resource.curationDetailses[0].curationDate){
		  $scope.resource.curationDetailses[0].curationDate=$filter('date')($scope.resource.curationDetailses[0].curationDate, 'yyyy-MM-dd HH:mm:ss')
	  }
	  if($scope.resource.embargoEnd){
		  $scope.resource.embargoEnd=$filter('date')($scope.resource.embargoEnd, 'yyyy-MM-dd HH:mm:ss')
	  }
	  try{
		  if($scope.resource.resourceDate.timeInstant){
			  $scope.resource.resourceDate.timeInstant=$filter('date')($scope.resource.resourceDate.timeInstant, 'yyyy-MM-dd HH:mm:ss')
		  }
	  }catch(err){
		  //VT:do nothing, if there is no time instant, ignore it.}
	  }
	  
	 
	  
	  if($scope.resource.locationInputType=="geographic" && $scope.useDegree && !isUndefinedOrNull($scope.longitude.degree) && !isUndefinedOrNull($scope.latitude.degree)){
		  var lng = (($scope.longitude.seconds?$scope.longitude.seconds/60:0) + ($scope.longitude.minutes?$scope.longitude.minutes:0))/60 + $scope.longitude.degree;
		  var lat = (($scope.latitude.seconds?$scope.latitude.seconds/60:0) + ($scope.latitude.minutes?$scope.latitude.minutes:0))/60 + $scope.latitude.degree;
		  if(!$scope.resource.location){
			  $scope.resource.location={}
		  }
		  $scope.resource.location.wkt = $scope.resource.location.wkt = "POINT(" + lng + " " + lat + ")";
	  }else if($scope.resource.locationInputType=="geographic" && !$scope.useDegree && !isUndefinedOrNull($scope.longitude.longitude) && !isUndefinedOrNull($scope.latitude.latitude)){	
		  if(!$scope.resource.location){
			  $scope.resource.location={}
		  }
		  $scope.resource.location.wkt = "POINT(" + $scope.longitude.longitude + " " + $scope.latitude.latitude + ")";
	  }
	  
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
      	      redirect: "/meta/"+data[0].sampleId,
      	      addAnother:"/addresource"
         	})
         	if($scope.resource.logDate.eventType == "destroyed" || $scope.resource.logDate.eventType == "deprecated"){
         		$scope.mode="unavailable";
	   		}
      	 }
      	$scope.loading=false;
      	
      	
      }).error(function(response,status) {
    	modalService.showModal({}, {    	            	           
    		 headerText: response.header ,
	           bodyText: "FAILURE:" + response.message
    	 });
    	$scope.loading=false;
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
		  if(wkt.type.toUpperCase() ==="POINT" || wkt.type.toUpperCase()==="POLYGON" || wkt.type.toUpperCase() ==="LINESTRING" || 
				  wkt.type.toUpperCase()==="CURVE" || wkt.type.toUpperCase()==="MULTIPOLYGON" || wkt.type.toUpperCase()==="TRIANGLE"
					  || wkt.type.toUpperCase()==="MULTICURVE" || wkt.type.toUpperCase()==="CURVEPOLYGON"){
			  $scope.location.wkt.$invalid = false;  
		  }else{
			  $scope.location.wkt.$invalid = true;
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
    		if(data.logDate.eventType != "destroyed" && data.logDate.eventType != "deprecated"){
    			 $scope.mode="update";    			
    			 $scope.resource.logDate.eventType="updated";
    		}else{
    			$scope.mode="unavailable";
    		}
         	initDataStructure();
         	         		
    	}     	 
     	
     }).error(function(response,status) {
    	if(status == 401){
    		$location.path("/login/" + $routeParams.igsn);
    	}else{
    		modalService.showModal({}, {    	            	           
	    		 headerText: response.header ,
		           bodyText: "CAUSE:" + response.message
	    	 }).then(function (result) {	            
	              $location.path('/addresource');	
	              window.location.href = $location.absUrl();
	         });
    	}
    	
     });	  
   }
	 
  
  if($routeParams.igsn){	  
	  getResource($routeParams.igsn); 
  }
  
}]);