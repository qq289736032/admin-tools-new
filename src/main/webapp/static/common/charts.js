/**收入图表*/
function initIncomeChart(id,title,datas){
    $(function () {
        $('#'+id).highcharts({
            chart: {
                zoomType: 'xy'
            },
            title: {
                text: title
            },
            xAxis: {
                categories: datas.date
            },
            yAxis: [
                { // Secondary yAxis
                    title: {
                        text: '充值用户数',
                        style: {
                            color: '#4572A7'
                        }
                    },
                    labels: {
                        format: '{value}',
                        style: {
                            color: '#4572A7'
                        }
                    },
                    opposite: true
                },
                { // Primary yAxis
                    labels: {
                        format: '{value}',
                        style: {
                            color: '#89A54E'
                        }
                    },
                    title: {
                        text: '新增充值用户数',
                        style: {
                            color: '#89A54E'
                        }
                    }
                }
            ],
            tooltip: {
                shared: true
            },
            legend: {
                align: 'right',
                x: -70,
                verticalAlign: 'top',
                y: 20,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },

            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                    }
                }
            },

            series: [{
                name: '非新增活跃用户数',
                type: 'column',
                yAxis: 1,
                data: datas.unCreate
            },
                {
                    name: '充值用户数',
                    color: '#89A54E',
                    type: 'spline',
                    data: datas.acu
                },
                {
                    name: '新增充值用户数',
                    color: 'red',
                    type: 'spline',
                    data: datas.dau
                }]
        });
    });
}