// Default colors
var brandPrimary = '#20a8d8';
var brandSuccess = '#4dbd74';
var brandInfo = '#63c2de';
var brandWarning = '#f8cb00';
var brandDanger = '#f86c6b';

var grayDark = '#2a2c36';
var gray = '#55595c';
var grayLight = '#818a91';
var grayLighter = '#d1d4d7';
var grayLightest = '#f8f9fa';

angular.module('app', [
    'ngAnimate',
    'ngSanitize',
    'ui.bootstrap',
    'ui.router',
    'oc.lazyLoad',
    'angular-loading-bar'
])
        .config(['cfpLoadingBarProvider', function (cfpLoadingBarProvider) {
                cfpLoadingBarProvider.includeSpinner = false;
                cfpLoadingBarProvider.latencyThreshold = 1;
            }])
        .factory('showBasicModalInfo', showBasicModalInfo)
        .factory('showBasicModalSuccess', showBasicModalSuccess)
        .factory('showBasicModalWarning', showBasicModalWarning)
        .factory('showBasicModalError', showBasicModalError);

// funciones como variables globales
function showBasicModalInfo($modal, titulo, contenido) {
    $modal.open({
        templateUrl: 'pages/common/modals/basicModalInfo.html',
        controller: 'modalCtrl',
        resolve: {
            data: function () {
                return {
                    titulo: titulo,
                    contenido: contenido};
            }
        }
    });
}

function showBasicModalSuccess($modal, titulo, contenido) {
    $modal.open({
        templateUrl: 'pages/common/modals/basicModalSuccess.html',
        controller: 'modalCtrl',
        resolve: {
            data: function () {
                return {
                    titulo: titulo,
                    contenido: contenido};
            }
        }
    });
}

function showBasicModalError($modal, titulo, contenido) {
    $modal.open({
        templateUrl: 'pages/common/modals/basicModalError.html',
        controller: 'modalCtrl',
        resolve: {
            data: function () {
                return {
                    titulo: titulo,
                    contenido: contenido};
            }
        }
    });
}

function showBasicModalWarning($modal, titulo, contenido) {
    $modal.open({
        templateUrl: 'pages/common/modals/basicModalWarning.html',
        controller: 'modalCtrl',
        resolve: {
            data: function () {
                return {
                    titulo: titulo,
                    contenido: contenido};
            }
        }
    });
}


function showModal($modal, templateUrl, controller, data) {
    $modal.open({
        templateUrl: templateUrl,
        controller: controller,
        resolve: {
            data: function () {
                return data;
            }
        }
    });
}
