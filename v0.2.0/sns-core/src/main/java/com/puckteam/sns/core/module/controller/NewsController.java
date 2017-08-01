package com.puckteam.sns.core.module.controller;

import com.google.gson.Gson;
import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.base.util.SequenceUtil;
import com.puckteam.sns.core.constant.CoreConfig;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.aop.annotation.UserSecurity;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.*;
import com.puckteam.sns.interfaces.core.vo.*;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 82402 on 2016/11/3.
 */

@Controller
@RequestMapping(value = "/news")

public class NewsController extends BaseController {

    @Autowired
    @Qualifier("newsService")
    public INewsService newsService;

    @Autowired
    @Qualifier("userService")
    public IUserService userService;

    @Autowired
    @Qualifier("likeService")
    public ILikeService likeService;

    @Autowired
    @Qualifier("commentService")
    public ICommentService commentService;

    @Autowired
    @Qualifier("imageService")
    public IImageService imageService;

    @Autowired
    @Qualifier("collectionService")
    public ICollectionService collectionService;

    @Autowired
    @Qualifier("noticeService")
    private INoticeService noticeService;

    @Autowired
    private UserUtil userUtil;



    /**
     * 动态创建
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addNews(HttpServletRequest request){

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String content = request.getParameter("content");
        String picture = request.getParameter("picture");
        String showScope=request.getParameter("showScope");
        String collection[]=request.getParameterValues("collection");
        String circle[]=request.getParameterValues("circle");
        News news = new News();
        news.setNewsId(UUID.randomUUID().toString().replace("-", ""));
        news.setLikeCount(0);
        news.setCommentCount(0);
        news.setContent(content);
        news.setShowScope(showScope);
        news.setStatus(CoreConstant.Status.NORMAL);
        news.setCreateUserId(currentUser.getUserId());
        if(newsService.checkNewsSensitiveKeyInContent(news))
        {
            if (newsService.addNews(news) > 0) {
                if (!(picture == null || picture == "")) {
                    String[] pictureStrings = picture.split(",");
                    for (int i = 0; i < pictureStrings.length; ++i) {
                        Image image = new Image();
                        image.setNewsId(news.getNewsId());
                        image.setPicIndex(i);
                        image.setPicId(pictureStrings[i]);
                        this.imageService.addImage(image);
                    }
                }
                if (this.newsService.addNewsRelaCircleCollection(news, circle, collection)) {
                    currentUser = userService.setUserExtInfo(currentUser);
                    request.getSession(true).setAttribute(CoreConstant.USER_SESSION, currentUser);
                    return renderJsonSucc(null);
                } else {
                    return renderJsonError("插入数据库出错");
                }
            }
            else {
                return renderJsonError("增加数据库出错!");
            }
        }
        else
        {
            return  renderJsonError("您所发表的动态因涉及到敏感词汇而被禁止发送!");
        }
    }

    /**
     * 动态删除
     * @param request
     * @return
     */
    @RequestMapping(value = "/del")
    @UserSecurity
    public String delNews(HttpServletRequest request, HttpServletResponse response){
        String newsId = request.getParameter("newsId");
        newsService.delNews(newsId);
        User currentUser = userUtil.getCurrentUser(request);
        userService.setUserExtInfo(currentUser);
        request.getSession(true).setAttribute(CoreConstant.USER_SESSION, currentUser);
        return "redirect:../";
    }


    /**
     * 评论页
     * @param request
     * @return
     */
    @RequestMapping(value = "/comment")
    public String newsComment(HttpServletRequest request){
        String newsId = request.getParameter("newsId");
        News news = newsService.queryForNewsByNewsId(newsId);
        request.setAttribute("news",news);
        request.setAttribute("commentList",this.newsService.queryForAllCommentByNewsId(newsId));
        return "module/news/newsComment.ftl";
    }

    /**
     * 评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addComment(HttpServletRequest request){

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String newsId = request.getParameter("newsId");
        String content = request.getParameter("content");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setNewsId(newsId);
        comment.setCommentId(UUID.randomUUID().toString().replace("-", ""));
        comment.setCreateUserId(currentUser.getUserId());
        if(commentService.addComment(comment)>0&&newsService.commentCountPlus(comment.getNewsId())>0){
             if(noticeService.commentByOthersNotice(comment))
                return renderJsonSucc(null);
            else
                return renderJsonError("增加数据库出错!");
        }
        else
        {
            return renderJsonError("增加数据库出错!");
        }
    }


    /**
     * 删除评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteComment")
    @UserSecurity
    public String deleteComment(HttpServletRequest request) {
        String commentId = request.getParameter("commentId");
        String newsId = request.getParameter("newsId");
        String isFirst=request.getParameter("isFirst");
        commentService.delComment(commentId);
        if ( isFirst.equals("Y")) {
            newsService.commentCountMinus(newsId);
        }
        News news = newsService.queryForNewsByNewsId(newsId);
        request.setAttribute("news",news);
        request.setAttribute("commentList",this.newsService.queryForAllCommentByNewsId(newsId));
        return "module/news/newsComment.ftl";
    }

    /**
     * 回复
     * @param request
     * @return
     */
    @RequestMapping(value = "/addReply",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addReply(HttpServletRequest request){
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }
        String newsId=request.getParameter("newsId");
        String commentId = request.getParameter("commentId");
        String content = request.getParameter("content");
        Comment comment = new Comment();
        comment.setNewsId(newsId);
        comment.setContent(content);
        comment.setReplyCommentId(commentId);
        comment.setCommentId(UUID.randomUUID().toString().replace("-", ""));
        comment.setCreateUserId(currentUser.getUserId());
        if(commentService.addReply(comment)>0){
            if(noticeService.replyByOtherNotice(comment))
                return renderJsonSucc(null);
            else
                return renderJsonError("增加数据库出错!");
        }
        else
        {
            return renderJsonError("增加数据库出错!");
        }
    }

