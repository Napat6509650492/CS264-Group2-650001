CREATE TABLE normalForm(
    id BIGINT IDENTITY PRIMARY KEY NOT NULL,
	topic NVARCHAR(max) NOT NULL,
	"too" NVARCHAR(max) NOT NULL,
    "date" DATE NOT NULL,
	title NVARCHAR(10) NOT NULL,
    studentFirstName NVARCHAR(max) NOT NULL,
    studentLastName NVARCHAR(max) NOT NULL,
    studentId NVARCHAR(max) NOT NULL,
    studentYear NVARCHAR(max) NOT NULL,
    studyField NVARCHAR(max) NOT NULL,
    advisor NVARCHAR(max) NOT NULL,
    addressNumber NVARCHAR(max) NOT NULL,
    moo NVARCHAR(max) NOT NULL,
    tumbol NVARCHAR(max) NOT NULL,
    amphur NVARCHAR(max) NOT NULL,
    province NVARCHAR(max) NOT NULL,
    postalCode NVARCHAR(max) NOT NULL,
    mobilePhone NVARCHAR(max) NOT NULL,
    phone NVARCHAR(max) NOT NULL,
	objective NVARCHAR(max) NOT NULL,
    cause NVARCHAR(max) NOT NULL,
	"state" INT NOT NULL,
	"message" NVARCHAR(max) NOT NULL
)

CREATE TABLE addDropForm(
    id BIGINT IDENTITY PRIMARY KEY NOT NULL,
	topic NVARCHAR(max) NOT NULL,
    "date" DATE NOT NULL,
	too NVARCHAR(max) NOT NULL,
	"addordrop" NVARCHAR(max) NOT NULL,
	title NVARCHAR(10) NOT NULL,
    studentFirstName NVARCHAR(max) NOT NULL,
    studentLastName NVARCHAR(max) NOT NULL,
    studentId NVARCHAR(max) NOT NULL,
    studentYear NVARCHAR(max) NOT NULL,
    studyField NVARCHAR(max) NOT NULL,
    advisor NVARCHAR(max) NOT NULL,
    addressNumber NVARCHAR(max) NOT NULL,
    moo NVARCHAR(max) NOT NULL,
    tumbol NVARCHAR(max) NOT NULL,
    amphur NVARCHAR(max) NOT NULL,
    province NVARCHAR(max) NOT NULL,
    postalCode NVARCHAR(max) NOT NULL,
    mobilePhone NVARCHAR(max) NOT NULL,
    phone NVARCHAR(max) NOT NULL,
    cause NVARCHAR(max) NOT NULL,
	subject NVARCHAR(max) NOT NULL,
	"state" INT,
	"message" NVARCHAR(max)
)

CREATE TABLE normalFiles (
    form_id BIGINT,
    file_data VARBINARY(max),
    file_name NVARCHAR(255),
    file_type NVARCHAR(50),
	file_size INT
    FOREIGN KEY (form_id) REFERENCES normalForm(id),
);

CREATE TABLE addDropFiles (
    form_id BIGINT,
    file_data VARBINARY(max),
    file_name NVARCHAR(255),
    file_type NVARCHAR(50),
	file_size INT
    FOREIGN KEY (form_id) REFERENCES addDropForm(id),
);