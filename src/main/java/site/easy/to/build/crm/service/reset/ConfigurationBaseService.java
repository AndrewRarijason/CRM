package site.easy.to.build.crm.service.reset;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConfigurationBaseService {
    private final JdbcTemplate jdbcTemplate;
    public ConfigurationBaseService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void truncateAllTables() {
        // Désactiver temporairement les contraintes de clés étrangères
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");

        // Récupérer toutes les tables de la base de données
        List<String> tables = jdbcTemplate.queryForList("SHOW TABLES", String.class);

        // Liste des tables à ne pas supprimer
        List<String> tablesAExclure = List.of("roles","oauth_users","user_profile","user_roles","users");

        // Truncate chaque table sauf celles à exclure
        for (String table : tables) {
            if (!tablesAExclure.contains(table)) {
                jdbcTemplate.execute("TRUNCATE TABLE " + table);
            }
        }

        // Réactiver les contraintes de clés étrangères
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }

}
