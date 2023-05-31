CREATE DATABASE IF NOT EXISTS projectmanagementdb;
USE projectmanagementdb;


CREATE TABLE users
(
    user_id  INT PRIMARY KEY AUTO_INCREMENT,
    name     varchar(100) NOT NULL,
    email    varchar(255) NOT NULL,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(255) NOT NULL
);


CREATE TABLE projects
(
    project_id  INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    status      ENUM('todo', 'doing', 'done') NOT NULL DEFAULT 'todo',
    budget      FLOAT        NOT NULL,
    start_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date    TIMESTAMP
);


CREATE TABLE subprojects
(
    subproject_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id    INT          NOT NULL,
    name          VARCHAR(100) NOT NULL,
    description   TEXT,
    status        ENUM('todo', 'doing', 'done') NOT NULL DEFAULT 'todo',
    budget        FLOAT        NOT NULL,
    start_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date      TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects (project_id) ON DELETE CASCADE
);


CREATE TABLE tasks
(
    task_id        INT PRIMARY KEY AUTO_INCREMENT,
    subproject_id  INT          NOT NULL,
    name           VARCHAR(100) NOT NULL,
    description    TEXT,
    status         ENUM('todo', 'doing', 'done') NOT NULL DEFAULT 'todo',
    start_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date       TIMESTAMP,
    budget         FLOAT        NOT NULL,
    estimated_time FLOAT        NOT NULL,
    FOREIGN KEY (subproject_id) REFERENCES subprojects (subproject_id) ON DELETE CASCADE
);


CREATE TABLE project_members
(
    project_member_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id           INT          NOT NULL,
    hourly_rate       FLOAT        NOT NULL,
    project_id        INT          NOT NULL,
    role              VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects (project_id) ON DELETE CASCADE
);


CREATE TABLE task_members
(
    task_member_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id           INT          NOT NULL,
    task_id        INT          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (task_id) REFERENCES tasks (task_id) ON DELETE CASCADE
);


CREATE TABLE customers
(
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL
);


CREATE TABLE project_customers
(
    project_customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id         INT NOT NULL,
    project_id          INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects (project_id) ON DELETE CASCADE
);



