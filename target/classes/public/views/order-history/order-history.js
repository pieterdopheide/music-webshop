'use strict';

angular.module('myApp.order-history', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/order-history', {
    templateUrl: 'views/order-history/order-history.html',
    controller: 'OrderHistoryCtrl'
  });
}])

.controller('OrderHistoryCtrl', ['$scope', '$http', function($scope, $http) {

	$http.get('http://localhost:8080/user/orders').success(function(data) {
		$scope.orders = data;
		console.log('succes retrieving orders');
		console.log(data);
	});

}]);