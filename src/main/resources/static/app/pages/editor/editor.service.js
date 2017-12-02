/**
 * Editor Service
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.editor')
      .service('EditorService',EditorService);

  /** @ngInject */
  function EditorService($http) {
	  
	  var self = this;
	  
	  self.publish = function(notice) {
	  	  console.log(notice.noticeTitle);
	  	  console.log(notice.noticeContent);
		  $http.defaults.headers.post["Content-Type"] = "application/json";
		  return $http.post("api/notice/save", JSON.stringify(notice));
	  }
  }

})();
