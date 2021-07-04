var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for(var i = 0; i < arrayLength; i++) {
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"),{
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#ff9900", "#8e5ea2"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
	   title: {
		display: true,
		text: 'Project Status'
	   }
    }
});

function decodeHtml(html) {//decode chartData
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}