    @RequestMapping(value="/upload3", method= RequestMethod.POST)
    @ResponseBody
    public Object upload3(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String uploadPath = CoreConfig.getInstance().getPackingConfig().getImageUploadUrl();

        //开发模式取本地
        if (CoreConfig.getInstance().isDevelopMode()) {
            uploadPath = request.getRealPath(uploadPath);
        }

        String newsPath = uploadPath + "/" + CoreConstant.UploadTargetDir.NEWS;

        File dir = new File(uploadPath);
        if (!dir.exists())
            dir.mkdirs();
        if(request instanceof MultipartRequest) {
            MultiValueMap<String, MultipartFile> map = ((MultipartRequest) request).getMultiFileMap();
            LinkedList<CommonsMultipartFile> imageList = (LinkedList) map.get("file");
            if (imageList != null && imageList.size() > 0) {
                CommonsMultipartFile file = imageList.get(0);
                String picId = SequenceUtil.uuid2();
                String fileName = picId+".jpg";
                FileItem fileItem = file.getFileItem();
                if (!fileItem.isFormField()) {
                    fileItem.write(new File(newsPath, fileName));
                    Map result = new HashMap();
                    result.put("picId",picId);
                    return new Gson().toJson(result);
                }
            }
        }
        return null;
    }

    /**
     * 动态点赞
     * @param request
     * @return
     */
    @RequestMapping(value = "/addLike",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addLike(HttpServletRequest request){

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String newsId = request.getParameter("newsId");
        News news = new News();
        news.setNewsId(newsId);
        Like like = new Like();
        like.setLikeId(SequenceUtil.uuid2());
        like.setNewsId(news.getNewsId());
        like.setCreateUserId(currentUser.getUserId());
        if(!likeService.ifLike(like.getNewsId(),like.getCreateUserId())) {
            if (newsService.likeCountPlus(news.getNewsId()) > 0 && likeService.addLike(like) > 0) {
                return renderJsonSucc(null);
            }
            else {
                return renderJsonError("修改数据库出错!");
            }
        }
        else if(likeService.ifLike(like.getNewsId(),like.getCreateUserId()))
        {
            if(newsService.likeCountMinus(news.getNewsId())>0&&likeService.delLike(like.getNewsId(),like.getCreateUserId())>0){
                return renderJsonSucc(null);
            }
            else {
                return renderJsonError("修改数据库出错!");
            }
        }
        else {
            return renderJsonError("修改数据库出错!");
        }
    }

    /**
     * 动态加入作品集
     * @param request
     * @param response
     * @return
             */
    @RequestMapping(value = "/addNewsToCollection")
    @ResponseBody
    public JsonResult addNewsToCollection(HttpServletRequest request,HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String newsId=request.getParameter("newsId");
        String collection[]=request.getParameterValues("collection");
        News news = new News();
        news.setNewsId(newsId);

        if(this.newsService.addNewsRelaCircleCollection(news,null,collection))
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库出错");
        }
    }


    /**
     * 动态加入艺术圈
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addNewsToCircle")
    @ResponseBody
    public JsonResult addNewsToCircle(HttpServletRequest request,HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String newsId=request.getParameter("newsId");
        String circle[]=request.getParameterValues("circle");
        News news = new News();
        news.setNewsId(newsId);

        if(this.newsService.addNewsRelaCircleCollection(news,circle,null))
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库出错");
        }
    }
    /**
     * 从作品集中删除动态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteNewsInConnection")
    @ResponseBody
    public JsonResult deleteNewsInConnection(HttpServletRequest request,HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String newsId=request.getParameter("newsId");
        String collection=request.getParameter("collectionId");
        NewsRelaCircleCollection newsRelaCircleCollection=new NewsRelaCircleCollection();
        newsRelaCircleCollection.setNewsId(newsId);
        newsRelaCircleCollection.setRelaType(CoreConstant.NewsAssociateCircleCollectionType.collectionNews);
        newsRelaCircleCollection.setTargetId(collection);

        if(this.newsService.deleteNewsRelaCircleCollection(newsRelaCircleCollection)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库出错");
        }
    }

    /**
     * 从艺术圈中删除动态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteNewsInCircle")
    @ResponseBody
    public JsonResult deleteNewsInCircle(HttpServletRequest request,HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String createUserId=request.getParameter("createUserId");
        String newsId=request.getParameter("newsId");
        String circle=request.getParameter("circleId");
        NewsRelaCircleCollection newsRelaCircleCollection=new NewsRelaCircleCollection();
        newsRelaCircleCollection.setNewsId(newsId);
        newsRelaCircleCollection.setRelaType(CoreConstant.NewsAssociateCircleCollectionType.cicleNews);
        newsRelaCircleCollection.setTargetId(circle);

        if(this.newsService.deleteNewsRelaCircleCollection(newsRelaCircleCollection)>0)
        {
            //不是由本人删除，则是由艺术圈管理员或圈主删除
            if(!currentUser.getUserId().equals(createUserId))
            {
                News news=new News();
                news.setNewsId(newsId);
                news.setCreateUserId(createUserId);
                //提醒艺术圈动态被删除
                if(noticeService.deleteCircleNewsNotice(newsRelaCircleCollection,news))
                {
                    return renderJsonSucc(null);
                }
                else
                {
                    return renderJsonError("插入数据库出错");
                }
            }
            else
            {
                return renderJsonSucc(null);
            }
        }
        else
        {
            return renderJsonError("插入数据库出错");
        }
    }


}
