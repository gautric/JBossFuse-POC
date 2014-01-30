

    var eventApp = angular.module('eventApp', []);
     
    eventApp.controller('EventListCtrl', function ($scope) {
    
    
    $scope.eventLimit = -15;
    
    // Create our websocket object with the address to the websocket
    var ws = new WebSocket("ws://localhost:9292/event.ws");
    
    ws.onopen = function(){  
        console.log("Socket has been opened!");  
    };
    
    ws.onmessage = function(message) {
        listener(JSON.parse(message.data));
    };
   
   
    function listener(data) {
      console.log("Received data from websocket: ", data);    
      $scope.events.push(data);
      $scope.$digest();
     
    }
    
    $scope.limit = function(i) {
       $scope.eventLimit = i;
     //  $scope.$digest();
    }
    
    $scope.formatDate = function(date) {
    	return moment(date).startOf('s').fromNow();
    }
    
    $scope.capitaliseFirstLetter = function(string) {
    	return string.charAt(0).toUpperCase() + string.slice(1);
	}
    
    $scope.events = [
    ];
    
    $scope.orderProp = 'creationTime';
    
    });
