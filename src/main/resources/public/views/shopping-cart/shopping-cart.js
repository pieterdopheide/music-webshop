'use strict';

angular.module('myApp.shopping-cart', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/shopping-cart', {
    templateUrl: 'views/shopping-cart/shopping-cart.html',
    controller: 'ShoppingCartCtrl'
  });
}])

.controller('ShoppingCartCtrl', ['$scope', '$http', '$route', function($scope, $http, $route) {

	$http.get('http://localhost:8080/rest/shopping-cart/').success(function(data) {
		$scope.products = data;
		console.log('succes list');
		console.log(data);
	});
	$http.get('http://localhost:8080/rest/shopping-cart/count').success(function(data) {
		$scope.cartCount = data;
		console.log('Found ' + data + ' product(s) in cart');
	});
	$http.get('http://localhost:8080/rest/shopping-cart/total').success(function(data) {
		$scope.cartTotal = data;
		console.log('Total: $' + data);
	});

	$scope.add = function(product) {
		$http.post('http://localhost:8080/rest/shopping-cart/add/' + product.id).success(function(data) {
			alert('Added ' + product.name + ' to cart!');
			console.log('Added product to cart:');
			console.log(product);
			$route.reload(); // reload page
		});
	}
	$scope.remove = function(product) {
		$http.delete('http://localhost:8080/rest/shopping-cart/remove/' + product.id).success(function(data) {
			alert('Removed ' + product.name + ' from cart!');
			console.log('Removed product from cart:');
			console.log(product);
			$route.reload(); // reload page
		});
	}

}]);