'use strict';

angular.module('myApp.products', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/products', {
    templateUrl: 'views/products/products.html',
    controller: 'ProductListCtrl'
  });
}])

.controller('ProductListCtrl', ['$scope', '$http', function($scope, $http) {

	$http.get('http://localhost:8080/rest/products').success(function(data) {
		$scope.products = data;
		console.log('succes list');
		console.log(data);
	});

}]);