package site.easy.to.build.crm.repository.importcsv;

import org.springframework.data.jpa.repository.JpaRepository;

import site.easy.to.build.crm.entity.importcsv.CustomerRequestImport;


public interface CustomerRequestRepository extends JpaRepository<CustomerRequestImport, Long> {
}
