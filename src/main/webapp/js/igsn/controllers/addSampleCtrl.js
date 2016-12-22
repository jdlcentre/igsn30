allControllers.controller('addSampleCtrl', ['$scope','$http','currentAuthService','$route','$templateCache','$location','modalService','selectListService',
                                                    function ($scope,$http,currentAuthService,$route,$templateCache,$location,modalService,selectListService) {
	
  $scope.getResourceType = selectListService.getResourceType();
  $scope.getMaterialType = selectListService.getMaterialType();
  $scope.getEpsg = selectListService.getEpsg();
  


	
   $scope.addSample = function(){
	   
   }
   
}]);