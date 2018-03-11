Ext.define('Kakebo.model.Category', {
    extend: 'Kakebo.model.Base',
    idProperty: null,
    clientIdProperty: 'clientId',

    fields: [
        {name: 'id', type: 'int', unique: true, persist: false},
        {name: 'name', type: 'string'}
    ],

    proxy: {
        type: 'rest',
        url: '/categories',
        writer: {
            writeAllFields: true
        }
    },

    validators: {
        name: 'presence'
    }
});