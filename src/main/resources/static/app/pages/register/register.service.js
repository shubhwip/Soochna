/**
 * Register Service
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.register')
      .service('RegisterService',RegisterService);

  /** @ngInject */
  function RegisterService($http) {
	  
	  var self = this;
	  
	  self.register = function(user) {
		  $http.defaults.headers.post["Content-Type"] = "application/json";
		  return $http.post("api/register", user);
	  }
  }

})();
