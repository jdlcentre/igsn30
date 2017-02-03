allControllers.controller('addResourceCtrl', ['$scope','$http','currentAuthService','$route','$templateCache','$location','modalService','selectListService',
                                                    function ($scope,$http,currentAuthService,$route,$templateCache,$location,modalService,selectListService) {
	
  $scope.getResourceType = selectListService.getResourceType();
  $scope.getMaterialType = selectListService.getMaterialType();
  $scope.getEpsg = selectListService.getEpsg();
  $scope.getIdentifierType = selectListService.getIdentifierType();
  $scope.getContributorType = selectListService.getContributorType();
  $scope.getRelationType = selectListService.getRelationType();
  
  
  $scope.form={};  
  $scope.form.contributors=[];
  $scope.form.contributors[0] = {};
  $scope.form.relatedResources=[];
  $scope.form.relatedResources[0] = {};
  
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
	   
  }
   
}]);