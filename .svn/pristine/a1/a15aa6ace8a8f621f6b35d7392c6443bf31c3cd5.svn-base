<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 申请加入艺术圈" description="个人信息" page_tab="circle_information" sidebar_about="show">
<div id="circle-content">
    <div class="artCircle-rightbar-short">
        <!--动态加载内容-->
        <div class="notice-bar">
            <div class="noticebt-active">申请信息</div>
        </div>
        <div style="margin-top:40px;margin-left:60px;">
            <div style="float:left;font-family:'Microsoft YaHei'">
            ${circle.circleName}艺术圈申请
                <br>
                <br>

                <textarea id="applyDesc"  placeholder="输入申请信息" style="width:400px;height:30px"></textarea>
                <br>
                <br>
                <div style="display:inline-block">
                    <button  onclick=submit('${circle.circleId}') class="mybutton2">提交</button>
                </div>
                <div style="display:inline-block">
                    <button class="mybutton1"><a href="${baseUrl!}/circle/circleQuery">取消</button>
                </div>
            </div>
            <div class="artCircle-rightbar-short-imgtitle">
                <img src="${uploadUrl!}${circle.avatar}">
                <br />
            ${circle.circleName}艺术圈
            </div>

        </div>
    </div>
</div>

</div>
<script>
    function submit(circleId){
        $("#submit_btn").attr("disabled",true);
        $.ajax({
            url:"${baseUrl!}/circle/applySubmit",
            async:false,
            cache:false,
            type:'post',
            dataType:"json",
            data:{
                circleId:circleId,
                applyDesc:$("#applyDesc").val()
            },
            success:function(data){
                if(data.code=='200') {
                    alert("申请成功!");
                    location.href="${baseUrl}/circle/circleExplore";
                }else{
                    $("#submit_btn").attr("disabled",false);
                }
            },
        });
    }
</script>

</@html>