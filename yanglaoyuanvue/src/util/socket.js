var websock = null;
var global_callback = null;


function initWebSocket() { //初始化weosocket
    //ws地址
    websock = new WebSocket("ws://localhost:8089/webSocket/" + localStorage.getItem("token"));

    websock.onmessage = function(e) {
        websocketonmessage(e);
    }
    websock.onclose = function(e) {
        websocketclose(e);
    }
    websock.onopen = function() {
        websocketOpen();
    }

    //连接发生错误的回调方法
    websock.onerror = function() {
        console.log("WebSocket连接发生错误");
    }
}



//数据接收
function websocketonmessage(e) {
    console.log(e);
    return e.data;
}

//数据发送
function websocketsend(agentData) {
    websock.send(JSON.stringify(agentData));
}

//关闭
function websocketclose(e) {
    console.log("connection closed (" + e.code + ")");
}

function websocketOpen(e) {
    console.log("连接成功");
}


export { initWebSocket, websock }