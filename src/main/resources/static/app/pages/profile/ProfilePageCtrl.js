

(function() {
	'use strict';

	angular.module('Soochna.pages.profile')
		.controller('ProfilePageCtrl', ProfilePageCtrl);

	function ProfilePageCtrl($scope, ProfileService) {

		self.init = function() {
				ProfileService.getUser().then(function(response) {
					self.user = response.data;
      			},function(errResponse){
         			console.log("Error");
      			});
		}

		self.init();
	}

})();
