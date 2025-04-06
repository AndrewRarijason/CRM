package site.easy.to.build.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Seuil;

@Repository
public interface SeuilRepository extends JpaRepository<Seuil, Integer> {
}
