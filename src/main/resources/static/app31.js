var stompClient = null;
var publicStompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#oneToOne").show();
    }
    else {
        $("#conversation").hide();
        $("#oneToOne").hide();
    }
    $("#greetings").html("");
}

function connect() {

	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        //點對點
        let user = $('#user').val();
        let url = '/ads/' + user + '/message';
        stompClient.subscribe(url, function (response) {
            var responseData = document.getElementById('responseData');
            console.log(response.body);
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(response.body));
            responseData.appendChild(p);
        });

        //廣播
        stompClient.subscribe('/topic/greetings', function (greeting) {
        	console.log(greeting);
        	showGreeting(JSON.parse(greeting.body).content);
        });
    });

}

function disconnect() {

    if (stompClient !== null) {
    	stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	stompClient.send("/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

//點對點送訊息
function sendMsg() {

	var user = $('#user').val();
    var content = document.getElementById('content').value;
    var to = document.getElementById('to').value;
    let param = {
    		'userId': user,
    		'content': content,
    		'to': to
    }
    console.log(param);
    stompClient.send("/messageToOne", {}, JSON.stringify(param));
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

