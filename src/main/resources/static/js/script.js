'use strict'

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var userId = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    userId = document.querySelector("#name").value.trim();

    if (userId) {
        usernamePage.classList.add("hidden");
        chatPage.classList.remove("hidden");

        // Creates a realtime communication client through web-socket
        var socket = new SockJS('/ws');
        // Creates a STOMP client over an existing WebSocket connection
        // to work with the STOMP protocol
        stompClient = Stomp.over(socket);
        const options = {};
        stompClient.connect(options, onConnect, onError);
    }

    event.preventDefault();
}

function getCookie(name) {
    const cookies = document.cookie.split(';');

    for (const cookie of cookies) {
        const [ cookieName, cookieValue ] = cookie.trim().split('=');
        if (cookieName === name) {
            return cookieValue;
        }
    }

    return null;
}

function onConnect() {
    console.log(`Connected to the web-socket successfully!`);

    // Subscribe to the public topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Sending username to the server
    const config = {};
    const payload = {
        sender: {
            token: getCookie("token")
        },
        messageType: 'JOIN',
    }
    stompClient.send('/app/chat/add-user', config, JSON.stringify(payload));

    connectingElement.classList.add("hidden");
}

function onMessageReceived(payload) {
    console.log(payload);
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');

    let action = message.messageType;
    let sender = message.sender.name;

    if (action === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = sender + ' joined!';
    } else if (action === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function onError() {
    connectingElement.textContent = 'Could not connect to the WebSocket, Please refresh again';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageBody = messageInput.value.trim();
    if (messageBody && stompClient) {
        var chatMessage = {
            sender: {
                userId: userId
            },
            content: messageBody,
            messageType: 'MESSAGE'
        };
        stompClient.send('/app/chat/send-message', {}, JSON.stringify(chatMessage));
        messageInput.content = '';
    }

    event.preventDefault();
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener("submit", connect, true);
messageForm.addEventListener("submit", sendMessage, true);
