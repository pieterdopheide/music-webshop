'use strict';

angular.module('myApp.navigation', ['ngRoute'])

.controller('NavigationCtrl', ['$scope', '$location', function($scope, $location) {

	$scope.isActive = function (viewLocation) {
		if ($location.path().substr(1, 8) === 'products'){
			return viewLocation === '/products';
		}
		return viewLocation === $location.path();
	};

}]);