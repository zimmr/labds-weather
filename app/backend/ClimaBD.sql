drop database if exists projeto_api;

create database projeto_api
character set utf8mb4
collate utf8mb4_unicode_ci;

use projeto_api;

create table Usuario(
	id_user varchar(50) NOT NULL primary key,
    nome varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    senha varchar(255) NOT NULL,
    usa_celsius bit NOT NULL
);

create table Historico(
	id_hist varchar(50) NOT NULL primary key,
    user_id varchar(50) NOT NULL,
    data_consulta date NOT NULL,
    cidade varchar(50) NOT NULL,
    estado varchar(50),
    pais varchar(50) NOT NULL,
    latitude decimal(9,6),
    longitude decimal(9,6),
    dados_consulta varchar(200)NOT NULL,
    
    constraint FK_Historico_Usuario
    foreign key (user_id)
    references Usuario(id_User)
);

create table Favoritos(
	id_fave varchar(50) NOT NULL PRIMARY KEY,
    user_id varchar(50) NOT NULL,
    titulo varchar(200) NOT NULL, 
    cidade varchar(50) NOT NULL,
    estado varchar(50),
    pais varchar(50) NOT NULL,
    latitude decimal(9,6),
    longitude decimal(9,6),
    
    constraint FK_Favoritos_Usuario
    foreign key (user_id)
    references Usuario(id_User)
);

create table Log(
	id_log varchar(50) NOT NULL PRIMARY KEY,
    data_consulta date NOT NULL,
    cidade varchar(50) NOT NULL,
    estado varchar(50),
    pais varchar(50) NOT NULL,
    latitude decimal(9,6),
    longitude decimal(9,6)
);