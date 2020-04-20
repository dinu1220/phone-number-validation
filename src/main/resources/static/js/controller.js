app.controller('phoneController', function($scope,$http) {
    $http.get('http://localhost:8080/phone/all').
    then(function(response) {
        if (response.data) {
            $scope.msg = "Data Received Successfully!";
            $scope.numbers = response.data;
        }else{
            $scope.msg = "No Phone Numbers Found";
        }
    });

    $scope.postData = function(number){
        console.log($scope.number);

        var data = {
            phone_numbers : [ number ]
        };
        console.log(JSON.stringify(data));

        $http.post('http://localhost:8080/phone/add', JSON.stringify(data)).then(function (response) {
            if (response.data) {
                $scope.msg = "Post Data Submitted Successfully!";
                $scope.numbers = response.data;
            }
        }, function (response) {
            $scope.msg = "Service not Exists";
            $scope.statusval = response.status;
            $scope.statustext = response.statusText;
            $scope.headers = response.headers();
        });
        return false;
    };

});

