var socketURL = 'http://127.0.0.1:1234';
var socketName = 'ChocoProfilerGUI';
var socket =  io.connect(socketURL);

console.log('Socket.id = ' + socket.id);

socket.on('connect', function(){
  console.log('Connected on ' + socketURL);
  socket.emit('newClient', 'ChocoProfilerGUI');
  console.log('Play')
  socket.emit('chocoControl', 'play');
});

socket.on('disconnect', function(){
  console.log('Disconnected...');
  socket.emit('clientLost', 'ChocoProfilerGUI');
});

document.getElementById('playBtn').addEventListener('click', function() {
	console.log('Play')
    socket.emit('chocoControl', 'play');
});

document.getElementById('pauseBtn').addEventListener('click', function() {
	console.log('Pause')
    socket.emit('chocoControl', 'pause');
});

socket.connect();