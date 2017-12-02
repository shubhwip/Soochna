
(function () {
  'use strict';

  var app = angular.module('Soochna.pages.profile', [])
  app.config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaAdmin.profile', {
          url: '/profile',
          title: 'Profile',
          templateUrl: 'app/pages/profile/profile.html',
          controller: 'ProfilePageCtrl as ProfileCtrl',
        });
  }

  })();
