<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 修改艺术圈" description="艺术圈信息" page_tab="circle_information" sidebar_about="show">
<h4>Puckart > 修改成员权限</h4>
<div class="container">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nac-tabs nav-stacked" data-spy="affix" data-offset-top="125">

                <form>
                    <h6>艺术圈id：</h6>
                    <input type="text" id="circleName" value="${member.circleId}">
                </form>
                <form>
                    <h6>成员id：</h6>
                    <input type="text" id="circleName" value="${member.memberId}">
                </form>
                <form>
                    <h6>用户id：</h6>
                    <input type="text" id="description" value="${member.userId}">
                </form>
                <form>
                    <h6>成员权限：</h6>
                    普通成员：
                    <input type="radio" value="03" name="memberType" <#if member.memberType=="03">checked="checked"</#if>   />
                    <br><br>
                    管理员：
                    <input type="radio" value="02" name="memberType" <#if member.memberType=="02">checked="checked"</#if>  />
                </form>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-8">
                        <a onclick="submit('${member.memberId}','${member.circleId}')" id="submit_btn" class="btn btn-raised btn-default">确定</a>
                    </div>
                </div>

            </ul>
        </div>
    </div>

</div>
<script>
    function submit(memberId,circleId){
        $("#submit_btn").attr("disabled",true);
        $.ajax({
            url:"${baseUrl!}/submitMemberRevise",
            async:false,
            cache:false,
            type:'post',
            dataType:"json",
            data:{
                memberId:memberId,
                circleId:circleId,
                memberType:$("input[name='memberType']:checked").val()
            },
            success:function(data){
                if(data.code=='200') {
                    alert("修改成功!");
                    location.href="${baseUrl}/memberManage?circleId=${member.circleId}";
                }else{
                    $("#submit_btn").attr("disabled",false);
                }
            },
        });
    }
</script>

</@html>