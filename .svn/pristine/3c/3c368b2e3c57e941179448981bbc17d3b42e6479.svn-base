package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.support.mvc.db.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoemWhite on 2016/11/2.
 */
@Repository
public class TestDao extends BaseDao {


    public void addBatchTest(){

        String sysdate = this.getSysdate();

        String sql = "insert into sns_artist(" +
                "artist_id,user_id,description,create_time" +
                ") values (?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";

        List<Object[]> batchArgs = new ArrayList<Object[]>();
        Object[][] params = new Object[2][4];

        List list = new ArrayList();

        params[0][0] = "1";
        params[0][1] = "1";
        params[0][2] = "1";
        params[0][3] = sysdate;

        params[1][0] = "2";
        params[1][1] = "2";
        params[1][2] = "2";
        params[1][3] = sysdate;

        batchArgs.add(params[0]);
        batchArgs.add(params[1]);

        int[] results = this.executeBatchUpdate(sql,batchArgs);

        for(int i : results){
            System.out.print(i);
        }

    }

}
