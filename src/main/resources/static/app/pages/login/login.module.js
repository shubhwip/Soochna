/**
 * @author Shubham Jain
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.login', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaMain.login', {
          url: '/login',
          templateUrl: 'app/pages/login/login.html',
          title: 'Soochna Admin Login',
          controller: 'LoginPageCtrl as LoginCtrl'
        });
  }

})();
