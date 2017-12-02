
(function () {
  'use strict';

  var app = angular.module('Soochna.pages.ministry', [])
  app.config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaAdmin.ministry', {
          url: '/ministry',
          title: 'Ministry',
          templateUrl: 'app/pages/ministry/ministry.html',
          sidebarMeta: {
            icon: 'fa fa fa-briefcase',
            order: 2,
          },
          controller: 'MinistryPageCtrl as MinistryCtrl',
        });
  }

  })();
