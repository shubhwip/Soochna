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
	  
	  self.login = function() {

	  }
  }

})();
