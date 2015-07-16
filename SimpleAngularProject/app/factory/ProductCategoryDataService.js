simpleApp.factory('ProductCategoryDataService', function($resource){
    var resource = $resource('http://10.197.125.32:8081/products/api/v1/categories/',
    {
        query: { method: 'GET', isArray: true }
    });
    return {
        getAllCategories: function(){
            var result = new Array();
            resource.query({}, function(jsonObjects){
                jsonObjects.forEach(function(element){
                    result.push(element);
                });
            });
            return result;
        }
    };
});