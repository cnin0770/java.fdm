async function draw_line_chart(dom_id, stock_symbol, barrier_level) {
    if (!dom_id) return;
    let result = await axios.get("/api/chart/" + stock_symbol);
    let quotes = result.data;
    if (!quotes) return;

    let ctx = document.getElementById(dom_id).getContext('2d');
    let chart_title = "Stock: " + stock_symbol.toUpperCase();

    let label_set = [];
    let data_set = [];
    let high_set = [];
    let low_set = [];
    let open_set = [];
    let close_set = [];

    quotes.forEach(function (item) {
        label_set.push(item.date.substring(0, 7));
        high_set.push(item.high);
        low_set.push(item.low);
        open_set.push(item.open);
        close_set.push(item.close);
    });

    data_set.push({
        data: high_set,
        label: "High Point",
        borderColor: "#cd533e",
        fill: false
    });
    data_set.push({
        data: low_set,
        label: "Low Point",
        borderColor: "#5e7fa2",
        fill: false
    });
    data_set.push({
        data: open_set,
        label: "Open Price",
        borderColor: "#57ba3c",
        fill: false
    });
    data_set.push({
        data: close_set,
        label: "Close Price",
        borderColor: "#181818",
        fill: false
    });

    if (barrier_level > 0) {
        data_set.push({
            data: Array(label_set.length).fill(barrier_level),
            label: "Barrier",
            borderColor: "#eed765",
            fill: false
        });
    }
    if (this.myChart) {
        this.myChart.destroy();
    }
    this.myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: label_set,
            datasets: data_set
        },
        options: {
            title: {
                display: true,
                text: chart_title
            }
        }
    });
    console.log("chart load complete.")
    return true;
}