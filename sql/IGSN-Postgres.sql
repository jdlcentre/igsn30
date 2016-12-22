--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.4.0
-- Started on 2016-12-16 13:31:46

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 10 (class 2615 OID 73271)
-- Name: version30; Type: SCHEMA; Schema: -; Owner: igsn
--

CREATE SCHEMA version30;


ALTER SCHEMA version30 OWNER TO igsn;

SET search_path = version30, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 282 (class 1259 OID 73284)
-- Name: alternate_identifiers; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE alternate_identifiers (
    resource_identifier character varying,
    alternate_identifier character varying,
    alternate_identifiers_id integer NOT NULL
);


ALTER TABLE alternate_identifiers OWNER TO igsn;

--
-- TOC entry 285 (class 1259 OID 73334)
-- Name: classifications; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE classifications (
    classifications_id integer NOT NULL,
    resource_identifier character varying,
    classification_uri character varying
);


ALTER TABLE classifications OWNER TO igsn;

--
-- TOC entry 288 (class 1259 OID 73373)
-- Name: collectors; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE collectors (
    collector_id integer NOT NULL,
    collector_name character varying,
    collector_identifier character varying,
    collector_identifier_type character varying,
    resource_identifier character varying
);


ALTER TABLE collectors OWNER TO igsn;

--
-- TOC entry 290 (class 1259 OID 73399)
-- Name: contributors; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE contributors (
    contributor_id integer NOT NULL,
    contributor_type character varying,
    contributor_name character varying,
    contributor_identifier character varying,
    contributor_identifier_type character varying,
    resource_identifier character varying
);


ALTER TABLE contributors OWNER TO igsn;

--
-- TOC entry 289 (class 1259 OID 73386)
-- Name: curation_details; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE curation_details (
    curation_id integer NOT NULL,
    curator character varying,
    curation_date timestamp without time zone,
    curation_location character varying,
    institution_uri character varying,
    resource_identifier character varying
);


ALTER TABLE curation_details OWNER TO igsn;

--
-- TOC entry 287 (class 1259 OID 73360)
-- Name: location; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE location (
    location_id integer NOT NULL,
    locality character varying,
    locality_uri character varying,
    srid character varying,
    vertical_datum character varying,
    geometry_uri character varying
);


ALTER TABLE location OWNER TO igsn;

--
-- TOC entry 292 (class 1259 OID 73425)
-- Name: log_date; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE log_date (
    log_date_id integer NOT NULL,
    event_type character(1),
    log_date timestamp without time zone
);


ALTER TABLE log_date OWNER TO igsn;

--
-- TOC entry 284 (class 1259 OID 73321)
-- Name: material_types; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE material_types (
    material_types_id integer NOT NULL,
    resource_identifier character varying,
    material_type character varying
);


ALTER TABLE material_types OWNER TO igsn;

--
-- TOC entry 291 (class 1259 OID 73412)
-- Name: related_resource_identifiers; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE related_resource_identifiers (
    related_resource_identifier_id integer NOT NULL,
    related_resource_identifier character varying,
    related_identifier_type character varying,
    relation_type character varying,
    resource_identifier character varying
);


ALTER TABLE related_resource_identifiers OWNER TO igsn;

--
-- TOC entry 283 (class 1259 OID 73308)
-- Name: resource_types; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE resource_types (
    resource_type_id integer NOT NULL,
    resource_type character varying,
    resource_identifier character varying
);


ALTER TABLE resource_types OWNER TO igsn;

--
-- TOC entry 281 (class 1259 OID 73276)
-- Name: resources; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE resources (
    resource_identifier character varying NOT NULL,
    registered_object_type character varying,
    landing_page character varying,
    is_public boolean,
    resource_title character varying,
    purpose character varying,
    location_id integer,
    date timestamp without time zone,
    method character varying,
    campaign character varying,
    comments character varying,
    log_date_id integer
);


ALTER TABLE resources OWNER TO igsn;

