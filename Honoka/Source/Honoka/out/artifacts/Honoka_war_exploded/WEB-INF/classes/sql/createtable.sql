-- Function: upd_timestamp()

-- DROP FUNCTION upd_timestamp();

CREATE OR REPLACE FUNCTION upd_timestamp()
  RETURNS trigger AS
$BODY$
BEGIN
    NEW.UPDATEDATE = CURRENT_TIMESTAMP;
    NEW.UPDATECOUNT = NEW.UPDATECOUNT+1;
    RETURN NEW;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION upd_timestamp()
  OWNER TO postgres;

-- Table: tzg_datamaster

-- DROP TABLE tzg_datamaster;

CREATE TABLE tzg_datamaster
(
  projectcode character varying(4) NOT NULL,
  subprojectcode character varying(2) NOT NULL,
  full_name character varying(80) NOT NULL,
  abbreviation_name character varying(40) NOT NULL,
  insertdate timestamp without time zone DEFAULT now(),
  insertuser_id serial NOT NULL,
  insertprogramid character varying(20),
  updatedate timestamp without time zone,
  updateuser_id character varying(20),
  updateprogramid character varying(20),
  updatecount integer DEFAULT 0,
  deleteflg character(1) DEFAULT '0'::bpchar,
  CONSTRAINT tzg_datamaster_pkey PRIMARY KEY (projectcode , subprojectcode )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tzg_datamaster
  OWNER TO postgres;

-- Trigger: t_datamaster on tzg_datamaster

-- DROP TRIGGER t_datamaster ON tzg_datamaster;

CREATE TRIGGER t_datamaster
  BEFORE UPDATE
  ON tzg_datamaster
  FOR EACH ROW
  EXECUTE PROCEDURE upd_timestamp();

-- Table: tzg_contributor

-- DROP TABLE tzg_contributor;

CREATE TABLE tzg_contributor
(
  contributor_id serial NOT NULL,
  contributor_mail character varying(45) NOT NULL,
  contributor_mobile_num character varying(20) NOT NULL,
  people_id_numbrt character varying(18),
  contributor_gender character varying(2) NOT NULL DEFAULT '00'::character varying,
  contributor_qq_number character varying(13),
  contributor_weibo_acount character varying(60),
  contributor_address character varying(200),
  contributor_nick_name character varying(45),
  contributor_real_name character varying(45) NOT NULL,
  password character varying(200) NOT NULL DEFAULT '123456'::character varying,
  access character varying(2) NOT NULL DEFAULT '00'::character varying,
  birthday timestamp with time zone,
  zipcode character varying(8),
  contributor_weibo_address character varying(127),
  volunteer_type character varying(16),
  volunteer_edu_degree character varying(2),
  volunteer_edu_major character varying(45),
  volunteer_company character varying(100),
  volunteer_title character varying(100),
  volunteer_religion character varying(40),
  contributor_education_exp character varying(1000),
  contributor_work_exp character varying(1000),
  contributor_health_info character varying(400),
  contributor_is_smoke character varying(2),
  emergency_contact_name character varying(40),
  emergency_contact_tel character varying(20),
  emergency_contact_relation character varying(200),
  hobby_specialty character varying(200),
  self_introduction character varying(1000),
  insertdate timestamp without time zone DEFAULT now(),
  insertuser_id serial NOT NULL,
  insertprogramid character varying(20),
  updatedate timestamp without time zone,
  updateuser_id character varying(20),
  updateprogramid character varying(20),
  updatecount integer DEFAULT 0,
  deleteflg character(1) DEFAULT '0'::bpchar,
  volunteer_status character varying(2),
  marital_status character varying(2),
  nation character varying(64),
  blood_type character varying(64),
  native_place character varying(64),
  current_residence character varying(64),
  CONSTRAINT tzg_contributor_pkey PRIMARY KEY (contributor_id ),
  CONSTRAINT tzg_contributor_contributor_mail_key UNIQUE (contributor_mail ),
  CONSTRAINT tzg_contributor_contributor_mobile_num_key UNIQUE (contributor_mobile_num ),
  CONSTRAINT tzg_contributor_people_id_numbrt_key UNIQUE (people_id_numbrt )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tzg_contributor
  OWNER TO postgres;

-- Trigger: t_contributor on tzg_contributor

-- DROP TRIGGER t_contributor ON tzg_contributor;

CREATE TRIGGER t_contributor
  BEFORE UPDATE
  ON tzg_contributor
  FOR EACH ROW
  EXECUTE PROCEDURE upd_timestamp();
  
-- Table: tzg_visitsor_application

-- DROP TABLE tzg_visitsor_application;

CREATE TABLE tzg_visitsor_application
(
  contributor_id serial NOT NULL,
  visitsor_experience character varying(2),
  visitsor_reason character varying(1000),
  other_activities character varying(2),
  other_activities_supplement character varying(1000),
  visitsor character varying(2),
  visitsor_supplement character varying(1000),
  entourage character varying(32),
  insertdate timestamp without time zone DEFAULT now(),
  insertuser_id serial NOT NULL,
  insertprogramid character varying(20),
  updatedate timestamp without time zone,
  updateuser_id character varying(20),
  updateprogramid character varying(20),
  updatecount integer DEFAULT 0,
  deleteflg character(1) DEFAULT '0'::bpchar,
  CONSTRAINT tzg_visitsor_application_pkey PRIMARY KEY (contributor_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tzg_visitsor_application
  OWNER TO postgres;

-- Trigger: t_visitsor_application on tzg_visitsor_application

-- DROP TRIGGER t_visitsor_application ON tzg_visitsor_application;

CREATE TRIGGER t_visitsor_application
  BEFORE UPDATE
  ON tzg_visitsor_application
  FOR EACH ROW
  EXECUTE PROCEDURE upd_timestamp();
  
-- Table: tzg_volun_teach_application

-- DROP TABLE tzg_volun_teach_application;

CREATE TABLE tzg_volun_teach_application
(
  contributor_id serial NOT NULL,
  volun_teach_exp character varying(2),
  volun_teach_exp_info character varying(1000),
  teachable_subject character varying(200) NOT NULL,
  volun_teach_time_limit character varying(2) NOT NULL,
  volun_teach_time_limit_info character varying(400) NOT NULL,
  volun_teach_place_sel character varying(2),
  volun_teach_place_info character varying(400),
  situation_is_working character varying(2),
  is_working_deal character varying(400),
  economic_info character varying(400),
  volun_teach_opinion character varying(400),
  volun_teach_risk_response character varying(400),
  volun_teach_desire character varying(400),
  other_voluntes_info character varying(400),
  family_attitude character varying(400) NOT NULL,
  family_elder_contact_info character varying(400) NOT NULL,
  volun_teach_plan character varying(1000),
  health_report character varying(2),
  volun_teach_start_date_plan character varying(8),
  insertdate timestamp without time zone DEFAULT now(),
  insertuser_id serial NOT NULL,
  insertprogramid character varying(20),
  updatedate timestamp without time zone,
  updateuser_id character varying(20),
  updateprogramid character varying(20),
  updatecount integer DEFAULT 0,
  deleteflg character(1) DEFAULT '0'::bpchar,
  CONSTRAINT tzg_volun_teach_application_pkey PRIMARY KEY (contributor_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tzg_volun_teach_application
  OWNER TO postgres;

-- Trigger: t_volun_teach_application on tzg_volun_teach_application

-- DROP TRIGGER t_volun_teach_application ON tzg_volun_teach_application;

CREATE TRIGGER t_volun_teach_application
  BEFORE UPDATE
  ON tzg_volun_teach_application
  FOR EACH ROW
  EXECUTE PROCEDURE upd_timestamp();
  
  
-- Table: tzg_volunteer

-- DROP TABLE tzg_volunteer;

CREATE TABLE tzg_volunteer
(
  contributor_id serial NOT NULL,
  volunteer_reason character varying(1000),
  other_commonweal_exp character varying(2),
  other_commonweal_exp_info character varying(1000),
  other_resource_apply character varying(1000),
  insertdate timestamp without time zone DEFAULT now(),
  insertuser_id serial NOT NULL,
  insertprogramid character varying(20),
  updatedate timestamp without time zone,
  updateuser_id character varying(20),
  updateprogramid character varying(20),
  updatecount integer DEFAULT 0,
  deleteflg character(1) DEFAULT '0'::bpchar,
  CONSTRAINT tzg_volunteer_pkey PRIMARY KEY (contributor_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tzg_volunteer
  OWNER TO postgres;

-- Trigger: t_volunteer on tzg_volunteer

-- DROP TRIGGER t_volunteer ON tzg_volunteer;

CREATE TRIGGER t_volunteer
  BEFORE UPDATE
  ON tzg_volunteer
  FOR EACH ROW
  EXECUTE PROCEDURE upd_timestamp();


  -- Table: tzg_event

-- DROP TABLE tzg_event;

CREATE TABLE tzg_event
(
  event_id serial NOT NULL,
  event_name character varying(45) NOT NULL,
  time_start date NOT NULL,
  time_end date NOT NULL,
  description character varying(1000) NOT NULL,
  leader_contributor_id serial NOT NULL,
  event_type character varying(2),
  insertdate timestamp without time zone DEFAULT now(),
  insertuser_id integer,
  insertprogramid character varying(20),
  updatedate timestamp without time zone,
  updateuser_id character varying(20),
  updateprogramid character varying(20),
  updatecount integer DEFAULT 0,
  deleteflg character(1) DEFAULT '0'::bpchar,
  CONSTRAINT tzg_event_pkey PRIMARY KEY (event_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tzg_event
  OWNER TO postgres;

-- Trigger: t_event on tzg_event

-- DROP TRIGGER t_event ON tzg_event;

CREATE TRIGGER t_event
  BEFORE UPDATE
  ON tzg_event
  FOR EACH ROW
  EXECUTE PROCEDURE upd_timestamp();
  
-- Table: tzg_event_partake

-- DROP TABLE tzg_event_partake;

CREATE TABLE tzg_event_partake
(
  event_id serial NOT NULL,
  contributor_id serial NOT NULL,
  partake_status character varying(2) NOT NULL,
  contribution character varying(200) NOT NULL,
  insertdate timestamp without time zone DEFAULT now(),
  insertuser_id serial NOT NULL,
  insertprogramid character varying(20),
  updatedate timestamp without time zone,
  updateuser_id character varying(20),
  updateprogramid character varying(20),
  updatecount integer DEFAULT 0,
  deleteflg character(1) DEFAULT '0'::bpchar,
  CONSTRAINT tzg_event_partake_pkey PRIMARY KEY (event_id , contributor_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tzg_event_partake
  OWNER TO postgres;

-- Trigger: t_event_partake on tzg_event_partake

-- DROP TRIGGER t_event_partake ON tzg_event_partake;

CREATE TRIGGER t_event_partake
  BEFORE UPDATE
  ON tzg_event_partake
  FOR EACH ROW
  EXECUTE PROCEDURE upd_timestamp();


  

  
 