'use strict';

angular.module('myApp.register', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/register', {
    templateUrl: 'views/register/register.html',
    controller: 'RegisterCtrl'
  });
}])

.controller('RegisterCtrl', ['$scope', '$http', function($scope, $http) {

	$scope.register = function(credentials) {
		var userObj = {userName: credentials.username,
						password: credentials.password
					};

	$http.post('http://localhost:8080/user/register', userObj).success(function(data) {
		// alert('Username: ' + credentials.username + ' passw: ' + credentials.password);
		alert('Registered!');
		$scope.products = data;
		console.log('succes');
		console.log(data);
	});
	}

}]);
