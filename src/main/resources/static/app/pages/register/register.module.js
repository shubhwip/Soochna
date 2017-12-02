/**
 * @author Shubham Jain
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.register', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaMain.register', {
          url: '/register',
          templateUrl: 'app/pages/register/register.html',
          title: 'Soochna Admin Registartion',
          controller: 'RegisterPageCtrl as RegisterCtrl'
        });
  }

})();
