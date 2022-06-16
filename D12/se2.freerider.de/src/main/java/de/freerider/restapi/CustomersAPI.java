package de.freerider.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/api/v1/customers") // endpoint for /customers resource collection
public interface CustomersAPI {
    /**
     * GET /customers
     * @return JSON Array with customers (compact).
     */
    @RequestMapping(method= RequestMethod.GET, value="", produces={"application/json"})
    ResponseEntity<List<?>> getCustomers();
    /**
     * GET /customers/{id}
     * @return JSON Array with customers (compact).
     */
    @RequestMapping(method=RequestMethod.GET, value="{id}",
            produces={"application/json"})
    ResponseEntity<?> getCustomer(@PathVariable("id") long id );
}
