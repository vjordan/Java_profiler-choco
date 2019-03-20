socket.on('staticSimpleData', function(data){
  var json = JSON.parse(data);
  console.log("staticSimpleData received: " + data);

  document.getElementById("nb-variables").innerHTML = json.variables;
  document.getElementById("nb-constraints").innerHTML = json.constraints;
});

socket.on('dynamicSimpleData', function(data){
  var json = JSON.parse(data);
  console.log("dynamicSimpleData received: " + data);

  document.getElementById("nb-solutions").innerHTML = json.solutions;
  document.getElementById("nb-fails").innerHTML = json.fails;
  document.getElementById("nb-blocks").innerHTML = json.blocks;
  document.getElementById("nb-nodes").innerHTML = json.nodes;

  document.getElementById("nb-solutions2").innerHTML = json.solutions;
  document.getElementById("nb-fails2").innerHTML = json.fails;
  document.getElementById("nb-blocks2").innerHTML = json.blocks;
  document.getElementById("nb-nodes2").innerHTML = json.nodes;

});