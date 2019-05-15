var exec = require('cordova/exec');

window.cameraCapture = function (options, success, error) {
    exec(success, error, 'CameraCapture', 'cameracapture', [options]);
};
