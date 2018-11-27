CREATE TABLE `demo_client` (
  `client_id` int(11) NOT NULL auto_increment,
  `client_name` varchar(255) default NULL,
  `client_password` varchar(255) default NULL,
  PRIMARY KEY  (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

CREATE TABLE `demo_server` (
  `server_id` int(11) NOT NULL auto_increment,
  `server_name` varchar(255) default NULL,
  `server_password` varchar(255) default NULL,
  PRIMARY KEY  (`server_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

