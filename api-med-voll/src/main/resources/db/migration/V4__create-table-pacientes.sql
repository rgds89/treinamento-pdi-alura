create table if not exists medvoll_paciente(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(14) not null unique,
    telefone varchar(20) not null,
    ativo tinyint not null,
    id_endereco bigint not null,
    primary key(id)
) ENGINE = innodb  default charset=UTF8;

alter table medvoll_paciente add constraint fk_endereco_paciente foreign key (id_endereco) references medvoll_endereco (id);