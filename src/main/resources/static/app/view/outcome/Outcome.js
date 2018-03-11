var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
    clicksToMoveEditor: 1,
    autoCancel: false
});

var store = Ext.create('Kakebo.store.Outcome', {
    autoDestroy: true,
    autoLoad: true
});

var feature = Ext.create('Ext.grid.feature.Grouping', {
    startCollapsed: true,
    groupHeaderTpl: '{columnName} {rows.length}',
    groupers: [{
        property: 'date',
        groupFn: function (record) {
            var date = new Date(record.date);
            return date.getFullYear() + "-" + date.getMonth();
        }
    }]
});

Ext.define('Kakebo.view.outcome.Panel', {
    extend: 'Ext.grid.Panel',
    xtype: 'outcome-panel',
    layout: 'fit',

    requires: [
        'Kakebo.store.Outcome'
    ],

    title: 'Outcome',
    controller: 'outcome',
    minHeight: '300',

    store: store,
    features: [{
        ftype: 'summary',
        dock: 'bottom'
    }, {
        ftype: 'grouping',
        groupHeaderTpl: Ext.create('Ext.XTemplate',
            'Group: ',
            '<div>{name:this.formatName}</div>',
            {
                formatName: function (name) {
                    var [day, month, year] = name.split("-");
                    return year + "-" + month;
                }
            }
        ),
        hideGroupedHeader: false,
        startCollapsed: false,
        id: 'dateCreatedGrouping',
        showSummaryRow: true,
        summaryType: 'sum',
        summaryRender: function (value) {
            return Ext.util.Format.currency(value, '€', 2);
        }
    }],

    tbar: [{
        text: 'Add Outcome',
        iconCls: 'x-fa fa-plus',
        handler: function () {
            var r = Ext.create('Kakebo.model.Outcome', {
                name: '',
                cost: 0,
                notes: ''
            });
            rowEditing.cancelEdit();

            var grid = this.up('grid');
            store.clearGrouping();
            store.insert(0, r);
            grid.getView().focusNode(r);

            rowEditing.startEdit(r, 0);
        }
    }, '-', {
        itemId: 'delete',
        text: 'Delete Outcome',
        iconCls: 'x-fa fa-minus',
        disabled: true,
        handler: function () {
            var selection = this.up("grid").getView().getSelectionModel().getSelection()[0];
            if (selection) {
                store.remove(selection);
            }
        }
    }, '-', {
        itemId: 'addCategory',
        text: 'Add Category',
        iconCls: 'x-fa fa-book',
        handler: 'onAddCategory'
    },{
        itemId: 'addSubcategory',
        text: 'Add SubCategory',
        iconCls: 'x-fa fa-bookmark',
        handler: 'onAddSubCategory'
    }, '->', {
        text: 'Sync',
        handler: function () {
            store.sync();
        }
    }],

    columns: [
        {
            text: 'Name',
            dataIndex: 'name',
            editor: {
                allowBlank: false
            }
        },
        {
            text: 'Category',
            dataIndex: 'category_id',
            editor: {
                allowBlank: false,
                xtype: 'combobox',
                id: "category",
                valueField: 'id',
                displayField: 'name',
                store: {
                    type: 'category'
                },
                listeners: {
                    select: function(combo, newValue, eOpts) {
                        var subcategory = rowEditing.editor.down('combobox[id=subcategory]');
                        subcategory.setValue(0);
                        return newValue;
                    }
                }

            },
            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                var row = Ext.data.StoreManager.lookup('Category').getById(value);
                return row ? row.get('name') : "";
            },
        },
        {
            text: 'Subcategory',
            dataIndex: 'subcategory_id',
            editor: {
                allowBlank: false,
                xtype: 'combobox',
                valueField: 'id',
                id: "subcategory",
                displayField: 'name',
                store: {
                    type: 'subcategory'
                },
                listeners: {
                    expand: function (combo) {
                        var comboStore = combo.store;
                        comboStore.clearFilter();
                        var category_id = rowEditing.editor.down('combobox[id=category]').getValue();
                        if (category_id) {
                            comboStore.filter("category_id", category_id);
                        }
                    }
                }

            },
            renderer: function (value, metaData, record, rowIndex, colIndex, store, view) {
                var row = Ext.data.StoreManager.lookup('SubCategory').getById(value);
                return row ? row.get('name') : "";
            }
        },
        {
            text: 'Cost',
            dataIndex: 'cost',
            editor: {
                allowBlank: false,
                format: '0.00'
            },
            xtype: 'numbercolumn',
            format: '0.00',
            renderer: function (valeur, record) {
                return Ext.util.Format.currency(valeur, '€', 2);
            },
            summaryType: 'sum',
            summaryRenderer: function (valeur, record) {
                return Ext.util.Format.currency(valeur, '€', 2);
            },
        },
        {
            text: 'Date',
            dataIndex: 'date',
            xtype: 'datecolumn',
            format: 'd-m-Y',
            editor: {
                xtype: 'datefield',
                allowBlank: true,
                format: 'd-m-Y'
            }
        },
        {
            text: 'Notes',
            dataIndex: 'notes',
            flex: 1,
            editor: {
                allowBlank: true
            }
        }
    ],

    plugins: [rowEditing],

    listeners: {
        selectionchange: function (view, records) {
            this.down('#delete').setDisabled(!records.length);
        },
        canceledit: function (editor, context, eOpts) {
            if (context.record.phantom) {
                store.remove(context.record);
            }
        }
    }
});