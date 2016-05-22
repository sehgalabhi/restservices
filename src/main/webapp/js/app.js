var testApp= angular.module('testApp', []);

testApp.controller('DepartmentControl', function($scope, $http) {
	  $http.get('http://localhost:8080/rest/departments').success(function(data) {
		    $scope.departments = data;
		  });
	  $http.get('http://localhost:8080/rest/departments/count').success(function(data) {
		    $scope.total = data;
		  });
	  
	  $http.get('http://localhost:8080/rest/departments/10').success(function(data) {
		    $scope.find = data;
		  });

	/*	$scope.departments= [
	                     
{'departmentId': 'Nexus S',
    'departmentName': 'Fast just got faster with Nexus S.'},
   {'departmentId': 'Motorola XOOM™ with Wi-Fi',
    'departmentName': 'The Next, Next Generation tablet.'},
   {'departmentId': 'MOTOROLA XOOM™',
    'departmentName': 'The Next, Next Generation tablet.'}    
	                     
	                     
	                     ];*/
	
	
	
});