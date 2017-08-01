package com.puckteam.sns.core.module.service;//package com.puckteam.sns.core.module.service;
//
//import com.puckteam.sns.interfaces.core.service.IFocusService;
//import com.puckteam.sns.interfaces.core.vo.SnsFocus;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
///**
// * Created by ZengJieHang on 2016/10/25.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//        ({"/spring-hikaricp.xml","/spring-mvc.xml"})
//public class TestFocusService
//{
//    @Autowired
//    @Qualifier("focusService")
//    private IFocusService focusService;
//
//    @Test
//    public void securityTest()//测试入口函数，相当于main
//    {
//        testGetFocus(); //获取关注列表
//        testGetFans();  //获取粉丝列表
//        testFocusOthers();//关注其他用户
//        testModifyFocusGroup();//移动关注分组
//        testCancelFocusOthers();//取消关注
//    }
//
//    //获取关注列表
//    public void testGetFocus()
//    {
//        String userId[]={"241b5970a43647d488de2e7012178822","bee10f0c297e4258992e77f4dacbdf93"};
//
//        System.out.println("用户关注列表: ");
//        for(int i=1;i<=userId.length;++i)
//        {
//            List<SnsFocus> snsFocusList = focusService.findSnsFocusByUserId(userId[i-1]);
//            if(snsFocusList!=null)
//            {
//                System.out.println("第"+i+"个测试数据(用户id: "+userId[i-1]+"): ");
//                for(SnsFocus snsFocus :snsFocusList)
//                {
//                    System.out.println(snsFocus.getUserId()+"、"+snsFocus.getUserName()+"、"+snsFocus.getUserNickname()+"、"+snsFocus.getUserEmail()+"、"+snsFocus.getUserMobile());
//                }
//            }else
//            {
//                System.out.println("第" + i + "个测试数据(用户id: "+userId[i-1]+"):无关注");
//            }
//        }
//        System.out.println();
//    }
//
//
//    //获取粉丝列表
//    public void testGetFans()
//    {
//        String focusUserId[]={"241b5970a43647d488de2e7012178822","bee10f0c297e4258992e77f4dacbdf93"};
//
//        System.out.println("用户粉丝列表: ");
//        for(int i=1;i<=focusUserId.length;++i)
//        {
//            List<SnsFocus> snsFocusList = focusService.findSnsFocusByFocusUserId(focusUserId[i-1]);
//            if(snsFocusList!=null)
//            {
//                System.out.println("第" + i + "个测试数据(用户id: " + focusUserId[i - 1] + "): ");
//                for (SnsFocus snsFocus : snsFocusList) {
//                    System.out.println(snsFocus.getUserId() + "、" + snsFocus.getUserName() + "、" + snsFocus.getUserNickname() + "、" + snsFocus.getUserEmail() + "、" + snsFocus.getUserMobile());
//                }
//            }
//            else
//            {
//                System.out.println("第" + i + "个测试数据(用户id: "+focusUserId[i-1]+"):无粉丝");
//            }
//        }
//        System.out.println();
//    }
//
//    //关注其他用户
//    public void  testFocusOthers()
//    {
//        String [][] userIdFocusUserIdTestList={
//                //成功
//                {"1","bee10f0c297e4258992e77f4dacbdf93","733a066e702b45eeb21154f418460cce","67cc094296f9448882dab8b77bcaf380"},
//                //成功
//                {"2","bee10f0c297e4258992e77f4dacbdf93","7cd336ea55de4697abe099652d56d980","867d58b35cd1444199736f9fcb9455b9"},
//                //成功
//                {"3","bee10f0c297e4258992e77f4dacbdf93","cfb633cd829043d9b327d0796efa50fb","bee10f0c297e4258992e77f4dacbdf93"},
//                //关注的用户ID不存在
//                {"4","bee10f0c297e4258992e77f4dacbdf93","","67cc094296f9448882dab8b77bcaf380"},
//                //关注的用户ID不存在
//                {"5","bee10f0c297e4258992e77f4dacbdf93","","67cc094296f9448882dab8b77bcaf380"},
//                //用户的分组不存在
//                {"6","bee10f0c297e4258992e77f4dacbdf93","58bf99e0dc034057929a0acceb9d95f3",""},
//                //用户的分组不存在
//                {"7","bee10f0c297e4258992e77f4dacbdf93","58bf99e0dc034057929a0acceb9d95f3","1"},
//                //已经关注
//                {"8","bee10f0c297e4258992e77f4dacbdf93","733a066e702b45eeb21154f418460cce","c38f6043063b4b1983afc3c1e2c0103b"},
//                //已经关注
//                {"9","bee10f0c297e4258992e77f4dacbdf93","7cd336ea55de4697abe099652d56d980","c38f6043063b4b1983afc3c1e2c0103b"},
//                //已经关注
//                {"10","58bf99e0dc034057929a0acceb9d95f3","bee10f0c297e4258992e77f4dacbdf93","3ff8d24f5f3d41bbb566ea999b86402f"},
//                //成功
//                {"11","58bf99e0dc034057929a0acceb9d95f3","241b5970a43647d488de2e7012178822","3ff8d24f5f3d41bbb566ea999b86402f"},
//        };
//
//        System.out.println("测试关注用户: ");
//
//        for(int i=1;i<=userIdFocusUserIdTestList.length;++i)
//        {
//            String focusId=userIdFocusUserIdTestList[i-1][0];
//            String userId=userIdFocusUserIdTestList[i-1][1];
//            String focusUserId=userIdFocusUserIdTestList[i-1][2];
//            String groupId=userIdFocusUserIdTestList[i-1][3];
//
//            if (focusUserId == null || focusUserId.equals("") || !this.focusService.isFocusUserIdExist(focusUserId))
//            {
//                System.out.println("第" + i + "个测试数据: " + "关注的用户ID不存在");
//                continue;
//            }
//            else if (groupId == null || groupId.equals("") || !this.focusService.isGroupIdExist(userId, groupId))
//            {
//                System.out.println("第" + i + "个测试数据: " + "用户的分组不存在");
//                continue;
//            }
//            else if (this.focusService.hasFocused(userId, focusUserId))
//            {
//                System.out.println("第" + i + "个测试数据: " + "已经关注了用户" + focusUserId);
//                continue;
//            }
//
//            SnsFocus snsFocus = new SnsFocus();
//            snsFocus.setUserId(userId);
//            snsFocus.setFocusId(focusId);
//            //snsFocus.setFocusId(UUID.randomUUID().toString().replace("-", ""));
//            snsFocus.setFocusUserId(focusUserId);
//            snsFocus.setGroupId(groupId);
//
//            if (focusService.addFocusItem(snsFocus) > 0)
//            {
//                System.out.println("第" + i + "个测试数据: " + "成功");
//            }
//            else
//            {
//                System.out.println("第" + i + "个测试数据: " + "插入数据库出错！");
//            }
//        }
//        System.out.println();
//    }
//
//    //移动关注分组
//    public void testModifyFocusGroup() {
//        String[][] focusIdGroupIdTestList = {
//                //成功
//                {"1", "bee10f0c297e4258992e77f4dacbdf93", "867d58b35cd1444199736f9fcb9455b9"},
//                //成功
//                {"2", "bee10f0c297e4258992e77f4dacbdf93", "67cc094296f9448882dab8b77bcaf380"},
//                //分组不存在
//                {"3", "bee10f0c297e4258992e77f4dacbdf93", "123456"},
//                //没有关注
//                {"8", "58bf99e0dc034057929a0acceb9d95f3", "3ff8d24f5f3d41bbb566ea999b86402f"}
//        };
//
//        System.out.println("测试移动分组: ");
//
//        for (int i = 1; i <= focusIdGroupIdTestList.length; ++i)
//        {
//            String focusId=focusIdGroupIdTestList[i-1][0];
//            String userId=focusIdGroupIdTestList[i-1][1];
//            String groupId=focusIdGroupIdTestList[i-1][2];
//
//            if (focusId == null || focusId.equals("") || !focusService.isFocusIdExist(focusId)) {
//                System.out.println("第" + i + "个测试数据: " + "没有关注该用户，不能移动分组");
//                continue;
//            } else if (groupId == null || groupId.equals("") || !focusService.isGroupIdExist(userId, groupId)) {
//                System.out.println("第" + i + "个测试数据: " + "用户的分组不存在");
//                continue;
//            }
//
//            if (focusService.removeFocusItemToGroup(focusId, groupId) > 0) {
//                System.out.println("第" + i + "个测试数据: " + "成功");
//            } else {
//                System.out.println("第" + i + "个测试数据: " + "更新数据库出错！");
//            }
//        }
//    }
//    //取消关注
//    public void testCancelFocusOthers()
//    {
//        System.out.println("取消关注测试:");
//        String[][] userIdFocusIdTestList={
//                //成功
//                {"bee10f0c297e4258992e77f4dacbdf93","1"},
//                //成功
//                {"bee10f0c297e4258992e77f4dacbdf93","2"},
//                //成功
//                {"bee10f0c297e4258992e77f4dacbdf93","3"},
//                //成功
//                {"58bf99e0dc034057929a0acceb9d95f3","11"},
//                //关注ID不能为空
//                {"bee10f0c297e4258992e77f4dacbdf93",""},
//                //关注ID不能为空
//                {"bee10f0c297e4258992e77f4dacbdf93",""},
//                //没有关注该用户不能取消关注
//                {"bee10f0c297e4258992e77f4dacbdf93","08029ab630264adbb741ba1107027e15"},
//                //没有关注该用户不能取消关注
//                {"241b5970a43647d488de2e7012178822","467503c94a6545a28c99467c3f8bcdd7"},
//        };
//
//        for(int i=1;i<=userIdFocusIdTestList.length;++i)
//        {
//            String userId=userIdFocusIdTestList[i-1][0];
//            String focusId = userIdFocusIdTestList[i-1][1];
//
//            if (focusId == null || focusId.equals(""))
//            {
//                System.out.println("第" + i + "个测试数据: "+"关注ID不能为空");
//                continue;
//            } else if (!focusService.isFocusIdExist(focusId)) {
//                System.out.println("第" + i + "个测试数据: "+"没有关注该用户，不能取消关注");
//                continue;
//            }
//
//            if (focusService.cancelFocusItem(focusId) > 0) {
//                System.out.println("第" + i + "个测试数据: "+"成功");
//            } else {
//                System.out.println("第" + i + "个测试数据: "+"删除数据库出错！");
//            }
//        }
//        System.out.println();
//    }
//
//}
