--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7 (Ubuntu 14.7-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.7 (Ubuntu 14.7-0ubuntu0.22.04.1)

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
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Interesse; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public."Interesse" (
    "idInteresse" bigint NOT NULL,
    "idUsuario" bigint NOT NULL,
    "idJogo" bigint NOT NULL,
    tipo smallint NOT NULL,
    peso smallint
);


ALTER TABLE public."Interesse" OWNER TO ti2cc;

--
-- Name: Interesse_idInteresse_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public."Interesse" ALTER COLUMN "idInteresse" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Interesse_idInteresse_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: Jogo; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public."Jogo" (
    "idJogo" bigint NOT NULL,
    titulo character varying NOT NULL,
    descricao text,
    display character varying,
    url character varying,
    pontuacao smallint
);


ALTER TABLE public."Jogo" OWNER TO ti2cc;

--
-- Name: Jogo_idJogo_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public."Jogo" ALTER COLUMN "idJogo" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Jogo_idJogo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: Recomendacao; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public."Recomendacao" (
    "idRecomendacao" bigint NOT NULL,
    "idUsuario" bigint NOT NULL,
    "idJogo" bigint NOT NULL,
    confianca smallint
);


ALTER TABLE public."Recomendacao" OWNER TO ti2cc;

--
-- Name: Recomendacao_idRecomendacao_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public."Recomendacao" ALTER COLUMN "idRecomendacao" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Recomendacao_idRecomendacao_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: Tag; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public."Tag" (
    "idTag" integer NOT NULL,
    "tagName" character varying NOT NULL,
    "tagDesc" text
);


ALTER TABLE public."Tag" OWNER TO ti2cc;

--
-- Name: TagList; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public."TagList" (
    "idJogo" bigint NOT NULL,
    "idTag" integer NOT NULL
);


ALTER TABLE public."TagList" OWNER TO ti2cc;

--
-- Name: Tag_idTag_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public."Tag" ALTER COLUMN "idTag" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Tag_idTag_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: Usuario; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public."Usuario" (
    "idUsuario" bigint NOT NULL,
    username character varying,
    email character varying,
    senha character(64) NOT NULL,
    grupo smallint DEFAULT 0
);


ALTER TABLE public."Usuario" OWNER TO ti2cc;

--
-- Name: Usuario_idUsuario_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public."Usuario" ALTER COLUMN "idUsuario" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Usuario_idUsuario_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: Interesse; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public."Interesse" ("idInteresse", "idUsuario", "idJogo", tipo, peso) FROM stdin;
\.


--
-- Data for Name: Jogo; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public."Jogo" ("idJogo", titulo, descricao, display, url, pontuacao) FROM stdin;
1	tesa	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	https://www.youtube.com/watch?v=NOxdNyzNYZ4&t=574s	https://store.steampowered.com/app/0001/Teste	10
2	fajlflf	o2h2\nasdad	rqorjq3o	fçamfamf	2
3	awwww	twtwt\nwtw	afafafa	aaaaaaaaa	2
\.


--
-- Data for Name: Recomendacao; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public."Recomendacao" ("idRecomendacao", "idUsuario", "idJogo", confianca) FROM stdin;
\.


--
-- Data for Name: Tag; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public."Tag" ("idTag", "tagName", "tagDesc") FROM stdin;
\.


--
-- Data for Name: TagList; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public."TagList" ("idJogo", "idTag") FROM stdin;
\.


--
-- Data for Name: Usuario; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

COPY public."Usuario" ("idUsuario", username, email, senha, grupo) FROM stdin;
1	afsf	faggeee	asfafawf                                                        	2
5			                                                                	1
\.


--
-- Name: Interesse_idInteresse_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public."Interesse_idInteresse_seq"', 1, false);


--
-- Name: Jogo_idJogo_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public."Jogo_idJogo_seq"', 3, true);


--
-- Name: Recomendacao_idRecomendacao_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public."Recomendacao_idRecomendacao_seq"', 1, false);


--
-- Name: Tag_idTag_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public."Tag_idTag_seq"', 1, false);


--
-- Name: Usuario_idUsuario_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public."Usuario_idUsuario_seq"', 5, true);


--
-- Name: Interesse Interesse_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Interesse"
    ADD CONSTRAINT "Interesse_pk" PRIMARY KEY ("idInteresse");


--
-- Name: Jogo Jogo_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Jogo"
    ADD CONSTRAINT "Jogo_pk" PRIMARY KEY ("idJogo");


--
-- Name: Recomendacao Recomendacao_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Recomendacao"
    ADD CONSTRAINT "Recomendacao_pk" PRIMARY KEY ("idRecomendacao");


--
-- Name: Tag Tag_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Tag"
    ADD CONSTRAINT "Tag_pk" PRIMARY KEY ("idTag");


--
-- Name: Usuario Usuario_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Usuario"
    ADD CONSTRAINT "Usuario_pk" PRIMARY KEY ("idUsuario");


--
-- Name: Interesse idJogo_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Interesse"
    ADD CONSTRAINT "idJogo_fk" FOREIGN KEY ("idJogo") REFERENCES public."Jogo"("idJogo");


--
-- Name: Recomendacao idJogo_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Recomendacao"
    ADD CONSTRAINT "idJogo_fk" FOREIGN KEY ("idJogo") REFERENCES public."Jogo"("idJogo");


--
-- Name: TagList idJogo_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."TagList"
    ADD CONSTRAINT "idJogo_fk" FOREIGN KEY ("idJogo") REFERENCES public."Jogo"("idJogo");


--
-- Name: TagList idTag_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."TagList"
    ADD CONSTRAINT "idTag_fk" FOREIGN KEY ("idTag") REFERENCES public."Tag"("idTag");


--
-- Name: Interesse idUsuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Interesse"
    ADD CONSTRAINT "idUsuario_fk" FOREIGN KEY ("idUsuario") REFERENCES public."Usuario"("idUsuario");


--
-- Name: Recomendacao idUsuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Recomendacao"
    ADD CONSTRAINT "idUsuario_fk" FOREIGN KEY ("idUsuario") REFERENCES public."Usuario"("idUsuario");


--
-- PostgreSQL database dump complete
--

