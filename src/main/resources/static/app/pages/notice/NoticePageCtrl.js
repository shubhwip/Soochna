(function() {
	'use strict';

	angular.module('Soochna.pages.notice')
		.controller('NoticePageCtrl', NoticePageCtrl);

	function NoticePageCtrl($scope, NoticeService) {

		// It will be take from user session
		self.ministryName="hrd";

		self.init = function () {
					NoticeService.getNoticesByMinistry(self.ministryName).then(function(response) {
							 $scope.notices = response.data.notices;
							 console.log("get notices by ministry successfull");
	      			},function(errResponse){
	         			console.log("Error");
	      			});
		}

		self.init();
	}

})();
