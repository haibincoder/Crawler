databases crawler;

CREATE TABLE `recommendnews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `datetime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

CREATE TABLE `weibo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) DEFAULT NULL,
  `content` longtext,
  `link` varchar(255) DEFAULT NULL,
  `datetime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1086 DEFAULT CHARSET=utf8;

CREATE TABLE `weixin_hotnews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `datetime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;


