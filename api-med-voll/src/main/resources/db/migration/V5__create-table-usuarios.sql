create table if not exists medvoll_usuario(
    id bigint not null auto_increment,
    login varchar(255) not null,
    senha varchar(255) not null,
    primary key(id)

)ENGINE = innodb  default charset=UTF8;