<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 评论" description="评论" page_tab="newsComment" sidebar_about="show">
    <#if news?exists>
        <div id="loginmain-show">
            <div id="loginMainshow">
                <div id="loginMainshowNo1">
                    <div class="outer">
                        <input type="hidden" id="hiddenNewsId" value="${news.newsId}">
                        <input type="hidden" id="hiddenCommentId" >
                        <a href="${baseUrl}/other/otherNewsShow?createUserId=${news.createUserId}" class="headPic"><img src="${uploadUrl!}${news.avatar}"></a>
                        <div class="main-show-message">
                            <a href="${baseUrl}/other/otherNewsShow?createUserId=${news.createUserId}"><p>${news.nickname}</p></a>
                            <span>${news.createTime}</span>
                        </div>
                        <ul class="slideButton">
                            <li><button>关注</button></li>
                            <li><a href="#">选项一</a></li>
                            <li><a href="#">选项二</a></li>
                            <li><a href="#">选项三</a></li>
                        </ul>
                        <p class="main-show-user-comment">${news.content}</p>
                        <div class="main-show-comment-img">
                            <#if news.imageList?exists>
                                <#list news.imageList as image>
                                    <img src="${uploadUrl!}/news/${image.picId}.jpg">
                                </#list>
                            </#if>
                        </div>
                        <div class="main-show-comment-buttons">
<#--                            <ul>
                                <li>
                                    <img src="${staticUrl!}/img/icon/send.png">
                                    <a href="#">转发</a>
                                </li>
                                <li>
                                    <img src="${staticUrl!}/img/icon/praise.png">
                                    <a href="#" onclick="addLike('${news.newsId}')" id="addLike_btn">点赞(<span id="addLike_count">${news.likeCount}</span>)</a>
                                </li>
                            </ul>-->
                        </div>
                        <div id="commentSend">
                            <textarea id="commentSendContent"></textarea>
                            <button id="addComment_btn" onclick="addComment('${news.newsId}')">发表评论</button>
                        </div>
                        <div id="commentShower">
                            <h3>评论:</h3>
                            <#if commentList?exists>
                                <#list commentList as comment>
                                    <#if comment.isFirstFloor=="Y">
                                        <div class="firstLayer">
                                            <a href="${baseUrl!}/other/otherNewsShow?createUserId=${comment.createUserId}" class="headPic"><img src="${uploadUrl!}${comment.createAvatar}">
                                            <span class="commentNickname">${comment.createNickname}</span></a>
                                            <span class="commentTime">${comment.createTime}</span>
                                            <div class="commentContent">
                                                ${comment.content}
                                            </div>
                                            <a href="###" class="getReplyListBtn">回复</a>
                                            <input type="hidden" id="commentIdSaver" value="${comment.commentId}">
                                            <#if Session.user.userId==comment.createUserId>
                                                <a href="###" class="getReplyListBtn"  onclick="deleteComment('${comment.commentId}','${comment.newsId}','Y')">删除</a>
                                            </#if>
                                        </div>
                                    <#elseif comment.isFirstFloor=="N">
                                        <div class="secondLayer">
                                            <a href="${baseUrl!}/other/otherNewsShow?createUserId=${comment.createUserId}"><span class="commentNickname">${comment.createNickname}</span></a>
                                            &nbsp;回复&nbsp;
                                            <a href="${baseUrl!}/other/otherNewsShow?createUserId=${comment.replyUserId}"><span class="commentNickname">${comment.replyNickname}</span></a>
                                            <span class="commentTime">${comment.createTime}</span>
                                            <div class="commentContent">
                                            ${comment.content}
                                            </div>
                                            <a href="###" class="getReplyListBtn">回复</a>
                                            <input type="hidden" id="commentIdSaver" value="${comment.commentId}">
                                            <#if Session.user.userId==comment.createUserId>
                                                <a href="#" class="getReplyListBtn" onclick="deleteComment('${comment.commentId}','${comment.newsId}','N')">删除</a>
                                            </#if>
                                        </div>
                                    </#if>
                                </#list>
                             </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#if>


<script>

    function deleteComment(commentId,newsId,isFirst)
    {
        if(window.confirm("确定删除该评论吗?"))
        {
            location.href="${baseUrl!}/news/deleteComment?commentId="+commentId+"&newsId="+newsId+"&isFirst="+isFirst;
         }
    }
    function addComment(id) {
            $("#addComment_btn").attr("disabled",true);

            $.ajax({
                url: "${baseUrl!}/news/addComment",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data:{
                    newsId:id,
                    content:$("#commentSendContent").val()
                },
                success:function(data){
                    if(data.code == '200'){
                        alert("评论成功");
                        location.reload();
                    }
                    else{
                        $("#addComment_btn").attr("disabled",false);
                    }
                },
            });

    }

    function cancelReply(node) {
        var $this = $(node);
        var cancelBtn = $('.cancelReplyListBtn');
        var cancelList = $('#commentReplyList');
        cancelBtn.remove();
        cancelList.remove();
    }
    function addReply(ele) {

        $.ajax({
            url: "${baseUrl!}/news/addReply",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data:{
                newsId:$("#hiddenNewsId").val(),
                commentId:$("#hiddenCommentId").val(),
                content:$("#replyContent").val()
            },
            success:function(data){
                if(data.code == '200'){
                    alert("回复成功");
                    location.reload();
                }
            },
        });
    }

    $(function () {
        var cancelReplyBtn = $("<a href='###' class='cancelReplyListBtn' onclick='cancelReply(this)'>取消回复</a>");
        var commentReplyList = $("<form id='commentReplyList'> <textarea id='replyContent'></textarea> <button type='button' id='reply_btn' onclick='addReply(this);'>发表</button> </form>");
        $('.getReplyListBtn').click(function () {
            $(this).parent().append(cancelReplyBtn).append(commentReplyList);
            $("#hiddenCommentId").val($(this).next().val());
        });
        $("#replyBtn").click(function () {

        });
    })

    function addLike(id){
        $("addLike_btn").attr("disabled",true);
        $.ajax({
            url: "${baseUrl!}/news/addLike",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data:{
                newsId:id
            },
            success:function(data){
                if(data.code == '200'){
                    location.href = "${baseUrl!}/news/comment";
                }
                else{
                    $("#addLike_btn").attr("disabled",false);
                }
            },
        });
    }
</script>
</@html>