simpleApp.factory('ProductDataService', function($resource){
    var resource = $resource('http://10.197.125.32:8081/products/api/v1/products/:id',
    { id: "@id" },
    {
        get: { method: 'GET' },
        save: { method: 'PUT' },
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' },
        remove: { method: 'DELETE' }
    });
    
    return {
        getAllProducts: function(){
            var result = new Array();
            resource.query({}, function(jsonObjects){
                jsonObjects.forEach(function(element){
                    result.push(element);
                });
            });
            return result;
        },
        getProduct: function(productId){
            return resource.get({id: productId});
        },
        createProduct: function(product){
            return resource.create(product);
        },
        saveProduct: function(productId, product){
            return resource.save({id: productId}, product);
        },
        deleteProduct: function(productId){
            return resource.remove({id: productId});
        }
    };
});