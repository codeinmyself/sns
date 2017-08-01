package com.puckteam.sns.core.module.service;

import com.puckteam.sns.base.util.StringClass;
import com.puckteam.sns.core.constant.AuthConstant;
import com.puckteam.sns.interfaces.core.service.IAuthService;
import com.puckteam.sns.interfaces.core.service.ICollectionService;
import com.puckteam.sns.interfaces.core.vo.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by PoemWhite on 2017/4/8.
 */
@Service("authService")
@Transactional
public class AuthService implements IAuthService {

    static Logger logger = LogManager.getLogger();

    @Autowired
    @Qualifier("collectionService")
    public ICollectionService collectionService;

    /**
     * 校验作品集操作权限
     * @param currentUserId
     * @param collectionId
     * @param authType
     * @return
     */
    public boolean checkCollectionOperate(String currentUserId, String collectionId, String authType){

        if("".equals(StringClass.getString(currentUserId))
                || "".equals(StringClass.getString(collectionId))
                || "".equals(StringClass.getString(authType))){

            if(logger.isInfoEnabled()){
                logger.info("collection auth fail{ input param empty}");
            }
            return false;
        }

        Collection collection = collectionService.findCollectionByCollectionId(collectionId);

        if(collection == null){
            return false;
        }

        if(AuthConstant.Collection.Owner.equals(authType)){

            if(currentUserId.equals(collection.getCreateUserId())){
                return true;
            }

        }else if (AuthConstant.Collection.Viewer.equals(authType)){

            //判断作品集是否公开
            return true;
        }

        return false;
    }
}
