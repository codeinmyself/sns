package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.base.util.SequenceUtil;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.constant.CoreConfig;
import com.puckteam.sns.core.support.aop.annotation.UserSecurity;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.ImageUploadUtil;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.ICircleService;
import com.puckteam.sns.interfaces.core.service.IMemberApplyService;
import com.puckteam.sns.interfaces.core.service.IMemberService;
import com.puckteam.sns.interfaces.core.service.INoticeService;
import com.puckteam.sns.interfaces.core.vo.*;
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
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * Created by lxq on 2016/10/14.
 */

@Controller
@RequestMapping(value = "/circle")
public class SnsCircleController extends BaseController {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private ImageUploadUtil imageUploadUtil;

    @Autowired
    @Qualifier("circleService")
    public ICircleService circleService;


    @Autowired
    @Qualifier("memberService")
    public IMemberService memberService;

    @Autowired
    @Qualifier("noticeService")
    public INoticeService noticeService;

    @Autowired
    @Qualifier("memberApplyService")
    public IMemberApplyService memberApplyService;


    /**
     * 新建艺术圈
     * @param request
     * @return
     */
    @RequestMapping(value = "circleAdd", method = RequestMethod.GET)
    @UserSecurity
    public String addCircle(HttpServletRequest request) {
        return "module/circle/circle_add.ftl";
    }

    /**
     * 提交艺术圈信息的填写
     * @param request
     * @return
     */
    @RequestMapping(value = "/circleSubmit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult submitCircleInformation(@RequestParam("uploadImage") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String circleName = request.getParameter("circleName");
        String description = request.getParameter("description");
        String showScope = request.getParameter("showScope");
        String applyStrategy = request.getParameter("applyStrategy");

        if (StringUtils.isEmpty(showScope)
                || StringUtils.isEmpty(applyStrategy)
                || StringUtils.isEmpty(circleName)
                || StringUtils.isEmpty((description))) {
            return renderJsonError("必要信息都不能为空");
        }

        String targetDir = CoreConstant.UploadTargetDir.CIRCLE;
        String fileId = SequenceUtil.uuid2();// 文件名
        String avatar = "";

        try{

            String filename = imageUploadUtil.upload(request,uploadFile,targetDir,fileId);
            avatar = "/" + targetDir + "/" + filename;

        }catch (IOException e){
            return renderJsonError("上传图片出错");
        }

        Circle circle = new Circle();
        circle.setCircleId(UUID.randomUUID().toString().replace("-", ""));
        circle.setCircleName(circleName);
        circle.setDescription(description);
        circle.setShowScope(showScope);
        circle.setApplyStrategy(applyStrategy);
        circle.setStatus(CoreConstant.Status.NORMAL);
        circle.setAvatar(avatar);

        circle.setCreateUserId(currentUser.getUserId());
        Member member = new Member();
        member.setMemberId(UUID.randomUUID().toString().replace("-", ""));
        member.setCircleId(circle.getCircleId());
        member.setUserId(currentUser.getUserId());
        member.setMemberType(CoreConstant.MemberType.CREATER);


        circleService.circleInformationInsert(circle);
        memberService.memberInformationInsert(member);

        return renderJsonSucc(null);
    }




    /**
     * 查询自己创建的艺术圈，返回艺术圈列表
     * @param request
     * @return
     */
    @RequestMapping(value = "circleQuery",method = RequestMethod.GET)
    public String queryForCircleInformation(HttpServletRequest request,HttpServletResponse response) {
        List<Circle> circleList = circleService.queryForCircleInformation();
        request.setAttribute("circleList", circleList);
        return "module/circle/circle_owner.ftl";
    }

    /**
     * 删除自己创建的艺术圈（根据ID）
     * @param request
     * @return
     */
    @RequestMapping(value = "circleDelete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteCircle(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }
        String circleId = request.getParameter("circleId");
        circleService.deleteCircleByCircleId(circleId);
        return renderJsonSucc(null);
    }



    /**
     * 点击修改后转到编辑页面
     * @param request
     * @return
     */
    @RequestMapping(value = "circleRev",method = RequestMethod.GET)
    public String revCircle(HttpServletRequest request){
        return "module/circle/circle_revise.ftl";
    }

