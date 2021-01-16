angular.module('app')
        .controller('modalCtrl', modalCtrl)
        .controller('personaCtrl', personaCtrl)
        .controller('infoPersonaModalCtrl', infoPersonaModalCtrl);


function modalCtrl($scope, $http, $uibModalInstance, $window, data) {
    $scope.data = data;

    $scope.setFocus = function () {
        var someElement = $window.document.getElementById('btn_cerrar');
        someElement.focus();
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}


function personaCtrl($scope, $http, $window, $uibModal, $rootScope) {
    $scope.data = {};
    $scope.data.lstPersonas = [];

    $scope.actualizar = function () {
        $http.get('./webresources/personas')
                .then(function (res) {
                    if (res && res.data) {
                        $scope.data.lstPersonas = res.data;
                    }
                }, function (error) {
                    showBasicModalError($uibModal, 'Error', 'Surgió un problema al consultar la lista de personas!');
                });
    };


    $scope.mostrarModalAgregar = function () {
        var modalInstance = $uibModal.open({
            templateUrl: 'pages/modals/infoPersona.html',
            controller: infoPersonaModalCtrl,
            size: 'lg',
            resolve: {
                data: function () {
                    return {
                        crear: 1
                    };
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            $scope.actualizar();
        });
    };

    $scope.mostrarModalModificar = function (data) {
        var modalInstance = $uibModal.open({
            templateUrl: 'pages/modals/infoPersona.html',
            controller: infoPersonaModalCtrl,
            size: 'lg',
            resolve: {
                data: function () {
                    return {
                        crear: 0,
                        data: data
                    };
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            $scope.actualizar();
        });
    };

    $scope.eliminarPersona = function (dni) {
        $http.delete('./webresources/personas/' + dni)
                .then(function (res) {
                    showBasicModalSuccess($uibModal, 'Exito', 'Usuario Eliminado');
                    $scope.actualizar();
                }, function (error) {
                    showBasicModalError($uibModal, 'Error', 'Error procesar la solicitud!');
                });
    };

    $scope.actualizar();

}

function infoPersonaModalCtrl($scope, $http, $uibModalInstance, $uibModal, data) {
    $scope.crear = data.crear;
    $scope.title = $scope.crear ? "Crear Persona" : "Actualizar Persona";
    $scope.data = data.data ? data.data : {};

    if (!data.crear) {
        $scope.data.fechaNacimiento = new Date($scope.data.fechaNacimiento);
        $scope.data.fechaBaja = $scope.data.fechaBaja ? new Date($scope.data.fechaBaja) : null;
    }

    $scope.save = function () {
        if (data.crear) {
            $http.post('./webresources/personas', JSON.stringify($scope.data))
                    .then(function (res) {
                        showBasicModalSuccess($uibModal, 'Exito', 'Usuario creado');
                        $scope.cancel();
                    }, function (error) {
                        console.log(error);
                        if (error.status === 409) {
                            showBasicModalError($uibModal, 'Error', 'Ya existe una persona con ese DNI!');
                        } else {
                            showBasicModalError($uibModal, 'Error', 'Error procesar la solicitud!');
                        }
                    });
        } else {
            $http.put('./webresources/personas', JSON.stringify($scope.data, function (key, value) {
                if (key === "$$hashKey") {
                    return undefined;
                }
                return value;
            }))
                    .then(function (res) {
                        showBasicModalSuccess($uibModal, 'Exito', 'Usuario Actualizado');
                        $scope.cancel();
                    }, function (error) {
                        showBasicModalError($uibModal, 'Error', 'Error procesar la solicitud!');
                    });
        }
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}