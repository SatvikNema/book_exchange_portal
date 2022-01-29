create table USER(
	id int not null auto_increment,
    email varchar(100),
    name varchar(100),
    username varchar(100),
    password varchar(500),
    mobile varchar(20),
    dob date,
    created datetime default now(),
    acc_verified bool default false,
    primary key(id)
);

create table COMMUNITY(
	id int not null auto_increment,
    name int,
    creator_id varchar(100),
    latitude decimal(9,5),
    longitude decimal(9,5),
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
    category varchar(100),
    description text,
    primary key(id)
);

create table USER_BOOK(
	id int not null auto_increment,
    user_id int not null,
    book_id int not null,
    user_book_category varchar(100),
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
    role_name varchar(50) not null,
    primary key(id)
);

create table USER_ROLE(
	id int not null auto_increment,
    role_id int not null default 3, --3 is role id for USER role
    user_id int not null,
    FOREIGN KEY (user_id) REFERENCES USER(id),
    FOREIGN KEY (role_id) REFERENCES ROLE(id),
    primary key(id)
);

create table POST(
	id int not null auto_increment,
    title varchar(100),
    creator_id int,
    community_id int,
    type varchar(100),
    description text,
    created datetime default now(),
    FOREIGN KEY (creator_id) REFERENCES USER(id),
    FOREIGN KEY (community_id) REFERENCES COMMUNITY(id),
    primary key(id)
);

SET FOREIGN_KEY_CHECKS = 0;
truncate table COMMUNITY;
SET FOREIGN_KEY_CHECKS = 0;

insert into USER
(email, name, username, password, mobile, dob) values
('satviknema@gmail.com', 'Satvik Nema', 'satvik', 'password', '9757257551', '1999-08-22'),
('keshav@gmail.com', 'Keshav Maplani', 'keshav', 'password', '1234567891', '1999-01-28'),
('kartik@gmail.com', 'Kartik Mittal', 'kartik', 'password', '7264825282', '1998-02-16'),
('tanya@gmail.com', 'Tanya Singh', 'tanya', 'password', '7649372546', '1998-03-09');

INSERT INTO COMMUNITY
(name, creator_id, latitude, longitude, city, state, country, num_users) values
("Kharghar Sector 10 community", 1, 19.0473, 73.0699, 'Kharghar', 'Maharashtra', 'India', 1),
("Delhi community", 3, 28.7041, 77.1025, 'Mustafabad', 'Delhi', 'India', 1);

INSERT INTO BOOK
(title, author, isbn, category, description) values
('Why We Sleep', 'Matheww Walker', 'god knows', 'Non fiction', 'A book that will completely change the perspective you have on sleeping'),
('Sapiens', 'Yuval Noah Harari', 'idk', 'Non fiction', 'A brief history of the entire human\'s evolution. A but boring but highly informative.'),
('Into Thin Air', 'Jon Krakauer', 'lmao idk', 'Non fiction', 'Description of the tragic everest story that hapened in 1997.'),
('Girl on the Train', 'Paula Hawkins', 'dk', 'Fiction', 'Mystery-thriller about a girl who is having some mental issues'),
('The Silent Patient', 'Alex Michaelides', 'idk', 'Fiction', 'Tracks one woman\'s act of violence against her husband—and of the therapist obsessed with uncovering her motive');

INSERT INTO USER_BOOK
(user_id, book_id, user_book_category) values
(1, 1, 'favorite'),
(1, 3, 'read'),
(1, 5, 'favorite'),
(1, 2, 'read'),
(2, 3, 'favorite'),
(2, 4, 'read'),
(2, 5, 'read'),
(2, 2, 'read'),
(2, 1, 'want to read'),
(4, 5, 'want to read'),
(4, 4, 'favorite'),
(4, 2, 'favorite'),
(4, 3, 'want to read');

INSERT INTO USER_COMMUNITY
(user_id, community_id, user_role) values
(1, 1, 'Admin'),
(2, 1, 'member'),
(3, 2, 'Admin'),
(4, 1, 'member');

INSERT INTO ROLE
(role_name) values
('DEV'),
('ADMIN'),
('USER');

INSERT INTO USER_ROLE
(role_id, user_id) values
(1, 1);

INSERT INTO USER_ROLE
(user_id) values (2), (3), (4);

INSERT INTO POST
(title, creator_id, community_id, type, description) values
('I WANT the book \'Never split the difference\'', 1, 1, 'EXCHANGE', 'something long text'),
('Review for the book \'why we sleep\'', 1, 1, 'REVIEW', 'hello bois gg well played lmaooo'),
('Loved \'into thin air\'', 2, 1, 'REVIEW', 'something long text 2'),
('Can someone exhange \'The Silent Patient\' with me', 4, 1, 'EXCHANGE', 'something long text. I can give \'the girl on the train\''),
('GUYS someone join plej', 3, 2, 'RANDOM', 'So lonely in here. need some crowd bois');