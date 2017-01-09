--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.4.0
-- Started on 2017-01-09 12:03:57

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
    classification_uri character varying,
    classification character varying
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
    resource_identifier character varying,
    collector_identifier_type integer
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
    resource_identifier character varying,
    contributor_identifier_type integer
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
    resource_identifier character varying,
    curating_institution character varying
);


ALTER TABLE curation_details OWNER TO igsn;

--
-- TOC entry 296 (class 1259 OID 73483)
-- Name: cv_identifier_type; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE cv_identifier_type (
    cv_identifier_type_id integer NOT NULL,
    identifier_type character varying
);


ALTER TABLE cv_identifier_type OWNER TO igsn;

--
-- TOC entry 294 (class 1259 OID 73470)
-- Name: cv_material_types; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE cv_material_types (
    cv_material_type_id integer NOT NULL,
    material_type character varying(100)
);


ALTER TABLE cv_material_types OWNER TO igsn;

--
-- TOC entry 295 (class 1259 OID 73475)
-- Name: cv_resource_type; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE cv_resource_type (
    cv_resource_type_id integer NOT NULL,
    resource_type character varying
);


ALTER TABLE cv_resource_type OWNER TO igsn;

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
    event_type character varying,
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
    material_type integer
);


ALTER TABLE material_types OWNER TO igsn;

--
-- TOC entry 293 (class 1259 OID 73442)
-- Name: method; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE method (
    method_id integer NOT NULL,
    method character varying,
    method_uri character varying
);


ALTER TABLE method OWNER TO igsn;

--
-- TOC entry 291 (class 1259 OID 73412)
-- Name: related_resources; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE related_resources (
    related_resource_id integer NOT NULL,
    related_resource character varying,
    relation_type character varying,
    resource_identifier character varying,
    related_resource_identifier_type integer
);


ALTER TABLE related_resources OWNER TO igsn;

--
-- TOC entry 297 (class 1259 OID 73521)
-- Name: resource_date; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE resource_date (
    resource_date_id integer NOT NULL,
    time_instant timestamp without time zone,
    time_period_start timestamp without time zone,
    time_period_end timestamp without time zone,
    resource_identifier character varying
);


ALTER TABLE resource_date OWNER TO igsn;

--
-- TOC entry 283 (class 1259 OID 73308)
-- Name: resource_types; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE resource_types (
    resource_type_id integer NOT NULL,
    resource_identifier character varying,
    resource_type integer
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
    campaign character varying,
    comments character varying,
    log_date_id integer,
    method_id integer
);


ALTER TABLE resources OWNER TO igsn;

--
-- TOC entry 286 (class 1259 OID 73347)
-- Name: sampled_features; Type: TABLE; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE TABLE sampled_features (
    sampled_features_id integer NOT NULL,
    resource_identifier character varying,
    sampled_feature_uri character varying,
    sampled_feature character varying
);


ALTER TABLE sampled_features OWNER TO igsn;

--
-- TOC entry 3752 (class 0 OID 73284)
-- Dependencies: 282
-- Data for Name: alternate_identifiers; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY alternate_identifiers (resource_identifier, alternate_identifier, alternate_identifiers_id) FROM stdin;
\.


--
-- TOC entry 3755 (class 0 OID 73334)
-- Dependencies: 285
-- Data for Name: classifications; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY classifications (classifications_id, resource_identifier, classification_uri, classification) FROM stdin;
\.


--
-- TOC entry 3758 (class 0 OID 73373)
-- Dependencies: 288
-- Data for Name: collectors; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY collectors (collector_id, collector_name, collector_identifier, resource_identifier, collector_identifier_type) FROM stdin;
\.


--
-- TOC entry 3760 (class 0 OID 73399)
-- Dependencies: 290
-- Data for Name: contributors; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY contributors (contributor_id, contributor_type, contributor_name, contributor_identifier, resource_identifier, contributor_identifier_type) FROM stdin;
\.


--
-- TOC entry 3759 (class 0 OID 73386)
-- Dependencies: 289
-- Data for Name: curation_details; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY curation_details (curation_id, curator, curation_date, curation_location, institution_uri, resource_identifier, curating_institution) FROM stdin;
\.


--
-- TOC entry 3766 (class 0 OID 73483)
-- Dependencies: 296
-- Data for Name: cv_identifier_type; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY cv_identifier_type (cv_identifier_type_id, identifier_type) FROM stdin;
\.


--
-- TOC entry 3764 (class 0 OID 73470)
-- Dependencies: 294
-- Data for Name: cv_material_types; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY cv_material_types (cv_material_type_id, material_type) FROM stdin;
\.


