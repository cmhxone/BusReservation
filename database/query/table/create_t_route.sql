CREATE TABLE t_route (
    routeid VARCHAR(20) NOT NULL,
    routeno VARCHAR(10) NOT NULL,
    routetp VARCHAR(20) NOT NULL,
    startnodenm VARCHAR(30) NOT NULL,
    endnodenm VARCHAR(30) NOT NULL,
    startvehicletime VARCHAR(4) NOT NULL,
    endvehicletime VARCHAR(4) NOT NULL,

    CONSTRAINT pk_t_route PRIMARY KEY(routeid)
);
