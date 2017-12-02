/**
 * Register Controller
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.register')
    .controller('RegisterPageCtrl', RegisterPageCtrl);

  /** @ngInject */
  function RegisterPageCtrl($http, RegisterService) {

		  
	  var self = this;
	  
	  self.init = function() {
		  self.user = {};
		  self.user.ministryName = '';
		  self.user.uid = '';
		  self.user.firstName = '';
		  self.user.lastName = '';
		  self.user.email = '';
		  self.user.password = '';
		  self.user.confirmPassword = '';
		  self.user.role= 'ADMIN';
	  }

	  self.validatePasswordMatch = function() {
		  self.registerForm.confirmPassword.$setValidity('password',false);
		  if(self.user.password !== self.user.confirmPassword)
			  return false;
		  self.registerForm.confirmPassword.$setValidity('password',true);
		  	  return true;
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
				  console.log("Error");
		  });
	  }	  
	  self.init();

	  }

})();
