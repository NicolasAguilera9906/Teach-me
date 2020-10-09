DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS messages;

CREATE TABLE IF NOT EXISTS users (
   id serial PRIMARY KEY,
   email VARCHAR(255) UNIQUE NOT NULL,
   first_name VARCHAR(255)  NOT NULL,
   last_name VARCHAR(255)  NOT NULL,
   password VARCHAR(255)  NOT NULL,
   description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS classes (
   id serial PRIMARY KEY,
   amount_of_students INTEGER(50) NOT NULL,
   capacity INTEGER(50) NOT NULL,
   date_of_end TIMESTAMP NOT NULL,
   date_of_init TIMESTAMP NOT NULL,
   description VARCHAR(255) UNIQUE NOT NULL,
   name VARCHAR(255) NOT NULL,
   professor INTEGER(100)NOT NULL
);

CREATE TABLE IF NOT EXISTS requests (
   class INTEGER(100) NOT NULL,
   student INTEGER(100) NOT NULL,
   accepted BOOLEAN,
   primary key(class,student)
);

CREATE TABLE IF NOT EXISTS sessions (
   id serial PRIMARY KEY,
   duration INTEGER NOT NULL,
   class INTEGER(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS messages (
   id serial PRIMARY KEY,
   session INTEGER(100) NOT NULL,
   content VARCHAR(255) NOT NULL,
   date_of_message TIMESTAMP NOT NULL
);

ALTER TABLE classes ADD CONSTRAINT FK_PROFESSOR
  foreign key (professor)
  references users (id);

ALTER TABLE requests ADD CONSTRAINT FK_REQUEST_STUDENT
  foreign key (student)
  references users (id);

ALTER TABLE requests ADD CONSTRAINT FK_REQUEST_CLASS
  foreign key (class) references classes (id)
  on delete cascade;

ALTER TABLE sessions ADD CONSTRAINT FK_SESSION_CLASS
  foreign key (class) references classes (id)
  on delete cascade;

ALTER TABLE messages ADD CONSTRAINT FK_MESSAGE_SESSION
  foreign key (session) references sessions (id)
  on delete cascade;



