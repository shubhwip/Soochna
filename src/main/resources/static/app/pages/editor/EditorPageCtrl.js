/**
 * @author Shubham
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.editor')
    .controller('EditorPageCtrl', EditorPageCtrl);

  function EditorPageCtrl($scope, EditorService, toastr) {
  	console.log("Editor Page Entry");

    var self = this;

    self.init = function() {
      self.notice = {};
      self.notice.noticeTitle = '';
      self.notice.noticeContent = '';
    }

  	self.publish = function() {
      EditorService.publish(self.notice).then(function(response) {
          if(self.noticeForm.$invalid)
            return;
          console.log("publish notice successfull");
      },function(errResponse){
        toastr.info("Notice Published successfully", "Information");
          console.log("Error");
      });
    }

  self.init();

  }

})();
