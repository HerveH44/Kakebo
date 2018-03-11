Ext.define('Kakebo.store.Outcome', {
    extend: 'Ext.data.Store',
    storeId: 'Outcome',
    alias: 'store.outcome',

    model: 'Kakebo.model.Outcome',

    autoLoad: true,
    grouper: {
        property: 'date',
        groupFn: function (record) {
            var date = new Date(record.get("date"));
            return date.getFullYear() + "-" + date.getMonth();
        }
    },

    sorters: [{
        property: 'id',
        direction: 'DESC'
    }]
});
