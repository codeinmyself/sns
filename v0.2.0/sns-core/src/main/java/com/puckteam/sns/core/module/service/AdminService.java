package com.puckteam.sns.core.module.service;

import com.puckteam.sns.base.util.MD5;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.module.dao.AdminDao;
import com.puckteam.sns.interfaces.core.service.IAdminService;
import com.puckteam.sns.interfaces.core.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by ZengJieHang on 2016/11/15.
 */
@Service("adminService")
@Transactional
public class AdminService implements IAdminService
{
    @Autowired
    private AdminDao adminDao;

    public List<Report> findReportList()
    {
        return this.adminDao.findReportList();
    }

    public List<User> findUserList()
    {
        return this.adminDao.findUserList();
    }

    public int lockUser(String userId)
    {
        return this.adminDao.lockUser(userId);
    }

    public List<SensitiveKey>findSensitiveKeys()
    {
        return this.adminDao.findSensitiveKeys();
    }

    public boolean isSensitiveKeyExist(String key)
    {
        Map resultMap=this.adminDao.isSensitiveKeyExist(key);
        if(Integer.parseInt(resultMap.get("number").toString())==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isSensitiveIdExist(String key){
        Map resultMap=this.adminDao.isSensitiveIdExist(key);
        if(Integer.parseInt(resultMap.get("number").toString())==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public int addKey(SensitiveKey sensitiveKey)
    {
        return this.adminDao.addKey(sensitiveKey);
    }

    public int removeKey(String key)
    {
        return this.adminDao.removeKey(key);
    }

    public User findUserByUserId(String userId)
    {
        return this.adminDao.findUserByUserId(userId);
    }

    public Collection findCollectionByCollectionId(String collectionId)
    {
        return this.adminDao.findCollectionByCollectionId(collectionId);
    }

    public Circle findCircleByCircleId(String circleId)
    {
        return this.adminDao.findCircleByCircleId(circleId);
    }

    public News findNewsByNewsId(String newsId)
    {
        News news= this.adminDao.findNewsByNewsId(newsId);
        if(news!=null)
        {
            news.setImageList(this.adminDao.findNewsImageByNewsId(news.getNewsId()));
        }
        return news;
    }

    public Comment findCommentByCommentId(String commentId)
    {
        return this.adminDao.findCommentByCommentId(commentId);
    }

    public Report findReportByReportId(String reportId)
    {
        return  this.adminDao.findReportByReportId(reportId);
    }

    public int haveDealReport(String reportId)
    {
        return this.adminDao.haveDealReport(reportId);
    }

    public int ignoreReport(String reportId)
    {
        return this.adminDao.haveDealReport(reportId);
    }

    public int deleteNewsOrCollectionOrCircleOrComment(String targetType,String targetId)
    {
        String table=null;
        String field=null;
        if(targetType.equals(CoreConstant.ReportTargetType.NEWS_REPORT))
        {
            table="sns_news";
            field="news_id";
        }
        else if(targetType.equals(CoreConstant.ReportTargetType.COLLECTION_REPORT))
        {
            table="sns_collection";
            field="collection_id";
        }
        else if(targetType.equals(CoreConstant.ReportTargetType.CIRCLE_REPORT))
        {
            table="sns_circle";
            field="circle_id";
        }
        else if(targetType.equals(CoreConstant.ReportTargetType.COMMENT_REPORT))
        {
            table="sns_comment";
            field="comment_id";
        }

        String sql="update "+table+" set status=? where "+field+"=?";
        return this.adminDao.deleteNewsOrCollectionOrCircleOrComment(sql,targetId);
    }

    public int replyReport(String reportId,String reportReply)
    {
        return this.adminDao.replyReport(reportId,reportReply);
    }

    public boolean adminLogin(String account,String password)
    {
        password= MD5.getHashString(password);
        Map map=this.adminDao.adminLogin(account,password);
        if(map.get("number").toString().equals("1"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
