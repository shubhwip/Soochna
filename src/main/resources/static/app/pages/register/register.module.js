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
          sidebarMeta: {
            icon: 'ion-android-home',
            order: 0,
          },
          controller: 'RegisterPageCtrl as RegisterCtrl'
        });
  }

})();
