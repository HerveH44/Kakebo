Ext.define('Kakebo.store.SubCategory', {
    extend: 'Ext.data.Store',
    storeId: 'SubCategory',
    alias: 'store.subcategory',

    model: 'Kakebo.model.SubCategory',

    autoLoad: true,
});