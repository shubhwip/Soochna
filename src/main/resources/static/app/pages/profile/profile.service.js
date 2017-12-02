(function () {
  'use strict';

  angular.module('Soochna.pages.profile')
      .service('ProfileService',ProfileService);

  /** @ngInject */
  function ProfileService($http) {
	  
	  var self = this;
	  
	  self.getUser = function() {
      return $http.get("/api/user/details");
	  }
  }

})();
