var departServices = angular.module('departmentServices', ['ngResource']);

departServices.factory('Department', ['$resource', 
		function($resource){
	 return $resource('departments', {}, {
	      query: {method:'GET', isArray:true}
	    });
	  }]);