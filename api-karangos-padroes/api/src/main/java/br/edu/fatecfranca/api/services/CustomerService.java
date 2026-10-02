package br.edu.fatecfranca.api.services;


import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;


import br.edu.fatecfranca.api.documents.CnpjDocumentProcessor;
import br.edu.fatecfranca.api.documents.CpfDocumentProcessor;
import br.edu.fatecfranca.api.documents.DocumentProcessor;
import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.repositories.CustomerRepository;


@Service
public class CustomerService {


   private final CustomerRepository repository;


   public CustomerService(CustomerRepository repository) {
       this.repository = repository;
   }


   // Padrão Factory Method: decide, com base na quantidade de dígitos do
   // documento, qual DocumentProcessor concreto deve validar/formatar o
   // identDocument do cliente antes de persistir.
   private String processDocument(String rawDocument) {
       String digits = rawDocument == null ? "" : rawDocument.replaceAll("\\D", "");

       DocumentProcessor processor = digits.length() == 14
               ? new CnpjDocumentProcessor()
               : new CpfDocumentProcessor();

       return processor.validateAndFormat(rawDocument);
   }


   public Customer create(Customer customer) {
       customer.setIdentDocument(processDocument(customer.getIdentDocument()));
       return repository.save(customer);
   }


   public List<Customer> findAll() {
       return repository.findAll();
   }


   public Optional<Customer> findById(Long id) {
       return repository.findById(id);
   }


   public Customer update(Customer customer) {
       customer.setIdentDocument(processDocument(customer.getIdentDocument()));
       return repository.save(customer);
   }


   public boolean existsById(Long id) {
       return repository.existsById(id);
   }


   public void deleteById(Long id) {
       repository.deleteById(id);
   }
}
