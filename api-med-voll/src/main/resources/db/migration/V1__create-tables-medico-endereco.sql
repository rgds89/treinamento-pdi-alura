create table if not exists medvoll_medico(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    especialidade varchar(100) not null,
    id_endereco bigint not null,
    primary key(id)
) ENGINE = innodb default charset=UTF8;

create table if not exists medvoll_endereco(
    id bigint not null auto_increment,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    primary key(id)
) ENGINE = innodb  default charset=UTF8;

alter table medvoll_medico add constraint fk_endereco foreign key (id_endereco) references medvoll_endereco (id);