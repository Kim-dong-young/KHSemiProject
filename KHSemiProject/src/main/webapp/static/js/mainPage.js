document.addEventListener('DOMContentLoaded', function() {
    function updateDonutCharts() {
        document.querySelectorAll('.donut-chart').forEach(function(chart) {
            const percent = chart.getAttribute('data-percent');
            const donutText = chart.querySelector('.donut-text');
            
            // Update the conic-gradient based on data-percent value
            chart.style.background = `conic-gradient(#FF9139 0% ${percent}%, #ddd ${percent}% 100%)`;

            // Optionally update the text inside the donut
            donutText.textContent = `${percent}%`;
        });
    }

    // Simulate fetching data from a server
    function fetchDataFromServer() {
        // Simulated server response
        return new Promise((resolve) => {
            setTimeout(() => {
                resolve({
                    '업적 달성률': 70,
                    '플레이한 퀴즈': 50,
                    '정답률': 80
                });
            }, 1000);
        });
    }

    // Fetch and update charts with data from the server
    fetchDataFromServer().then(data => {
        document.querySelectorAll('.donut-chart').forEach(chart => {
            const contentRate = chart.nextElementSibling.textContent;
            const percent = data[contentRate];
            chart.setAttribute('data-percent', percent);
            updateDonutCharts();
        });
    });
});