CREATE TABLE customer
(
    dni            varchar(20) NOT NULL,
    full_name       varchar(50) NOT NULL,
    credit_card    varchar(20) NOT NULL,
    total_flights  int NOT NULL,
    total_lodgings int NOT NULL,
    total_tours    int NOT NULL,
    phone_number  varchar(20) NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY ( dni )
);

CREATE TABLE fly
(
    "id"           bigserial NOT NULL,
    origin_lat   decimal NOT NULL,
    origin_lng   decimal NOT NULL,
    destiny_lng  decimal NOT NULL,
    destiny_lat  decimal NOT NULL,
    origin_name  varchar(20) NOT NULL,
    destiny_name varchar(20) NOT NULL,
    aero_line varchar(20) NOT NULL,
    price double precision NOT NULL,
    CONSTRAINT pk_fly PRIMARY KEY ( "id" )
);