'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.navigation',
  'myApp.home',
  'myApp.products',
  'myApp.product-detail',
  'myApp.shopping-cart',
  'myApp.login',
  'myApp.register'
]).
config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) { // Login tutorial, added $httpProvider
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
	    when('/login', {
	        templateUrl: 'views/login/login.html',
	        controller: 'LoginCtrl'
	    }).
		otherwise({redirectTo: '/home'});

		// Login tutorial
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);
