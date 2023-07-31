CREATE SCHEMA IF NOT EXISTS intern_flow;

-- Team table
CREATE TABLE IF NOT EXISTS intern_flow.Team (
    team_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(50) NOT NULL,
    team_leader_id BIGINT
    );

-- Student table
CREATE TABLE IF NOT EXISTS intern_flow.Student (
    student_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_id BIGINT REFERENCES intern_flow.Team(team_id),
    first_name VARCHAR(70) NOT NULL,
    last_name VARCHAR(70) NOT NULL,
    email VARCHAR(50) NOT NULL
    );

-- Mentor table
CREATE TABLE IF NOT EXISTS intern_flow.Mentor (
    mentor_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(70) NOT NULL,
    last_name VARCHAR(70) NOT NULL,
    email VARCHAR(50) NOT NULL
    );

-- Activities table
CREATE TABLE IF NOT EXISTS intern_flow.Activities (
    activity_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    activity_name VARCHAR(50) NOT NULL,
    creation_date TIMESTAMP DEFAULT current_timestamp,
    activity_date DATE
    );

-- Attendance table
CREATE TABLE IF NOT EXISTS intern_flow.Attendance (
    attendance_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    mentor_id BIGINT REFERENCES intern_flow.Mentor(mentor_id),
    activity_id BIGINT REFERENCES intern_flow.Activities(activity_id),
    student_id BIGINT REFERENCES intern_flow.Student(student_id),
    status VARCHAR(10) NOT NULL
    );

-- Grades table
CREATE TABLE IF NOT EXISTS intern_flow.Grades (
    grade_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    mentor_id BIGINT REFERENCES intern_flow.Mentor(mentor_id),
    activity_id BIGINT REFERENCES intern_flow.Activities(activity_id),
    student_id BIGINT REFERENCES intern_flow.Student(student_id),
    grade_value INT NOT NULL,
    comment VARCHAR(255)
    );


ALTER TABLE intern_flow.Team
DROP CONSTRAINT IF EXISTS fk_team_leader;

ALTER TABLE intern_flow.Team
    ADD CONSTRAINT fk_team_leader
        FOREIGN KEY (team_leader_id)
            REFERENCES intern_flow.Student(student_id);
