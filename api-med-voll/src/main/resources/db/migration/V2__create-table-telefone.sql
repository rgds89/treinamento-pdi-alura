create table if not exists medvoll_telefone(
    id bigint not null auto_increment,
    ddd varchar(2) not null,
    numero varchar(9) not null,
    id_medico bigint not null,
    primary key(id)
)ENGINE = innodb default charset=UTF8;

alter table medvoll_telefone add constraint fk_telefone foreign key (id_medico) references medvoll_medico (id);