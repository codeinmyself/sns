drop database if exists sns;

CREATE DATABASE `sns` DEFAULT CHARACTER SET utf8 ;

use sns;

-- ----------------------------
-- Table structure for `sns_admin`
-- ----------------------------
DROP TABLE IF EXISTS `sns_admin`;
CREATE TABLE `sns_admin` (
  `admin_id` varchar(32) NOT NULL,
  `admin_password` varchar(256) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员信息表';

-- ----------------------------
-- Records of sns_admin
-- ----------------------------
INSERT INTO `sns_admin` VALUES ('1', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `sns_admin` VALUES ('2', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `sns_admin` VALUES ('3', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `sns_admin` VALUES ('4', 'e10adc3949ba59abbe56e057f20f883e');

drop table if exists sns_artist;
CREATE TABLE `sns_artist` (
  `artist_id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `description` varchar(1024) NOT NULL COMMENT '描述',
  `status` varchar(1) NOT NULL COMMENT '',
  `create_time` datetime NOT NULL,

  PRIMARY KEY (`artist_id`),
  KEY `idx_sns_artist_col_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='艺术家信息表';

drop table if exists sns_circle;
CREATE TABLE `sns_circle` (
  `circle_id` varchar(32) NOT NULL COMMENT '艺术圈id',
  `circle_name` varchar(256) NOT NULL COMMENT '名称',
  `show_scope` varchar(2) NOT NULL COMMENT '艺术圈公开范围：01公开、02仅限艺术圈成员',
  `apply_strategy` varchar(2) NOT NULL COMMENT '申请策略：\n01 无需申请加入\n02 需申请加入',
  `description` varchar(1024) DEFAULT NULL,
  `avatar` varchar(256) NOT NULL COMMENT '艺术圈头像',
  `status` varchar(2) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`circle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='艺术圈';

drop table if exists sns_collection;
CREATE TABLE `sns_collection` (
  `collection_id` varchar(32) NOT NULL COMMENT '主键',
  `collection_name` varchar(256) NOT NULL COMMENT '名称',
  `show_scope` varchar(2) NOT NULL COMMENT '作品集公开范围：01公开、02仅限用户关注者、03仅限自己；',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` varchar(2) NOT NULL COMMENT '状态，0删除、1正常',
  `cover` varchar(256) NOT NULL COMMENT '作品集头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建用户id',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作品集';

drop table if exists sns_comment;
CREATE TABLE `sns_comment` (
  `comment_id` varchar(32) NOT NULL COMMENT '主键',
  `news_id` varchar(32) NOT NULL COMMENT '评论的动态id',
  `reply_comment_id` varchar(45) DEFAULT NULL COMMENT '回复的评论id，可为空',
  `content` varchar(1024) DEFAULT NULL COMMENT '评论内容',
  `status` varchar(2) NOT NULL COMMENT '状态，0删除、1正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建用户id',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

drop table if exists sns_focus;
CREATE TABLE `sns_focus` (
  `focus_id` varchar(32) NOT NULL COMMENT '关注id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `focus_user_id` varchar(32) NOT NULL COMMENT '被关注人的用户id',
  `group_id` varchar(32) DEFAULT  NULL COMMENT '关注分组',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`focus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注信息表';

drop table if exists sns_focus_group;
CREATE TABLE `sns_focus_group` (
  `group_id` varchar(32) NOT NULL COMMENT '主键',
  `group_name` varchar(128) NOT NULL COMMENT '分组名',
  `create_user_id` varchar(45) NOT NULL COMMENT '创建者id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注用户分组表';

drop table if exists sns_like;
CREATE TABLE `sns_like` (
  `like_id` varchar(32) NOT NULL COMMENT '主键',
  `news_id` varchar(32) NOT NULL COMMENT '动态id',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建者id',
  PRIMARY KEY (`like_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞表';

drop table if exists sns_member;
CREATE TABLE `sns_member` (
  `member_id` varchar(32) NOT NULL COMMENT '成员id',
  `circle_id` varchar(32) NOT NULL COMMENT '艺术圈id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `member_type` varchar(2) NOT NULL COMMENT '成员类型：\n01创建者\n02管理员\n03普通成员',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='艺术圈成员表';

drop table if exists sns_member_apply;
CREATE TABLE `sns_member_apply` (
  `apply_id` varchar(32) NOT NULL COMMENT '申请id',
  `collection_id` varchar(32) NOT NULL COMMENT '艺术圈id',
  `apply_user_id` varchar(32) NOT NULL COMMENT '申请人id',
  `apply_time` datetime NOT NULL COMMENT '申请发起时间',
  `apply_desc` varchar(128) DEFAULT NULL COMMENT '申请描述',
  `apply_status` varchar(2) NOT NULL COMMENT '申请状态 01申请中、02申请通过、03申请不通过',
  `verify_user_id` varchar(32) DEFAULT NULL COMMENT '审核人id',
  `verify_time` datetime DEFAULT NULL COMMENT '审核时间',
  `verify_desc` varchar(128) DEFAULT NULL COMMENT '审核描述',
  PRIMARY KEY (`apply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='艺术圈成员申请信息表';

drop table if exists sns_news;
CREATE TABLE `sns_news` (
  `news_id` varchar(32) NOT NULL,
  `show_scope` varchar(2) NOT NULL COMMENT '动态公开范围：01公开、02仅限用户关注者、03仅限自己、04仅限艺术圈',
  `content` longtext NOT NULL COMMENT '内容',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数',
  `click_count` int(11) DEFAULT  '0' COMMENT '浏览量',
  `status` varchar(2) DEFAULT '1' COMMENT '状态，0删除、1正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建用户',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态信息表';

DROP TABLE IF EXISTS `sns_news_image`;
CREATE TABLE `sns_news_image` (
  `pic_id` varchar(32) NOT NULL COMMENT '主键',
  `news_id` varchar(32) NOT NULL COMMENT '动态id',
  `pic_index` int(11) NOT NULL COMMENT '动态内图片显示顺序，从1开始排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`pic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态关联图片表';

drop table if exists sns_news_rela;
CREATE TABLE `sns_news_rela` (
  `rela_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `news_id` varchar(32) NOT NULL COMMENT '动态id',
  `rela_type` varchar(2) NOT NULL COMMENT '关联类型\n01关联到作品集\n02关联到艺术圈',
  `target_id` varchar(32) NOT NULL COMMENT '关联目标的id\n若rela_type=01，则关联sns_collection表的collection_id\n若rela_type=02，则关联sns_circle表的circle_id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`rela_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态关联表';

drop table if exists sns_user;
CREATE TABLE `sns_user` (
  `user_id` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `signature` varchar(256) DEFAULT NULL COMMENT '简介',
  `is_artist` varchar(2) DEFAULT NULL COMMENT '是否是艺术家 0否 1是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_locked` varchar(2) DEFAULT '0' COMMENT '是否被封号\n0否 1是',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

drop table if exists sns_notice;
CREATE TABLE `sns_notice` (
  `notice_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `notice_content` varchar(512) NOT NULL COMMENT '通知内容',
  `read_status` varchar(2) NOT NULL COMMENT '读取状态：0未读 1已读',
  `receive_user_id` varchar(32) NOT NULL COMMENT '目标用户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `notice_type` varchar(2) NOT NULL COMMENT '通知类型\n01 申请加入艺术圈通知\n02 艺术圈审核结果通知\n03 加入艺术圈通知\n04 退出艺术圈通知\n更多类型见代码\n',
  `target_id` varchar(32) NOT NULL COMMENT '目标id\n根据notice_type对应不同的表的主键\n例如notice_type=01，target_id为艺术圈id',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知表';

drop table if exists sns_report;
CREATE TABLE `sns_report` (
  `report_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `report_type` varchar(2) NOT NULL COMMENT '举报类型\n01 违法信息\n02 露骨色情内容\n03 垃圾内容\n04 人身攻击\n05 其他',
  `report_desc` varchar(256) DEFAULT NULL,
  `create_user_id` varchar(32) NOT NULL,
  `read_status` varchar(1) DEFAULT '0' COMMENT '1已阅读，0未阅读',
  `create_time` datetime NOT NULL,
  `target_type` varchar(2) NOT NULL COMMENT '举报的目标类型：\n01 举报用户\n02 举报动态\n03 举报艺术圈\n04 举报作品集\n05 举报评论',
  `target_id` varchar(32) NOT NULL COMMENT '举报目标id\n根据举报目标类型匹配',
  `report_reply` varchar(1024) DEFAULT  NULL COMMENT '举报的回复',
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='举报表';

DROP TABLE IF EXISTS `sns_sensitive_word`;
CREATE TABLE `sns_sensitive_word` (
  `word_id` varchar(32) NOT NULL COMMENT '关键字ID',
  `word_name` varchar(256) NOT NULL COMMENT '关键字',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='敏感词表';



-- ----------------------------
-- Procedure structure for `createCommentList`
-- ----------------------------
DROP PROCEDURE IF EXISTS `createCommentList`;
DELIMITER ;;
CREATE PROCEDURE `createCommentList`(IN rootId varchar(32),IN commentSeq INT,IN reply varchar(32))
begin
DECLARE done INT DEFAULT 0;
DECLARE b varchar(32);
DECLARE replyUserId varchar(32);
DECLARE commentstatus varchar(2);
DECLARE cur1 CURSOR FOR SELECT comment_id,create_user_id,status FROM sns_comment where reply_comment_id=rootId;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;


OPEN cur1;
FETCH cur1 INTO b,replyUserId,commentstatus;
WHILE done=0 DO
			insert into tmplst values (b,'N',commentSeq,reply,commentstatus);
       CALL createCommentList(b,commentSeq,replyUserId);
       FETCH cur1 INTO b,replyUserId,commentstatus;
END WHILE;

 CLOSE cur1;
  END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `showCommentList`
-- ----------------------------
DROP PROCEDURE IF EXISTS `showCommentList`;
DELIMITER ;;
CREATE PROCEDURE `showCommentList`(IN newsId varchar(32))
begin
declare commentSeq int DEFAULT 1;
declare done int default 0;
declare commentId varchar(32);
declare replyUserId varchar(32);
declare cur1 cursor for select comment_id,create_user_id from sns_comment where news_Id=newsId and reply_comment_id is null and status='1' order by create_time asc;
declare continue handler for not found set done=1;

drop TEMPORARY table if exists tmplst;
CREATE TEMPORARY TABLE tmplst
(commentNode varchar(32) primary key,isFirstFloor varchar(1),belongToComment int,replyUserId varchar(32),isNormal varchar(2) );
truncate TABLE tmplst;

open cur1;

fetch  cur1 into commentId,replyUserId;
while done=0 do
 insert into tmplst values (commentId,'Y',commentSeq,NULL,'1');
	call createCommentList(commentId,commentSeq,replyUserId);
  set commentSeq=commentSeq+1;
fetch cur1 into commentId,replyUserId;
end while;

close cur1;
select 	tmplst.isFirstFloor as isFirstFloor,
				tmplst.replyUserId as replyUserId,
				sns_comment.comment_id,
        sns_comment.news_id,
				sns_comment.reply_comment_id,
				sns_comment.content,
				date_format(sns_comment.create_time,'%Y-%m-%d %H:%i:%s') as createTime,
				sns_comment.create_user_id,
				A.user_id as createUserId,
				A.nickname as createNickName,
				A.avatar as createAvatar
				from tmplst,sns_comment,sns_user A
				where tmplst.isNormal='1'
				and tmplst.commentNode=sns_comment.comment_id
        and A.user_id=sns_comment.create_user_id
       order by belongToComment asc,createTime asc;
drop TEMPORARY table if exists tmplst;

end
;;
DELIMITER ;
