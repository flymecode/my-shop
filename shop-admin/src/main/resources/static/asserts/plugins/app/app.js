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

    };
    var handlerCheckBoxAll = function () {
        masterCheck.on('ifClicked',function (e) {
            if (e.target.checked) {
                checkBox.iCheck('uncheck')
            } else {
                checkBox.iCheck('check')
            }
        })
    };
    var handlerShowDetail = function (url) {
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'html',
            success: function (data) {
                $('.modal-detail-body').html(data);
                $("#modal-detail").modal('show');
            }
        })
    };
    /**
     * 初始化dataTables
     */
    var handlerInitDataTables = function (url,columns) {
        var dataTable = $('#user_list').DataTable({
            'paging'      : true,
            'lengthChange': false,
            'processing'  : true,
            'searching'   : false,
            'ordering'    : false,
            'info'        : true,
            'autoWidth'   : false,
            'serverSide'  : true,
            'deferRender' : true,
            'ajax'        : {
                "url" : url,
            },
            'columns' :columns,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            'initComplete': function (setting, json) {
                handInitCheckBox();
                handlerCheckBoxAll();
            }
        })
        return dataTable;
    }
    
    return {
        init: function () {
            handInitCheckBox();
            handlerCheckBoxAll();
        },
        /**
         * 用户选择框
         * @returns {*}
         */
        getCheckBox: function () {
            return checkBox;
        },
        /**
         * 分页的数据
         * @param url
         * @param cloumns
         * @returns {jQuery}
         */
        setTable: function (url,cloumns) {
            return handlerInitDataTables(url, cloumns);

        },
        /**
         * 显示用户详情
         * @param url
         */
        getDetail: function (url) {
            handlerShowDetail();
        }
    }

}();
$(document).ready(function () {
    App.init()
})

