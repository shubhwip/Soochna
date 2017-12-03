
(function () {
  'use strict';

  angular.module('Soochna.pages.notice')
      .service('NoticeService',NoticeService);

  /** @ngInject */
  function NoticeService($http) {

	  var self = this;

	  self.getNoticesByMinistry = function() {
		  return $http.get("/api/notice/byMinistry");
	  }
  }

})();
