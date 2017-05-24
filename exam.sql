/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50022
Source Host           : 127.0.0.1:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2017-05-24 09:19:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(3) NOT NULL auto_increment COMMENT '管理员id',
  `name` varchar(20) NOT NULL COMMENT '管理员名',
  `password` varchar(30) NOT NULL COMMENT '密码',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '李坤', '123123');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(10) NOT NULL COMMENT '班级编号',
  `name` varchar(50) NOT NULL COMMENT '班级名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('13070641', '计算机一班');
INSERT INTO `class` VALUES ('13070642', '计算机二班');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(3) NOT NULL auto_increment COMMENT '课程id',
  `name` varchar(10) NOT NULL COMMENT '课程名',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '数据结构');
INSERT INTO `course` VALUES ('2', 'JAVA');
INSERT INTO `course` VALUES ('3', '数据库');
INSERT INTO `course` VALUES ('4', '算法设计');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` int(4) NOT NULL auto_increment COMMENT '考试id',
  `name` varchar(50) NOT NULL COMMENT '考试名称',
  `pageId` int(4) NOT NULL COMMENT '考试所用试卷id',
  `time` datetime NOT NULL COMMENT '考试时间',
  `grade` varchar(20) NOT NULL COMMENT '考试班级',
  `flag` int(2) NOT NULL default '0' COMMENT '考试标记 0表示未考',
  `teacherId` int(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('1', 'Java阶段测试', '1', '2017-05-05 10:00:00', '13070641', '0', '1');
INSERT INTO `exam` VALUES ('2', 'Java期中考试', '19', '2017-05-22 16:00:00', '13070641', '0', '1');
INSERT INTO `exam` VALUES ('4', '我的考试', '19', '2017-05-22 18:00:00', '13070641', '2', '1');
INSERT INTO `exam` VALUES ('5', '计算机考试', '1', '2017-05-22 21:30:00', '13070641', '0', '1');

-- ----------------------------
-- Table structure for page
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `id` int(3) NOT NULL auto_increment COMMENT '试卷id',
  `name` varchar(20) NOT NULL COMMENT '试卷名',
  `courseId` int(3) NOT NULL,
  `total_cent` varchar(3) NOT NULL COMMENT '试卷总分',
  `diff` int(4) NOT NULL COMMENT '试卷难度',
  `chapter_start` int(4) default NULL COMMENT '章节开始',
  `chapter_end` int(4) default NULL COMMENT '章节结束',
  `crea_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  `upda_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '更新时间',
  `teacherId` int(4) NOT NULL COMMENT '教师id',
  `ans_time` int(11) NOT NULL COMMENT '答卷时长',
  `page_type` varchar(10) default NULL COMMENT '期中/期末/测试',
  `assembly_type` varchar(10) default NULL COMMENT '组卷方式',
  PRIMARY KEY  (`id`),
  KEY `teacherid` (`teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page
-- ----------------------------
INSERT INTO `page` VALUES ('1', 'JAVA期中考试1', '2', '100', '1', '1', '6', '2017-05-02 22:24:53', '2017-04-28 14:21:56', '1', '120', '1', '2');
INSERT INTO `page` VALUES ('9', '测试试卷1', '1', '100', '1', '1', '5', '2017-05-02 22:24:55', '2017-04-29 22:01:30', '1', '50', '3', '2');
INSERT INTO `page` VALUES ('10', '测试试卷2', '4', '120', '3', '1', '6', '2017-04-29 23:51:59', '2017-04-29 23:51:59', '1', '120', '3', '2');
INSERT INTO `page` VALUES ('11', '测试试卷3', '3', '100', '2', '1', '4', '2017-05-02 23:45:22', '2017-04-29 23:56:16', '1', '100', '3', '2');
INSERT INTO `page` VALUES ('19', 'Java测试1', '2', '100', '1', '1', '6', '2017-05-01 22:04:03', '2017-05-01 22:04:03', '1', '100', '3', '1');

