package guru.springframework.sfgpetclinicconor.service.map;

import guru.springframework.sfgpetclinicconor.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        // Nested if Impl (bad - harder to read)
//        if (object != null) {
//            if (object.getId() == null) {
//                object.setId(getNextId());
//            }
//            map.put(object.getId(), object);
//        } else {
//            throw new RuntimeException("Object cannot be null");
//        }

        // Non-nested Control Flow (better: guard clause - easier to read)
        if (object == null) {
            throw new RuntimeException("Object cannot be null");
        }

        if (object.getId() == null) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);

        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {
        // Tutorial Impl (BAD) - Uses Exception Handling for Control Flow (anti-pattern and code smell: https://softwareengineering.stackexchange.com/questions/189222/are-exceptions-as-control-flow-considered-a-serious-antipattern-if-so-why)
//        Long nextId;
//        try {
//            nextId = Collections.max(map.keySet()) + 1;
//        } catch (NoSuchElementException e) {
//            nextId = 1L;
//        }
//        return nextId;

        // Impl from Comment Section (better) - uses Conditional Guard Logic for Control Flow
        if (this.map.isEmpty()) {
            return 1L;
        } else {
            return (long) (map.size() + 1);
        }
    }
}
