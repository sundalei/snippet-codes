https://www.baeldung.com/executable-jar-with-maven
https://winterbe.com/posts/2018/08/29/migrate-maven-projects-to-java-11-jigsaw/
https://www.selenium.dev/documentation/en/webdriver/driver_requirements/

create database demo;
use demo;

create table storage_file (id int(11) not null auto_increment, name varchar(50) not null, last_modified datetime not null, created_at datetime not null, primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

INSERT INTO DOMN_STORAGE_FILE (OWNER_FOLDER_ID, FILE_NAME, FILE_KEY, FILE_SIZE, CONTENT_TYPE, DELETED_DATETIME, UPDATE_COUNT, LAST_CHANGE_USER_ID, LAST_CHANGE_DATETIME, CREATED_DATETIME) VALUES (?, ?, ?, ?, ?, NULL, 0, ?, GETUTCDATE(), ?)