--
-- TOC entry 286 (class 1259 OID 73347)
-- Name: sampled_features; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE sampled_features (
    sampled_features_id integer NOT NULL,
    resource_identifier character varying,
    sampled_feature_uri character varying
);


ALTER TABLE sampled_features OWNER TO igsn;

--
-- TOC entry 3563 (class 2606 OID 73291)
-- Name: pk_alternate_identifiers; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY alternate_identifiers
    ADD CONSTRAINT pk_alternate_identifiers PRIMARY KEY (alternate_identifiers_id);


--
-- TOC entry 3569 (class 2606 OID 73341)
-- Name: pk_classifications; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT pk_classifications PRIMARY KEY (classifications_id);


--
-- TOC entry 3575 (class 2606 OID 73380)
-- Name: pk_collectors; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY collectors
    ADD CONSTRAINT pk_collectors PRIMARY KEY (collector_id);


--
-- TOC entry 3579 (class 2606 OID 73406)
-- Name: pk_contributors; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY contributors
    ADD CONSTRAINT pk_contributors PRIMARY KEY (contributor_id);


--
-- TOC entry 3577 (class 2606 OID 73393)
-- Name: pk_curation_details; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY curation_details
    ADD CONSTRAINT pk_curation_details PRIMARY KEY (curation_id);


--
-- TOC entry 3573 (class 2606 OID 73367)
-- Name: pk_location; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY location
    ADD CONSTRAINT pk_location PRIMARY KEY (location_id);


--
-- TOC entry 3583 (class 2606 OID 73429)
-- Name: pk_log_date; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY log_date
    ADD CONSTRAINT pk_log_date PRIMARY KEY (log_date_id);


--
-- TOC entry 3567 (class 2606 OID 73328)
-- Name: pk_material_types; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY material_types
    ADD CONSTRAINT pk_material_types PRIMARY KEY (material_types_id);


--
-- TOC entry 3581 (class 2606 OID 73419)
-- Name: pk_related_resource_identifier; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY related_resource_identifiers
    ADD CONSTRAINT pk_related_resource_identifier PRIMARY KEY (related_resource_identifier_id);


--
-- TOC entry 3560 (class 2606 OID 73283)
-- Name: pk_resource; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT pk_resource PRIMARY KEY (resource_identifier);


--
-- TOC entry 3565 (class 2606 OID 73315)
-- Name: pk_resources; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY resource_types
    ADD CONSTRAINT pk_resources PRIMARY KEY (resource_type_id);


--
-- TOC entry 3571 (class 2606 OID 73354)
-- Name: pk_sampled_features; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY sampled_features
    ADD CONSTRAINT pk_sampled_features PRIMARY KEY (sampled_features_id);


--
-- TOC entry 3561 (class 1259 OID 73307)
-- Name: fki_resources; Type: INDEX; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE INDEX fki_resources ON alternate_identifiers USING btree (resource_identifier);


--
-- TOC entry 3585 (class 2606 OID 73368)
-- Name: fk_location; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE;


--
-- TOC entry 3584 (class 2606 OID 73430)
-- Name: fk_log_date; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT fk_log_date FOREIGN KEY (log_date_id) REFERENCES log_date(log_date_id) ON DELETE CASCADE;


--
-- TOC entry 3586 (class 2606 OID 73302)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY alternate_identifiers
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3587 (class 2606 OID 73316)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resource_types
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3588 (class 2606 OID 73329)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY material_types
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3589 (class 2606 OID 73342)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3590 (class 2606 OID 73355)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY sampled_features
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3591 (class 2606 OID 73381)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY collectors
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3592 (class 2606 OID 73394)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY curation_details
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3593 (class 2606 OID 73407)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY contributors
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3594 (class 2606 OID 73420)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY related_resource_identifiers
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


-- Completed on 2016-12-16 13:31:54

--
-- PostgreSQL database dump complete
--

