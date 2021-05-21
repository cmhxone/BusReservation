CREATE VIEW v_node
AS
    SELECT nodeid,
            nodeno,
            nodename,
            gpslati,
            gpslong
    FROM
        t_node