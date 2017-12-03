/**
 * Login Service
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.login')
      .service('LoginService',LoginService);

  /** @ngInject */
  function LoginService($http) {
	  
	  var self = this;
	  
	  self.login = function(user) {
      console.log(user);
      $http.defaults.headers.post["Content-Type"] = "application/json";
      return $http.post("/api/login", user);
	  }
  }

})();
