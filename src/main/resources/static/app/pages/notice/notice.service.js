
(function () {
  'use strict';

  angular.module('Soochna.pages.soochna')
      .service('NoticeService',NoticeService);

  /** @ngInject */
  function NoticeService($http) {

	  var self = this;

	  self.getNoticesByMinistry = function(ministryName) {

	  }
  }

})();
