CREATE TABLE IF NOT EXISTS `usuario` (
                           `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(45),
                           `sobrenome` varchar(100),
                           `email` varchar(60),
                           `telefone` varchar(20),
                           `senha` varchar(100),
                            `account_non_expired` bit(1) DEFAULT NULL,
                            `account_non_locked` bit(1) DEFAULT NULL,
                            `credentials_non_expired` bit(1) DEFAULT NULL,
                            `enabled` bit(1) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
