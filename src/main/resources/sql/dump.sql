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
    peso integer
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
    steamIdJogo bigint NOT NULL,
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
    confianca smallint,
    tipo smallint DEFAULT 0
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
-- Name: TagLink; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.TagLink (
    idJogo bigint NOT NULL,
    idTag integer NOT NULL
);


ALTER TABLE public.TagLink OWNER TO ti2cc;

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
    username character varying(31),
    email character varying(255),
    senha bytea NOT NULL,
    tipo smallint DEFAULT 0
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

-- TODO jogos


--
-- Data for Name: Recomendacao; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.Recomendacao OVERRIDING SYSTEM VALUE VALUES (1, 1, 1, 95, 1);
INSERT INTO public.Recomendacao OVERRIDING SYSTEM VALUE VALUES (2, 2, 3, 95, 1);
INSERT INTO public.Recomendacao OVERRIDING SYSTEM VALUE VALUES (3, 3, 6, 80, 1);


--
-- Data for Name: Tag; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (1, 'Action', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (2, 'Adventure', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (3, 'Casual', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (4, 'Experimental', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (5, 'Puzzle', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (6, 'Racing', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (7, 'RPG', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (8, 'Simulation', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (9, 'Sports', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (10, 'Strategy', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (11, 'Tabletop', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (12, 'Action RPG', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (13, 'Action-Adventure', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (14, 'Arcade', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (15, 'Auto Battler', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (16, 'Automobile Sim', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (17, 'Base Building', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (18, 'Baseball', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (19, 'Basketball', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (20, 'Battle Royale', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (21, 'BMX', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (22, 'Board Game', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (23, 'Bowling', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (24, 'Building', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (25, 'Card Game', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (26, 'Character Action Game', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (27, 'Chess', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (28, 'Clicker', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (29, 'Cycling', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (30, 'Diplomacy', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (31, 'eSports', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (32, 'Experimental', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (33, 'Exploration', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (34, 'Farming Sim', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (35, 'Fighting', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (36, 'Football', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (37, 'God Game', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (38, 'Golf', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (39, 'Hacking', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (40, 'Hidden Object', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (41, 'Hockey', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (42, 'Idler', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (43, 'Interactive Fiction', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (44, 'Management', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (45, 'Match 3', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (46, 'Medical Sim', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (47, 'Mini Golf', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (48, 'Mining', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (49, 'MMORPG', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (50, 'MOBA', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (51, 'Motocross', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (52, 'Open World', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (53, 'Outbreak Sim', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (54, 'Party-Based RPG', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (55, 'Pinball', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (56, 'Platformer', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (57, 'Point &amp; Click', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (58, 'Rhythm', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (59, 'Roguelike', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (60, 'RTS', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (61, 'Sandbox', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (62, 'Shooter', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (63, 'Skateboarding', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (64, 'Skating', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (65, 'Skiing', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (66, 'Snowboarding', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (67, 'Soccer', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (68, 'Space Sim', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (69, 'Stealth', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (70, 'Strategy RPG', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (71, 'Survival', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (72, 'Tennis', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (73, 'Tower Defense', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (74, 'Trivia', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (75, 'Turn-Based Strategy', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (76, 'Visual Novel', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (77, 'Walking Simulator', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (78, 'Word Game', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (79, 'Wrestling', 'Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (80, '2D Fighter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (81, '2D Platformer', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (82, '3D Fighter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (83, '3D Platformer', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (84, '4X', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (85, 'Action Roguelike', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (86, 'Arena Shooter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (87, 'Beat ''em up', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (88, 'Bullet Hell', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (89, 'Card Battler', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (90, 'Choose Your Own Adventure', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (91, 'City Builder', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (92, 'Collectathon', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (93, 'Colony Sim', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (94, 'Combat Racing', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (95, 'CRPG', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (96, 'Dating Sim', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (97, 'Dungeon Crawler', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (98, 'Education', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (99, 'Flight', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (100, 'FPS', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (101, 'Grand Strategy', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (102, 'Hack and Slash', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (103, 'Heist', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (104, 'Hero Shooter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (105, 'Horror', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (106, 'Immersive Sim', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (107, 'Investigation', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (108, 'JRPG', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (109, 'Life Sim', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (110, 'Looter Shooter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (111, 'Metroidvania', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (112, 'Mystery Dungeon', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (113, 'On-Rails Shooter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (114, 'Open World Survival Craft', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (115, 'Political Sim', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (116, 'Precision Platformer', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (117, 'Programming', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (118, 'Real Time Tactics', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (119, 'Roguelite', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (120, 'Roguevania', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (121, 'Runner', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (122, 'Shoot ''Em Up', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (123, 'Side Scroller', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (124, 'Sokoban', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (125, 'Solitaire', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (126, 'Souls-like', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (127, 'Spectacle fighter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (128, 'Spelling', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (129, 'Survival Horror', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (130, 'Tactical RPG', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (131, 'Third-Person Shooter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (132, 'Time Management', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (133, 'Top-Down Shooter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (134, 'Trading', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (135, 'Trading Card Game', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (136, 'Traditional Roguelike', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (137, 'Turn-Based Tactics', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (138, 'Twin Stick Shooter', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (139, 'Typing', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (140, 'Wargame', 'Sub-Genre');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (141, '2.5D', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (142, '2D', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (143, '360 Video', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (144, '3D', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (145, '3D Vision', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (146, 'Abstract', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (147, 'Anime', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (148, 'Cartoon', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (149, 'Cartoony', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (150, 'Cinematic', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (151, 'Colorful', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (152, 'Comic Book', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (153, 'Cute', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (154, 'First-Person', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (155, 'FMV', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (156, 'Hand-drawn', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (157, 'Isometric', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (158, 'Minimalist', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (159, 'Noir', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (160, 'Pixel Graphics', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (161, 'Psychedelic', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (162, 'Realistic', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (163, 'Split Screen', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (164, 'Stylized', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (165, 'Text-Based', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (166, 'Third Person', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (167, 'Top-Down', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (168, 'Voxel', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (169, 'VR', 'View');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (170, '1980s', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (171, '1990''s', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (172, 'Agriculture', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (173, 'Aliens', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (174, 'Alternate History', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (175, 'America', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (176, 'Atmospheric', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (177, 'Assassin', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (178, 'Bikes', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (179, 'Capitalism', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (180, 'Cats', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (181, 'Cold War', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (182, 'Comic Book', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (183, 'Conspiracy', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (184, 'Crime', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (185, 'Cyberpunk', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (186, 'Dark', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (187, 'Dark Fantasy', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (188, 'Demons', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (189, 'Destruction', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (190, 'Detective', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (191, 'Dinosaurs', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (192, 'Diplomacy', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (193, 'Dog', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (194, 'Dragons', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (195, 'Dynamic Narration', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (196, 'Economy', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (197, 'Education', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (198, 'Faith', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (199, 'Family Friendly', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (200, 'Fantasy', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (201, 'Foreign', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (202, 'Futuristic', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (203, 'Gambling', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (204, 'Game Development', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (205, 'Gothic', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (206, 'Heist', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (207, 'Historical', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (208, 'Horses', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (209, 'Illuminati', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (210, 'Investigation', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (211, 'Jet', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (212, 'Lemmings', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (213, 'LGBTQ+', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (214, 'Logic', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (215, 'Loot', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (216, 'Lovecraftian', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (217, 'Magic', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (218, 'Management', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (219, 'Mars', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (220, 'Mechs', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (221, 'Medieval', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (222, 'Memes', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (223, 'Military', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (224, 'Modern', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (225, 'Motorbike', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (226, 'Mystery', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (227, 'Mythology', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (228, 'Nature', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (229, 'Naval', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (230, 'Ninja', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (231, 'Offroad', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (232, 'Old School', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (233, 'Otome', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (234, 'Parkour', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (235, 'Philosophical', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (236, 'Pirates', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (237, 'Political', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (238, 'Politics', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (239, 'Pool', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (240, 'Post-apocalyptic', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (241, 'Programming', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (242, 'Retro', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (243, 'Robots', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (244, 'Romance', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (245, 'Rome', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (246, 'Satire', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (247, 'Science', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (248, 'Sci-fi', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (249, 'Sniper', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (250, 'Snow', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (251, 'Space', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (252, 'Stealth', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (253, 'Steampunk', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (254, 'Submarine', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (255, 'Superhero', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (256, 'Supernatural', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (257, 'Surreal', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (258, 'Survival', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (259, 'Swordplay', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (260, 'Tactical', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (261, 'Tanks', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (262, 'Thriller', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (263, 'Time Travel', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (264, 'Trains', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (265, 'Transhumanism', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (266, 'Transportation', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (267, 'Underground', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (268, 'Underwater', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (269, 'Vampire', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (270, 'War', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (271, 'Werewolves', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (272, 'Western', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (273, 'World War I', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (274, 'World War II', 'Themes & Moods');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (275, '6DOF', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (276, 'Archery', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (277, 'Artificial Intelligence', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (278, 'Asymmetric VR', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (279, 'ATV', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (280, 'Automation', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (281, 'Base Building', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (282, 'Boxing', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (283, 'Building', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (284, 'Bullet Time', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (285, 'Character Customization', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (286, 'Choices Matter', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (287, 'Class-Based', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (288, 'Combat', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (289, 'Conversation', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (290, 'Crafting', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (291, 'Deckbuilding', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (292, 'Driving', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (293, 'Fishing', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (294, 'Flight', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (295, 'FMV', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (296, 'Grid-Based Movement', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (297, 'Gun Customization', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (298, 'Hack and Slash', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (299, 'Hacking', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (300, 'Hex Grid', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (301, 'Hunting', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (302, 'Inventory Management', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (303, 'Level Editor', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (304, 'Linear', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (305, 'Martial Arts', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (306, 'Mining', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (307, 'Moddable', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (308, 'Multiple Endings', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (309, 'Music-Based Procedural Generation', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (310, 'Narration', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (311, 'Naval Combat', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (312, 'Nonlinear', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (313, 'Open World', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (314, 'Perma Death', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (315, 'Physics', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (316, 'Procedural Generation', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (317, 'PvE', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (318, 'PvP', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (319, 'Quick-Time Events', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (320, 'Resource Management', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (321, 'Sailing', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (322, 'Score Attack', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (323, 'Stealth', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (324, 'Story Rich', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (325, 'Tabletop', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (326, 'Team-Based', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (327, 'Text-Based', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (328, 'Time Manipulation', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (329, 'Trading', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (330, 'Turn-Based Combat', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (331, 'Turn-Based Tactics', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (332, 'Tutorial', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (333, 'Vehicular Combat', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (334, 'Female Protagonist', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (335, 'Silent Protagonist', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (336, 'Villain Protagonist', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (337, 'Minigames', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (338, 'Intentionally Awkward Controls', 'Feature');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (339, '4 Player Local', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (340, 'Asynchronous Multiplayer', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (341, 'Co-op', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (342, 'Co-op Campaign', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (343, 'Local Co-Op', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (344, 'Local Multiplayer', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (345, 'Massively Multiplayer', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (346, 'Multiplayer', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (347, 'Online Co-Op', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (348, 'Singleplayer', 'Player Group Size');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (349, 'Based on a Novel', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (350, 'Batman', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (351, 'Documentary', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (352, 'Drama', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (353, 'Dungeons &amp; Dragons', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (354, 'Episodic', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (355, 'Experience', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (356, 'Feature Film', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (357, 'Games Workshop', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (358, 'Indie', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (359, 'Lara Croft', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (360, 'LEGO', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (361, 'Mod', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (362, 'Movie', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (363, 'Music', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (364, 'Real-Time', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (365, 'Real-Time with Pause', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (366, 'Remake', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (367, 'Sequel', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (368, 'Soundtrack', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (369, 'Star Wars', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (370, 'Time Attack', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (371, 'Turn-Based', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (372, 'Warhammer 40K', 'Other');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (373, 'Animation & Modeling', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (374, 'Audio Production', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (375, 'Benchmark', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (376, 'Design & Illustration', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (377, 'GameMaker', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (378, 'Gaming', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (379, 'Photo Editing', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (380, 'RPGMaker', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (381, 'Software', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (382, 'Software Training', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (383, 'Utilities', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (384, 'Video Production', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (385, 'Web Publishing', 'Tool');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (386, 'Addictive', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (387, 'Beautiful', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (388, 'Classic', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (389, 'Competitive', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (390, 'Cult Classic', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (391, 'Difficult', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (392, 'Emotional ', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (393, 'Epic', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (394, 'Fast-Paced', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (395, 'Funny', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (396, 'Great Soundtrack', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (397, 'Lore-Rich', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (398, 'Masterpiece', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (399, 'Psychological', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (400, 'Relaxing', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (401, 'Replay Value', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (402, 'Short', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (403, 'Unforgiving', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (404, 'Comedy', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (405, 'Dark Comedy', 'Experience');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (406, 'Blood', 'Content Warning');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (407, 'Gore', 'Content Warning');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (408, 'Mature', 'Content Warning');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (409, 'NSFW', 'Content Warning');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (410, 'Nudity', 'Content Warning');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (411, 'Sexual Content', 'Content Warning');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (412, 'Violent', 'Content Warning');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (413, 'Controller', 'Input');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (414, 'Hardware', 'Input');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (415, 'Mouse only', 'Input');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (416, 'Steam Machine', 'Input');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (417, 'Touch-Friendly', 'Input');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (418, 'TrackIR', 'Input');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (419, 'Voice Control', 'Input');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (420, 'Crowdfunded', 'Funding');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (421, 'Early Access', 'Funding');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (422, 'Free to Play', 'Funding');
INSERT INTO public.Tag OVERRIDING SYSTEM VALUE VALUES (423, 'Kickstarter', 'Funding');


--
-- Data for Name: TagLink; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.TagLink VALUES (1, 1);
INSERT INTO public.TagLink VALUES (1, 2);
INSERT INTO public.TagLink VALUES (2, 1);
INSERT INTO public.TagLink VALUES (2, 2);
INSERT INTO public.TagLink VALUES (3, 2);
INSERT INTO public.TagLink VALUES (3, 3);
INSERT INTO public.TagLink VALUES (4, 2);
INSERT INTO public.TagLink VALUES (4, 3);
INSERT INTO public.TagLink VALUES (5, 4);
INSERT INTO public.TagLink VALUES (6, 4);


--
-- Data for Name: Usuario; Type: TABLE DATA; Schema: public; Owner: ti2cc
--

INSERT INTO public.Usuario OVERRIDING SYSTEM VALUE VALUES (1, 'All_V_2005', 'alvim@esq.com', '\xD6190AAA534471B220D2AB7603A24AEE34257C989712DBB5E6B0819F373A1F54', 2);
INSERT INTO public.Usuario OVERRIDING SYSTEM VALUE VALUES (2, 'Laurencio', 'laur@enc.com', '\x87C9BEF270AB9A108612F90A6894C0B34A84983132431958EB8AE5684C0C98F4', 1);
INSERT INTO public.Usuario OVERRIDING SYSTEM VALUE VALUES (3, 'Joao', 'jojo@oao.com', '\xAF2AF07B3DBC70A6E1523DCCE01641A5F566FA86BCCB963FC43D5B98A456566D', 1);


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
-- Name: TagLink idJogo_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.TagLink
    ADD CONSTRAINT idJogo_fk FOREIGN KEY (idJogo) REFERENCES public.Jogo(idJogo);


--
-- Name: TagLink idTag_fk; Type: FK CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.TagLink
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
