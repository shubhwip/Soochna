
(function () {
  'use strict';

  angular.module('Soochna.pages.soochna')
      .service('NoticeService',NoticeService);

  /** @ngInject */
  function NoticeService($http) {

	  var self = this;

	  self.getNoticesByMinistry = function(ministryName) {
      console.log(ministryName + "sjj");
		  return $http.get("/api/notice/byMinistry?ministry-name="+ministryName);
	  }
  }

})();
