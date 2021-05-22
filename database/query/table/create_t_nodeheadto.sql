CREATE TABLE t_nodeheadto (
    nodeno VARCHAR(10) NOT NULL,
    headto VARCHAR(30) DEFAULT '',

    CONSTRAINT fk_t_nodeheadto_nodeid FOREIGN KEY(nodeid) REFERENCES t_node(nodeid)
);