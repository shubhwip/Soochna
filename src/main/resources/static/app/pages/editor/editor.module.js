/**
 * @author Shubham
 */
(function () {
  'use strict';

  // https://codepen.io/official_naveen/pen/RKQweE
  // https://naveensingh.net/how-to-use-ckeditor-in-angularjs-with-custom-directive/

  var app = angular.module('Soochna.pages.editor', ["ui.bootstrap.modal"])
  app.config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
        .state('soochnaAdmin.editor', {
          url: '/editor',
          title: 'Editor',
          templateUrl: 'app/pages/editor/editor.html',
          sidebarMeta: {
            icon: 'ion-compose',
            order: 1,
          },
          controller: 'EditorPageCtrl as EditorCtrl',
        });
  }

// https://stackoverflow.com/questions/32576594/angularjs-directive-function-not-called

  app.directive('ckEditor', function () {
  return {
    require: '?ngModel',
    link: function (scope, elm, attr, ngModel) {
      var ck = CKEDITOR.replace(elm[0]);
      if (!ngModel) return;
      ck.on('instanceReady', function () {
        ck.setData(ngModel.$viewValue);
      });
      function updateModel() {
        scope.$apply(function () {
          ngModel.$setViewValue(ck.getData());
        });
      }
      ck.on('change', updateModel);
      ck.on('key', updateModel);
      ck.on('dataReady', updateModel);

      ngModel.$render = function (value) {
        ck.setData(ngModel.$viewValue);
      };
    }
  };
});



})();
