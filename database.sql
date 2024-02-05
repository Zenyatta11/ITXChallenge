--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0 (Debian 12.0-2.pgdg100+1)
-- Dumped by pg_dump version 16.0

-- Started on 2024-02-05 02:57:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16497)
-- Name: items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.items (
    item_id bigint NOT NULL,
    name character varying(255) NOT NULL,
    sales_unit bigint NOT NULL
);


ALTER TABLE public.items OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16397)
-- Name: items_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.items_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.items_seq OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16502)
-- Name: stock_records; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stock_records (
    stock_item_id bigint NOT NULL,
    type character varying(255) NOT NULL,
    amount bigint NOT NULL,
    CONSTRAINT stock_records_type_check CHECK (((type)::text = ANY ((ARRAY['SMALL'::character varying, 'MEDIUM'::character varying, 'LARGE'::character varying])::text[])))
);


ALTER TABLE public.stock_records OWNER TO postgres;

--
-- TOC entry 2914 (class 0 OID 16497)
-- Dependencies: 203
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.items (item_id, name, sales_unit) FROM stdin;
1552	V-NECH BASIC SHIRT	100
1553	CONTRASTING FABRIC T-SHIRT	50
1554	RAISED PRINT T-SHIRT	80
1555	PLEATED T-SHIRT	3
1556	CONTRASTING LACE T-SHIRT	650
1557	SLOGAN T-SHIRT	20
1602	BRANDED PANTS	20
1603	BRANDED SWEATER	55
\.


--
-- TOC entry 2915 (class 0 OID 16502)
-- Dependencies: 204
-- Data for Name: stock_records; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stock_records (stock_item_id, type, amount) FROM stdin;
1552	MEDIUM	9
1552	SMALL	4
1553	MEDIUM	9
1553	LARGE	9
1553	SMALL	35
1554	MEDIUM	2
1554	LARGE	20
1554	SMALL	20
1555	LARGE	10
1555	MEDIUM	30
1555	SMALL	25
1556	MEDIUM	1
1557	MEDIUM	2
1557	LARGE	5
1557	SMALL	9
1602	MEDIUM	2
1602	LARGE	5
1602	SMALL	9
1603	MEDIUM	2
1603	SMALL	100
1603	LARGE	5
\.


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 202
-- Name: items_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.items_seq', 1751, true);


--
-- TOC entry 2783 (class 2606 OID 16501)
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (item_id);


--
-- TOC entry 2785 (class 2606 OID 16507)
-- Name: stock_records stock_records_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock_records
    ADD CONSTRAINT stock_records_pkey PRIMARY KEY (stock_item_id, type);


--
-- TOC entry 2786 (class 2606 OID 16508)
-- Name: stock_records fk9hi0mi401mn7oyyp1h65it0qa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock_records
    ADD CONSTRAINT fk9hi0mi401mn7oyyp1h65it0qa FOREIGN KEY (stock_item_id) REFERENCES public.items(item_id);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2024-02-05 02:57:04

--
-- PostgreSQL database dump complete
--

