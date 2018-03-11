Ext.define('Kakebo.view.main.Home', {
    extend: 'Ext.panel.Panel',
    xtype: 'home',
    layout:'anchor',

    require: [
        'Ext.chart.CartesianChart ',
        'Ext.chart.axis.Numeric',
        'Ext.chart.axis.Category',
        'Ext.chart.series.Bar',
        'Ext.chart.interactions.ItemHighlight',
    ],

    items: [{
        xtype: 'chart',
        title: 'Outcomes',
        height: 500,
        width: 500,
        store: {
            type: 'outcome'
        },
        insetPadding: {
            top: 40,
            bottom: 40,
            left: 20,
            right: 40
        },
        // interactions: {
        //     type: 'itemedit',
        //     tooltip: {
        //         renderer: 'onEditTipRender'
        //     },
        //     renderer: 'onColumnEdit'
        // },
        axes: [{
            type: 'numeric',
            position: 'left',
            minimum: 30,
            titleMargin: 20,
            title: {
                text: 'Outcomes'
            },
            // listeners: {
            //     rangechange: 'onAxisRangeChange'
            // }
        }, {
            type: 'category',
            position: 'bottom'
        }],
        animation: !Ext.isIE8,
        series: {
            type: 'bar',
            xField: 'month',
            yField: 'cost',
            style: {
                minGapWidth: 20
            },
            highlight: {
                strokeStyle: 'black',
                fillStyle: 'gold'
            },
            label: {
                field: 'month',
                display: 'insideEnd',
                // renderer: function (value) {
                //     return value.toFixed(1);
                // }
            }
        },
        sprites: {
            type: 'text',
            text: 'Redwood City Climate Data',
            fontSize: 22,
            width: 100,
            height: 30,
            x: 40, // the sprite x position
            y: 20  // the sprite y position
        },
        // listeners: {
        //     afterrender: 'onAfterRender',
        //     beginitemedit: 'onBeginItemEdit',
        //     enditemedit: 'onEndItemEdit'
        // }
    }, {
        xtype: 'cartesian',
        title: 'Chart',
        height: 500,
        width: 500,
        insetPadding: 40,
        legend: true,
        store: {
            fields: ['month', 'sales', 'order'],
            data: [
                {month: 'Q1', sales: 100, order: 20},
                {month: 'Q2', sales: 250, order: 120},
                {month: 'Q3', sales: 75, order: 40},
                {month: 'Q4', sales: 120, order: 25}
            ]
        },
        axes: [{
            title: 'Sale',
            type: 'numeric',
            position: 'left',
            fields: ['sales']
        },
            {
                title: 'Order',
                type: 'numeric',
                position: 'right',
                fields: ['order'],
                maximum: 200
            },
            {
                title: 'Quarter',
                type: 'category',
                position: 'bottom',
                fields: ['month']
            }],
        sprites: [{
            type: 'text',
            text: 'Quaterly Sales and Orders',
            font: '22px Helvetica',
            width: 100,
            height: 20,
            x: 40,
            y: 20
        }],
        series: [{
            type: 'bar',
            xField: 'month',
            yField: 'sales',
            title: 'Sale'
        }, {
            type: 'area',
            xField: 'month',
            yField: 'order',
            title: 'Order',
            showMarkers: true,
            marker: {
                type: 'circle',
                radius: 5
            },
            style: {
                opacity: 0.5
            }
        }]
    }]

});
