
var exec = require('cordova/exec');
window.multipleFileSelect = function(options,success, error) {
    exec(success, error, "MultipleFileselect", "multiplefileselect", [options]
);
};
