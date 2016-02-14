-- ----------------------------
-- Sequence structure for API_KEY_RECORD_ID_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."API_KEY_RECORD_ID_seq";
CREATE SEQUENCE "public"."API_KEY_RECORD_ID_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."API_KEY_RECORD_ID_seq"', 1, true);

-- ----------------------------
-- Sequence structure for COM_INFO_RECORD_ID_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."COM_INFO_RECORD_ID_seq";
CREATE SEQUENCE "public"."COM_INFO_RECORD_ID_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."COM_INFO_RECORD_ID_seq"', 1, true);

-- ----------------------------
-- Sequence structure for DEPT_INFO_RECORD_ID_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."DEPT_INFO_RECORD_ID_seq";
CREATE SEQUENCE "public"."DEPT_INFO_RECORD_ID_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."DEPT_INFO_RECORD_ID_seq"', 1, true);

-- ----------------------------
-- Sequence structure for LEVEL_INFO_RECORD_ID_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."LEVEL_INFO_RECORD_ID_seq";
CREATE SEQUENCE "public"."LEVEL_INFO_RECORD_ID_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."LEVEL_INFO_RECORD_ID_seq"', 1, true);

-- ----------------------------
-- Sequence structure for METRO_INFO_RECORD_ID_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."METRO_INFO_RECORD_ID_seq";
CREATE SEQUENCE "public"."METRO_INFO_RECORD_ID_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."METRO_INFO_RECORD_ID_seq"', 1, true);

-- ----------------------------
-- Sequence structure for POINT_INFO_RECORD_ID_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."POINT_INFO_RECORD_ID_seq";
CREATE SEQUENCE "public"."POINT_INFO_RECORD_ID_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."POINT_INFO_RECORD_ID_seq"', 1, true);

-- ----------------------------
-- Sequence structure for STAFF_INFO_RECORD_ID_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."STAFF_INFO_RECORD_ID_seq";
CREATE SEQUENCE "public"."STAFF_INFO_RECORD_ID_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."STAFF_INFO_RECORD_ID_seq"', 1, true);

-- ----------------------------
-- Sequence structure for test_record_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."test_record_id_seq";
CREATE SEQUENCE "public"."test_record_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."test_record_id_seq"', 1, true);

