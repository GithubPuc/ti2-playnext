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
-- Name: public; Type: SCHEMA; Schema: -; Owner: ti2cc
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO ti2cc;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Interesse; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.Interesse (
    idInteresse bigint NOT NULL,
    idUsuario bigint NOT NULL,
    idJogo bigint NOT NULL,
    tipo smallint NOT NULL,
    peso smallint
);


ALTER TABLE public.Interesse OWNER TO ti2cc;

--
-- Name: Interesse_idInteresse_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public.Interesse ALTER COLUMN idInteresse ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.Interesse_idInteresse_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: Jogo; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.Jogo (
    idJogo bigint NOT NULL,
    titulo character varying NOT NULL,
    descricao text,
    display character varying,
    url character varying,
    pontuacao smallint
);


ALTER TABLE public.Jogo OWNER TO ti2cc;

--
-- Name: Jogo_idJogo_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public.Jogo ALTER COLUMN idJogo ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.Jogo_idJogo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: Recomendacao; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.Recomendacao (
    idRecomendacao bigint NOT NULL,
    idUsuario bigint NOT NULL,
    idJogo bigint NOT NULL,
    confianca smallint
);


ALTER TABLE public.Recomendacao OWNER TO ti2cc;

--
-- Name: Recomendacao_idRecomendacao_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public.Recomendacao ALTER COLUMN idRecomendacao ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.Recomendacao_idRecomendacao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: Tag; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.Tag (
    idTag integer NOT NULL,
    tagName character varying NOT NULL,
    tagDesc text
);


ALTER TABLE public.Tag OWNER TO ti2cc;

--
-- Name: TagRel; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.TagRel (
    idJogo bigint NOT NULL,
    idTag integer NOT NULL
);


ALTER TABLE public.TagRel OWNER TO ti2cc;

--
-- Name: Tag_idTag_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public.Tag ALTER COLUMN idTag ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.Tag_idTag_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

--
-- Name: Usuario; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.Usuario (
    idUsuario bigint NOT NULL,
    username character varying,
    email character varying,
    senha character(64) NOT NULL,
    grupo smallint DEFAULT 0
);


ALTER TABLE public.Usuario OWNER TO ti2cc;

--
-- Name: Usuario_idUsuario_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

ALTER TABLE public.Usuario ALTER COLUMN idUsuario ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.Usuario_idUsuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: Interesse; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.Interesse OVERRIDING SYSTEM VALUE VALUES (1, 1, 2, 1, 8);
INSERT INTO public.Interesse OVERRIDING SYSTEM VALUE VALUES (2, 2, 4, 1, 8);
INSERT INTO public.Interesse OVERRIDING SYSTEM VALUE VALUES (3, 3, 5, 1, 120);


--
-- Data for Name: Jogo; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.Jogo OVERRIDING SYSTEM VALUE VALUES (1, 'Latinizer 3', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'https://www.youtube.com/watch?v=NOxdNyzNYZ4&t=574s', 'https://store.steampowered.com/app/0001/Teste', 10);
INSERT INTO public.Jogo OVERRIDING SYSTEM VALUE VALUES (2, 'Latinizer 2', 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'https://www.youtube.com/watch?v=NOxdNyzNYZ4&t=574s', 'https://store.steampowered.com/app/0002/Teste', 10);
INSERT INTO public.Jogo OVERRIDING SYSTEM VALUE VALUES (3, 'SlashChop', 'Slash and Chop your way through the hordes of...', 'https://www.youtube.com/watch?v=NOxdNyzNYZ4&t=574s', 'https://store.steampowered.com/app/0003/Teste', 8);
INSERT INTO public.Jogo OVERRIDING SYSTEM VALUE VALUES (4, 'ChopChop', 'Chop and Chop your way through the hordes of...', 'https://www.youtube.com/watch?v=NOxdNyzNYZ4&t=574s', 'https://store.steampowered.com/app/0004/Teste', 8);
INSERT INTO public.Jogo OVERRIDING SYSTEM VALUE VALUES (5, 'Amish Game of Life', 'Learn to apreciate the simpler things in this explosive...', 'https://www.youtube.com/watch?v=NOxdNyzNYZ4&t=574s', 'https://store.steampowered.com/app/0005/Teste', 10);
INSERT INTO public.Jogo OVERRIDING SYSTEM VALUE VALUES (6, 'Cobras and Elevators', 'The revamped version of the timeless classic Snakes and Ladders', 'https://www.youtube.com/watch?v=NOxdNyzNYZ4&t=574s', 'https://store.steampowered.com/app/0006/Teste', 9);


