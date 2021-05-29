CREATE VIEW v_reservation
AS
    SELECT nodeid,
           routeid
    FROM
        t_reservation;