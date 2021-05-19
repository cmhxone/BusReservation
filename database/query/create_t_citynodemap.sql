CREATE TABLE t_citynodemap (
    citycode INT NOT NULL,
    nodeid VARCHAR(20) NOT NULL,

    CONSTRAINT fk_t_citynodemap_citycode FOREIGN KEY (citycode) REFERENCES t_city(citycode),
    CONSTRAINT fk_t_citynodemap_nodeid FOREIGN KEY(nodeid) REFERENCES t_node(nodeid)
);