/**
 * Login Controller
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.login')
    .controller('LoginPageCtrl', LoginPageCtrl);

  /** @ngInject */
  function LoginPageCtrl($http, LoginService, $state) {
	  
	  var self = this;
	  
	  self.init = function() {
		  self.user = {};
		  self.user.uid = '';
		  self.user.password = '';
	  }
	  
	  self.login = function(){
	  	LoginService.login(self.user).then(function(response) {
	  		   if(self.loginForm.$invalid)
			  		return;
			  	self.user.uid = '';
			  	self.user.password = '';
			  	console.log("login successfull");
		  },function(errResponse){
				$state.go('soochnaAdmin.welcome', {} , { reload: 'soochnaAdmin.welcome' });
		  		console.log(errResponse.data);
			  	if (errResponse.status == 410){
						console.log("Error");
			  	}
			  	else
					  console.log("Error Login Failed");
		  });
	  }
	  self.init();

	  }
})();
