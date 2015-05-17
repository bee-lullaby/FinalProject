DROP DATABASE IF EXISTS academic_portal;

CREATE DATABASE academic_portal;
USE academic_portal;
DROP TABLE IF EXISTS time_table;
DROP TABLE IF EXISTS teacher_busy_semester;
DROP TABLE IF EXISTS class_course_teacher_semester;
DROP TABLE IF EXISTS class_course_semester;
DROP TABLE IF EXISTS teacher_course_semester;
DROP TABLE IF EXISTS teacher_semester;
DROP TABLE IF EXISTS class_semester;
DROP TABLE IF EXISTS course_semester;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS semesters;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS teachers;

CREATE TABLE teachers (
	teacher_id INT NOT NULL AUTO_INCREMENT,
	account VARCHAR(20) NOT NULL,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(50) NOT NULL,
	account_type VARCHAR(20) NOT NULL,
	PRIMARY KEY (teacher_id),
	UNIQUE KEY (account),
	UNIQUE KEY (email)
);

CREATE TABLE courses (
	course_id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	name VARCHAR(100) NOT NULL,
	slots INT NOT NULL,	
	PRIMARY KEY (course_id),
	UNIQUE KEY (code)
);

CREATE TABLE rooms (
	room_id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	room_type VARCHAR(100) NOT NULL,
	capacity INT NOT NULL,
	PRIMARY KEY (room_id),
	UNIQUE KEY (code)
);

CREATE TABLE classes (
	class_id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	PRIMARY KEY (class_id),
	UNIQUE KEY (code)
);

CREATE TABLE semesters (
	semester_id INT NOT NULL AUTO_INCREMENT, 
	code VARCHAR(10) NOT NULL,
	name VARCHAR(100) NOT NULL,
	semester_year INT NOT NULL,
	start_date DATE NOT NULL, 								
	end_date DATE NOT NULL,   									
	PRIMARY KEY (semester_id),
	UNIQUE KEY (code)
);

CREATE TABLE course_semester (
	course_semester_id INT NOT NULL AUTO_INCREMENT,
	course_id INT NOT NULL,
	semester_id INT NOT NULL,
	PRIMARY KEY (course_semester_id),
	UNIQUE KEY (course_id, semester_id),
	FOREIGN KEY (course_id) REFERENCES courses(course_id),
	FOREIGN KEY (semester_id) REFERENCES semesters(semester_id)
);

CREATE TABLE class_semester (
	class_semester_id INT NOT NULL AUTO_INCREMENT,
	class_id INT NOT NULL,
	semester_id INT NOT NULL,
	students INT NOT NULL,
	PRIMARY KEY (class_semester_id),
	UNIQUE KEY (class_id, semester_id),
	FOREIGN KEY (class_id) REFERENCES classes(class_id),
	FOREIGN KEY (semester_id) REFERENCES semesters(semester_id)
);

CREATE TABLE teacher_semester (
	teacher_semester_id INT NOT NULL AUTO_INCREMENT,
	teacher_id INT NOT NULL,
	semester_id INT NOT NULL,
	PRIMARY KEY (teacher_semester_id),
	UNIQUE KEY (teacher_id, semester_id),
	FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id),
	FOREIGN KEY (semester_id) REFERENCES semesters(semester_id)
);
CREATE TABLE teacher_course_semester (  		 /* hoc ky K, mon hoc A co cac giao vien A1, A2 */			
	course_teacher_semester_id INT NOT NULL AUTO_INCREMENT,
	teacher_semester_id INT NOT NULL,
	course_semester_id INT NOT NULL,	
	PRIMARY KEY (course_teacher_semester_id),
	FOREIGN KEY (course_semester_id) REFERENCES course_semester(course_semester_id),
	FOREIGN KEY (teacher_semester_id) REFERENCES teacher_semester(teacher_semester_id)
);

CREATE TABLE class_course_semester (			/* hoc ky K, lop L co mon hoc A */
	class_course_semester_id INT NOT NULL AUTO_INCREMENT,
	class_semester_id INT NOT NULL,
	course_semester_id INT NOT NULL,
	PRIMARY KEY (class_course_semester_id),
	FOREIGN KEY (class_semester_id) REFERENCES class_semester(class_semester_id),
	FOREIGN KEY (course_semester_id) REFERENCES course_semester(course_semester_id)
);

CREATE TABLE class_course_teacher_semester (			/* hoc ky K, lop L co mon hoc A */
	class_course_semester_id INT NOT NULL AUTO_INCREMENT,
	class_semester_id INT NOT NULL,
	course_semester_id INT NOT NULL,
	teacher_semester_id INT NOT NULL,
	PRIMARY KEY (class_course_semester_id),
	
	FOREIGN KEY (class_semester_id) REFERENCES class_semester(class_semester_id),
	FOREIGN KEY (course_semester_id) REFERENCES course_semester(course_semester_id),
	FOREIGN KEY (teacher_semester_id) REFERENCES teacher_semester(teacher_semester_id)
);


CREATE TABLE teacher_busy_semester (
	teacher_busy_semester_id INT NOT NULL AUTO_INCREMENT,
	teacher_semester_id INT NOT NULL,
	date_busy DATE NOT NULL,
	slot_busy INT NOT NULL,
	PRIMARY KEY (teacher_busy_semester_id),
	UNIQUE KEY (teacher_semester_id),
	FOREIGN KEY (teacher_semester_id) REFERENCES teacher_semester(teacher_semester_id)
);

CREATE TABLE time_table (
	time_table_id INT NOT NULL AUTO_INCREMENT,
	class_course_semester_id INT NOT NULL,
	date DATE NOT NULL,
	slot INT NOT NULL,
	room_id INT NOT NULL,
	PRIMARY KEY (time_table_id),
	UNIQUE KEY (date, slot, room_id),
	UNIQUE KEY (date, slot, class_course_semester_id),
	FOREIGN KEY (class_course_semester_id) REFERENCES class_course_semester(class_course_semester_id),
	FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);