<#include "common/_layout.ftl"/>
<@html title="puckart社区" description="" page_tab="topic"
sidebar_user_info="show" sidebar_newtopic="show" sidebar_about="show">
    <#if Session.user??>
    <div id="loginmain-show">
        <div id="loginPublish">
            <form action="" method="get">
                <textarea id="newsContent"></textarea>
                <div>
                    <a href="#"><img class="commentBtn" src="${staticUrl!}/img/icon/face.png"></a>
                    <a href="#" id="openLayerBtn"><img class="commentBtn" src="${staticUrl!}/img/icon/pic.png"></a>
                    <button type="button"  id="addNews_btn">发表</button>
                </div>
            </form>
        </div>
        <div id="loginMainshow">
            <#if newsList?exists>
                <#list newsList as news>
                    <div id="loginMainshowNo1">
                        <div class="outer">
                            <a href="${baseUrl}/other/otherNewsShow?createUserId=${news.createUserId}" class="headPic"><img src="${uploadUrl!}${news.avatar}"></a>
                            <div class="main-show-message">
                                <p><strong><a href="${baseUrl}/other/otherNewsShow?createUserId=${news.createUserId}">${news.nickname!}</a></strong></p>
                                <span class="newsTime">${news.createTime}</span>
                                <span>浏览：${news.clickCount}</span>
                            </div>
                            <ul class="slideButton">
                                <li><button class="slideOperate">▼</button></li>
                                <li>
                                    <a href="javascript:void(0)" class="reportBtn">举报</a>
                                    <input type="hidden" value="${news.newsId}" id="getReportNewsId">
                                </li>
                                <#if Session.user.userId==news.createUserId>
                                    <li>
                                        <a href="javascript:void(0)" onclick="addNewsToCollection('${news.newsId}','${news.showScope}')">加入作品集</a>
                                    </li>
                                    <#if news.showScope=="02"||news.showScope=="01">
                                        <li>
                                            <a href="javascript:void(0)" onclick="addNewsToCircle('${news.newsId}')">加入艺术圈</a>
                                        </li>
                                    </#if>
                                    <li><a href="#" onclick="deletenews('${news.newsId}')">删除</a></li>
                                </#if>
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
                                <ul>
<#--                                    <li>
                                        <img src="${staticUrl!}/img/icon/send.png">
                                        <a href="#" class="resendBtn">转发</a>
                                    </li>-->
                                    <li>
                                        <a href="${baseUrl!}/news/comment?newsId=${news.newsId}">评论(<span id="addComment_count">${news.commentCount}</span>)</a>
                                    </li>
                                    <li>
                                        <img src="${staticUrl!}/img/icon/praise.png">
                                        <a href="#" onclick="addLike('${news.newsId}')" id="addLike_btn">点赞(<span id="addLike_count">${news.likeCount}</span>)</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>

    <div id="loginMasterpieces">
        <ul>
            <li>
                热门作品集
            </li>
            <#if topCollectionList?exists>
                <#list topCollectionList as topCollection>
                    <#if topCollection_index==0>
                        <li class="masterpieces-first">
                            <img src="${uploadUrl!}${topCollection.cover}" class="masterpieces-best">
                            <div>
                                <img src="${staticUrl!}/img/icon/0.png" class="masterpieces-number">
                                <p><a href="#">${topCollection.collectionName}</a></p>
                                <p>${topCollection.createNickname}</p>
                            </div>
                        </li>
                    <#else >
                        <li>
                            <img src="${staticUrl!}/img/icon/${topCollection_index}.png" class="masterpieces-number">
                            <a href="#">${topCollection.collectionName}</a>
                        </li>
                    </#if>
                </#list>
            </#if>
        </ul>
    </div>
    <#else >
        <#if newsList?exists>
            <#list newsList as news>
            <div id="main-show">
                <div class="main-show-inner">
                    <a href="#" class="headPic"><img src="${uploadUrl!}${news.avatar}"></a>
                    <div class="main-show-message">
                        <p><strong>${news.nickname}</strong></p>
                        <span class="newsTime">${news.createTime}</span>
                        <span>浏览：${news.clickCount}</span>
                    </div>
                <#--        <button>关注</button>-->
                    <p class="main-show-user-comment">${news.content}</p>

                    <div class="main-show-comment-img">
                        <#if news.imageList?exists>
                            <#list news.imageList as image>
                                <img src="${uploadUrl!}/news/${image.picId}.jpg">
                            </#list>
                        </#if>
                    </div>
                    <div class="main-show-comment-buttons">
                    </div>
                </div>
            </div>
            </#list>
        </#if >

    <div id="masterpieces">
        <ul>
            <li>
                热门作品集
            </li>
            <#if topCollectionList?exists>
                <#list topCollectionList as topCollection>
                    <#if topCollection_index==0>
                        <li class="masterpieces-first">
                            <img src="${uploadUrl!}${topCollection.cover}" class="masterpieces-best">
                            <div>
                                <img src="${staticUrl!}/img/icon/0.png" class="masterpieces-number">
                                <p><a href="#">${topCollection.collectionName}</a></p>
                                <p>${topCollection.createNickname}</p>
                            </div>
                        </li>
                    <#else >
                        <li>
                            <img src="${staticUrl!}/img/icon/${topCollection_index}.png" class="masterpieces-number">
                            <a href="#">${topCollection.collectionName}</a>
                        </li>
                    </#if>
                </#list>
            </#if>
        </ul>
    </div>
    </#if >

