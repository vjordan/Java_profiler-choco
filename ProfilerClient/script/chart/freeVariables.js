var freeVariablesConf = {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            label: "",
            backgroundColor: window.chartColors.blue,
            borderColor: window.chartColors.blue,
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
                    labelString: 'Free variables'
                }
            }]
        },
        legend: {
            display: false
        }
    }
};

socket.on('freeVariables:addData', function(data){
	var json = JSON.parse(data);
    console.log("linechart:addData received: " + data);

	if (freeVariablesConf.data.datasets.length > 0) {
        freeVariablesConf.data.labels.push(eval(json.label));
        freeVariablesConf.data.datasets[0].data.push(eval(json.value));

        window.freeVariables.update();
    }

});

var freeVariablesCanvas = $("#freeVariables").get(0).getContext("2d");
window.freeVariables = new Chart(freeVariablesCanvas, freeVariablesConf);
