Ext.define('Kakebo.store.Category', {
    extend: 'Ext.data.Store',
    storeId: 'Category',
    alias: 'store.category',

    model: 'Kakebo.model.Category',

    autoLoad: true,
});
