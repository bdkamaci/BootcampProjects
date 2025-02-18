PGDMP      
                |           tourismagency    16.2    16.2 "    5           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            6           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            7           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            8           1262    16768    tourismagency    DATABASE     o   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    16819    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id bigint NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_autopark boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_gym boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16818    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    226            �            1259    16795    pension    TABLE     ~   CREATE TABLE public.pension (
    pension_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    pension_type text NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    16794    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    16803    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    room_id bigint NOT NULL,
    check_in date NOT NULL,
    check_out date NOT NULL,
    guest_count bigint NOT NULL,
    guest_identification text NOT NULL,
    guest_mail text NOT NULL,
    guest_phone text NOT NULL,
    guest_name text NOT NULL,
    guest_count_adult bigint NOT NULL,
    guest_count_children bigint NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16802    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    16811    room    TABLE     �  CREATE TABLE public.room (
    room_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    pension_id bigint NOT NULL,
    season_id bigint NOT NULL,
    room_type text NOT NULL,
    stock bigint NOT NULL,
    adult_price double precision NOT NULL,
    children_price double precision NOT NULL,
    bed_capacity bigint NOT NULL,
    area bigint NOT NULL,
    tv boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    vault boolean NOT NULL,
    projector boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16810    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    16789    season    TABLE     �   CREATE TABLE public.season (
    season_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16788    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16773    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16772    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            2          0    16819    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_mail, hotel_phone, hotel_star, hotel_autopark, hotel_wifi, hotel_pool, hotel_gym, hotel_concierge, hotel_spa, hotel_room_service) FROM stdin;
    public          postgres    false    226    )       ,          0    16795    pension 
   TABLE DATA           E   COPY public.pension (pension_id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    220   f)       .          0    16803    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, check_in, check_out, guest_count, guest_identification, guest_mail, guest_phone, guest_name, guest_count_adult, guest_count_children) FROM stdin;
    public          postgres    false    222   �)       0          0    16811    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_id, pension_id, season_id, room_type, stock, adult_price, children_price, bed_capacity, area, tv, minibar, game_console, vault, projector) FROM stdin;
    public          postgres    false    224   *       *          0    16789    season 
   TABLE DATA           K   COPY public.season (season_id, hotel_id, start_date, end_date) FROM stdin;
    public          postgres    false    218   k*       (          0    16773    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    216   �*       9           0    0    hotel_hotel_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 2, true);
          public          postgres    false    225            :           0    0    pension_pension_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.pension_pension_id_seq', 8, true);
          public          postgres    false    219            ;           0    0    reservation_reservation_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 2, true);
          public          postgres    false    221            <           0    0    room_room_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.room_room_id_seq', 2, true);
          public          postgres    false    223            =           0    0    season_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.season_season_id_seq', 3, true);
          public          postgres    false    217            >           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 6, true);
          public          postgres    false    215            �           2606    16825    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    226            �           2606    16801    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    220            �           2606    16809    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    222            �           2606    16817    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    224            �           2606    16793    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    218            �           2606    16779    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    216            2   6   x�3�I-.�%@�D�%��rj[(�0qjiiqUq�!�,����� t:1      ,   g   x�U�K
�0��0��u�-c�!���q�r��k��
�H��%fM�����m¼+'��3Z��Ό�@�_�v�[�#�h
-��Q9��a2S(���5 <?�!�      .   0   x�3�4�4202�50�5@b�KR�K����K��E��b���� �C      0   >   x�3�4�`O?wW(�`�&�����Vh`�ij�i�il�Y�i ����� �)�      *   ,   x�3�4�4202�50"(�L�؀�.c�14�56����� �	Z      (   J   x�3�LL����4426�tt����2�L�-�ɯLM������G��rs����ch0����e��,�*����� ���     