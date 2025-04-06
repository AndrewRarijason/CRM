CREATE VIEW vue_statistique_budget AS
SELECT 
    c.customer_id,
    c.name AS customer_name,
    COALESCE(SUM(b.valeur), 0) AS total_budget,
    COALESCE(SUM(d.valeur_depense), 0) AS total_depense,
    (COALESCE(SUM(b.valeur), 0) - COALESCE(SUM(d.valeur_depense), 0)) AS budget_restant
FROM customer c
LEFT JOIN budget b ON c.customer_id = b.customer_id
LEFT JOIN depense d ON c.customer_id = (
    SELECT customer_id FROM trigger_ticket t WHERE t.ticket_id = d.ticket_id LIMIT 1
) OR c.customer_id = (
    SELECT customer_id FROM trigger_lead l WHERE l.lead_id = d.lead_id LIMIT 1
)
GROUP BY c.customer_id, c.name;

exemple:
------------+---------------+--------------+---------------+----------------+
| customer_id | customer_name | total_budget | total_depense | budget_restant |
+-------------+---------------+--------------+---------------+----------------+
|           1 | jean          |      1512.75 |          0.00 |        1512.75 |

CREATE VIEW vue_liste_budget AS
SELECT 
    b.budget_id,
    b.valeur,
    b.date_budget,
    c.customer_id,
    c.name AS customer_name
FROM budget b
JOIN customer c ON b.customer_id = c.customer_id;
+-----------+---------+---------------------+-------------+---------------+
| budget_id | valeur  | date_budget         | customer_id | customer_name |
+-----------+---------+---------------------+-------------+---------------+
|         1 | 1500.75 | 2025-03-22 14:00:00 |           1 | jean          |
|         2 |   12.00 | 2025-03-22 19:39:00 |           1 | jean          |
+-----------+---------+---------------------+-------------+---------------+

CREATE VIEW vue_statistique_depense AS
SELECT 
    c.customer_id,
    c.name AS customer_name,
    COALESCE(SUM(CASE WHEN d.lead_id IS NOT NULL THEN d.valeur_depense ELSE 0 END), 0) AS total_depense_lead,
    COALESCE(SUM(CASE WHEN d.ticket_id IS NOT NULL THEN d.valeur_depense ELSE 0 END), 0) AS total_depense_ticket,
    COALESCE(SUM(d.valeur_depense), 0) AS total_depense
FROM customer c
LEFT JOIN trigger_lead l ON c.customer_id = l.customer_id
LEFT JOIN trigger_ticket t ON c.customer_id = t.customer_id
LEFT JOIN depense d ON d.lead_id = l.lead_id OR d.ticket_id = t.ticket_id
GROUP BY c.customer_id, c.name;

exemple:
    -> GROUP BY c.customer_id, c.name;
+-------------+---------------+--------------------+----------------------+---------------+
| customer_id | customer_name | total_depense_lead | total_depense_ticket | total_depense |
+-------------+---------------+--------------------+----------------------+---------------+
|           1 | jean          |               0.00 |                 0.00 |          0.00 |
+-------------+---------------+--------------------+----------------------+---------------+

