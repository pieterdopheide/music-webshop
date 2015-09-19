'use strict';

angular.module('myApp.navigation', ['ngRoute'])

.controller('NavigationCtrl', ['$scope', '$location', '$http', '$rootScope', function($scope, $location, $http, $rootScope) { // Login tutorial, added $http, $rootScope

	$scope.isActive = function (viewLocation) {
		if ($location.path().substr(1, 8) === 'products'){
			return viewLocation === '/products';
		}
		return viewLocation === $location.path();
	};

$scope.logout = function() {
  $http.post('logout', {}).success(function() {
    $rootScope.authenticated = false;
    $location.path("/");
  }).error(function(data) {
    $rootScope.authenticated = false;
  });
}

  // end tutorial code

}]);