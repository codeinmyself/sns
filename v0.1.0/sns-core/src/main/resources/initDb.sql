drop database if exists sns;

CREATE DATABASE `sns` DEFAULT CHARACTER SET utf8 ;

use sns;

drop table if exists sns_artist;
CREATE TABLE `sns_artist` (
  `artist_id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `description` varchar(1024) NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`artist_id`),
  KEY `idx_sns_artist_col_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='艺术家信息表';

drop table if exists sns_collection;
CREATE TABLE `sns_collection` (
  `collection_id` varchar(32) NOT NULL COMMENT '主键',
  `collection_type` varchar(2) NOT NULL COMMENT '集合类型，01作品集、02艺术圈',
  `collection_name` varchar(256) DEFAULT NULL COMMENT '名称',
  `show_scope` varchar(2) DEFAULT NULL COMMENT '公开范围：\n对作品集，01公开、02仅限用户关注者、03仅限自己；\n对艺术圈，01公开、04仅限艺术圈关注',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `status` varchar(2) DEFAULT NULL COMMENT '状态，0删除、1正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态集合表，包含作品集和艺术圈两种业务模型';

drop table if exists sns_comment;
CREATE TABLE `sns_comment` (
  `comment_id` varchar(32) NOT NULL COMMENT '主键',
  `news_id` varchar(32) NOT NULL COMMENT '评论的动态id',
  `reply_comment_id` varchar(45) DEFAULT NULL COMMENT '回复的评论id，可为空',
  `content` varchar(1024) DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建用户id',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

drop table if exists sns_focus;
CREATE TABLE `sns_focus` (
  `focus_id` varchar(32) NOT NULL COMMENT '关注id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `focus_user_id` varchar(32) NOT NULL COMMENT '被关注人的用户id',
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

drop table if exists sns_focus_group_rela;
CREATE TABLE `sns_focus_group_rela` (
  `rela_id` varchar(32) NOT NULL COMMENT '主键',
  `focus_id` varchar(32) NOT NULL COMMENT '关注id',
  `group_id` varchar(32) NOT NULL COMMENT '关注分组id',
  PRIMARY KEY (`rela_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注用户分组关联表';

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
  `collection_id` varchar(32) NOT NULL COMMENT '艺术圈id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `is_admin` varchar(2) NOT NULL COMMENT '是否管理员， 0否 1是',
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
  `collection_id` varchar(32) DEFAULT NULL COMMENT '所属集合id，null表示公开',
  `content` longtext NOT NULL COMMENT '内容',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数',
  `status` varchar(2) DEFAULT '1' COMMENT '状态，0删除、1正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建用户',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态信息表';

drop table if exists sns_user;
CREATE TABLE `sns_user` (
  `user_id` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(256) NOT NULL COMMENT '用户名',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `email` varchar(256) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机',
  `nickname` varchar(256) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `is_artist` varchar(2) DEFAULT NULL COMMENT '是否是艺术家',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
