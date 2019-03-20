var variablesMapConf = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [],
                backgroundColor: [
                    window.chartColors.red,
                    window.chartColors.blue
                ],
                label: 'Dataset 1'
            }],
            labels: [
                "Free",
                "Used"
            ]
        },
        options: {
            tooltips: {
                enabled: false
            },
            responsive: true,
            legend: {
                display: true
            }
        }
    };

socket.on('variablesMap:update', function(data) {
    var json = JSON.parse(data);
    console.log("variablesMap:update received: " + data);

    variablesMapConf.data.datasets.forEach(function(dataset) {
        dataset.data = json.values;
    });

    window.variablesMap.update();
});

var variablesMapCanvas = document.getElementById("variablesMap").getContext("2d");
window.variablesMap = new Chart(variablesMapCanvas, variablesMapConf);