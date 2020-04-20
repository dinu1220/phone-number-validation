var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/getnumber',{
            templateUrl: '/views/users.html',
            controller: 'phoneController'
        })
        .when('/addnumber',{
            templateUrl: '/views/addnumber.html',
            controller: 'phoneController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

