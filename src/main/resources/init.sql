create table USER(
	id int not null auto_increment,
    email varchar(100),
    name varchar(100),
    password varchar(500),
    mobile varchar(20),
    dob date,
    created datetime default now(),
    acc_verified bool default false,
    primary key(id)
);


create table COMMUNITY(
	id int not null auto_increment,
    name varchar(100),
    creator_id varchar(100),
    latitude decimal,
    longitude decimal,
    city varchar(100),
    state varchar(100),
    country varchar(100),
    num_users int,
    primary key(id)
);

create table BOOK(
	id int not null auto_increment,
    title varchar(100),
    author varchar(100),
    isbn varchar(500),
    primary key(id)
);

create table USER_BOOK(
	id int not null auto_increment,
    user_id int not null,
    book_id int not null,
    FOREIGN KEY (user_id) REFERENCES USER(id),
    FOREIGN KEY (book_id) REFERENCES BOOK(id),
    primary key(id)
);

create table USER_COMMUNITY(
	id int not null auto_increment,
    user_id int not null,
    community_id int not null,
    user_role varchar(20),
    FOREIGN KEY (user_id) REFERENCES USER(id),
    FOREIGN KEY (community_id) REFERENCES COMMUNITY(id),
    primary key(id)
);

create table ROLE(
	id int not null auto_increment,
    role_name int not null,
    primary key(id)
);

create table USER_ROLE(
	id int not null auto_increment,
    role_id int not null,
    user_id int not null,
    FOREIGN KEY (user_id) REFERENCES USER(id),
    FOREIGN KEY (role_id) REFERENCES ROLE(id),
    primary key(id)
);


