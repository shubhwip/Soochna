
(function () {
  'use strict';

  var app = angular.module('Soochna.pages.notice', [])
  app.config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaAdmin.notice', {
          url: '/notice',
          title: 'Notices',
          templateUrl: 'app/pages/notice/notice.html',
          controller: 'NoticePageCtrl as NoticeCtrl',
        });
  }

    app.filter('mysce', function($sce) { return $sce.trustAsHtml; });

  })();