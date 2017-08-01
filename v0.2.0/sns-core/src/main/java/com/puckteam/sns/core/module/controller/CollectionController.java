package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.util.SequenceUtil;
import com.puckteam.sns.core.constant.AuthConstant;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.constant.CoreConfig;
import com.puckteam.sns.core.support.aop.annotation.UserSecurity;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.core.support.util.ImageUploadUtil;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.IAuthService;
import com.puckteam.sns.interfaces.core.service.ICollectionService;
import com.puckteam.sns.interfaces.core.vo.Collection;
import com.puckteam.sns.interfaces.core.vo.News;
import com.puckteam.sns.interfaces.core.vo.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


/**
 * Created by 82402 on 2016/10/15.
 */
@Controller
@RequestMapping(value = "/collection")
public class CollectionController extends BaseController {

    @Autowired
    @Qualifier("collectionService")
    public ICollectionService collectionService;

    @Autowired
    @Qualifier("authService")
    public IAuthService authService;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private ImageUploadUtil imageUploadUtil;

    /**
     * 作品集首页
     *
     * @param request
     * @return
     */
    @UserSecurity
    @RequestMapping(value = "/index")
    public String collectionIndex(HttpServletRequest request) {
        List<Collection> collectionList = collectionService.findCollectionListByCreateUserId(userUtil.getCurrentUser(request).getUserId());
        request.setAttribute("collectionList", collectionList);
        return "module/collection/collection_index.ftl";
    }



    /**
     * 展示作品集页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "collectionShow", method = RequestMethod.GET)
    public String collectionShow(HttpServletRequest request) {
        return "module/collection/collection_show.ftl";
    }

    /**
     * 作品集创建
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addCollection(@RequestParam("addfile") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws Exception {

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String collectionName = request.getParameter("collectionName");
        String showScope = request.getParameter("showScope");
        String description = request.getParameter("description");
        String userId = currentUser.getUserId();

        if (StringUtils.isEmpty(collectionName)
                || StringUtils.isEmpty(showScope)
                || StringUtils.isEmpty(description)) {
            return renderJsonError("必要信息不能为空");
        }

        String targetDir = CoreConstant.UploadTargetDir.COLLECTION;
        String fileId = SequenceUtil.uuid2();// 文件名
        String cover = "";

        try{

            String filename = imageUploadUtil.upload(request,uploadFile,targetDir,fileId);
            cover = "/" + targetDir + "/" + filename;

        }catch (IOException e){
            return renderJsonError("上传图片出错");
        }


        Collection collection = new Collection();
        collection.setCollectionId(UUID.randomUUID().toString().replace("-", ""));
        collection.setCollectionName(collectionName);
        collection.setShowScope(showScope);
        collection.setDescription(description);
        collection.setStatus(CoreConstant.Status.NORMAL);
        collection.setCreateUserId(userId);
        collection.setCover(cover);

        if (collectionService.addCollection(collection) > 0) {
            return renderJsonSucc(null);
        }

        return renderJsonError("插入数据库出错!");
    }


    /**
     * 作品集删除
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResult deleteCollection(HttpServletRequest request) {

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String collectionId = request.getParameter("collectionId");

        if(!authService.checkCollectionOperate(
                currentUser.getUserId(),collectionId, AuthConstant.Collection.Owner)){
            return renderJsonUnAuth();
        }

        if (collectionService.delCollectionByCollectionId(collectionId) > 0) {
            return renderJsonSucc(null);
        } else {
            return renderJsonError("删除数据库出错!");
        }
    }


    /**
     * 作品集修改
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateAndUpload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateCollection(@RequestParam("updateFile") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws Exception {

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String collectionId = request.getParameter("collectionId");

        if(!authService.checkCollectionOperate(
                currentUser.getUserId(),collectionId, AuthConstant.Collection.Owner)){
            return renderJsonUnAuth();
        }

        String collectionName = request.getParameter("collectionName");
        String showScope = request.getParameter("showScope");
        String description = request.getParameter("description");

        if (StringUtils.isEmpty(collectionName)
                || StringUtils.isEmpty(showScope)
                || StringUtils.isEmpty(description)) {
            return renderJsonError("必要信息不能为空");
        }


        String targetDir = CoreConstant.UploadTargetDir.COLLECTION;
        String fileId = SequenceUtil.uuid2();// 文件名
        String cover = "";

        try{

            String filename = imageUploadUtil.upload(request,uploadFile,targetDir,fileId);
            cover = "/" + targetDir + "/" + filename;

        }catch (IOException e){
            return renderJsonError("上传图片出错");
        }

        Collection collection = new Collection();
        collection.setCollectionId(collectionId);
        collection.setCollectionName(collectionName);
        collection.setShowScope(showScope);
        collection.setDescription(description);
        collection.setCover(cover);

        if (this.collectionService.updCollection(collection) > 0) {
            return renderJsonSucc(null);
        }

        return renderJsonError("更新数据库出错");
    }


    @RequestMapping(value = "/updateWithoutUpload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateCollectionWithoutCover(HttpServletRequest request, HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String collectionId = request.getParameter("collectionId");

        if(!authService.checkCollectionOperate(
                currentUser.getUserId(),collectionId, AuthConstant.Collection.Owner)){
            return renderJsonUnAuth();
        }

        String collectionName = request.getParameter("collectionName");
        String showScope = request.getParameter("showScope");
        String description = request.getParameter("description");

        Collection collection = new Collection();
        collection.setCollectionId(collectionId);
        collection.setCollectionName(collectionName);
        collection.setShowScope(showScope);
        collection.setDescription(description);
        if (StringUtils.isEmpty(collectionName) || StringUtils.isEmpty(showScope) || StringUtils.isEmpty(description)) {
            return renderJsonError("必要信息不能为空");
        } else {
            if (this.collectionService.updCollectionWithoutCover(collection) > 0) {
                return renderJsonSucc(null);
            } else {
                return renderJsonError("更新数据库出错");
            }
        }
    }

    @RequestMapping(value = "/show")
    public String turnToCollectionShow(HttpServletRequest request)
    {
        String collectionId=request.getParameter("collectionId");

        List<News> newsList=this.collectionService.queryNewsListByCollectionId(collectionId);
        request.setAttribute("newsList",newsList);
        request.setAttribute("collectionId",collectionId);
        return "module/collection/collection_show.ftl";
    }

    /**
     * 通过动态id查找未加入作品集
     * 用来加入作品集功能
     * 作品集的权限必须低于动态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addNewsToCollection")
    @ResponseBody
    public List<Collection> getCollectionByNewsToAdd(HttpServletRequest request,HttpServletResponse response)
    {
        String newsId=request.getParameter("newsId");
        String showScope=request.getParameter("showScope");
        News news=new News();
        news.setNewsId(newsId);
        news.setShowScope(showScope);
        return this.collectionService.getCollectionByNewsToAdd(news,userUtil.getCurrentUser(request).getUserId());
    }
}
