CREATE TABLE t_node(
    nodeid VARCHAR(20) NOT NULL,
    nodeno VARCHAR(10) NOT NULL,
    nodename VARCHAR(30) NOT NULL,
    gpslati REAL NOT NULL,
    gpslong REAL NOT NULL,

    CONSTRAINT pk_t_node PRIMARY KEY(nodeid)
);

CREATE INDEX idx_t_node_nodename ON t_node(nodename);
CREATE INDEX idx_t_node_nodeno ON t_node(nodeno);