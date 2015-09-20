'use strict';

angular.module('myApp.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'views/login/login.html',
    controller: 'LoginCtrl' // Login tutorial, changed LoginCtrl to NavigationCtrl
  });
}])

.controller('LoginCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location) {

// Login tutorial code
	// var authenticate = function(credentials, callback) {

 //    var headers = credentials ? {authorization : "Basic "
 //        + btoa(credentials.username + ":" + credentials.password)
 //    } : {};

 //    $http.get('user', {headers : headers}).success(function(data) {
 //      if (data.name) {
 //        $rootScope.authenticated = true;
 //        $rootScope.username = 'admin';
 //      } else {
 //        $rootScope.authenticated = false;
 //      }
 //      callback && callback();
 //    }).error(function() {
 //      $rootScope.authenticated = false;
 //      callback && callback();
 //    });

 //  }

 //  authenticate();
 //  $scope.credentials = {};
  // alert('before login function');
  // $scope.login = function() {
  // 	// alert('login function ' + 'username: ' + $scope.credentials.username + ' password: ' + $scope.credentials.password);
  //     authenticate($scope.credentials, function() {
  //       if ($rootScope.authenticated) {
  //         $location.path("/");
  //         $scope.error = false;
  //       } else {
  //         $location.path("/login");
  //         $scope.error = true;
  //       }
  //     });
  // };

}]);