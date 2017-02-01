var app = angular.module('app', ['ngRoute','allControllers','ui.bootstrap','ngAnimate','ngFileUpload','ui-leaflet','nemLogging']);

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
    	  redirectTo: '/addresource'
      
      }).
      when('/addresource', {
        templateUrl: 'restricted/addResource.html'
     
      }).  
      when('/registrant', {
          templateUrl: 'restricted/registrant.html',
          resolve:{
              "check":function($location,currentAuthService){   
                  if(currentAuthService.getAuthenticated() && !currentAuthService.getIsAllocator()){ 
                	  $location.path('/');    //redirect user to home.
                      alert("You don't have access here");
                  }
              }
          }
        }). 
      when('/meta/:igsn', {
          templateUrl: 'views/meta.html'        
      }).    
      when('/login/:igsn', {
          templateUrl: 'views/login.html'        
      }).      
      otherwise({
        redirectTo: '/'
      });
  
  }]);




app.directive('jqdatepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
         link: function (scope, element, attrs, ngModelCtrl) {
            element.datepicker({
            	dateFormat: 'd/M/yy',
                onSelect: function (date) {
                	ngModelCtrl.$setViewValue(date);
                	ngModelCtrl.$render();
                    scope.$apply();
                }
            });
        }
    };
});

