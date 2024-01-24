package socialnetwork.repository.memory;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID,E> {

    // the validator is made of the value that you give (Utilizatori,Prietenie)
    // the map is made from the id that you give (if Utilizatori id = long, if Prietenie id = tuple<Long,Long>
    // and the E is the entity (if Utilizatori E = of tipe utiliaztori and if Prietenie E = tipe Prietenie)

    private Validator<E> validator;
    protected Map<ID,E> entities;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }

    @Override
    public E findOne(ID id){
        if (id==null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) {
        if (entity==null)
            throw new IllegalArgumentException("entity must be not null");
        validator.validate(entity);
        if(entities.get(entity.getId()) != null) {
            throw new IllegalArgumentException("this id is already in use");
           // System.out.println("this id is already in use");
            //return entity;
        }
        else entities.put(entity.getId(),entity);
        return null;
    }

    @Override
    public E delete(ID id) {

        if(id == null)
            throw new IllegalArgumentException("id must not be null");
        //validator.validate(id);
        else if(entities.get(id) == null)
            throw new IllegalArgumentException("id is not in the list");


        return entities.remove(id);

    }

    @Override
    public E update(E entity) {

        if(entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        validator.validate(entity);

        entities.put(entity.getId(),entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(),entity);
            return null;
        }
        return entity;

    }
//    public void  ceva()
//    {
//        System.out.println("dada");
//    }


//    @Override
//    public void add_friendship(Long id1, Long id2) {};
//
//    @Override
//    public void add_friends(Long id1, Long id2) {};


}
