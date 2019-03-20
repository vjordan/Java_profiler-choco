var objectiveConf = {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            label: "Objective",
            backgroundColor: window.chartColors.blue,
            borderColor: window.chartColors.blue,
            data: [],
            lineTension: 0,
            fill: false,
        }, {
            label: "Max",
            backgroundColor: window.chartColors.red,
            borderColor: window.chartColors.red,
            data: [],
            lineTension: 0,
            fill: false,
        }]
    },
    options: {
        maintainAspectRatio: true,
        responsive: true,

        title:{
            display:false,
            text:'Objective'
        },
        tooltips: {
            enabled: false,
            mode: 'index',
            intersect: false,   
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
                    labelString: 'Objective'
                }
            }]
        },
        legend: {
            display: false
        }
    }
};

socket.on('objective:addData', function(data){
	var json = JSON.parse(data);
    console.log("objective:addData received: " + data);

	if (objectiveConf.data.datasets.length > 0) {
        objectiveConf.data.labels.push(eval(json.label));
        objectiveConf.data.datasets[0].data.push(eval(json.value));
        objectiveConf.data.datasets[1].data.push(eval(json.max));

        window.objective.update();
    }

});

var objectiveCanvas = $("#objective").get(0).getContext("2d");
window.objective = new Chart(objectiveCanvas, objectiveConf);