--
-- TOC entry 3765 (class 0 OID 73475)
-- Dependencies: 295
-- Data for Name: cv_resource_type; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY cv_resource_type (cv_resource_type_id, resource_type) FROM stdin;
\.


--
-- TOC entry 3757 (class 0 OID 73360)
-- Dependencies: 287
-- Data for Name: location; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY location (location_id, locality, locality_uri, srid, vertical_datum, geometry_uri) FROM stdin;
\.


--
-- TOC entry 3762 (class 0 OID 73425)
-- Dependencies: 292
-- Data for Name: log_date; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY log_date (log_date_id, event_type, log_date) FROM stdin;
\.


--
-- TOC entry 3754 (class 0 OID 73321)
-- Dependencies: 284
-- Data for Name: material_types; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY material_types (material_types_id, resource_identifier, material_type) FROM stdin;
\.


--
-- TOC entry 3763 (class 0 OID 73442)
-- Dependencies: 293
-- Data for Name: method; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY method (method_id, method, method_uri) FROM stdin;
\.


--
-- TOC entry 3761 (class 0 OID 73412)
-- Dependencies: 291
-- Data for Name: related_resources; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY related_resources (related_resource_id, related_resource, relation_type, resource_identifier, related_resource_identifier_type) FROM stdin;
\.


--
-- TOC entry 3767 (class 0 OID 73521)
-- Dependencies: 297
-- Data for Name: resource_date; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY resource_date (resource_date_id, time_instant, time_period_start, time_period_end, resource_identifier) FROM stdin;
\.


--
-- TOC entry 3753 (class 0 OID 73308)
-- Dependencies: 283
-- Data for Name: resource_types; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY resource_types (resource_type_id, resource_identifier, resource_type) FROM stdin;
\.


--
-- TOC entry 3751 (class 0 OID 73276)
-- Dependencies: 281
-- Data for Name: resources; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY resources (resource_identifier, registered_object_type, landing_page, is_public, resource_title, purpose, location_id, date, campaign, comments, log_date_id, method_id) FROM stdin;
\.


--
-- TOC entry 3756 (class 0 OID 73347)
-- Dependencies: 286
-- Data for Name: sampled_features; Type: TABLE DATA; Schema: version30; Owner: igsn
--

COPY sampled_features (sampled_features_id, resource_identifier, sampled_feature_uri, sampled_feature) FROM stdin;
\.


--
-- TOC entry 3588 (class 2606 OID 73291)
-- Name: pk_alternate_identifiers; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY alternate_identifiers
    ADD CONSTRAINT pk_alternate_identifiers PRIMARY KEY (alternate_identifiers_id);


--
-- TOC entry 3594 (class 2606 OID 73341)
-- Name: pk_classifications; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT pk_classifications PRIMARY KEY (classifications_id);


--
-- TOC entry 3600 (class 2606 OID 73380)
-- Name: pk_collectors; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY collectors
    ADD CONSTRAINT pk_collectors PRIMARY KEY (collector_id);


--
-- TOC entry 3604 (class 2606 OID 73406)
-- Name: pk_contributors; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY contributors
    ADD CONSTRAINT pk_contributors PRIMARY KEY (contributor_id);


--
-- TOC entry 3602 (class 2606 OID 73393)
-- Name: pk_curation_details; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY curation_details
    ADD CONSTRAINT pk_curation_details PRIMARY KEY (curation_id);


--
-- TOC entry 3616 (class 2606 OID 73490)
-- Name: pk_cv_identifier_type; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY cv_identifier_type
    ADD CONSTRAINT pk_cv_identifier_type PRIMARY KEY (cv_identifier_type_id);


--
-- TOC entry 3612 (class 2606 OID 73474)
-- Name: pk_cv_material_type; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY cv_material_types
    ADD CONSTRAINT pk_cv_material_type PRIMARY KEY (cv_material_type_id);


--
-- TOC entry 3614 (class 2606 OID 73482)
-- Name: pk_cv_resource_type; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY cv_resource_type
    ADD CONSTRAINT pk_cv_resource_type PRIMARY KEY (cv_resource_type_id);


--
-- TOC entry 3598 (class 2606 OID 73367)
-- Name: pk_location; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY location
    ADD CONSTRAINT pk_location PRIMARY KEY (location_id);


--
-- TOC entry 3608 (class 2606 OID 73429)
-- Name: pk_log_date; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY log_date
    ADD CONSTRAINT pk_log_date PRIMARY KEY (log_date_id);


--
-- TOC entry 3592 (class 2606 OID 73328)
-- Name: pk_material_types; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY material_types
    ADD CONSTRAINT pk_material_types PRIMARY KEY (material_types_id);