-- ----------------------------
-- Table structure for API_KEY
-- ----------------------------
DROP TABLE IF EXISTS "public"."API_KEY";
CREATE TABLE "public"."API_KEY" (
"RECORD_ID" int4 DEFAULT nextval('"API_KEY_RECORD_ID_seq"'::regclass) NOT NULL,
"KEY" varchar COLLATE "default",
"PROVIDER" varchar COLLATE "default",
"AMOUNT" int4 DEFAULT 0,
"LAST_CALL" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."API_KEY" IS 'API KEY 表';
COMMENT ON COLUMN "public"."API_KEY"."RECORD_ID" IS '记录 ID';
COMMENT ON COLUMN "public"."API_KEY"."KEY" IS 'API KEY';
COMMENT ON COLUMN "public"."API_KEY"."PROVIDER" IS 'API 提供方';

-- ----------------------------
-- Table structure for COM_INFO
-- ----------------------------
DROP TABLE IF EXISTS "public"."COM_INFO";
CREATE TABLE "public"."COM_INFO" (
"RECORD_ID" int4 DEFAULT nextval('"COM_INFO_RECORD_ID_seq"'::regclass) NOT NULL,
"COM_ID" varchar COLLATE "default" NOT NULL,
"COM_NAME" varchar COLLATE "default",
"COM_ADDR" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."COM_INFO" IS '公司信息表';
COMMENT ON COLUMN "public"."COM_INFO"."RECORD_ID" IS '记录 ID';
COMMENT ON COLUMN "public"."COM_INFO"."COM_ID" IS '公司 ID';
COMMENT ON COLUMN "public"."COM_INFO"."COM_NAME" IS '公司名称';
COMMENT ON COLUMN "public"."COM_INFO"."COM_ADDR" IS '公司地址';

-- ----------------------------
-- Table structure for DEPT_INFO
-- ----------------------------
DROP TABLE IF EXISTS "public"."DEPT_INFO";
CREATE TABLE "public"."DEPT_INFO" (
"RECORD_ID" int4 DEFAULT nextval('"DEPT_INFO_RECORD_ID_seq"'::regclass) NOT NULL,
"DEPT_ID" varchar COLLATE "default" NOT NULL,
"DEPT_NAME" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."DEPT_INFO" IS '部门信息表';
COMMENT ON COLUMN "public"."DEPT_INFO"."RECORD_ID" IS '记录 ID';
COMMENT ON COLUMN "public"."DEPT_INFO"."DEPT_ID" IS '部门 ID';
COMMENT ON COLUMN "public"."DEPT_INFO"."DEPT_NAME" IS '部门名字';

-- ----------------------------
-- Table structure for LEVEL_INFO
-- ----------------------------
DROP TABLE IF EXISTS "public"."LEVEL_INFO";
CREATE TABLE "public"."LEVEL_INFO" (
"RECORD_ID" int4 DEFAULT nextval('"LEVEL_INFO_RECORD_ID_seq"'::regclass) NOT NULL,
"LEVEL_ID" varchar COLLATE "default" NOT NULL,
"LEVEL_NAME" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."LEVEL_INFO" IS '部门信息表';
COMMENT ON COLUMN "public"."LEVEL_INFO"."RECORD_ID" IS '记录 ID';
COMMENT ON COLUMN "public"."LEVEL_INFO"."LEVEL_ID" IS '职称 ID';
COMMENT ON COLUMN "public"."LEVEL_INFO"."LEVEL_NAME" IS '职称名称';

-- ----------------------------
-- Table structure for METRO_INFO
-- ----------------------------
DROP TABLE IF EXISTS "public"."METRO_INFO";
CREATE TABLE "public"."METRO_INFO" (
"RECORD_ID" int4 DEFAULT nextval('"METRO_INFO_RECORD_ID_seq"'::regclass) NOT NULL,
"LINE_NAME" varchar(32) COLLATE "default" NOT NULL,
"STA_ID" varchar(32) COLLATE "default" NOT NULL,
"STA_NAME" varchar COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for POINT_INFO
-- ----------------------------
DROP TABLE IF EXISTS "public"."POINT_INFO";
CREATE TABLE "public"."POINT_INFO" (
"RECORD_ID" int4 DEFAULT nextval('"POINT_INFO_RECORD_ID_seq"'::regclass) NOT NULL,
"KEY_ID" varchar COLLATE "default" NOT NULL,
"BAIDU_RECORD_LNG" float8,
"BAIDU_RECORD_LAT" float8,
"AMAP_RECORD_LNG" float8,
"AMAP_RECORD_LAT" float8,
"DELETE_FLAG" int2 DEFAULT 0,
"RECORD_TYPE" varchar COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."POINT_INFO" IS '坐标记录表';
COMMENT ON COLUMN "public"."POINT_INFO"."RECORD_ID" IS '记录 ID';
COMMENT ON COLUMN "public"."POINT_INFO"."KEY_ID" IS '关键字 ID';
COMMENT ON COLUMN "public"."POINT_INFO"."BAIDU_RECORD_LNG" IS '百度记录经度';
COMMENT ON COLUMN "public"."POINT_INFO"."BAIDU_RECORD_LAT" IS '百度记录纬度';
COMMENT ON COLUMN "public"."POINT_INFO"."AMAP_RECORD_LNG" IS '高德记录经度';
COMMENT ON COLUMN "public"."POINT_INFO"."AMAP_RECORD_LAT" IS '高德记录纬度';

-- ----------------------------
-- Table structure for STAFF_INFO
-- ----------------------------
DROP TABLE IF EXISTS "public"."STAFF_INFO";
CREATE TABLE "public"."STAFF_INFO" (
"RECORD_ID" int4 DEFAULT nextval('"STAFF_INFO_RECORD_ID_seq"'::regclass) NOT NULL,
"STAFF_COM_ID" varchar COLLATE "default",
"STAFF_DEPT_ID" varchar COLLATE "default",
"STAFF_LEVEL_ID" varchar COLLATE "default",
"STAFF_ID" varchar COLLATE "default" NOT NULL,
"STAFF_NAME" varchar COLLATE "default",
"STAFF_TEL" varchar COLLATE "default",
"STAFF_ADDR" varchar COLLATE "default",
"DELETE_FLAG" int2 DEFAULT 0
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."STAFF_INFO"."RECORD_ID" IS '记录 ID';
COMMENT ON COLUMN "public"."STAFF_INFO"."STAFF_COM_ID" IS '员工所属公司 ID';
COMMENT ON COLUMN "public"."STAFF_INFO"."STAFF_DEPT_ID" IS '员工所属部门 ID';
COMMENT ON COLUMN "public"."STAFF_INFO"."STAFF_LEVEL_ID" IS '员工所属职称 ID';
COMMENT ON COLUMN "public"."STAFF_INFO"."STAFF_ID" IS '员工工号';
COMMENT ON COLUMN "public"."STAFF_INFO"."STAFF_NAME" IS '员工姓名';
COMMENT ON COLUMN "public"."STAFF_INFO"."STAFF_TEL" IS '员工联系电话';
COMMENT ON COLUMN "public"."STAFF_INFO"."STAFF_ADDR" IS '员工住址';
COMMENT ON COLUMN "public"."STAFF_INFO"."DELETE_FLAG" IS '删除标志';

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS "public"."test";
CREATE TABLE "public"."test" (
"record_id" int4 DEFAULT nextval('test_record_id_seq'::regclass) NOT NULL,
"record_content" varchar(128) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."API_KEY_RECORD_ID_seq" OWNED BY "API_KEY"."RECORD_ID";
ALTER SEQUENCE "public"."COM_INFO_RECORD_ID_seq" OWNED BY "COM_INFO"."RECORD_ID";
ALTER SEQUENCE "public"."DEPT_INFO_RECORD_ID_seq" OWNED BY "DEPT_INFO"."RECORD_ID";
ALTER SEQUENCE "public"."LEVEL_INFO_RECORD_ID_seq" OWNED BY "LEVEL_INFO"."RECORD_ID";
ALTER SEQUENCE "public"."METRO_INFO_RECORD_ID_seq" OWNED BY "METRO_INFO"."RECORD_ID";
ALTER SEQUENCE "public"."POINT_INFO_RECORD_ID_seq" OWNED BY "POINT_INFO"."RECORD_ID";
ALTER SEQUENCE "public"."STAFF_INFO_RECORD_ID_seq" OWNED BY "STAFF_INFO"."RECORD_ID";
ALTER SEQUENCE "public"."test_record_id_seq" OWNED BY "test"."record_id";

-- ----------------------------
-- Primary Key structure for table API_KEY
-- ----------------------------
ALTER TABLE "public"."API_KEY" ADD PRIMARY KEY ("RECORD_ID");

-- ----------------------------
-- Primary Key structure for table COM_INFO
-- ----------------------------
ALTER TABLE "public"."COM_INFO" ADD PRIMARY KEY ("RECORD_ID", "COM_ID");

-- ----------------------------
-- Primary Key structure for table DEPT_INFO
-- ----------------------------
ALTER TABLE "public"."DEPT_INFO" ADD PRIMARY KEY ("RECORD_ID", "DEPT_ID");

-- ----------------------------
-- Primary Key structure for table LEVEL_INFO
-- ----------------------------
ALTER TABLE "public"."LEVEL_INFO" ADD PRIMARY KEY ("RECORD_ID", "LEVEL_ID");

-- ----------------------------
-- Primary Key structure for table METRO_INFO
-- ----------------------------
ALTER TABLE "public"."METRO_INFO" ADD PRIMARY KEY ("RECORD_ID", "STA_ID");

-- ----------------------------
-- Primary Key structure for table POINT_INFO
-- ----------------------------
ALTER TABLE "public"."POINT_INFO" ADD PRIMARY KEY ("RECORD_ID", "KEY_ID");

-- ----------------------------
-- Primary Key structure for table STAFF_INFO
-- ----------------------------
ALTER TABLE "public"."STAFF_INFO" ADD PRIMARY KEY ("RECORD_ID", "STAFF_ID");

-- ----------------------------
-- Primary Key structure for table test
-- ----------------------------
ALTER TABLE "public"."test" ADD PRIMARY KEY ("record_id");
