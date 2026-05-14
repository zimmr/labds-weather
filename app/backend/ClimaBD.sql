drop database if exists projeto_api;

create database projeto_api
character set utf8mb4
collate utf8mb4_unicode_ci;

use projeto_api;

create table Clima(
	id int auto_increment primary key,
	cidade varchar(100),
	temperatura varchar(20),
	descricao varchar(100)
);

create table Usuario(
	id_User varchar(50) NOT NULL primary key,
    nome varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    senha varchar(255) NOT NULL,
    usa_Celsius bit NOT NULL
);

create table Historico(
	id_Hist varchar(50) NOT NULL primary key,
    user_id varchar(50) NOT NULL,
    data_consulta date NOT NULL,
    cidade varchar(50) NOT NULL,
    estado varchar(50) NOT NULL,
    pais varchar(50) NOT NULL,
    latitude decimal(9,6) NOT NULL,
    longitude decimal(9,6) NOT NULL,
    dados_consulta varchar(200)NOT NULL,
    
    constraint FK_Historico_Usuario
    foreign key (user_id)
    references Usuario(id_User)
);

create table Favoritos(
	id_Fave varchar(50) NOT NULL PRIMARY KEY,
    user_id varchar(50) NOT NULL,
    titulo varchar(200) NOT NULL, 
    cidade varchar(50) NOT NULL,
    estado varchar(50) NOT NULL,
    pais varchar(50) NOT NULL,
    latitude decimal(9,6) NOT NULL,
    longitude decimal(9,6) NOT NULL,
    
    constraint FK_Favoritos_Usuario
    foreign key (user_id)
    references Usuario(id_User)
);

create table Log(
	id_Log varchar(50) NOT NULL PRIMARY KEY,
    data_consulta date NOT NULL,
    cidade varchar(50) NOT NULL,
    estado varchar(50) NOT NULL,
    pais varchar(50) NOT NULL,
    latitude decimal(9,6) NOT NULL,
    longitude decimal(9,6) NOT NULL
);