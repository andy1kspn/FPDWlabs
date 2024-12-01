function connectWebSocket() {
    console.log("Connecting to WebSocket...");
    const socket = new SockJS('/game-websocket');
    stompClient = Stomp.over(socket);
    stompClient.debug = function(str) {
        console.log(str);
    };
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/game-updates', function(gameState) {
            console.log('Received game update:', gameState);
            updateGameState(JSON.parse(gameState.body));
        });

        stompClient.subscribe('/topic/player-status', function(playerStatus) {
            console.log('Received player status:', playerStatus);
            handlePlayerStatus(JSON.parse(playerStatus.body));
        });
    }, function(error) {
        console.error('STOMP error:', error);
    });
}