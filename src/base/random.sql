DELIMITER //

CREATE PROCEDURE GenerateRandomData(IN nombreDeLigne INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE max_users INT;
    DECLARE max_customers INT;
    DECLARE last_lead_id INT;
    DECLARE last_ticket_id INT;

    -- Vérifier le nombre d'enregistrements
SELECT COUNT(*) INTO max_users FROM users;
SELECT COUNT(*) INTO max_customers FROM customer;

-- Vérifier si les tables contiennent des données
IF max_users = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Aucun utilisateur trouvé dans la table users';
END IF;

    IF max_customers = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Aucun client trouvé dans la table customer';
END IF;

    WHILE i < nombreDeLigne DO
        -- Sélectionner un ID existant au hasard
        SET @random_user_id = (SELECT id FROM users ORDER BY RAND() LIMIT 1);
        SET @random_customer_id = (SELECT customer_id FROM customer ORDER BY RAND() LIMIT 1);

        -- Insertion dans trigger_lead
INSERT INTO trigger_lead (customer_id, user_id, name, phone, status, created_at)
VALUES (
           @random_customer_id,
           @random_user_id,
           CONCAT('LeadName', i),
           CONCAT('+123456789', FLOOR(RAND() * 100)),
           CASE FLOOR(RAND() * 4)
               WHEN 0 THEN 'meeting-to-schedule'
               WHEN 1 THEN 'assign-to-sales'
               WHEN 2 THEN 'archived'
               ELSE 'success'
               END,
           NOW() - INTERVAL FLOOR(RAND() * 30) DAY
       );
SET last_lead_id = LAST_INSERT_ID();

        -- Insertion dans trigger_ticket
INSERT INTO trigger_ticket (customer_id, manager_id, subject, description, status, created_at)
VALUES (
           @random_customer_id,
           @random_user_id,
           CONCAT('TicketSubject', i),
           CONCAT('Description ', i),
           CASE FLOOR(RAND() * 9)
               WHEN 0 THEN 'open'
               WHEN 1 THEN 'assigned'
               WHEN 2 THEN 'on-hold'
               WHEN 3 THEN 'in-progress'
               WHEN 4 THEN 'resolved'
               WHEN 5 THEN 'closed'
               WHEN 6 THEN 'reopened'
               WHEN 7 THEN 'pending-customer-response'
               ELSE 'escalated'
               END,
           NOW() - INTERVAL FLOOR(RAND() * 30) DAY
       );
SET last_ticket_id = LAST_INSERT_ID();

        -- Insertion dans budget
INSERT INTO budget (customer_id, valeur, date_budget)
VALUES (
           @random_customer_id,
           ROUND(RAND() * 1000, 2),
           NOW() - INTERVAL FLOOR(RAND() * 30) DAY
       );

-- Insertion dans depense
INSERT INTO depense (valeur_depense, date_depense, etat, lead_id, ticket_id)
VALUES (
           ROUND(RAND() * 500, 2),
           NOW() - INTERVAL FLOOR(RAND() * 30) DAY,
           FLOOR(RAND() * 3),
           IF(RAND() > 0.5, last_lead_id, NULL),
           IF(RAND() > 0.5, last_ticket_id, NULL)
       );

SET i = i + 1;
END WHILE;
END //

DELIMITER ;
