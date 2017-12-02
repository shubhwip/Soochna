
(function () {
  'use strict';

  angular.module('Soochna.pages.soochna')
      .service('SoochnaService',SoochnaService);

  /** @ngInject */
  function SoochnaService($http) {

	  var self = this;

	  self.getAllNotices = function() {
		  return $http.get("/api/notice/ministry");
	  }
  }

})();
