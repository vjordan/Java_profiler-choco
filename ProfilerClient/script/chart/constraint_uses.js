var constraintUsesConf = {
        type: 'horizontalBar',
        data: {
    		labels: [],
            datasets: [{
                backgroundColor: Chart.helpers.color(window.chartColors.blue).alpha(0.7).rgbString(),
                borderColor: window.chartColors.red,
                borderWidth: 1,
                data: []
            }]

        },
        options: {
            elements: {
                rectangle: {
                    borderWidth: 2,
                }
            },
            maintainAspectRatio: true,
            responsive: true,
            legend: {
                display: false
            },
            title: {
                display: false
            },
            tooltips: {
                enabled: false
            }
        }
    }

socket.on('constraintUses:update', function(data){
    var json = JSON.parse(data);
    console.log("constraintUses:init received: " + data);

    constraintUsesConf.data.datasets[0].data = json.values;
    constraintUsesConf.data.labels = json.labels;
    window.constraintUses.update();
});

var constraintUsesCanvas = document.getElementById("constraintUses").getContext("2d");
window.constraintUses = new Chart(constraintUsesCanvas, constraintUsesConf);