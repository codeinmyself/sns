<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 艺术圈" description="艺术圈首页" page_tab="circle_index" sidebar_about="show">


        <div style="width: 680px; background-color: #F2F2F2; height: 50px; padding: 10px; vertical-align: top;  box-shadow: 5px 5px 5px #AEAEAE;">
            <select id="showScope" class="selectblank">
                <option value="01" selected="selected" >所有艺术圈</option>
                <option value="02" >艺术圈1</option>
                <option value="03" >艺术圈2</option>
            </select>
        </div>
        <!---------------------------------------------->
        <div style="width: 680px;margin-top:15px;margin-left:240px;">

            <div class="main-show-inner">
                <a href="#"><img src="${staticUrl!}/img/circle/IMG_2810.JPG"></a>
                <div class="main-show-message">
                    <p></p>
                    <span>1分钟前</span>
                    <span>浏览(12)</span>
                </div>

                <button>关注</button>
                <div style="float:right;line-height:50px;height:50px;color:gray;font-family:'Microsoft YaHei';font-size:14px">删除该动态</div>
                <p class="main-show-user-comment">原创作品集，欢迎点评</p>
                <div class="main-show-comment-img">
                    <img src="${staticUrl!}/img/circle/IMG_2810.JPG">
                    <img src="${staticUrl!}/img/circle/IMG_2810.JPG">
                    <img src="${staticUrl!}/img/circle/IMG_2810.JPG">
                </div>
                <div class="main-show-comment-buttons">
                    <ul>
                        <li>
                            <img src="${staticUrl!}/img/icon/love.png">
                            <a href="#">收藏</a>
                        </li>
                        <li>
                            <img src="${staticUrl!}/img/icon/send.png">
                            <a href="#">转发</a>
                        </li>
                        <li>
                            <img src="${staticUrl!}/img/icon/comment.png">
                            <a href="#">评论</a>
                        </li>
                        <li>
                            <img src="${staticUrl!}/img/icon/praise.png">
                            <a href="#">点赞</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>



<script>

    $(document).ready(function () {
        $("#flip").click(function () {
            $(".panel").slideToggle("slow");
            if ($("#side-nav").height() == 288) {
                $("#side-nav").animate({ height: '370px' });
            }
            else {
                $("#side-nav").animate({ height: '288px' });
            }


        });
    });

</script>

</@html>