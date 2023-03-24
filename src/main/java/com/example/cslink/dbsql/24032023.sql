use `database_spring_boot_project`;

DELIMITER |
CREATE PROCEDURE get_cosmetologist_reservations(IN cosmetologistId BIGINT, IN procedureId BIGINT, IN customerId BIGINT)
BEGIN
SELECT c.name AS cosmetologist_name, r.appointment_time.start, r.appointment_time.end,
       p.name AS procedure_name, p.price AS procedure_price, p.duration AS procedure_duration,
       u.name AS customer_name, u.phone.number AS customer_phone
FROM reservation r
         JOIN cosmetologist c ON r.cosmetologist_id = c.id
         JOIN procedures p ON r.procedure_id = p.id
         JOIN user_profile u ON r.client_id = u.id
WHERE (cosmetologistId IS NULL OR r.cosmetologist_id = cosmetologistId)
  AND (procedureId IS NULL OR r.procedure_id = procedureId)
  AND (customerId IS NULL OR r.client_id = customerId);
END|
DELIMITER ;