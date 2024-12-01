document.addEventListener('DOMContentLoaded', function() {
    connectWebSocket();

    const submitButton = document.getElementById('submit-answer');
    if (submitButton) {
        submitButton.addEventListener('click', function() {
            const answer = document.getElementById('answer-input').value;
            if (stompClient && gamePlayerId) {
                stompClient.send("/app/submit-answer", {},
                    JSON.stringify({
                        playerId: gamePlayerId,
                        answer: answer
                    })
                );
            }
        });
    }
});
