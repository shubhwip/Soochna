

(function() {
	'use strict';

	angular.module('Soochna.pages.ministry')
		.controller('MinistryPageCtrl', MinistryPageCtrl);

	function MinistryPageCtrl($scope, MinistryService) {

		self.init = function() {
				MinistryService.getMinistries().then(function(response) {
						 $scope.ministries = response.data.ministry;
						 console.log("get ministries successfull");
      			},function(errResponse){
         			console.log("Error");
      			});
		}

		self.init();
	}

})();
