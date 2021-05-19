CREATE TABLE t_driver (
    driverid VARCHAR(20) NOT NULL,
    drivername VARCHAR(30) NOT NULL,

    CONSTRAINT pk_t_driver PRIMARY KEY(driverid)
);

CREATE INDEX idx_t_driver_drivername ON t_driver(drivername);

INSERT INTO t_driver(
                     driverid,
                     drivername)
VALUES (
        'JJB50027346',
        '조무현'
       )