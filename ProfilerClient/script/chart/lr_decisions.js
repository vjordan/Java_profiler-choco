var lrDecisionsConf = {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            label: "",
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
            text:'Line Chart Test'
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
                    labelString: 'Left-Right decisions'
                }
            }]
        },
        legend: {
            display: false
        }
    }
};

socket.on('lrDecisions:addData', function(data){
	var json = JSON.parse(data);
    console.log("linechart:addData received: " + data);

	if (lrDecisionsConf.data.datasets.length > 0) {
        lrDecisionsConf.data.labels.push(eval(json.label));
        lrDecisionsConf.data.datasets[0].data.push(eval(json.value));

        window.lrDecisions.update();
    }

});

var lrDecisionsCanvas = $("#lrDecisions").get(0).getContext("2d");
window.lrDecisions = new Chart(lrDecisionsCanvas, lrDecisionsConf);
