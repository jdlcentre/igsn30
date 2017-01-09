--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.4.0
-- Started on 2017-01-09 13:04:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 10 (class 2615 OID 73271)
-- Name: version30; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA version30;


SET search_path = version30, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 282 (class 1259 OID 73284)
-- Name: alternate_identifiers; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE alternate_identifiers (
    resource_identifier character varying,
    alternate_identifier character varying,
    alternate_identifiers_id integer NOT NULL
);


--
-- TOC entry 285 (class 1259 OID 73334)
-- Name: classifications; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE classifications (
    classifications_id integer NOT NULL,
    resource_identifier character varying,
    classification_uri character varying,
    classification character varying
);


--
-- TOC entry 288 (class 1259 OID 73373)
-- Name: collectors; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE collectors (
    collector_id integer NOT NULL,
    collector_name character varying,
    collector_identifier character varying,
    resource_identifier character varying,
    collector_identifier_type integer
);


--
-- TOC entry 290 (class 1259 OID 73399)
-- Name: contributors; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE contributors (
    contributor_id integer NOT NULL,
    contributor_type character varying,
    contributor_name character varying,
    contributor_identifier character varying,
    resource_identifier character varying,
    contributor_identifier_type integer
);


--
-- TOC entry 289 (class 1259 OID 73386)
-- Name: curation_details; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE curation_details (
    curation_id integer NOT NULL,
    curator character varying,
    curation_date timestamp without time zone,
    curation_location character varying,
    institution_uri character varying,
    resource_identifier character varying,
    curating_institution character varying
);


--
-- TOC entry 296 (class 1259 OID 73483)
-- Name: cv_identifier_type; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE cv_identifier_type (
    cv_identifier_type_id integer NOT NULL,
    identifier_type character varying
);


--
-- TOC entry 294 (class 1259 OID 73470)
-- Name: cv_material_types; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE cv_material_types (
    cv_material_type_id integer NOT NULL,
    material_type character varying(100)
);


--
-- TOC entry 295 (class 1259 OID 73475)
-- Name: cv_resource_type; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE cv_resource_type (
    cv_resource_type_id integer NOT NULL,
    resource_type character varying
);


--
-- TOC entry 287 (class 1259 OID 73360)
-- Name: location; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE location (
    location_id integer NOT NULL,
    locality character varying,
    locality_uri character varying,
    srid character varying,
    vertical_datum character varying,
    geometry_uri character varying
);


--
-- TOC entry 292 (class 1259 OID 73425)
-- Name: log_date; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE log_date (
    log_date_id integer NOT NULL,
    event_type character varying,
    log_date timestamp without time zone
);


--
-- TOC entry 284 (class 1259 OID 73321)
-- Name: material_types; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE material_types (
    material_types_id integer NOT NULL,
    resource_identifier character varying,
    material_type integer
);


--
-- TOC entry 293 (class 1259 OID 73442)
-- Name: method; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE method (
    method_id integer NOT NULL,
    method character varying,
    method_uri character varying
);


--
-- TOC entry 291 (class 1259 OID 73412)
-- Name: related_resources; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE related_resources (
    related_resource_id integer NOT NULL,
    related_resource character varying,
    relation_type character varying,
    resource_identifier character varying,
    related_resource_identifier_type integer
);


--
-- TOC entry 297 (class 1259 OID 73521)
-- Name: resource_date; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE resource_date (
    resource_date_id integer NOT NULL,
    time_instant timestamp without time zone,
    time_period_start timestamp without time zone,
    time_period_end timestamp without time zone,
    resource_identifier character varying
);


--
-- TOC entry 283 (class 1259 OID 73308)
-- Name: resource_types; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE resource_types (
    resource_type_id integer NOT NULL,
    resource_identifier character varying,
    resource_type integer
);


--
-- TOC entry 281 (class 1259 OID 73276)
-- Name: resources; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
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
    campaign character varying,
    comments character varying,
    log_date_id integer,
    method_id integer
);


--
-- TOC entry 286 (class 1259 OID 73347)
-- Name: sampled_features; Type: TABLE; Schema: version30; Owner: -; Tablespace: 
--

CREATE TABLE sampled_features (
    sampled_features_id integer NOT NULL,
    resource_identifier character varying,
    sampled_feature_uri character varying,
    sampled_feature character varying
);


--
-- TOC entry 3588 (class 2606 OID 73291)
-- Name: pk_alternate_identifiers; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY alternate_identifiers
    ADD CONSTRAINT pk_alternate_identifiers PRIMARY KEY (alternate_identifiers_id);


--
-- TOC entry 3594 (class 2606 OID 73341)
-- Name: pk_classifications; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT pk_classifications PRIMARY KEY (classifications_id);


--
-- TOC entry 3600 (class 2606 OID 73380)
-- Name: pk_collectors; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY collectors
    ADD CONSTRAINT pk_collectors PRIMARY KEY (collector_id);


--
-- TOC entry 3604 (class 2606 OID 73406)
-- Name: pk_contributors; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY contributors
    ADD CONSTRAINT pk_contributors PRIMARY KEY (contributor_id);


--
-- TOC entry 3602 (class 2606 OID 73393)
-- Name: pk_curation_details; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY curation_details
    ADD CONSTRAINT pk_curation_details PRIMARY KEY (curation_id);


--
-- TOC entry 3616 (class 2606 OID 73490)
-- Name: pk_cv_identifier_type; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cv_identifier_type
    ADD CONSTRAINT pk_cv_identifier_type PRIMARY KEY (cv_identifier_type_id);


