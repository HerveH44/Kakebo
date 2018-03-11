Ext.define('Kakebo.model.SubCategory', {
    extend: 'Kakebo.model.Base',
    idProperty: null,
    clientIdProperty: 'clientId',

    fields: [
        {name: 'id', type: 'int', unique: true, persist: false},
        {name: 'name', type: 'string'},
        {name: 'category', reference: 'Category'}
    ],

    proxy: {
        type: 'rest',
        url: '/subcategories',
        writer: {
            writeAllFields: true
        }
    },

    validators: {
        name: 'presence'
    }
});