<!--制定范围模态框-->
<div id="news-modal">
    <div id="news-list">
        <form method="post" action="action-login.php" autocomplete="off">
            <label>
                <p class="label-name">请指定动态的公开范围</p>
            </label>
                <input type="radio" name="newsRangeRadio" id="rangeGroup" class="newRangeItem" value="03"><span>仅自己</span>
                <input type="radio" name="newsRangeRadio" id="rangeGroup" class="newRangeItem" value="02"><span>仅关注者</span>
                <input type="radio" name="newsRangeRadio" id="rangeGroup" class="newRangeItem" value="01"><span>公开</span>
            <label>
                <p class="label-name">保存于作品集</p>
            </label>
            <div id="collectionAll" style="overflow-y: scroll;height: 90px;">
                <#if collectionList?exists>
                    <#list collectionList as collection>
                        <div>
                            <input type="checkbox" name="newsCollectionRange" id="collectionGroup" value="${collection.collectionId}">
                            <span>${collection.collectionName}</span>
                        </div>
                    </#list>
                </#if>
            </div>
            <div id="collectionFocus" style="overflow-y: scroll;height: 90px;display: none">
                <#if collectionList?exists>
                    <#list collectionList as collection>
                        <#if collection.showScope=="02"||collection.showScope=="03">
                            <div>
                                <input type="checkbox" name="newsCollectionRange" id="collectionGroup" value="${collection.collectionId}">
                                <span>${collection.collectionName}</span>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div id="collectionMyself" style="overflow-y: scroll;height: 90px;display: none">
                <#if collectionList?exists>
                    <#list collectionList as collection>
                        <#if collection.showScope=="03">
                            <div>
                                <input type="checkbox" name="newsCollectionRange" id="collectionGroup" value="${collection.collectionId}">
                                <span>${collection.collectionName}</span>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <br>
            <label id="circleLabel">
                <p class="label-name">发送至艺术圈</p>
            </label>
            <div id="circleAll" style="overflow-y: scroll;height: 90px;">
                <#if circleList?exists>
                    <#list circleList as circle>
                     <div>
                        <input type="checkbox" name="newsCircleRange" id="collectionGroup" value="${circle.circleId}">
                        <span>${circle.circleName}</span>
                     </div>
                    </#list>
                </#if>
            </div>
            <br>
            <br>
            <button type="button" id="saveToGroup" onclick="addNewsClick()">发表</button>
            <button type="button" id="cancelToGroup">取消</button>
        </form>
    </div>
</div>


<div id="report-modal">
    <div id="report-list">
        <form method="post" autocomplete="off">
            <label>
                <p class="label-name">举报类型</p>
            </label>
            <input type="hidden" id="reportHiddenNewsId">
            <input type="radio" name="reportReason" id="reportReason1" value="01"><span>违法信息</span>
            <input type="radio" name="reportReason" id="reportReason2" value="02"><span>露骨色情内容</span>
            <input type="radio" name="reportReason" id="reportReason3" value="03"><span>垃圾信息</span>
            <input type="radio" name="reportReason" id="reportReason4" value="04"><span>人身攻击</span>
            <input type="radio" name="reportReason" id="reportReason5" value="05"><span>其他</span>
            <label>
                <p class="label-name">举报原因</p>
            </label>
            <textarea id="reportComment"></textarea>
            <button type="button" id="reportSaveToGroup" onclick="reportNews()">举报</button>
            <button type="button" id="reportCancelToGroup">取消</button>
        </form>
    </div>
</div>

<!--加入作品集-->
<div id="addCollection-modal">
    <div id="addCollection-list">
        <form method="post" autocomplete="off">
            <input type="hidden" id="addNewsToCollectionHiddenNewsId">
            <label>
                <p class="label-name">选择作品集</p>
            </label>
            <div id="addCollectionAll" style="overflow-y: scroll;height: 90px;">
            </div>
            <button type="button" id="addCollectionSaveToGroup" onclick="addNewsToCollectionSubmit()">确定</button>
            <button type="button" id="addCollectionCancelToGroup">取消</button>
        </form>
    </div>
