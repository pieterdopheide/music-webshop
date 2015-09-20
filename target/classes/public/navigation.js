'use strict';

angular.module('myApp.navigation', ['ngRoute'])

.controller('NavigationCtrl', ['$scope', '$location', '$http', '$rootScope', '$route', function($scope, $location, $http, $rootScope, $route) { // Login tutorial, added $http, $rootScope

	$scope.isActive = function (viewLocation) {
		if ($location.path().substr(1, 8) === 'products'){
			return viewLocation === '/products';
		}
		return viewLocation === $location.path();
	};

// Login tutorial code
$scope.logout = function() {
  $http.post('logout', {}).success(function() {
    $rootScope.authenticated = false;
    $location.path("/");
  }).error(function(data) {
    $rootScope.authenticated = false;
  });
}

	var authenticate = function(credentials, callback) {

    var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
    } : {};

    $http.get('user', {headers : headers}).success(function(data) {
      if (data.name) {
        $rootScope.authenticated = true;
        $rootScope.username = 'admin';
      } else {
        $rootScope.authenticated = false;
      }
      callback && callback();
    }).error(function() {
      $rootScope.authenticated = false;
      callback && callback();
    });

  }

  authenticate();
  $scope.credentials = {};
    $scope.login = function() {
  	// alert('login function ' + 'username: ' + $scope.credentials.username + ' password: ' + $scope.credentials.password);
      authenticate($scope.credentials, function() {
        if ($rootScope.authenticated) {
          $location.path("/");
          $scope.error = false;
          $route.reload(); // when logged in, refresh to show username in navigationbar
        } else {
          $location.path("/login");
          $scope.error = true;
        }
      });
  };

  // end tutorial code
// $scope.getUsername = function() {
  $http.get('http://localhost:8080/user').success(function(data) {
		$scope.username = data.name;
		console.log('Username: ');
		console.log(data.name);
	});

}]);