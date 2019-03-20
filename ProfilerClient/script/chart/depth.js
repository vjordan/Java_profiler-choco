var depthConf = {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            label: "",
            backgroundColor: window.chartColors.green,
            borderColor: window.chartColors.green,
            data: [],
            lineTension: 0,
            fill: false,
        }]
    },
    options: {
        maintainAspectRatio: true,
        responsive: true,
        title:{
            display:false
        },
        tooltips: {
            enabled: false   
        },
        /*hover: {
            enabled: false,
            mode: 'nearest',
            intersect: true
        },*/
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Nodes'
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Depth'
                }
            }]
        },
        legend: {
            display: false
        }
    }
};

socket.on('depth:addData', function(data){
	var json = JSON.parse(data);
    console.log("linechart:addData received: " + data);

	if (depthConf.data.datasets.length > 0) {
        depthConf.data.labels.push(eval(json.label));
        depthConf.data.datasets[0].data.push(eval(json.value));

        window.depth.update();
    }

});

var depthCanvas = $("#depth").get(0).getContext("2d");
window.depth = new Chart(depthCanvas, depthConf);
