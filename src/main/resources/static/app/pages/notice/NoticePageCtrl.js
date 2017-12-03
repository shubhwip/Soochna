(function() {
	'use strict';

	angular.module('Soochna.pages.notice')
		.controller('NoticePageCtrl', NoticePageCtrl);

	function NoticePageCtrl($scope, NoticeService) {

		jQuery(document).ready(function () {
			$(".soochnaContent").readmore({
			    speed: 100, // (in milliseconds)
				maxHeight: 100, // (in pixels)
				collapsedHeight: 50,
				heightMargin: 16, // (in pixels, avoids collapsing blocks that are only slightly larger than maxHeight)
				moreLink: '<a href="#">Read more</a>', // (raw HTML)
				lessLink: '<a href="#">Close</a>', // (raw HTML)
				embedCSS: true, // (insert required CSS dynamically, set this to false if you include the necessary CSS in a stylesheet)
				sectionCSS: 'display: block; width: 100%;', // (sets the styling of the blocks)
				startOpen: false, // (do not immediately truncate, start in the fully opened position)
				expandedClass: 'readmore-js-expanded', // (class added to expanded blocks)
				collapsedClass: 'readmore-js-collapsed', // (class added to collapsed blocks)
				beforeToggle: function() {}, // (called after a more or less link is clicked, but before the block is collapsed or expanded)
				afterToggle: function() {} // (called after the block is collapsed or expanded)
				});
			  });


		self.init = function () {
					NoticeService.getNoticesByMinistry().then(function(response) {
							 $scope.notices = response.data.notices;
							 console.log("get notices by ministry successfull");
	      			},function(errResponse){
	         			console.log("Error");
	      			});
		}

		self.init();
	}

})();
