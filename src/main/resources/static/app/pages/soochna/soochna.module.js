
(function () {
  'use strict';

  var app = angular.module('Soochna.pages.soochna', [])
  app.config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaMain.soochna', {
          url: '/soochna',
          title: 'Soochna',
          templateUrl: 'app/pages/soochna/soochna.html',
          controller: 'SoochnaPageCtrl as SoochnaCtrl',
        });
  }

  app.filter('mysce', function($sce) { return $sce.trustAsHtml; });

  })();
