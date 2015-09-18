'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.navigation',
  'myApp.home',
  'myApp.products',
  'myApp.product-detail',
  'myApp.shopping-cart'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.
		when('/products', {
			templateUrl: 'views/products/products.html',
			controller: 'ProductListCtrl'
		}).
		when('/products/:productId', {
	        templateUrl: 'views/product-detail/product-detail.html',
	        controller: 'ProductDetailCtrl'
	    }).
	    when('/shopping-cart', {
	        templateUrl: 'views/shopping-cart/shopping-cart.html',
	        controller: 'ShoppingCartCtrl'
	    }).
		otherwise({redirectTo: '/home'});
}]);