-- ----------------------------
-- Table structure for page_question
-- ----------------------------
DROP TABLE IF EXISTS `page_question`;
CREATE TABLE `page_question` (
  `pageId` int(3) NOT NULL,
  `questionId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_question
-- ----------------------------
INSERT INTO `page_question` VALUES ('1', '9');
INSERT INTO `page_question` VALUES ('1', '10');
INSERT INTO `page_question` VALUES ('1', '11');
INSERT INTO `page_question` VALUES ('1', '12');
INSERT INTO `page_question` VALUES ('1', '20');
INSERT INTO `page_question` VALUES ('1', '52');
INSERT INTO `page_question` VALUES ('1', '43');
INSERT INTO `page_question` VALUES ('1', '70');
INSERT INTO `page_question` VALUES ('1', '22');
INSERT INTO `page_question` VALUES ('1', '25');
INSERT INTO `page_question` VALUES ('1', '61');
INSERT INTO `page_question` VALUES ('1', '8');
INSERT INTO `page_question` VALUES ('1', '23');
INSERT INTO `page_question` VALUES ('1', '29');
INSERT INTO `page_question` VALUES ('1', '31');
INSERT INTO `page_question` VALUES ('1', '38');
INSERT INTO `page_question` VALUES ('1', '40');
INSERT INTO `page_question` VALUES ('1', '4');
INSERT INTO `page_question` VALUES ('9', '2');
INSERT INTO `page_question` VALUES ('12', '3');
INSERT INTO `page_question` VALUES ('12', '19');
INSERT INTO `page_question` VALUES ('19', '368');
INSERT INTO `page_question` VALUES ('19', '222');
INSERT INTO `page_question` VALUES ('19', '20');
INSERT INTO `page_question` VALUES ('19', '74');
INSERT INTO `page_question` VALUES ('19', '256');
INSERT INTO `page_question` VALUES ('19', '276');
INSERT INTO `page_question` VALUES ('19', '267');
INSERT INTO `page_question` VALUES ('19', '110');
INSERT INTO `page_question` VALUES ('19', '375');
INSERT INTO `page_question` VALUES ('19', '4');
INSERT INTO `page_question` VALUES ('19', '23');
INSERT INTO `page_question` VALUES ('19', '140');
INSERT INTO `page_question` VALUES ('19', '360');
INSERT INTO `page_question` VALUES ('19', '77');
INSERT INTO `page_question` VALUES ('19', '131');
INSERT INTO `page_question` VALUES ('19', '393');
INSERT INTO `page_question` VALUES ('19', '402');
INSERT INTO `page_question` VALUES ('19', '406');
INSERT INTO `page_question` VALUES ('19', '396');
INSERT INTO `page_question` VALUES ('19', '397');
INSERT INTO `page_question` VALUES ('19', '180');
INSERT INTO `page_question` VALUES ('19', '115');
INSERT INTO `page_question` VALUES ('19', '371');

-- ----------------------------
-- Table structure for page_questionnum
-- ----------------------------
DROP TABLE IF EXISTS `page_questionnum`;
CREATE TABLE `page_questionnum` (
  `pageId` int(3) NOT NULL COMMENT '试卷id',
  `typeId` int(4) NOT NULL COMMENT '类型',
  `num` int(2) NOT NULL COMMENT '数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_questionnum
-- ----------------------------

-- ----------------------------
-- Table structure for practice
-- ----------------------------
DROP TABLE IF EXISTS `practice`;
CREATE TABLE `practice` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL COMMENT '练习名',
  `describe` varchar(255) default NULL COMMENT '描述',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice
-- ----------------------------

-- ----------------------------
-- Table structure for practice_question
-- ----------------------------
DROP TABLE IF EXISTS `practice_question`;
CREATE TABLE `practice_question` (
  `practiceId` int(10) NOT NULL COMMENT '练习id',
  `questionId` int(10) NOT NULL COMMENT '题目id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_question
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(4) NOT NULL auto_increment COMMENT '试题id',
  `courseId` int(3) NOT NULL,
  `content` varchar(50) NOT NULL COMMENT '试题内容',
  `option1` varchar(50) default NULL COMMENT '选项1',
  `option2` varchar(50) default NULL COMMENT '选项2',
  `option3` varchar(50) default NULL COMMENT '选项3',
  `option4` varchar(50) default NULL COMMENT '选项4',
  `answer` varchar(1000) default NULL COMMENT '答案',
  `chapter` int(4) default NULL COMMENT '所属章节',
  `type` int(4) NOT NULL COMMENT '试题类型id',
  `diff` int(4) NOT NULL COMMENT '试题难度 1简单 2一般 3困难',
  PRIMARY KEY  (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('2', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '1');
INSERT INTO `question` VALUES ('3', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('4', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('5', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('6', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('8', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('9', '3', '事务的四大特性:?,?,?,?', null, null, null, null, '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('10', '2', '请写出冒泡排序', null, null, null, null, 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('11', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('12', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('17', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('18', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '1');
INSERT INTO `question` VALUES ('19', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('20', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('21', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('22', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('23', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('24', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('25', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('26', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('27', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '1');
INSERT INTO `question` VALUES ('28', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('29', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('30', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('31', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('32', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('33', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('34', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('35', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('36', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('37', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('38', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('39', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('40', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('41', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('42', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('43', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('44', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('45', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('46', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('47', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('48', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('49', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('50', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('51', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('52', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('53', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('54', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('55', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('56', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('57', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('58', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('59', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('60', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('61', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('62', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('63', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('64', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('65', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('66', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('67', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('68', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('69', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('70', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('71', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('72', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('73', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('74', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('75', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('76', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('77', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('78', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('79', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('80', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('81', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('82', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('83', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('84', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('85', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('86', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('87', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('88', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('89', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('90', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('91', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('92', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('93', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('94', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('95', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('96', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('97', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('98', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('99', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('100', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('101', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('102', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('103', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('104', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('105', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('106', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('107', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('108', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('109', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('110', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('111', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('112', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('113', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('114', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('115', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('116', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('117', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('118', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('119', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('120', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('121', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('122', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('123', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('124', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('125', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('126', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('127', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('128', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('129', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('130', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('131', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('132', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('133', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('134', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('135', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('136', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('137', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('138', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('139', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('140', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('141', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('142', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('143', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('144', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('145', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('146', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('147', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('148', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('149', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('150', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('151', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('152', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('153', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('154', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('155', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('156', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('157', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('158', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('159', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('160', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('161', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('162', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('163', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('164', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('165', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('166', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('167', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('168', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('169', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('170', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('171', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('172', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('173', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('174', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('175', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('176', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('177', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('178', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('179', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('180', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('181', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('182', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('183', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('184', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('185', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('186', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('187', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('188', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('189', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('190', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('191', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('192', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('193', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('194', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('195', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('196', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('197', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('198', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('199', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('200', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('201', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('202', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('203', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('204', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('205', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('206', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('207', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('208', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('209', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('210', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('211', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('212', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('213', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('214', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('215', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('216', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('217', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('218', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('219', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('220', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('221', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('222', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('223', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('224', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('225', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('226', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('227', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('228', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('229', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('230', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('231', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('232', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('233', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('234', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('235', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('236', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('237', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('238', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('239', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('240', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('241', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('242', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('243', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('244', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('245', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('246', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('247', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('248', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('249', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('250', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('251', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('252', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('253', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('254', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('255', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('256', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('257', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('258', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('259', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('260', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('261', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('262', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('263', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('264', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('265', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('266', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('267', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('268', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('269', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('270', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('271', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('272', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('273', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('274', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('275', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('276', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('277', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('278', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('279', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('280', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('281', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('282', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('283', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('284', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('285', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('286', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('287', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('288', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('289', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('290', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('291', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('292', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('293', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('294', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('295', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('296', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('297', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('298', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('299', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('300', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('301', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('302', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('303', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('304', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('305', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('306', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('307', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('308', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('309', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('310', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('311', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('312', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('313', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('314', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('315', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('316', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('317', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('318', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('319', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('320', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('321', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('322', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('323', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('324', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('325', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('326', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('327', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('328', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('329', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('330', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('331', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('332', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('333', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('334', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('335', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('336', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('337', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('338', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('339', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('340', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('341', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('342', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('343', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('344', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('345', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('346', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('347', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('348', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('349', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('350', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('351', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('352', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('353', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('354', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('355', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('356', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('357', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('358', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('359', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('360', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('361', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('362', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('363', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('364', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('365', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('366', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('367', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('368', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('369', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('370', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('371', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('372', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('373', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('374', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('375', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('376', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('377', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('378', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('379', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('380', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('381', '2', '按照Java的标识符命名规范，下列表示一个类的标识符正确的是', 'HelloMan', 'helloman', 'StudentInfo', 'studentInfo', 'A,C', '3', '2', '2');
INSERT INTO `question` VALUES ('382', '1', ' 一个栈的输入序列为1 2 3 4 5，则下列序列中不可能是栈得输出序列的是', '5 4 1 3 2', '2 3 4 1 5', '1 5 4 3 2', '2 3 1 4 5', 'A', '1', '1', '2');
INSERT INTO `question` VALUES ('383', '2', '以下代码段将创建几个对象 String s1=\"bc\";\r\nString s2=\"bc\"; ', '2', '3', '0', '1', 'D', '1', '1', '2');
INSERT INTO `question` VALUES ('384', '2', ' 设x为int型变量，则执行以下语句段后，x的值为：\r\nx=10;\r\nx+=x-=x-x;', '30', '20', '40', '10', 'B', '1', '1', '1');
INSERT INTO `question` VALUES ('385', '2', '下面说法正确的是', 'JAVA中线程是非抢占式的', 'JAVA中的线程不可以共享数据', '每个JAVA程序都至少有一个线程，即主线程', 'JAVA中的线程不可以共享代码', 'C', '1', '1', '3');
INSERT INTO `question` VALUES ('386', '2', '随着Java技术的不断发展，Sun公司根据市场进一步将Java细分，其中针对普通PC应用的是', 'J2EE', 'JDK', 'J2ME', 'J2SE', 'C', '1', '1', '1');
INSERT INTO `question` VALUES ('387', '2', '下面正确声明一个一维数组的是', 'String [] a', 'String a[]', 'char a[][]', 'String a[10]', 'A,B', '2', '2', '1');
INSERT INTO `question` VALUES ('388', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('389', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('390', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('391', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('392', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('393', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('394', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('395', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('396', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('397', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('398', '3', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('399', '2', '请写出冒泡排序', '', '', '', '', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '3', '4', '1');
INSERT INTO `question` VALUES ('400', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('401', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('402', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('403', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('404', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('405', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('406', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');
INSERT INTO `question` VALUES ('407', '2', '事务的四大特性:?,?,?,?', '', '', '', '', '原子性,一致性,持久性,隔离性', '3', '3', '1');

-- ----------------------------
-- Table structure for question_type
-- ----------------------------
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type` (
  `id` int(4) NOT NULL auto_increment COMMENT '唯一标示id',
  `name` varchar(20) NOT NULL COMMENT '类型名',
  `mode` int(2) NOT NULL COMMENT '主观/客观 1/2',
  `cent` int(4) NOT NULL COMMENT '分值',
  `num` int(4) NOT NULL COMMENT '试题数量',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_type
-- ----------------------------
INSERT INTO `question_type` VALUES ('1', '单选题', '2', '2', '0');
INSERT INTO `question_type` VALUES ('2', '多选题', '2', '5', '0');
INSERT INTO `question_type` VALUES ('3', '填空题', '2', '5', '0');
INSERT INTO `question_type` VALUES ('4', '简答题', '1', '10', '0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(4) NOT NULL auto_increment COMMENT '唯一标示id',
  `no` int(10) NOT NULL COMMENT '学号',
  `name` varchar(20) NOT NULL COMMENT '学生姓名',
  `grade` varchar(10) NOT NULL COMMENT '班级',
  `password` varchar(30) NOT NULL COMMENT '密码',
  `email` varchar(30) default NULL COMMENT '邮箱',
  `img` varchar(20) default NULL COMMENT '头像',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `no` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '1307064101', '丁鑫', '13070641', '123456', '930017197@qq.com', '');
INSERT INTO `student` VALUES ('2', '1307064102', '李坤', '13070641', '123123', '1617455243@qq.com', null);
INSERT INTO `student` VALUES ('3', '1307064103', '张1', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('4', '1307064104', '张2', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('5', '1307064105', '张5', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('6', '1307064106', '张6', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('7', '1307064107', '张7', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('8', '1307064108', '张8', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('9', '1307064109', '张9', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('10', '1307064110', '张10', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('11', '1307064111', '张11', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('12', '1307064112', '张12', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('13', '1307064113', '张13', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('14', '1307064114', '张14', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('15', '1307064115', '张15', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('16', '1307064116', '张16', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('17', '1307064117', '张17', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('18', '1307064118', '张18', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('19', '1307064119', '张19', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('20', '1307064120', '张20', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('21', '1307064121', '张21', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('22', '1307064122', '张22', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('23', '1307064123', '张23', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('24', '1307064124', '张24', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('25', '1307064125', '张25', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('26', '1307064126', '张26', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('27', '1307064127', '张27', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('28', '1307064128', '张28', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('29', '1307064129', '张29', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('30', '1307064130', '张30', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('31', '1307064131', '张31', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('32', '1307064132', '张32', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('33', '1307064133', '张33', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('34', '1307064134', '张34', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('35', '1307064135', '张35', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('36', '1307064136', '张36', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('37', '1307064137', '张37', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('38', '1307064138', '张38', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('39', '1307064139', '张39', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('40', '1307064140', '张40', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('41', '1307064141', '张41', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('42', '1307064142', '张42', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('43', '1307064143', '张43', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('44', '1307064144', '张44', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('45', '1307064145', '张45', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('46', '1307064146', '张46', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('47', '1307064147', '张47', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('48', '1307064148', '张48', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('49', '1307064149', '张49', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('50', '1307064150', '张50', '13070641', '123123', '123@123.com', null);
INSERT INTO `student` VALUES ('52', '1307064151', '李梦机', '13070641', '123123', '123321@qq.com', null);

-- ----------------------------
-- Table structure for student_exam
-- ----------------------------
DROP TABLE IF EXISTS `student_exam`;
CREATE TABLE `student_exam` (
  `stuId` int(4) NOT NULL COMMENT '学生id',
  `examId` int(4) NOT NULL COMMENT '考试id',
  `questionId` int(4) NOT NULL COMMENT '试题id',
  `answer` varchar(1000) NOT NULL COMMENT '学生答案',
  `cent` int(4) default NULL COMMENT '得分'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_exam
-- ----------------------------
INSERT INTO `student_exam` VALUES ('1', '4', '4', 'B', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '20', 'B', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '23', 'A,B', '5');
INSERT INTO `student_exam` VALUES ('1', '4', '74', 'D', '0');
INSERT INTO `student_exam` VALUES ('1', '4', '77', 'B,C', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '110', 'B', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '115', 'ffff', '5');
INSERT INTO `student_exam` VALUES ('1', '4', '131', 'A,B,C,D', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '140', 'C', '0');
INSERT INTO `student_exam` VALUES ('1', '4', '180', 'ggggg', '5');
INSERT INTO `student_exam` VALUES ('1', '4', '222', 'D', '0');
INSERT INTO `student_exam` VALUES ('1', '4', '256', 'C', '0');
INSERT INTO `student_exam` VALUES ('1', '4', '267', 'D', '0');
INSERT INTO `student_exam` VALUES ('1', '4', '276', 'D', '0');
INSERT INTO `student_exam` VALUES ('1', '4', '360', 'B', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '368', 'C', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '371', 'public class BubbleSort\r\n{\r\n    public void sort(int[] a)\r\n    {\r\n        int temp = 0;\r\n        for (int i = a.length - 1; i > 0; --i)\r\n        {\r\n            for (int j = 0; j < i; ++j)\r\n            {\r\n                if (a[j + 1] < a[j])\r\n                {\r\n                    temp = a[j];\r\n                    a[j] = a[j + 1];\r\n                    a[j + 1] = temp;\r\n                }\r\n            }\r\n        }\r\n    }\r\n}', '5');
INSERT INTO `student_exam` VALUES ('1', '4', '375', 'D', '0');
INSERT INTO `student_exam` VALUES ('1', '4', '393', '原子性,一致性,隔离性 ,持久性', '5');
INSERT INTO `student_exam` VALUES ('1', '4', '396', '急急急,原子性,打分,一致性', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '397', '打分,个 ,水电费,啊啊', '0');
INSERT INTO `student_exam` VALUES ('1', '4', '402', '第三方,一致性,试试, 恩恩', '2');
INSERT INTO `student_exam` VALUES ('1', '4', '406', '水电费,天堂,语言,哦哦', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '4', 'B', '2');
INSERT INTO `student_exam` VALUES ('2', '4', '20', 'B', '2');
INSERT INTO `student_exam` VALUES ('2', '4', '23', 'A,B', '5');
INSERT INTO `student_exam` VALUES ('2', '4', '74', 'B', '2');
INSERT INTO `student_exam` VALUES ('2', '4', '77', 'B,C', '2');
INSERT INTO `student_exam` VALUES ('2', '4', '110', 'D', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '115', 'gggggggg', '5');
INSERT INTO `student_exam` VALUES ('2', '4', '131', 'B', '2');
INSERT INTO `student_exam` VALUES ('2', '4', '140', 'A,B,C', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '180', 'uuuuuuuuuu', '5');
INSERT INTO `student_exam` VALUES ('2', '4', '222', 'D', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '256', 'D', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '267', 'D', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '276', 'D', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '360', 'C', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '368', 'D', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '371', 'iiiiiiiiiiiiiii', '5');
INSERT INTO `student_exam` VALUES ('2', '4', '375', 'D', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '393', '原子性,隔离性,一致性,持久性', '5');
INSERT INTO `student_exam` VALUES ('2', '4', '396', '啊啊,试试,得到,方法', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '397', '原子性,方法,一致性,是v', '2');
INSERT INTO `student_exam` VALUES ('2', '4', '402', '四大,得到,人人,啊啊', '0');
INSERT INTO `student_exam` VALUES ('2', '4', '406', '是,的,一致性,方法', '2');

-- ----------------------------
-- Table structure for student_score
-- ----------------------------
DROP TABLE IF EXISTS `student_score`;
CREATE TABLE `student_score` (
  `stuId` int(4) NOT NULL COMMENT '学生id',
  `examId` int(4) NOT NULL COMMENT '考试id',
  `score` int(4) default NULL COMMENT '得分'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_score
-- ----------------------------
INSERT INTO `student_score` VALUES ('1', '4', '43');
INSERT INTO `student_score` VALUES ('2', '4', '39');
INSERT INTO `student_score` VALUES ('3', '4', '92');
INSERT INTO `student_score` VALUES ('4', '4', '93');
INSERT INTO `student_score` VALUES ('5', '4', '95');
INSERT INTO `student_score` VALUES ('6', '4', '80');
INSERT INTO `student_score` VALUES ('7', '4', '52');
INSERT INTO `student_score` VALUES ('8', '4', '80');
INSERT INTO `student_score` VALUES ('9', '4', '80');
INSERT INTO `student_score` VALUES ('10', '4', '53');
INSERT INTO `student_score` VALUES ('11', '4', '97');
INSERT INTO `student_score` VALUES ('12', '4', '49');
INSERT INTO `student_score` VALUES ('14', '4', '93');
INSERT INTO `student_score` VALUES ('15', '4', '65');
INSERT INTO `student_score` VALUES ('16', '4', '63');
INSERT INTO `student_score` VALUES ('17', '4', '80');
INSERT INTO `student_score` VALUES ('18', '4', '20');
INSERT INTO `student_score` VALUES ('19', '4', '94');
INSERT INTO `student_score` VALUES ('20', '4', '80');
INSERT INTO `student_score` VALUES ('21', '4', '81');
INSERT INTO `student_score` VALUES ('22', '4', '80');
INSERT INTO `student_score` VALUES ('23', '4', '74');
INSERT INTO `student_score` VALUES ('24', '4', '83');
INSERT INTO `student_score` VALUES ('25', '4', '80');
INSERT INTO `student_score` VALUES ('26', '4', '81');
INSERT INTO `student_score` VALUES ('27', '4', '72');
INSERT INTO `student_score` VALUES ('28', '4', '86');
INSERT INTO `student_score` VALUES ('29', '4', '80');
INSERT INTO `student_score` VALUES ('30', '4', '82');
INSERT INTO `student_score` VALUES ('31', '4', '80');
INSERT INTO `student_score` VALUES ('32', '4', '71');
INSERT INTO `student_score` VALUES ('33', '4', '76');
INSERT INTO `student_score` VALUES ('34', '4', '80');
INSERT INTO `student_score` VALUES ('35', '4', '79');
INSERT INTO `student_score` VALUES ('36', '4', '87');
INSERT INTO `student_score` VALUES ('37', '4', '80');
INSERT INTO `student_score` VALUES ('38', '4', '80');
INSERT INTO `student_score` VALUES ('39', '4', '97');
INSERT INTO `student_score` VALUES ('40', '4', '80');
INSERT INTO `student_score` VALUES ('41', '4', '80');
INSERT INTO `student_score` VALUES ('42', '4', '80');
INSERT INTO `student_score` VALUES ('43', '4', '80');
INSERT INTO `student_score` VALUES ('44', '4', '25');
INSERT INTO `student_score` VALUES ('45', '4', '80');
INSERT INTO `student_score` VALUES ('46', '4', '75');
INSERT INTO `student_score` VALUES ('47', '4', '80');
INSERT INTO `student_score` VALUES ('48', '4', '66');
INSERT INTO `student_score` VALUES ('49', '4', '63');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(10) NOT NULL auto_increment COMMENT '唯一标示id',
  `no` int(10) NOT NULL COMMENT '教室编号',
  `name` varchar(20) NOT NULL COMMENT '教师姓名',
  `password` varchar(30) NOT NULL COMMENT '密码',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `no` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '1', '徐志勇', '123123');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `a` char(1) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
