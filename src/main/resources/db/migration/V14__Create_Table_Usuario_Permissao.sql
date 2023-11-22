CREATE TABLE IF NOT EXISTS `usuario_permissao` (
  `id_usuario` bigint(20) NOT NULL,
  `id_permissao` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_permissao`),
  KEY `fk_user_permission_permission` (`id_permissao`),
  CONSTRAINT `fk_user_permission` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_user_permission_permission` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id`)
) ENGINE=InnoDB;