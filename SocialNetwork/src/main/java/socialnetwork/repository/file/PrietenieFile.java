package socialnetwork.repository.file;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PrietenieFile extends AbstractFileRepository<Tuple<Long,Long>, Prietenie>{


    public PrietenieFile(String fileName, Validator<Prietenie> validator) {
        super(fileName, validator);
    }

    @Override
    public Prietenie extractEntity(List<String> attributes) {
        Tuple pair = new Tuple(Long.parseLong(attributes.get(0)),Long.parseLong(attributes.get(1)));
        Prietenie p = new Prietenie(LocalDateTime.parse(attributes.get(2)));
        p.setId(pair);

        return p;
    }

    @Override
    protected String createEntityAsString(Prietenie entity) {
        System.out.println(entity.getId());
        System.out.println("in priet create");
        return entity.getId().getLeft()+";"+entity.getId().getRight()+";"+entity.getDate();
    }




}
