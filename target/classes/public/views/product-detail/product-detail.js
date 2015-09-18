'use strict';

angular.module('myApp.product-detail', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/products/:productId', {
    templateUrl: 'views/product-detail/product-detail.html',
    controller: 'ProductDetailCtrl'
  });
}])

.controller('ProductDetailCtrl', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http) {

	$http.get('http://localhost:8080/rest/products/' + $routeParams.productId).success(function(data) {
		$scope.product = data;
		console.log('succes detail');
		console.log(data);
	});

}]);