--
-- TOC entry 3610 (class 2606 OID 73449)
-- Name: pk_method; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY method
    ADD CONSTRAINT pk_method PRIMARY KEY (method_id);


--
-- TOC entry 3606 (class 2606 OID 73436)
-- Name: pk_related_resource; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY related_resources
    ADD CONSTRAINT pk_related_resource PRIMARY KEY (related_resource_id);


--
-- TOC entry 3585 (class 2606 OID 73283)
-- Name: pk_resource; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT pk_resource PRIMARY KEY (resource_identifier);


--
-- TOC entry 3618 (class 2606 OID 73525)
-- Name: pk_resource_date; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY resource_date
    ADD CONSTRAINT pk_resource_date PRIMARY KEY (resource_date_id);


--
-- TOC entry 3590 (class 2606 OID 73315)
-- Name: pk_resources; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY resource_types
    ADD CONSTRAINT pk_resources PRIMARY KEY (resource_type_id);


--
-- TOC entry 3596 (class 2606 OID 73354)
-- Name: pk_sampled_features; Type: CONSTRAINT; Schema: version30; Owner: igsn; Tablespace: 
--

ALTER TABLE ONLY sampled_features
    ADD CONSTRAINT pk_sampled_features PRIMARY KEY (sampled_features_id);


--
-- TOC entry 3586 (class 1259 OID 73307)
-- Name: fki_resources; Type: INDEX; Schema: version30; Owner: igsn; Tablespace: 
--

CREATE INDEX fki_resources ON alternate_identifiers USING btree (resource_identifier);


--
-- TOC entry 3625 (class 2606 OID 73491)
-- Name: fk_cv_material_type; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY material_types
    ADD CONSTRAINT fk_cv_material_type FOREIGN KEY (material_type) REFERENCES cv_material_types(cv_material_type_id);


--
-- TOC entry 3623 (class 2606 OID 73496)
-- Name: fk_cv_resource_type; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resource_types
    ADD CONSTRAINT fk_cv_resource_type FOREIGN KEY (resource_type) REFERENCES cv_resource_type(cv_resource_type_id);


--
-- TOC entry 3632 (class 2606 OID 73511)
-- Name: fk_idenfifier_type; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY contributors
    ADD CONSTRAINT fk_idenfifier_type FOREIGN KEY (contributor_identifier_type) REFERENCES cv_identifier_type(cv_identifier_type_id);


--
-- TOC entry 3634 (class 2606 OID 73506)
-- Name: fk_identifier_type; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY related_resources
    ADD CONSTRAINT fk_identifier_type FOREIGN KEY (related_resource_identifier_type) REFERENCES cv_identifier_type(cv_identifier_type_id);


--
-- TOC entry 3629 (class 2606 OID 73516)
-- Name: fk_identifier_type; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY collectors
    ADD CONSTRAINT fk_identifier_type FOREIGN KEY (collector_identifier_type) REFERENCES cv_identifier_type(cv_identifier_type_id);


--
-- TOC entry 3621 (class 2606 OID 73368)
-- Name: fk_location; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE;


--
-- TOC entry 3620 (class 2606 OID 73430)
-- Name: fk_log_date; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT fk_log_date FOREIGN KEY (log_date_id) REFERENCES log_date(log_date_id) ON DELETE CASCADE;


--
-- TOC entry 3619 (class 2606 OID 73450)
-- Name: fk_method; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resources
    ADD CONSTRAINT fk_method FOREIGN KEY (method_id) REFERENCES method(method_id) ON DELETE CASCADE;


--
-- TOC entry 3636 (class 2606 OID 73534)
-- Name: fk_resource; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resource_date
    ADD CONSTRAINT fk_resource FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3622 (class 2606 OID 73302)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY alternate_identifiers
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3624 (class 2606 OID 73316)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY resource_types
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3626 (class 2606 OID 73329)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY material_types
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3627 (class 2606 OID 73342)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY classifications
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3628 (class 2606 OID 73355)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY sampled_features
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3630 (class 2606 OID 73381)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY collectors
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3631 (class 2606 OID 73394)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY curation_details
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3633 (class 2606 OID 73407)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY contributors
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


--
-- TOC entry 3635 (class 2606 OID 73437)
-- Name: fk_resources; Type: FK CONSTRAINT; Schema: version30; Owner: igsn
--

ALTER TABLE ONLY related_resources
    ADD CONSTRAINT fk_resources FOREIGN KEY (resource_identifier) REFERENCES resources(resource_identifier) ON DELETE CASCADE;


-- Completed on 2017-01-09 12:04:08

--
-- PostgreSQL database dump complete
--

