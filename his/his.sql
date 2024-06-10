CREATE SCHEMA `his` DEFAULT CHARACTER SET utf8mb4 ;

CREATE TABLE `his`.`patient` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO `his`.`patient` (`id`, `name`) VALUES ('1', '张三');
INSERT INTO `his`.`patient` (`id`, `name`) VALUES ('2', '李四');