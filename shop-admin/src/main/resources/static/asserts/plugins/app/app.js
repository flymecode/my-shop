var App = function () {
    var masterCheck;
    var checkBox;

    var handInitCheckBox = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        masterCheck = $('input[type="checkbox"].minimal.icheck_master');
        checkBox = $('input[type="checkbox"].minimal');

    }
    var handlerCheckBoxAll = function () {
        masterCheck.on('ifClicked',function (e) {
            if (e.target.checked) {
                checkBox.iCheck('uncheck')
            } else {
                checkBox.iCheck('check')
            }
        })
    };
    return {
        init: function () {
            handInitCheckBox();
            handlerCheckBoxAll();
        },
        getCheckBox: function () {
            return checkBox;
        }
    }

}();
$(document).ready(function () {
    App.init()
})