    /**
     * 获取编辑页面的输入
     * @param request
     * @return
     */
    @RequestMapping(value = "circleEdit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult editCircle(@RequestParam("uploadImage") MultipartFile uploadFile,HttpServletRequest request){

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String circleId=request.getParameter("circleId");
        String circleName = request.getParameter("circleName");
        String description = request.getParameter("description");
        String showScope = request.getParameter("showScope");
        String applyStrategy = request.getParameter("applyStrategy");

        if (StringUtils.isEmpty(showScope)
                || StringUtils.isEmpty(applyStrategy)
                || StringUtils.isEmpty(circleName)
                || StringUtils.isEmpty((description))) {
            return renderJsonError("必要信息都不能为空");
        }

        String targetDir = CoreConstant.UploadTargetDir.COLLECTION;
        String fileId = SequenceUtil.uuid2();// 文件名
        String avatar = "";

        try{

            String filename = imageUploadUtil.upload(request,uploadFile,targetDir,fileId);
            avatar = "/" + targetDir + "/" + filename;

        }catch (IOException e){
            return renderJsonError("上传图片出错");
        }

        Member member1=new Member();
        member1.setUserId(currentUser.getUserId());
        member1.setCircleId(circleId);
        Member memberI=memberService.queryMemberByCircleIdAndUserId(member1);
        request.setAttribute("memberI",memberI);



        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircleName(circleName);
        circle.setDescription(description);
        circle.setShowScope(showScope);
        circle.setApplyStrategy(applyStrategy);
        circle.setAvatar(avatar);

        circleService.editCircleByCircleId(circle);
        return renderJsonSucc(null);


    }
    /**
     * 获取编辑页面的输入
     * @param request
     * @return
     */

