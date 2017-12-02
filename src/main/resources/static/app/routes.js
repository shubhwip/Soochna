'use strict';

angular.module('Soochna')
.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

  console.log("routes entry");
  $urlRouterProvider.otherwise('/welcome');

  $stateProvider
  .state('soochnaAdmin', {
    abstract: true,
    templateUrl: 'app/pages/common/layouts/soochnaadmin.html',
    resolve: {
      loadCSS: ['$ocLazyLoad', function($ocLazyLoad) {
        // you can lazy load CSS files
        return $ocLazyLoad.load([{
          serie: true,
          name: 'Admin CSS',
          files: ['app/admin.css']
        }]);
      }]
    }
  })
  .state('soochnaMain', {
    abstract: true,
    templateUrl: 'app/pages/common/layouts/soochnamain.html',
    resolve: {
      loadCSS: ['$ocLazyLoad', function($ocLazyLoad) {
        // you can lazy load CSS files
        return $ocLazyLoad.load([{
          serie: true,
          name: 'Main css',
          files: ['app/main.css']
        }]);
      }]
    }
  })


}]);
