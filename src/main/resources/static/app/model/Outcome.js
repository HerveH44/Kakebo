Ext.define('Kakebo.model.Outcome', {
    extend: 'Kakebo.model.Base',
    idProperty: null,
    clientIdProperty: 'clientId',

    fields: [
        {name: 'id', type: 'int', unique: true, persist: false},
        {name: 'name', type: 'string'},
        {name: 'cost', type: 'int'},
        {name: 'notes', type: 'string'},
        {name: 'date', type: 'date', dateFormat: 'time'},
        {name: 'category', reference: 'Category'},
        {name: 'subcategory', reference: 'SubCategory'},
        {name: 'category_id', type: 'int'},
        {name: 'subcategory_id', type: 'int'},
        {name: 'month', type:'string', persist: false, calculate: (data) => {
            const date = new Date(data.date);
            return `${date.getFullYear()}-${date.getUTCMonth() + 1}`;
        }}
    ],

    proxy: {
        type: 'rest',
        url: '/outcomes',
        writer: {
            writeAllFields: true
        }
    },

    validators: {
        name: 'presence',
        count: 'presence'
    }
});