    @RequestMapping(value = "circleEditWithoutUpload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateCollectionWithoutCover(HttpServletRequest request, HttpServletResponse response)
    {
        String circleId=request.getParameter("circleId");
        String circleName = request.getParameter("circleName");
        String description = request.getParameter("description");
        String showScope = request.getParameter("showScope");
        String applyStrategy = request.getParameter("applyStrategy");

        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircleName(circleName);
        circle.setDescription(description);
        circle.setShowScope(showScope);
        circle.setApplyStrategy(applyStrategy);
        Member member1=new Member();
        member1.setUserId(userUtil.getCurrentUser(request).getUserId());
        member1.setCircleId(circleId);
        Member memberI=memberService.queryMemberByCircleIdAndUserId(member1);
        request.setAttribute("memberI",memberI);
        if (StringUtils.isEmpty(showScope) || StringUtils.isEmpty(applyStrategy) || StringUtils.isEmpty(circleName) || StringUtils.isEmpty((description))) {
            return renderJsonError("全部信息都不能为空");
        } else {
            circleService.editCircleByCircleId(circle);
            return renderJsonSucc(null);
        }
    }


    /**
     * 探索艺术圈页面
     * @param request
     * @return
     */
    @RequestMapping(value = "circleExplore",method = RequestMethod.GET)
    public String exploreCircle(HttpServletRequest request)
    {
        List<Circle> circleList=this.circleService.findPopularCircle(userUtil.getCurrentUser(request).getUserId());
        request.setAttribute("circleList",circleList);
        return "module/circle/circle_explore.ftl";
    }

    /**
     * 通过艺术圈昵称/艺术圈描述  模糊查询艺术圈
     * @param request
     * @return
     */
    @RequestMapping(value = "circleSearch",method = RequestMethod.GET)
    public String searchCircleByPartName(HttpServletRequest request, HttpServletResponse response) {
        String  partName="";
        try{
           partName = new String(request.getParameter("keyword").getBytes("iso-8859-1"), "utf-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(partName.equals(""))return "redirect:/circle/circleExplore";
        else {
            CircleQueryCourier queryCourier = new CircleQueryCourier();
            queryCourier.setPartName(partName);
            queryCourier.setUserId(userUtil.getCurrentUser(request).getUserId());
            List<Circle> circleList1 = circleService.searchCircleByPartName(queryCourier);
            request.setAttribute("circleList1", circleList1);
            return "module/circle/circle_search.ftl";
        }
    }

    /**
     * 提交  申请填写的信息
     * @param request
     * @return
     */
    @RequestMapping(value = "applySubmit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult applySubmit(HttpServletRequest request, HttpServletResponse response) {
        String circleId = request.getParameter("circleId");
        String applyDesc=request.getParameter("applyDesc");
        MemberApply memberApply=new MemberApply();
        memberApply.setCircleId(circleId);
        memberApply.setApplyDesc(applyDesc);
        memberApply.setApplyId(UUID.randomUUID().toString().replace("-", ""));
        memberApply.setApplyUserId(userUtil.getCurrentUser(request).getUserId());
        memberApply.setApplyStatus(CoreConstant.MemberApplyType.APPLYING);
        memberApplyService.memberApplyInformationInsert(memberApply);
        if(noticeService.applyToCircleNotice(memberApply))
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库出错!");
        }
    }

    /**
     * 展示艺术圈申请信息
     * @param request
     * @return
     */
    @RequestMapping(value = "circleApply",method = RequestMethod.GET)
    public String saveApplySubmit(HttpServletRequest request, HttpServletResponse response) {
        String circleId = request.getParameter("circleId");
        Circle circle=circleService.findCircleByCircleId(circleId);
        request.setAttribute("circle", circle);
        return "module/circle/circle_apply.ftl";
    }




    /**
     * 艺术圈动态
     * @param request
     * @return
     */
    @RequestMapping(value = "circleActivity",method = RequestMethod.GET)
    public String circleActivity(HttpServletRequest request){

        String circleId=request.getParameter("circleId");
        Circle circle=circleService.findCircleByCircleId(circleId);
        request.setAttribute("circle", circle);
        Member member1=new Member();
        member1.setUserId(userUtil.getCurrentUser(request).getUserId());
        member1.setCircleId(circleId);
        Member member=memberService.queryMemberByCircleIdAndUserId(member1);
        request.setAttribute("member",member);
        List<News> newsList=this.circleService.queryNewsListByCircleId(circleId);
        request.setAttribute("newsList",newsList);

        return "module/circle/circle_activity.ftl";
    }
    /**
     * 艺术圈基本信息
     * @param request
     * @return
     */
    @RequestMapping(value = "circleMessage",method = RequestMethod.GET)
    public String circleMessage(HttpServletRequest request){
        String circleId=request.getParameter("circleId");
        Circle circle=circleService.findCircleByCircleId(circleId);
        request.setAttribute("circle", circle);
        Member member1=new Member();
        member1.setUserId(userUtil.getCurrentUser(request).getUserId());
        member1.setCircleId(circleId);
        Member memberI=memberService.queryMemberByCircleIdAndUserId(member1);
        request.setAttribute("memberI",memberI);
        return "module/circle/circle_revise.ftl";
    }

    /**
     * 申请管理动态
     * @param request
     * @return
     */
    @RequestMapping(value = "circleApplyManage",method = RequestMethod.GET)
    public String circleApplyManage(HttpServletRequest request){
        String circleId=request.getParameter("circleId");
        MemberApply memberApply=new MemberApply();
        memberApply.setCircleId(circleId);
        List<MemberApply> memberApplyList = memberApplyService.queryForMemberApplyInformation(memberApply);
        request.setAttribute("memberApplyList", memberApplyList);
        Circle circle=circleService.findCircleByCircleId(circleId);
        request.setAttribute("circle", circle);
        Member member1=new Member();
        member1.setUserId(userUtil.getCurrentUser(request).getUserId());
        member1.setCircleId(circleId);
        Member memberI=memberService.queryMemberByCircleIdAndUserId(member1);
        request.setAttribute("memberI",memberI);
        return "module/circle/apply_manage.ftl";
    }



    /**
     * 同意申请
     * @param request
     * @return
     */
    @RequestMapping(value ="applyDeal",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult dealApply(HttpServletRequest request, HttpServletResponse response)
    {
        String circleId=request.getParameter("circleId");
        String applyStatus=request.getParameter("applyStatus");
        String applyUserId=request.getParameter("applyUserId");
        MemberApply memberApply=new MemberApply();
        memberApply.setCircleId(circleId);
        memberApply.setVerifyUserId(userUtil.getCurrentUser(request).getUserId());
        memberApply.setApplyUserId(applyUserId);
        if(applyStatus.equals(CoreConstant.MemberApplyType.ADMIT))
        {
            memberApply.setApplyStatus(CoreConstant.MemberApplyType.ADMIT);
            Member member=new Member();
            member.setMemberId(UUID.randomUUID().toString().replace("-", ""));
            member.setCircleId(circleId);
            member.setUserId(applyUserId);
            member.setMemberType(CoreConstant.MemberType.NORMAL);
            memberService.memberInformationInsert(member);

            if(!(noticeService.addToCircleNotice(member)))
            {
                return renderJsonError("插入数据库出错!");
            }
        }
        else
        {
            memberApply.setApplyStatus(CoreConstant.MemberApplyType.DISAGREE);
        }
        memberApplyService.checkApplyInformationInsert(memberApply);

        if (noticeService.circleApplyResultNotice(memberApply))
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库出错!");
        }
    }
    /**
     * 直接加入
     * @param request
     * @return
     */
    @RequestMapping(value ="directApply",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult directApply(HttpServletRequest request, HttpServletResponse response) {
        String circleId=request.getParameter("circleId");

        Member member = new Member();
        member.setMemberId(UUID.randomUUID().toString().replace("-", ""));

        member.setCircleId(circleId);
        member.setUserId(userUtil.getCurrentUser(request).getUserId());
        member.setMemberType(CoreConstant.MemberType.NORMAL);

        memberService.memberInformationInsert(member);
        if(!(noticeService.addToCircleNotice(member)))
        {
            return renderJsonError("插入数据库出错!");
        }
        return renderJsonSucc(null);

    }

    /**
     * 加入的艺术圈
     * @param request
     * @return
     */
    @RequestMapping(value = "circleJoin",method = RequestMethod.GET)
    public String queryForCircleJoin(HttpServletRequest request,HttpServletResponse response) {

        Member member = new Member();
        member.setUserId(userUtil.getCurrentUser(request).getUserId());
        List<Circle> joinList = memberService.queryForCircleJoin(member);
        request.setAttribute("joinList", joinList);
        return "module/circle/circle_join.ftl";
    }


    /**
     * 退出艺术圈
     * @param request
     * @return
     */
    @RequestMapping(value = "circleOut",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult outCircle(HttpServletRequest request, HttpServletResponse response) {
        String circleId = request.getParameter("circleId");
        Member member=new Member();
        member.setCircleId(circleId);
        member.setUserId(userUtil.getCurrentUser(request).getUserId());
        memberService.deleteMemberByCircleIdAndUserId(member);
        if(noticeService.exitCircleNotice(member)) {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库出错!");
        }
    }
    /**
     * 删除艺术圈成员
     * @param request
     * @return
     */
    @RequestMapping(value = "deleteMember",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteMember(HttpServletRequest request, HttpServletResponse response) {
        String memberId = request.getParameter("memberId");
        Member member = this.memberService.queryMemberByMemberId(memberId);
        memberService.deleteMemberByMemberId(memberId);
        if (noticeService.removeCircleMemberNotice(member)) {
            return renderJsonSucc(null);
        } else
        {
            return renderJsonError("插入数据库失败!");
        }
    }

    /**
     * 成员管理
     * @param request
     * @return
     */
    @RequestMapping(value = "memberManage",method = RequestMethod.GET)
    public String manageMember(HttpServletRequest request,HttpServletResponse response) {
        String circleId=request.getParameter("circleId");
        Member member1 = new Member();
        member1.setCircleId(circleId);
        member1.setUserId(userUtil.getCurrentUser(request).getUserId());
        Member member=memberService.queryMemberByCircleIdAndUserId(member1);
        request .setAttribute("memberMe",member);
        List<Member> memberList = memberService.queryForCircleMember(member1);
        request.setAttribute("memberList", memberList);
        Circle circle=circleService.findCircleByCircleId(circleId);
        request.setAttribute("circle", circle);
        return "module/circle/circle_member.ftl";
    }


    /**
     * 修改成员权限
     * @param request
     * @return
     */
    @RequestMapping(value = "memberRevise",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult reviseMember(HttpServletRequest request, HttpServletResponse response) {
        String memberId=request.getParameter("memberId");
        String circleId=request.getParameter("circleId");
        String memberType=request.getParameter("memberType");
        Member member = new Member();
        member.setMemberId(memberId);
        member.setCircleId(circleId);
        member.setMemberType(memberType);
        request.setAttribute("member", member);
        return renderJsonSucc(null);


    }

    /**
     * 保存修改成员信息
     * @param request
     * @return
     */
    @RequestMapping(value = "submitMemberRevise",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult submitMemberRevise(HttpServletRequest request, HttpServletResponse response) {
        String memberId=request.getParameter("memberId");
        String memberType=request.getParameter("memberType");
        Member member = memberService.queryMemberByMemberId(memberId);
        if(memberType.equals(CoreConstant.MemberType.MANAGER)){
            member.setMemberType(CoreConstant.MemberType.MANAGER);
        }
        else{
            member.setMemberType(CoreConstant.MemberType.NORMAL);
        }
        memberService.updateMemberByMemberId(member);
        if (member.getMemberType().equals(CoreConstant.MemberType.MANAGER))
        {
            if(!this.noticeService.promoteToCircleAdminNotice(member))
            {
                return renderJsonError("插入数据库出错!");
            }
        }
        else if(member.getMemberType().equals(CoreConstant.MemberType.NORMAL))
        {
            if(!this.noticeService.removeCircleAdminNotice(member))
            {
                return renderJsonError("插入数据库出错!");
            }
        }
        request.setAttribute("member", member);

        return renderJsonSucc(null);
    }

    /**
     * 通过newsId查找未加入艺术圈
     * 用来加入艺术圈功能
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addNewsToCircle")
    @ResponseBody
    public List<Circle> getCircleByNewsToAdd(HttpServletRequest request,HttpServletResponse response)
    {
        String newsId=request.getParameter("newsId");
        News news=new News();
        news.setNewsId(newsId);
        return this.circleService.getCircleIsInAndNewsNotId(news,userUtil.getCurrentUser(request).getUserId());
    }
}





