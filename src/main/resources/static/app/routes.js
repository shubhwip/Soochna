'use strict';

angular.module('Soochna')
.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

  console.log("routes entry");
  $urlRouterProvider.otherwise('/soochna');

  $stateProvider
  .state('soochnaAdmin', {
    abstract: true,
    templateUrl: 'app/pages/layouts/soochnaadmin.html',
    resolve: {
      loadCSS: ['$ocLazyLoad', function($ocLazyLoad) {
        // you can lazy load CSS files
        return $ocLazyLoad.load([{
          serie: true,
          name: 'Admin CSS',
          files: ['assets/admin.css']
        }]);
      }]
    }
  })
  .state('soochnaMain', {
    abstract: true,
    templateUrl: 'app/pages/layouts/soochnamain.html',
    resolve: {
      loadCSS: ['$ocLazyLoad', function($ocLazyLoad) {
        // you can lazy load CSS files
        return $ocLazyLoad.load([{
          serie: true,
          name: 'Main css',
          files: ['assets/main.css']
        }]);
      }]
    }
  })


}]);
