//package com.puckteam.sns.core.module.service;
//
//import com.puckteam.sns.core.constant.CoreConstant;
//import com.puckteam.sns.interfaces.core.service.ICollectionAssociateNewsService;
//import com.puckteam.sns.interfaces.core.service.ICommentService;
//import com.puckteam.sns.interfaces.core.service.ILikeService;
//import com.puckteam.sns.interfaces.core.service.INewsService;
//import com.puckteam.sns.interfaces.core.vo.CollectionAssociateNews;
//import com.puckteam.sns.interfaces.core.vo.Comment;
//import com.puckteam.sns.interfaces.core.vo.Like;
//import com.puckteam.sns.interfaces.core.vo.News;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.UUID;
//
///**
// * Created by 82402 on 2016/11/3.
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"/spring-hikaricp.xml","/spring-mvc.xml"})
//
//public class NewsServiceTest {
//
//    @Autowired
//    @Qualifier("newsService")
//    private INewsService newsService;
//
//    @Autowired
//    @Qualifier("collectionAssociateNewsService")
//    private ICollectionAssociateNewsService collectionAssociateNewsService;
//
//    @Autowired
//    @Qualifier("likeService")
//    private ILikeService likeService;
//
//    @Autowired
//    @Qualifier("commentService")
//    private ICommentService commentService;
//
//    /**
//     * 动态创建测试
//     */
//    @Test
//    public void addNewsTest(){
//        CollectionAssociateNews collectionAssociateNews = new CollectionAssociateNews();
//        News news = new News();
//        news.setNewsId(UUID.randomUUID().toString().replace("-", ""));
//        news.setShowScope("01");
//        news.setContent("哈哈哈");
//        news.setLikeCount(0);
//        news.setCommentCount(0);
//        news.setStatus(CoreConstant.Status.NORMAL);
//        collectionAssociateNews.setRelaId(UUID.randomUUID().toString().replace("-", ""));
//        collectionAssociateNews.setNewsId(news.getNewsId());
//        collectionAssociateNews.setRelaType(CoreConstant.Associate.COLLECTION);
//        collectionAssociateNews.setTargetId("167bdd03ad984cda956d4f3927b25067");
//        collectionAssociateNewsService.addAssociate(collectionAssociateNews);
//        newsService.addNews(news);
//    }
//
//    /**
//     * 动态删除测试
//     */
//    @Test
//    public void delNewsTest(){
//        News news = new News();
//        news.setNewsId("a8fa21cd38594a2da4571179b7e1e270");
//        newsService.delNews(news.getNewsId());
//    }
//
//    /**
//     * 动态更新测试
//     */
//    @Test
//    public void updNewsTest(){
//        News news = new News();
//        news.setNewsId("4981304dcf62466bac455717b27db367");
//        news.setShowScope("02");
//        news.setContent("嘿嘿嘿");
//        newsService.updNews(news);
//    }
//
//    /**
//     * 动态从集合移除测试
//     */
//    @Test
//    public void delNewsFromCollection(){
//        CollectionAssociateNews collectionAssociateNews = new CollectionAssociateNews();
//        collectionAssociateNews.setRelaId("55149893ea9a45c3b4e1ce29d941b891");
//        collectionAssociateNewsService.delAssociate(collectionAssociateNews.getRelaId());
//    }
//
//    /**
//     * 点赞测试
//     */
//    @Test
//    public void addLike(){
//        Like like = new Like();
//        like.setLikeId(UUID.randomUUID().toString().replace("-", ""));
//        like.setNewsId("dc8c4cdadc724a95bf89eb95d561c4c3");
//        like.setCreateUserId("7f69e44a070b4d968ab25f4dd22ee9dd");
//        likeService.addLike(like);
//        News news = new News();
//        news.setNewsId("dc8c4cdadc724a95bf89eb95d561c4c3");
//        newsService.likeCountPlus(news.getNewsId());
//    }
//
//    /**
//     * 取消点赞测试
//     */
//    @Test
//    public void delLike(){
//        Like like = new Like();
//        like.setLikeId("35005afafb53457d9c37e65c3f898bac");
//        likeService.delLike(like.getLikeId());
//        News news = new News();
//        news.setNewsId("4c28b2af51ff485194ae56b686110a88");
//        newsService.likeCountMinus(news.getNewsId());
//    }
//
//    /**
//     * 评论测试
//     */
//    @Test
//    public void addComment(){
//        Comment comment = new Comment();
//        comment.setCommentId(UUID.randomUUID().toString().replace("-", ""));
//        comment.setNewsId("4c28b2af51ff485194ae56b686110a88");
//        comment.setContent("嘻嘻");
//        comment.setCreateUserId("7f69e44a070b4d968ab25f4dd22ee9dd");
//        commentService.addComment(comment);
//        News news = new News();
//        news.setNewsId("4c28b2af51ff485194ae56b686110a88");
//        newsService.commentCountPlus(news.getNewsId());
//    }
//
//    /**
//     * 回复测试
//     */
//    @Test
//    public void addReply(){
//        Comment comment = new Comment();
//        comment.setCommentId(UUID.randomUUID().toString().replace("-", ""));
//        comment.setNewsId("4c28b2af51ff485194ae56b686110a88");
//        comment.setContent("嘿嘿");
//        comment.setCreateUserId("299db975aad94b9299a74eb980608e46");
//        String commentId = "bee3fdc5a7b04d1bb0dd34e2e73113ae";
//        commentService.addReply(comment,commentId);
//        News news = new News();
//        news.setNewsId("4c28b2af51ff485194ae56b686110a88");
//        newsService.commentCountPlus(news.getNewsId());
//    }
//
//    /**
//     * 删除评论测试
//     */
//    @Test
//    public void delComment(){
//        String commentId = "bee3fdc5a7b04d1bb0dd34e2e73113ae";
//        String createUserId = "7f69e44a070b4d968ab25f4dd22ee9dd";
//        commentService.delComment(commentId,createUserId);
//        String newsId = "4c28b2af51ff485194ae56b686110a88";
//        newsService.commentCountMinus(newsId);
//    }
//}
