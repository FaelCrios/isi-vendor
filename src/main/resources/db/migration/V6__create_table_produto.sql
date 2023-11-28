CREATE TABLE IF NOT EXISTS `produto`(
                          `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                          `name` varchar(60),
                          `descricao` varchar(100),
                          `price` double,
                          `img_url` varchar(100),
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;