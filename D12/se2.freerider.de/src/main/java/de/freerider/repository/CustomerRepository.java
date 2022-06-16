package de.freerider.repository;

import de.freerider.datamodel.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Component
public class CustomerRepository implements CrudRepository<Customer, Long> {
    private HashMap<Long, Customer> repo = new HashMap<Long, Customer>();

    @Override
    public <S extends Customer> S save(S entity) {
        if (entity != null) {
            return (S) repo.put(entity.getId(), (Customer) entity);
        }
        return null;
    }

    @Override
    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
        ArrayList<Customer> temp = new ArrayList<>();
        if ((S) entities != null) {
            for (Customer c : entities) {
                temp.add(c);
                save(c);
            }
            return (Iterable<S>) temp;
        }
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        if (aLong == null) return false;
        return repo.containsKey(aLong);
    }

    @Override
    public Optional<Customer> findById(Long aLong) {
        if (aLong == null) return Optional.empty();
        return Optional.of(repo.get(aLong));
    }

    @Override
    public Iterable<Customer> findAll() {
        return (Iterable<Customer>) repo.values();
    }

    @Override
    public Iterable<Customer> findAllById(Iterable<Long> longs) {
        ArrayList<Customer> temp = new ArrayList<>();
        if (longs == null) return null;
        for (Long l : longs) {
            Customer c = repo.get(l);
            if (c != null) temp.add(c);
        }
        return temp;
    }

    @Override
    public long count() {
        return repo.size();
    }

    @Override
    public void deleteById(Long aLong) {
        if (aLong == null) return;
        repo.remove(aLong);
    }

    @Override
    public void delete(Customer entity) {
        if (entity != null) {
            for (Long l : repo.keySet()) {
                if (entity.equals(repo.get(l))) deleteById(l);
            }
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        if (longs == null) return;
        for (Long l : longs) {
            deleteById(l);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {
        if (entities == null) return;
        for (Customer c : entities) {
            delete(c);
        }
    }

    @Override
    public void deleteAll() {
        repo.clear();
    }
}
