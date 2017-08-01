package com.puckteam.sns.core.support.mvc.controller;

import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.base.query.Page;
import com.puckteam.sns.base.query.QueryCourier;
import com.puckteam.sns.base.query.QueryResult;
import com.puckteam.sns.base.util.StringClass;
import com.puckteam.sns.core.constant.CoreConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by PoemWhite on 16/4/13.
 */
public class BaseController {
    public JsonResult renderJsonResult(JsonResult jsonResult){
        return jsonResult;
    }

    public JsonResult renderJsonSucc(Object object){
        return renderJsonResult(
                new JsonResult(CoreConstant.JSON_CODE_SUCCESS, CoreConstant.JSON_MSG_SUCCESS,object));
    }

    public JsonResult renderJsonError(String msg){
        return renderJsonResult(
                new JsonResult(CoreConstant.JSON_CODE_FAILURE,msg,null));
    }

    /**
     * 用户未登录
     * @return
     */
    public JsonResult renderJsonUnLogin(){
        return renderJsonResult(
                new JsonResult(CoreConstant.JSON_CODE_UNLOGIN,null,null));
    }

    /**
     * 无权限操作
     * @return
     */
    public JsonResult renderJsonUnAuth(){
        return renderJsonResult(
                new JsonResult(CoreConstant.JSON_CODE_UNAUTH,"无权限操作",null));
    }

    public static <T extends QueryCourier> T request2QueryCourier(HttpServletRequest request,int pageSize, T queryCourier) {
        Page page = new Page();

        int currentPageIndex = 1;
        String p = StringClass.getString(request.getParameter("p"));
        if(!"".equals(p)){
            currentPageIndex = Integer.parseInt(p);
        }
        int start = (currentPageIndex - 1) * pageSize;

        page.setStart(start);
        page.setLimit(pageSize);
        queryCourier.setPage(page);

        return queryCourier;
    }

    public static <T> void queryCourier2Request(HttpServletRequest request, QueryResult<T> queryResult, QueryCourier queryCourier) {
        Page page = queryCourier.getPage();
        if (page != null) {

            page.setTotal(queryResult.getTotal());

        }
        request.setAttribute("pagination",page);
    }
}
