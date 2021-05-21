CREATE TABLE t_noderoutemap (
    nodeid VARCHAR(20) NOT NULL,
    routeid VARCHAR(20) NOT NULL,

    CONSTRAINT fk_t_noderoutemap_nodeid FOREIGN KEY(nodeid) REFERENCES t_node(nodeid),
    CONSTRAINT fk_t_noderoutemap_routeid FOREIGN KEY(routeid) REFERENCES t_route(routeid)
);