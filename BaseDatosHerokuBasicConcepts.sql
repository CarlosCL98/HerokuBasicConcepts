CREATE TABLE public."user" (
	id varchar(500) NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE public.car (
	licenceplate varchar(200) NOT NULL,
	brand varchar(100) NOT NULL,
	CONSTRAINT car_pk PRIMARY KEY (licenceplate)
);
