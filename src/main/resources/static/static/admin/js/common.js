﻿layui.use('upload', function(){
    var upload = layui.upload,
        $ = layui.$;

    //使用时dom（后续优化）
    /*<div class="layui-form-item">
        <label class="layui-form-label">图片</label>
        <div class="layui-input-block">
            <div class="layui-upload-list">
                <input type="text" name="image" class="layui-input">
            </div>
            <button type="button" class="layui-btn" id="file-upload-btn" data-file-type="image">
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
        </div>
    </div>*/
    var fileUpload = function(){
        //执行实例
        var elem = '#file-upload-btn';
        var $elem = $(elem);
        var instance = upload.render({
            elem: elem,
            url: '/admin/file/upload/',
            data:{
                fileType:function(){
                    var fileType = $elem.attr('data-file-type');
                    if(!fileType){
                        fileType = 'image';
                    }
                    return fileType;
                }
            },
            done: function(result){
                if(result.type === 'success'){
                    $elem.prev().find('input').val(result.data);
                }else{
                    layer.msg(result.content, {time: 2000});
                }
            },
            error: function(){
                layer.msg("请求异常", {time: 2000});
            }
        });
        return instance;
    }();

});