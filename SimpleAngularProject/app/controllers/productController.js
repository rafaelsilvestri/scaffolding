simpleApp.controller('ProductController', function ($scope, $modal, ProductDataService) {
    $scope.product = null;
    $scope.productSave = null;
    $scope.products = ProductDataService.getAllProducts();
    $scope.gridOptions = {
        data: 'products',
        columnDefs: [
            {field: 'id', displayName: 'Code'},
            {field: 'name', displayName: 'Name'},
            {field: 'amount', displayName: 'Price'},
            {field: 'description', displayName: 'Description'},
            {field: 'category.name', displayName: 'Category'}
        ],
        multiSelect: false,
        selectedItems: [],
        afterSelectionChange: function (item) {
            if (item.selected) {
                $scope.product = $scope.gridOptions.selectedItems[0];
            }
        }
    };

    $scope.refreshGrid = function () {
        $scope.products = ProductDataService.getAllProducts();
    };

    $scope.open = function () {
        var modalInstance = $modal.open({
            templateUrl: 'productModal.html',
            controller: 'ProductModalController',
            resolve: {
                productSave: function () {
                    return angular.copy($scope.productSave);
                },
                products: function () {
                    return $scope.products;
                }
            }
        });
    };

    $scope.new = function () {
        $scope.productSave = new Object();
        $scope.open();
    };

    $scope.edit = function () {
        $scope.productSave = $scope.product;
        $scope.open();
    };

    $scope.delete = function () {
        var modalInstance = $modal.open({
            templateUrl: 'deleteProductModal.html',
            controller: 'DeleteProductModalController',
            resolve: {
                product: function () {
                    return $scope.product;
                },
                products: function () {
                    return $scope.products;
                }
            }
        });
    };

}).controller('ProductModalController', function ($scope, $modalInstance, products,
        productSave, ProductDataService, ProductCategoryDataService) {
    $scope.product = productSave;
    $scope.categories = ProductCategoryDataService.getAllCategories();

    $scope.template = {name: 'Atendimento', url: 'views/product/product.html'};

    $scope.saveProduct = function () {
        if ($scope.product.id) {
            $scope.product = ProductDataService.saveProduct($scope.product.id, $scope.product);
            var index = products.indexOf($scope.product);
            products[index] = $scope.product;
        } else {
            $scope.product = ProductDataService.createProduct($scope.product);
            products.push($scope.product);
        }

        $modalInstance.dismiss('save');
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}).controller('DeleteProductModalController', function ($scope, $modalInstance, products, product, ProductDataService) {
    $scope.message = 'Are you sure you want to delete ' + product.name + '?';
    ;

    $scope.confirm = function () {
        $scope.delete();
        $modalInstance.dismiss('confirm');
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.delete = function () {
        var index = products.indexOf(product);
        ProductDataService.deleteProduct(product.id);
        product = null;
        products.splice(index, 1);
    };
});