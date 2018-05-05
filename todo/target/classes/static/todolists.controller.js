var app = angular.module("myApp", []);

app.controller("myCtrl", function($scope , $http) {
    //var $scope = this;
    $scope.note = ""
    $scope.todolist = [];
    $scope.todolistComp = [];
    $scope.todolistInComp = [];
    $scope.getAll = getAll;
    $scope.getCompleted = getCompleted;
    $scope.getInComplete = getInComplete;
    $scope.createTODO=createTODO;
    $scope.deleteTODO= deleteTODO;
    $scope.updateTODO= updateTODO;
    init();

    function init(){
        getAll();
        getCompleted();
        getInComplete();
    }

    function getTODOChanges(){
        getCompleted();
        getInComplete();
    }

    function getAll(){
        var url = "http://localhost:8082/todos/all";
        var todolistsAll = $http.get(url);
        todolistsAll.then(function(response){
            $scope.todolist = response.data;
        });
    }

    function getCompleted(){
        var url = "http://localhost:8082/todos/complete";
        var todolistsCompeted = $http.get(url);
        todolistsCompeted.then(function(response){
            $scope.todolistComp = response.data;
        });
    }

    function getInComplete(){
        var url = "http://localhost:8082/todos/incomplete";
        var todolistsCompeted = $http.get(url);
        todolistsCompeted.then(function(response){
            $scope.todolistInComp = response.data;
        });
    }


    function deleteTODO(id){
        var url = "http://localhost:8082/todos/delete/" + id;
        $http.delete(url).then(function(response){
            $scope.todolist = response.data;
            getTODOChanges();
        });

    }

    function updateTODO(todo){
        var url = "http://localhost:8082/todos/update" ;
        $http.put(url,todo).then(function(response){
            $scope.todolist = response.data;
            getTODOChanges();
        });
    }


    function createTODO(){
        var url = "http://localhost:8082/todos/create";

        $http.post(url,$scope.note).then(function(response){
            $scope.todolist = response.data;
            getTODOChanges();
            $scope.note="";
        });

    }
    $scope.firstName = "John";
    $scope.lastName = "Vimalraj";


});