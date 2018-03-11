Ext.define('Kakebo.view.outcome.OutcomeController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.outcome',

    onAddCategory: function (btn) {
        Ext.MessageBox.show({
            title: 'Add a category',
            msg: 'Please the name of the category:',
            width: 300,
            buttons: Ext.MessageBox.OKCANCEL,
            multiline: true,
            scope: this,
            fn: function (btn, text) {
                Ext.Ajax.request({
                    url: "/categories",
                    method: "POST",
                    jsonData: {
                        name: text
                    },
                    success: function (request) {
                        Ext.toast({
                            html: `The category ${text} has been saved!`,
                            closable: false,
                            align: 't',
                            slideInDuration: 400,
                            minWidth: 400,
                        });
                    },
                    failure: (request) => {
                        Ext.toast({
                            html: `The category ${request} hasn't been saved!`,
                            closable: false,
                            align: 't',
                            slideInDuration: 400,
                            minWidth: 400,
                        });
                    }
                })
            },
            animateTarget: btn
        });
    },


    onAddSubCategory: function (btn) {
        var dialog =  new Ext.Window({
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            bodyPadding: 10,
            title: 'Add SubCategory',
            modal: true,
            width: 300,
            items: [{
                xtype: 'combobox',
                name: 'category',
                fieldLabel: 'Category',
                valueField: 'id',
                displayField: 'name',
                store: {
                    type: 'category'
                }
            }, {
                xtype: 'textfield',
                name: 'subcategory',
                fieldLabel: 'Subcategory name'
            }],
            buttons: [{
                text: 'Ok',
                formBind: true,
                listeners: {
                    click: function() {
                        var category = this.up('window').down('combobox[name=category]').getValue();
                        var subcategory = this.up('window').down('textfield[name=subcategory]').getValue();
                        this.up("window").close();
                        Ext.Ajax.request({
                            url: "/subcategories",
                            method: "POST",
                            jsonData: {
                                id: 0,
                                name: subcategory,
                                category_id: category
                            },
                            success: function (request) {
                                Ext.toast({
                                    html: `The subcategory ${subcategory} has been saved!`,
                                    closable: false,
                                    align: 't',
                                    slideInDuration: 400,
                                    minWidth: 400,
                                });
                            },
                            failure: (request) => {
                                Ext.toast({
                                    html: `The subcategory ${subcategory} hasn't been saved!`,
                                    closable: false,
                                    align: 't',
                                    slideInDuration: 400,
                                    minWidth: 400,
                                });
                            }
                        })
                    }
                }
            }, {
                text: 'Cancel',
                listeners: {
                    click: function() {
                        this.up("window").close();
                    }
                }
            }]
        });

        dialog.show();
    },
});