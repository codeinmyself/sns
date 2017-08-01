package com.puckteam.sns.core.module.service;//package com.puckteam.sns.core.module.service;
//
//import com.puckteam.sns.interfaces.core.service.IFocusGroupService;
//import com.puckteam.sns.interfaces.core.vo.SnsFocusGroup;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * Created by ZengJieHang on 2016/10/31.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"/spring-hikaricp.xml","/spring-mvc.xml"})
//public class TestFocusGroupService
//{
//    @Autowired
//    @Qualifier("focusGroupService")
//    private IFocusGroupService focusGroupService;
//
//    @Test
//    public void securityTest()//测试入口函数，相当于main
//    {
//        testCreateNewGroup();   //新建分组
//        testModifyGroup();      //修改分组名称
//        testDeleteGroupByGroupId();//删除分组
//    }
//
//    //新建分组
//    public void testCreateNewGroup()
//    {
//        /*  加入groupId的目的
//            方便后续修改分组名称、删除分组名称
//         */
//        String[][] userGroupNameTestList= {
//                {"733a066e702b45eeb21154f418460cce","1","我的分组1"},//成功
//                {"733a066e702b45eeb21154f418460cce","2","我的分组2"},//成功
//                {"733a066e702b45eeb21154f418460cce","3","我的分组3"},//成功
//                {"733a066e702b45eeb21154f418460cce","4","我的分组1"},//分组名存在
//                {"733a066e702b45eeb21154f418460cce","5",""},//分组名不能为空
//                {"733a066e702b45eeb21154f418460cce","6",null},//分组名不能为空
//                {"7cd336ea55de4697abe099652d56d980","7","我的家人"},//成功
//                {"7cd336ea55de4697abe099652d56d980","8","我的朋友"},//成功
//                {"733a066e702b45eeb21154f418460cce","9","我的分组2"},//分组名存在
//        };
//
//        System.out.println("测试插入新分组：");
//        System.out.println();
//
//        for(int i=1;i<=userGroupNameTestList.length;++i)
//        {
//            String userId=userGroupNameTestList[i-1][0];
//            String groupId=userGroupNameTestList[i-1][1];
//            String groupName=userGroupNameTestList[i-1][2];
//
//            if(groupName==null||groupName.equals(""))
//            {
//                System.out.println("第"+i+"个测试数据: "+"分组名称不能为空!");
//                continue;
//            }
//            else if(this.focusGroupService.isGroupNameExist (userId,groupName))
//            {
//                System.out.println("第"+i+"个测试数据: "+"用户"+userId+"已存在分组"+groupName);
//                continue;
//            }
//
//            SnsFocusGroup snsFocusGroup = new SnsFocusGroup();
//            snsFocusGroup.setCreateUserId(userId);
//            snsFocusGroup.setGroupName(groupName);
//            snsFocusGroup.setGroupId(groupId);
//            //snsFocusGroup.setGroupId(UUID.randomUUID().toString().replace("-", ""));
//
//            if (focusGroupService.createNewGroup(snsFocusGroup) > 0)
//            {
//                System.out.println("第"+i+"个测试数据: "+"成功");
//            }
//            else
//            {
//                System.out.println("第"+i+"个测试数据: "+"插入数据库出错");
//            }
//        }
//
//        System.out.println();
//    }
//
//    //修改分组名称
//    public void testModifyGroup()
//    {
//        String[][] userGroupIdGroupNameTestList= {
//                {"733a066e702b45eeb21154f418460cce","1","(重命名)我的分组1"},//成功
//                {"733a066e702b45eeb21154f418460cce","2","(重命名)我的分组2"},//成功
//                {"733a066e702b45eeb21154f418460cce","random","我的分组3"},//分组不存在
//                {"733a066e702b45eeb21154f418460cce","","我的分组3"},//分组不存在
//                {"733a066e702b45eeb21154f418460cce",null,"我的分组3"},//分组不存在
//                {"733a066e702b45eeb21154f418460cce","3","",},//分组名称不能为空
//                {"733a066e702b45eeb21154f418460cce","3",null},//分组名称不能为空
//                {"7cd336ea55de4697abe099652d56d980","7","我的朋友"},//名称已经存在
//                {"7cd336ea55de4697abe099652d56d980","8","我的家人"},//名称已经存在
//                {"7cd336ea55de4697abe099652d56d980","4","我的分组2"},//分组不存在
//                {"7cd336ea55de4697abe099652d56d980","7","(重命名)我的家人"},//成功
//        };
//
//        System.out.println("测试修改分组名称：");
//
//
//        for(int i=1;i<=userGroupIdGroupNameTestList.length;++i)
//        {
//            String userId = userGroupIdGroupNameTestList[i-1][0];
//            String groupId = userGroupIdGroupNameTestList[i-1][1];
//            String groupName = userGroupIdGroupNameTestList[i-1][2];
//
//            if (groupId == null || groupId.equals("") || !focusGroupService.isGroupIdExist(userId, groupId)) {
//                System.out.println("第" + i + "个测试数据: " + "分组不存在!");
//                continue;
//            } else if (groupName == null || groupName.equals("")) {
//                System.out.println("第" + i + "个测试数据: " + "分组名称不能为空!");
//                continue;
//            } else if (this.focusGroupService.isGroupNameExist(userId, groupName)) {
//                System.out.println("第" + i + "个测试数据: " + "分组名称已经存在!");
//                continue;
//            }
//
//            if (focusGroupService.modifyGroup(groupId, groupName) > 0) {
//                System.out.println("第" + i + "个测试数据: " + "成功");
//            } else {
//                System.out.println("第" + i + "个测试数据: " + "更新数据库出错");
//            }
//            System.out.println();
//        }
//    }
//
//    //删除分组
//    public void testDeleteGroupByGroupId()
//    {
//        System.out.println("测试删除分组名称：");
//
//        String[][] userIdGroupIdTestList={
//                {"733a066e702b45eeb21154f418460cce","1"},//成功
//                {"733a066e702b45eeb21154f418460cce","2"},//成功
//                {"733a066e702b45eeb21154f418460cce","3"},//成功
//                {"733a066e702b45eeb21154f418460cce",""},//分组Id不存在
//                {"733a066e702b45eeb21154f418460cce",null},//分组名称不存在
//                {"733a066e702b45eeb21154f418460cce","4"},//用户没有这个分组
//                {"733a066e702b45eeb21154f418460cce","5"},//用户没有这个分组
//                {"733a066e702b45eeb21154f418460cce","6"},//用户没有这个分组
//                {"7cd336ea55de4697abe099652d56d980","7"},//成功
//                {"7cd336ea55de4697abe099652d56d980","8"}//成功
//        };
//
//        for(int i=1;i<=userIdGroupIdTestList.length;++i)
//        {
//            String userId = userIdGroupIdTestList[i - 1][0];
//            String groupId = userIdGroupIdTestList[i - 1][1];
//
//            if (groupId == null || groupId.equals(""))
//            {
//                System.out.println("第" + i + "个测试数据: " + "分组Id不存在!");
//                continue;
//            }
//            else if (!this.focusGroupService.isGroupIdExist(userId, groupId))
//            {
//                System.out.println("第" + i + "个测试数据: 用户" +userId+ "没有分组" + groupId);
//                continue;
//            }
//
//            if (focusGroupService.deleteGroup(groupId) > 0)
//            {
//                System.out.println("第" + i + "个测试数据: 成功");
//            }
//            else
//                {
//                System.out.println("第" + i + "个测试数据: 删除数据库出错");
//            }
//        }
//    }
//
//}
