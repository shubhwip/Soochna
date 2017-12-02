/**
 * Register Controller
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.register')
    .controller('RegisterPageCtrl', RegisterPageCtrl);

  /** @ngInject */
  function RegisterPageCtrl($http, RegisterService, $state, toastr) {

// 1. http://localhost:8181/api/register
// Request:
// {
// 	"firstName":"Ankit",
// 	"lastName":"Jain",
// 	"uid": "111122223333",
// 	"password": "ankit",
// 	"ministryName": "Ministry of Commerce",
// 	"email":"ankit@abc.com"
// }

		  
	  var self = this;
	  
	  self.init = function() {
		  self.user = {};
		  self.user.ministryName = '';
		  self.user.uid = '';
		  self.user.firstName = '';
		  self.user.lastName = '';
		  self.user.email = '';
		  self.user.password = '';
	  }


	  self.register = function() {
	  	RegisterService.register(self.user).then(function(response) {
	  		  console.log("register");
	  		  if(self.registerForm.$invalid)
			  		return;
			  self.user.password = '';
			  self.user.confirmPassword = '';
			  console.log("register successfull");
		  },function(errResponse){
		  		toastr.info("Registration Successful Redirecting to Login Page", "Information");
		  	    $state.go('soochnaMain.login', {} , { reload: 'soochnaMain.login' });
				console.log("Error");
		  });
	  }	  
	  self.init();

	  }

})();
