angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadPage = function (page) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: page
            }
        }).then(function (response) {
            $scope.productsPage = response.data;

            let minPageIndex = page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = page + 2;
            if (maxPageIndex > $scope.productsPage.totalPages) {
                maxPageIndex = $scope.productsPage.totalPages;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);

        });
        $scope.showCart();
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.loadPage(1);
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.showCart = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                $scope.cartProducts = response.data;
                // $scope.sum = $scope.cartProducts.sum;
            });
    };

    $scope.addProductInCart = function (id) {
        $http.post(contextPath + '/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.showCart();
            });
    };

    $scope.deleteProductFromCart = function (id) {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'DELETE',
            params: {
                id: id
            }
        }).then(function (response) {
            $scope.showCart();
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'DELETE'
        }).then(function (response) {
            $scope.showCart();
        });
    };

    $scope.loadPage(1);
});