--
-- Data for Name: Recomendacao; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.Recomendacao OVERRIDING SYSTEM VALUE VALUES (1, 1, 1, 95);
INSERT INTO public.Recomendacao OVERRIDING SYSTEM VALUE VALUES (2, 2, 3, 95);
INSERT INTO public.Recomendacao OVERRIDING SYSTEM VALUE VALUES (3, 3, 6, 80);


--
-- Data for Name: Tag; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (1, 'Action', 'Games that go *kaboom*! (occasionally)');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (2, 'FPS', 'First Person Shooter');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (3, 'Survival', 'Games that make you have to eat');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (4, 'Party', 'Games that ruin friendships');


--
-- Data for Name: TagRel; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.TagRel VALUES (1, 1);
INSERT INTO public.TagRel VALUES (1, 2);
INSERT INTO public.TagRel VALUES (2, 1);
INSERT INTO public.TagRel VALUES (2, 2);
INSERT INTO public.TagRel VALUES (3, 2);
INSERT INTO public.TagRel VALUES (3, 3);
INSERT INTO public.TagRel VALUES (4, 2);
INSERT INTO public.TagRel VALUES (4, 3);
INSERT INTO public.TagRel VALUES (5, 4);
INSERT INTO public.TagRel VALUES (6, 4);


--
-- Data for Name: Usuario; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.Usuario OVERRIDING SYSTEM VALUE VALUES (1, 'All_V_2005', 'alvim@esq.com', 'XV9zLLi8KRmtyWz5ZHGTgpr3eZklhF56Cj2qtuhanSvmY8vuN2bEZGGCuM8EZIjl', 2);
INSERT INTO public.Usuario OVERRIDING SYSTEM VALUE VALUES (2, 'Laurencio', 'laur@enc.com', 'GJSNnYmBEmzxgbssQQ6jo4116xJXnfjYTeTfWNz3tJoRcB2Xb65SMPLdhG8QLNAu', 1);
INSERT INTO public.Usuario OVERRIDING SYSTEM VALUE VALUES (3, 'Joao', 'jojo@oao.com', 'oIfMNWZ7KiZ6rBLwAddBLUMgKXDuYuhN5roVPxm2hMeuVMtHuhGnFWfFc4neeV2M', 1);


--
-- Name: Interesse_idInteresse_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public.Interesse_idInteresse_seq', 3, true);


--
-- Name: Jogo_idJogo_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public.Jogo_idJogo_seq', 6, true);


--
-- Name: Recomendacao_idRecomendacao_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public.Recomendacao_idRecomendacao_seq', 3, true);


--
-- Name: Tag_idTag_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public.Tag_idTag_seq', 4, true);


--
-- Name: Usuario_idUsuario_seq; Type: SEQUENCE SET; Schema: public; Owner: ti2cc
--

SELECT pg_catalog.setval('public.Usuario_idUsuario_seq', 3, true);


--
-- Name: Interesse Interesse_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Interesse
    ADD CONSTRAINT Interesse_pk PRIMARY KEY (idInteresse);


--
-- Name: Jogo Jogo_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Jogo
    ADD CONSTRAINT Jogo_pk PRIMARY KEY (idJogo);


--
-- Name: Recomendacao Recomendacao_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Recomendacao
    ADD CONSTRAINT Recomendacao_pk PRIMARY KEY (idRecomendacao);


--
-- Name: Tag Tag_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Tag
    ADD CONSTRAINT Tag_pk PRIMARY KEY (idTag);


--
-- Name: Usuario Usuario_pk; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Usuario
    ADD CONSTRAINT Usuario_pk PRIMARY KEY (idUsuario);


--
-- Name: Interesse idJogo_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Interesse
    ADD CONSTRAINT idJogo_fk FOREIGN KEY (idJogo) REFERENCES public.Jogo(idJogo);


--
-- Name: Recomendacao idJogo_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Recomendacao
    ADD CONSTRAINT idJogo_fk FOREIGN KEY (idJogo) REFERENCES public.Jogo(idJogo);


--
-- Name: TagRel idJogo_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.TagRel
    ADD CONSTRAINT idJogo_fk FOREIGN KEY (idJogo) REFERENCES public.Jogo(idJogo);


--
-- Name: TagRel idTag_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.TagRel
    ADD CONSTRAINT idTag_fk FOREIGN KEY (idTag) REFERENCES public.Tag(idTag);


--
-- Name: Interesse idUsuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Interesse
    ADD CONSTRAINT idUsuario_fk FOREIGN KEY (idUsuario) REFERENCES public.Usuario(idUsuario);


--
-- Name: Recomendacao idUsuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.Recomendacao
    ADD CONSTRAINT idUsuario_fk FOREIGN KEY (idUsuario) REFERENCES public.Usuario(idUsuario);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO ti2cc;


--
-- PostgreSQL database dump complete
--
