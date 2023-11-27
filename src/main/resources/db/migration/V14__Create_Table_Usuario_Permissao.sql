CREATE TABLE IF NOT EXISTS `usuario_permissao` (
    `id_usuario` bigint(20) NOT NULL,
    `id_permissao` bigint(20) NOT NULL,
    PRIMARY KEY (`id_usuario`,`id_permissao`),
    KEY `fk_usuario_permissao_permissao` (`id_permissao`),
    CONSTRAINT `fk_usuario_permissao` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
    CONSTRAINT `fk_usuario_permissao_id_permissao` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id`)
    ) ENGINE=InnoDB;
