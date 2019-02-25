CREATE DATABASE catchwork CHARACTER SET utf8 COLLATE utf8_general_ci;

create table job(
job_id int primary key auto_increment,
job_name VARCHAR(50) not null,
salary VARCHAR(50) not null,
work_year varchar(50) not null,
edu_bg varchar(50) not null,
loc varchar(50) not null,
company_name VARCHAR(50) not null,
company_full_name VARCHAR(50),
company_type varchar(50),
financing_stage varchar(50),
company_size varchar(50),
release_date VARCHAR(50) not null,
grab_date varchar(50) not null,
job_desc VARCHAR(1000),
origin_site VARCHAR(50) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table schedule_info(
info_id int primary key auto_increment,
work_year varchar(50) not null,
edu_bg varchar(50) not null,
loc varchar(50) not null,
query_word varchar(50),
work_type varchar(50),
company_type varchar(50),
salary varchar(50),
financing_stage varchar(50),
company_size varchar(50),
release_date varchar(50),
grab_pages int(10) default 10
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table schedule_info add column persist int(2) not null default 1;

create table schedule_info_sites(
info_id int,
site_name varchar(50),
site_url varchar(100),
grab_num int(10)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
