create table REQUIREMET
(
  id bigint not null auto_increment
    primary key,
  function_id bigint not null,
  category varchar(1000) not null,
  category_value varchar(1000) not null,
  constraint requirements_id_uindex
  unique (id)
)
;

create index REQUIREMET_WINAPI_FUNCTION_ID_fk
  on REQUIREMET (function_id)
;

create table WINAPI_FUNCTION
(
  ID bigint not null auto_increment
    primary key,
  NAME char(30) not null,
  DESCRIPTION varchar(1000) not null,
  CLASS_ID bigint null,
  syntax varchar(1000) not null,
  return_type varchar(256) not null,
  return_type_description varchar(1000) not null,
  constraint FUNCTIONX
  unique (ID)
)
;

create index CLASS_ID
  on WINAPI_FUNCTION (CLASS_ID)
;

alter table REQUIREMET
  add constraint REQUIREMET_WINAPI_FUNCTION_ID_fk
foreign key (function_id) references WINAPI_FUNCTION (ID)
  on update cascade on delete cascade
;

create table WINAPI_PARAMETER
(
  ID bigint not null auto_increment
    primary key,
  FUNCTION_ID bigint null,
  first_defintion char(30) not null,
  type_defintion char(30) not null,
  description varchar(1000) not null,
  constraint PARAMX
  unique (ID),
  constraint WINAPI_PARAMETER_ibfk_1
  foreign key (FUNCTION_ID) references WINAPI_FUNCTION (ID)
    on delete cascade
)
;

create index FUNCTION_ID
  on WINAPI_PARAMETER (FUNCTION_ID)
;

create table WINAPI_USER_ELEMENT
(
  ID bigint not null auto_increment
    primary key,
  NAME char(30) not null,
  DESCRIPTION varchar(1000) not null,
  constraint CLASSX
  unique (ID)
)
;

alter table WINAPI_FUNCTION
  add constraint WINAPI_FUNCTION_ibfk_1
foreign key (CLASS_ID) references WINAPI_USER_ELEMENT (ID)
  on delete cascade
;

