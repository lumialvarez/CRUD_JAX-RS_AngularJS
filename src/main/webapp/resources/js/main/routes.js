angular.module('app').config(['$stateProvider', '$urlRouterProvider', '$ocLazyLoadProvider', '$qProvider', function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, $qProvider) {

        $qProvider.errorOnUnhandledRejections(false);

        $ocLazyLoadProvider.config({
            debug: true
        });

        $stateProvider
                .state('appSimple', {
                    abstract: true,
                    templateUrl: 'pages/common/layouts/simple.html',
                    resolve: {
                        loadPlugin: ['$ocLazyLoad', function ($ocLazyLoad) {
                                return $ocLazyLoad.load([{
                                        serie: true,
                                        name: 'Simple Line Icons',
                                        files: ['resources/css/simple-line-icons.css']
                                    }, {
                                        serie: true,
                                        name: 'Styles',
                                        files: ['resources/css/styles.css']
                                    }]);
                            }]
                    }
                })

                .state('appSimple.persona', {
                    url: '/persona',
                    templateUrl: 'pages/persona.html',
                    controller: 'personaCtrl',
                    resolve: {
                        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    serie: true,
                                    name: 'Controllers',
                                    files: ['resources/js/controllers/mainControllers.js']
                                });
                            }]
                    }

                });

        $urlRouterProvider.otherwise('/persona');
    }]);
