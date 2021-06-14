CREATE TABLE t_nodeheadto (
    nodeno VARCHAR(10) NOT NULL,
    headto VARCHAR(30)
);

CREATE INDEX idx_t_nodeheadto_nodeno ON t_nodeheadto(nodeno);