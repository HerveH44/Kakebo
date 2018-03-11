/*
 * This file launches the application by asking Ext JS to create
 * and launch() the Application class.
 */
Ext.application({
    extend: 'Kakebo.Application',

    name: 'Kakebo',

    requires: [
        // This will automatically load all classes in the Kakebo namespace
        // so that application classes do not need to require each other.
        'Kakebo.*'
    ],

    // The name of the initial view to create.
    mainView: 'Kakebo.view.main.Main'
});
