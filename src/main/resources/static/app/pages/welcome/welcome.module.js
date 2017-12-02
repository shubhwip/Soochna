/**
 * @author Shubham Jain
 */
(function () {
  'use strict';

  var app = angular.module('Soochna.pages.welcome', [])
  app.config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaAdmin.welcome', {
          url: '/welcome',
          templateUrl: 'app/pages/welcome/welcome.html',
          title: 'Welcome to Soochna',
          sidebarMeta: {
            icon: 'ion-android-home',
            order: 0,
          }
        });
  }

  app.controller('Soochna.pages.welcome', function($scope) {
        $scope.activeMenu = 'Welcome';
  });

})();
