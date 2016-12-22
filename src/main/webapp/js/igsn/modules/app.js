var app = angular.module('app', ['ngRoute','allControllers','ui.bootstrap','uiGmapgoogle-maps','ngAnimate','ngFileUpload']);

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
    	  redirectTo: '/addsample'
      
      }).
      when('/addsample', {
        templateUrl: 'restricted/addSample.html'
     
      }).    
      when('/login', {
          templateUrl: 'views/login.html'        
      }).      
      otherwise({
        redirectTo: '/'
      });
  
  }]);

app.config(function(uiGmapGoogleMapApiProvider) {
    uiGmapGoogleMapApiProvider.configure({
        //    key: 'your api key',
        //v: '3.20', //defaults to latest 3.X anyhow
        libraries: 'weather,geometry,visualization'
    });
})










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

