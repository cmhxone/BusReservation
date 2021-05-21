CREATE VIEW v_route
AS
    SELECT routeid,
            routeno,
            routetp,
            startnodenm,
            endnodenm,
            startvehicletime,
            endvehicletime
    FROM
        t_route