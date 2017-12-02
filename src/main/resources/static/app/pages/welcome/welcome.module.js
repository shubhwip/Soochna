/**
 * @author Shubham Jain
 */
(function () {
  'use strict';

  angular.module('Soochna.pages.welcome', [])
      .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaAdmin.welcome', {
          url: '/dashboard',
          templateUrl: 'app/pages/welcome/welcome.html',
          title: 'Welcome to Soochna',
          sidebarMeta: {
            icon: 'ion-android-home',
            order: 0,
          },
          controller: 'WelcomePageCtrl as WelcomeCtrl'
        });
  }

})();
