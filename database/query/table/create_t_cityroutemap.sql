CREATE TABLE t_cityroutemap (
    citycode INT NOT NULL,
    routeid VARCHAR(20) NOT NULL,

    CONSTRAINT fk_t_cityroutemap_citycode FOREIGN KEY(citycode) REFERENCES t_city(citycode),
    CONSTRAINT fk_t_cityroutemap_routeid FOREIGN KEY(routeid) REFERENCES t_route(routeid)
);