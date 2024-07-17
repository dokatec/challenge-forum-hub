CREATE TABLE topicos(

id bigint not null auto_increment,
titulo varchar(100) not null,
curso varchar(100) not null ,
mensagem varchar(100) not null ,
nome varchar(100) not null unique,


PRIMARY KEY(id)

)