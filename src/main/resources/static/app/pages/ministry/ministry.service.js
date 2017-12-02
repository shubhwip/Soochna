
(function () {
  'use strict';

  angular.module('Soochna.pages.ministry')
      .service('MinistryService',MinistryService);

  /** @ngInject */
  function MinistryService($http) {

	  var self = this;

	  self.getMinistries = function() {
		  return $http.get("/api/ministry/all");
	  }
  }

})();
