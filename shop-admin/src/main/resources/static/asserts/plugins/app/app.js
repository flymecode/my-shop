var App = function () {
    var masterCheck;
    var checkBox;


    /**
     * 文件上传
     * @param elementId
     * @param url
     */
    var handlerInitDropzone = function (elementId, url) {
        Dropzone.autoDiscover = false;
        var myDropzone = new Dropzone(elementId, {
            url: url,
            dictDefaultMessage : '拖动文件至此或者点击上传',
            paramName : 'dropFile',
            maxFileszie: 2,
            acceptedFiles: '.jpg,.git,.png,.jpeg',
            addRemoveLinks: true,
            dictResponseError: '文件上传失败',
            dictRemoveLinks: '删除',
            dictCancelUpload: '取消',
            dictFileTooBig: '文件过大上传文件最大支持',
            dictFallbackMessage: '浏览器不支持',
            init: function () {
                this.on('success', function (file, data) {
                    $("#pic").val(data.url);
                });
            }
        });
    };
    var handInitCheckBox = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        masterCheck = $('input[type="checkbox"].minimal.icheck_master');
        checkBox = $('input[type="checkbox"].minimal');

    };
    var formateTime = function (data) {
        var date = new Date(data.updated);
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentDate = date.getFullYear() + "-" + month + "-" + strDate
            + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        return currentDate;
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
            dataType: 'json',
            success: function (data) {
                $("#v-username").text(data.data.username);
                $("#v-email").text(data.data.email);
                $("#v-updated").text(data.data.updated);
                $("#v-phone").text(data.data.phone);
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

    var handlerInitTree = function (url, param,callback) {
        var setting = {
            view : {
                selectedMulti:false
            },
            async: {
                enable : true,
                type: 'post',
                url: url,
                autoParam: param
            }
        }

        $.fn.zTree.init($('#myTree'), setting);
        $('#btnOk').bind('click', function () {
            var zTree = $.fn.zTree.getZTreeObj('myTree');
            var nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert('请选择一个节点')
            } else {
                callback(nodes);
            }
        });
    };
    
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
            handlerShowDetail(url);
        },
        getTree: function (url, param, callbcak) {
            handlerInitTree(url, param, callbcak);
        },
        dropz: function (elementId, url) {
            handlerInitDropzone(elementId, url);
        },
        formateTime: function (data) {
            return formateTime(data);
        }
    }

}();
$(document).ready(function () {
    App.init()
})

