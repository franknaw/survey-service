----------------------------------
----------------------------------
-- Data schema for survey forms --
----------------------------------
----------------------------------

CREATE SEQUENCE form_sequence
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE form (
  id int PRIMARY KEY NOT NULL,
  name character varying(255) UNIQUE,
  organization character varying(255),
  comment character varying(255)
);

CREATE UNIQUE INDEX idx_form_name ON form USING btree (name) WHERE name IS NOT NULL;
CREATE UNIQUE INDEX idx_form_organization ON form USING btree (organization) WHERE organization IS NOT NULL;


CREATE TABLE form_questions (
  form_id int  NOT NULL,
  question_id int  NOT NULL,
  CONSTRAINT pk__form_questions PRIMARY KEY (form_id, question_id),
  CONSTRAINT fk__form_questions__form FOREIGN KEY (form_id) REFERENCES form (id) ON DELETE CASCADE,
  CONSTRAINT fk__form_questions__question FOREIGN KEY (question_id) REFERENCES form_question (id) ON DELETE CASCADE
);

CREATE TABLE form_question (
  id int PRIMARY KEY NOT NULL,
  question character varying(255) UNIQUE
);

CREATE TABLE form_instructors (
  form_id int  NOT NULL,
  instructor_id int  NOT NULL,
  CONSTRAINT pk__form_questions PRIMARY KEY (form_id, instructor_id),
  CONSTRAINT fk__form_instructors__form FOREIGN KEY (form_id) REFERENCES form (id) ON DELETE CASCADE,
  CONSTRAINT fk__form_instructors__question FOREIGN KEY (instructor_id) REFERENCES form_instructor (id) ON DELETE CASCADE
);

CREATE TABLE form_instructor (
  id int PRIMARY KEY NOT NULL,
  instructor character varying(255) UNIQUE
);



----------------------------------
----------------------------------
-- Data schema for survey entry --
----------------------------------
----------------------------------

CREATE SEQUENCE entry_sequence
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE survey_entry (
  id bigint PRIMARY KEY NOT NULL,
  user character varying(255) NOT NULL,
  organization character varying(255) NOT NULL,
  entry_date timestamp without time zone
);

CREATE TABLE survey_ratings (
  survey_id bigint NOT NULL,
  form_id bigint NOT NULL,
  question character varying(255) NOT NULL,
  rating integer NOT NULL,
  CONSTRAINT pk__survey_comment PRIMARY KEY (survey_id, form_id, question),
  CONSTRAINT fk__survey_comment__survey FOREIGN KEY (survey_id) REFERENCES survey_entry (id) ON DELETE CASCADE
);

CREATE TABLE survey_instructors (
  survey_id bigint NOT NULL,
  form_id bigint NOT NULL,
  instructor character varying(255) NOT NULL,
  CONSTRAINT pk__survey_comment PRIMARY KEY (survey_id, form_id, instructor),
  CONSTRAINT fk__survey_comment__survey FOREIGN KEY (survey_id) REFERENCES survey_entry (id) ON DELETE CASCADE
);

CREATE TABLE survey_comment (
  survey_id bigint NOT NULL,
  form_id bigint NOT NULL,
  question character varying(255) NOT NULL,
  comment text NOT NULL
  CONSTRAINT pk__survey_comment PRIMARY KEY (survey_id, form_id, question),
  CONSTRAINT fk__survey_comment__survey FOREIGN KEY (survey_id) REFERENCES survey_entry (id) ON DELETE CASCADE
);