</div>

<!--加入艺术圈-->
<div id="addCircle-modal">
    <div id="addCircle-list">
        <form method="post" autocomplete="off">
            <input type="hidden" id="addNewsToCircleHiddenNewsId">
            <label>
                <p class="label-name">选择艺术圈</p>
            </label>
            <div id="addCircleAll" style="overflow-y: scroll;height: 90px;">
            </div>
            <button type="button" id="addCircleSaveToGroup" onclick="addNewsToCircleSubmit()">确定</button>
            <button type="button" id="addCircleCancelToGroup">取消</button>
        </form>
    </div>
</div>

<script type="text/javascript">


    $(function()
    {
        for(var i=0;i<$(".newsTime").length;++i)
            $(".newsTime")[i].innerHTML = moment($(".newsTime")[i].innerHTML,"YYYY-MM-DD h:mm:ss").fromNow();
        $(".slideOperate").click(function () {
            $(this).parent().siblings('li').slideToggle('fast');
        })
    });

    $(function(){

        var offset = $('#openLayerBtn').offset();
        if(offset) {
            myUploader.init({
                server: '${baseUrl!}/news/upload3',
                swf: '${staticUrl!}/js/Uploader.swf',
                picIds: 'picIds',
                fileIds: 'fileIds',
                openLayerBtn: 'openLayerBtn',
                layerOffset: {
                    top: offset.top,
                    left: offset.left - 180
                }
            });
        }
    });

    function  addNewsClick()
    {
        var newsShowScope=$("input[name='newsRangeRadio']:checked").val();
        if(newsShowScope==null)
        {
            alert("请选择动态的公开范围!");
        }
        else
        {
            var arrCollection = [];
            $("input[name='newsCollectionRange']:checked").each(function () {
                arrCollection.push(this.value);
            });
            var arrCircle = [];
            $("input[name='newsCircleRange']:checked").each(function () {
                arrCircle.push(this.value);
            });
            $.ajax({
                url: "${baseUrl!}/news/add",
                async: false,
                cache: false,
                traditional: true,
                type: 'post',
                dataType: "json",
                data: {
                    picture: $("#picIds").val(),
                    content: $("#newsContent").val(),
                    showScope: newsShowScope,
                    collection: arrCollection,
                    circle: arrCircle
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("成功发表！");
                        location.href = "${baseUrl!}/";
                    }
                    else
                    {
                        alert(data.message);
                    }
                },
            });
        }
    }

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
                    location.href = "${baseUrl!}/";
                }
                else{
                    $("#addLike_btn").attr("disabled",false);
                }
            },
        });
    }

    function deletenews(newsId)
    {
        if(window.confirm("确定删除该动态吗?"))
        {
            location.href ="${baseUrl}/news/del?newsId="+newsId;
        }
    }
    $(function(){
        var openBtn = document.getElementById('addNews_btn');
        getToCenter($('#addCollection-list'));
        getToCenter($('#addCircle-list'));
        if(openBtn) {
            var closeBtn = document.getElementById('cancelToGroup');
            var targetList = document.getElementById('news-modal');
            getToCenter($('#news-list'));
            new newsModal(openBtn, closeBtn, targetList);
        }
    });

    $(document).ready(function()
    {
        $(".newRangeItem").change(
            function()
            {
                var $selectedvalue = $("input[name='newsRangeRadio']:checked").val();
                if ($selectedvalue == "01")
                {
                    $("#collectionAll").css("display", "block");
                    $("#collectionFocus").css("display","none");
                    $("#collectionMyself").css("display","none");
                    $("#circleAll").css("display","block");
                    $("#circleLabel").css("display","block");
                }
                else if($selectedvalue == "02")
                {
                    $("#collectionAll").css("display", "none");
                    $("#collectionFocus").css("display","block");
                    $("#collectionMyself").css("display","none");
                    $("#circleAll").css("display","block");
                    $("#circleLabel").css("display","block");
                }
                else
                {
                    $("#collectionAll").css("display", "none");
                    $("#collectionFocus").css("display","none");
                    $("#collectionMyself").css("display","block");
                    $("#circleAll").css("display","none");
                    $("#circleLabel").css("display","none");
                }
            }
        );
    });

    /*举报*/
    $(function () {
        var openBtn = $('.reportBtn');
        if(openBtn) {
            var closeBtn = document.getElementById('reportCancelToGroup');
            var targetList = document.getElementById('report-modal');
            getToCenter($('#report-list'));
            for (var i = 0; i < openBtn.length; i++) {
                new model(openBtn[i], closeBtn, targetList);
            }
        }
    });

    function reportNews()
    {
        var reportType=$("input[name='reportReason']:checked").val();
        var reportReason=$("#reportComment").val();
        if(!reportType)
        {
            alert("请选择举报的类型!");
        }
        else if(!reportReason)
        {
            alert("请填写举报的原因!");
        }
        else
        {
            $.ajax({
                url: "${baseUrl!}/report/add",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    reportType:reportType,
                    reportDesc:reportReason,
                    targetType:'02',
                    targetId:$("#reportHiddenNewsId").val()
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("举报成功！");
                        location.reload();
                    }
                    else
                    {
                        alert(data.message);
                    }
                },
            });
        }
    }
    function addNewsToCollection(newsId,showScope)
    {
        $.ajax({
            url: "${baseUrl!}/collection/addNewsToCollection",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                newsId:newsId,
                showScope:showScope
            },
            success: function (data)
            {
                $("#addNewsToCollectionHiddenNewsId").val(newsId);
                $("#addCollection-modal").css("display","block");
                document.body.style.overflow="hidden";
                for(var i=0;i<data.length;i++){
                    var targetCheckbox = $("<input type='checkbox' name='addCollectionCheckbox'>");
                    var targetSpan = $("<span></span>");
                    var div = $("<div id='comeFromJs'></div>");
                    targetCheckbox.attr('value',data[i].collectionId);
                    targetSpan.html(data[i].collectionName);
                    div.append(targetCheckbox).append(targetSpan);
                    $("#addCollectionAll").append(div);

                }
            }
        });
    }
    $("#addCollectionCancelToGroup").click(function () {
        $("#addCollection-modal").css("display","none");
        $("#comeFromJs").remove();
        document.body.style.overflow = "scroll";
    })

    function addNewsToCollectionSubmit()
    {
        var arrCollection = [];
        $("input[name='addCollectionCheckbox']:checked").each(function () {
            arrCollection.push(this.value);
        });
        if(arrCollection.length!=0)
        {
            $.ajax({
                url: "${baseUrl!}/news/addNewsToCollection",
                async: false,
                cache: false,
                traditional: true,
                type: 'post',
                dataType: "json",
                data: {
                    newsId: $("#addNewsToCollectionHiddenNewsId").val(),
                    collection: arrCollection
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("成功加入作品集！");
                        location.reload();
                    }
                    else {
                        alert(data.message);
                    }
                },
            });
        }
        else
        {
            location.reload();
        }
    }

    function addNewsToCircle(newsId)
    {
        $.ajax({
            url: "${baseUrl!}/circle/addNewsToCircle",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                newsId:newsId
            },
            success: function (data)
            {
                $("#addNewsToCircleHiddenNewsId").val(newsId);
                $("#addCircle-modal").css("display","block");
                document.body.style.overflow="hidden";
                for(var i=0;i<data.length;i++){
                    var targetCheckbox = $("<input type='checkbox' name='addCircleCheckbox'>");
                    var targetSpan = $("<span></span>");
                    var div = $("<div id='comeFromJs'></div>");
                    targetCheckbox.attr('value',data[i].circleId);
                    targetSpan.html(data[i].circleName);
                    div.append(targetCheckbox).append(targetSpan);
                    $("#addCircleAll").append(div);
                }
            }
        });
    }

    $("#addCircleCancelToGroup").click(function () {
        $("#addCircle-modal").css("display","none");
        $("#comeFromJs").remove();
        document.body.style.overflow = "scroll";
    })

    function addNewsToCircleSubmit()
    {
        var arrCircle = [];
        $("input[name='addCircleCheckbox']:checked").each(function () {
            arrCircle.push(this.value);
        });
        if(arrCircle.length!=0)
        {
            $.ajax({
                url: "${baseUrl!}/news/addNewsToCircle",
                async: false,
                cache: false,
                traditional: true,
                type: 'post',
                dataType: "json",
                data: {
                    newsId: $("#addNewsToCircleHiddenNewsId").val(),
                    circle: arrCircle
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("成功加入艺术圈！");
                        location.reload();
                    }
                    else {
                        alert(data.message);
                    }
                },
            });
        }
        else
        {
            location.reload();
        }
    }
</script>

<link  href="${staticUrl!}/js/layui/css/layui.css" rel="stylesheet"/>
<script src="${staticUrl!}/js/layui/layui.js"></script>
<link  href="${staticUrl!}/js/webuploader/webuploader.css" rel="stylesheet"/>
<script src="${staticUrl!}/js/webuploader/webuploader.js"></script>
<script src="${staticUrl!}/js/webuploader/myUploader.js"></script>
</@html>