--
-- TOC entry 3612 (class 2606 OID 73474)
-- Name: pk_cv_material_type; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cv_material_types
    ADD CONSTRAINT pk_cv_material_type PRIMARY KEY (cv_material_type_id);


--
-- TOC entry 3614 (class 2606 OID 73482)
-- Name: pk_cv_resource_type; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cv_resource_type
    ADD CONSTRAINT pk_cv_resource_type PRIMARY KEY (cv_resource_type_id);


--
-- TOC entry 3598 (class 2606 OID 73367)
-- Name: pk_location; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY location
    ADD CONSTRAINT pk_location PRIMARY KEY (location_id);


--
-- TOC entry 3608 (class 2606 OID 73429)
-- Name: pk_log_date; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY log_date
    ADD CONSTRAINT pk_log_date PRIMARY KEY (log_date_id);


--
-- TOC entry 3592 (class 2606 OID 73328)
-- Name: pk_material_types; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY material_types
    ADD CONSTRAINT pk_material_types PRIMARY KEY (material_types_id);


--
-- TOC entry 3610 (class 2606 OID 73449)
-- Name: pk_method; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY method
    ADD CONSTRAINT pk_method PRIMARY KEY (method_id);


--
-- TOC entry 3606 (class 2606 OID 73436)
-- Name: pk_related_resource; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY related_resources
    ADD CONSTRAINT pk_related_resource PRIMARY KEY (related_resource_id);


--
-- TOC entry 3585 (class 2606 OID 73283)
-- Name: pk_resource; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT pk_resource PRIMARY KEY (resource_identifier);


--
-- TOC entry 3618 (class 2606 OID 73525)
-- Name: pk_resource_date; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY resource_date
    ADD CONSTRAINT pk_resource_date PRIMARY KEY (resource_date_id);


--
-- TOC entry 3590 (class 2606 OID 73315)
-- Name: pk_resources; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY resource_types
    ADD CONSTRAINT pk_resources PRIMARY KEY (resource_type_id);


--
-- TOC entry 3596 (class 2606 OID 73354)
-- Name: pk_sampled_features; Type: CONSTRAINT; Schema: version30; Owner: -; Tablespace: 
--

ALTER TABLE ONLY sampled_features
    ADD CONSTRAINT pk_sampled_features PRIMARY KEY (sampled_features_id);


--
-- TOC entry 3586 (class 1259 OID 73307)
-- Name: fki_resources; Type: INDEX; Schema: version30; Owner: -; Tablespace: 
--

CREATE INDEX fki_resources ON alternate_identifiers USING btree (resource_identifier);


--
-- TOC entry 3625 (class 2606 OID 73491)
-- Name: fk_cv_material_type; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY material_types
    ADD CONSTRAINT fk_cv_material_type FOREIGN KEY (material_type) REFERENCES cv_material_types(cv_material_type_id);


--
-- TOC entry 3623 (class 2606 OID 73496)
-- Name: fk_cv_resource_type; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY resource_types
    ADD CONSTRAINT fk_cv_resource_type FOREIGN KEY (resource_type) REFERENCES cv_resource_type(cv_resource_type_id);


--
-- TOC entry 3632 (class 2606 OID 73511)
-- Name: fk_idenfifier_type; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY contributors
    ADD CONSTRAINT fk_idenfifier_type FOREIGN KEY (contributor_identifier_type) REFERENCES cv_identifier_type(cv_identifier_type_id);


--
-- TOC entry 3634 (class 2606 OID 73506)
-- Name: fk_identifier_type; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY related_resources
    ADD CONSTRAINT fk_identifier_type FOREIGN KEY (related_resource_identifier_type) REFERENCES cv_identifier_type(cv_identifier_type_id);


--
-- TOC entry 3629 (class 2606 OID 73516)
-- Name: fk_identifier_type; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY collectors
    ADD CONSTRAINT fk_identifier_type FOREIGN KEY (collector_identifier_type) REFERENCES cv_identifier_type(cv_identifier_type_id);


--
-- TOC entry 3621 (class 2606 OID 73368)
-- Name: fk_location; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE;


--
-- TOC entry 3620 (class 2606 OID 73430)
-- Name: fk_log_date; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT fk_log_date FOREIGN KEY (log_date_id) REFERENCES log_date(log_date_id) ON DELETE CASCADE;


--
-- TOC entry 3619 (class 2606 OID 73450)
-- Name: fk_method; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT fk_method FOREIGN KEY (method_id) REFERENCES method(method_id) ON DELETE CASCADE;


--
-- TOC entry 3636 (class 2606 OID 73534)
-- Name: fk_resource; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY resource_date
    ADD CONSTRAINT fk_resource FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3622 (class 2606 OID 73302)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY alternate_identifiers
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3624 (class 2606 OID 73316)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY resource_types
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3626 (class 2606 OID 73329)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY material_types
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3627 (class 2606 OID 73342)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3628 (class 2606 OID 73355)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY sampled_features
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3630 (class 2606 OID 73381)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY collectors
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3631 (class 2606 OID 73394)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY curation_details
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3633 (class 2606 OID 73407)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY contributors
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3635 (class 2606 OID 73437)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: -
--

ALTER TABLE ONLY related_resources
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


-- Completed on 2017-01-09 13:04:51

--
-- PostgreSQL database dump complete
--

