'use strict';

/* App Module */

var simpleApp = angular.module('simpleApp', [
    'ngResource',
    'ngRoute',
    'ngGrid',
    'ui.bootstrap'
]);

simpleApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/customers', {
        templateUrl: 'views/customer/customers.html'
      }).
      when('/products', {
        templateUrl: 'views/product/products.html',
        controller: 'ProductController'
      }).
      when('/sales', {
        templateUrl: 'views/sale/sales.html'
      }).
      otherwise({
        redirectTo: '/index'
      });
  }]);
