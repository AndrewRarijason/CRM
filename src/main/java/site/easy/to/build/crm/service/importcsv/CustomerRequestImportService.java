package site.easy.to.build.crm.service.importcsv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.importcsv.CustomerRequestImport;
import site.easy.to.build.crm.repository.importcsv.CustomerRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerRequestImportService {

    @Autowired
    private CustomerRequestRepository repository;

    public List<CustomerRequestImport> getAllRequests() {
        return repository.findAll();
    }

    public Optional<CustomerRequestImport> getRequestById(Long id) {
        return repository.findById(id);
    }

    public CustomerRequestImport saveRequest(CustomerRequestImport request) {
        return repository.save(request);
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}
