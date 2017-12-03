

(function() {
	'use strict';

	angular.module('Soochna.pages.profile')
		.controller('ProfilePageCtrl', ProfilePageCtrl);

	function ProfilePageCtrl($scope, ProfileService, $http) {

		self.init = function() {
				ProfileService.getUser().then(function(response) {
					$scope.user = response.data;
      			},function(errResponse){
         			console.log("Error");
      			});
		}

		self.logout = function() {
			console.log("skjbxkj");
						$http.post("/api/logout");
		}

		self.init();
	}

})();
