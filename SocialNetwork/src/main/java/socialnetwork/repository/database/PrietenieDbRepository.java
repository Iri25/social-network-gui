package socialnetwork.repository.database;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PrietenieDbRepository implements Repository<Tuple<Long,Long>, Prietenie> {
    private String url;
    private String username;
    private String password;
    private Validator<Utilizator> validator;

    public PrietenieDbRepository(String url, String username, String password, Validator<Utilizator> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }


    @Override
    public Prietenie findOne(Tuple<Long, Long> id) {
        String q = "SELECT * FROM prietenii where id1 = ? and id2 =?";


        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,id.getLeft().intValue());
            statement.setInt(2,id.getRight().intValue());
            ResultSet resultSet = statement.executeQuery();

            //System.out.println(resultSet);

            if(resultSet.next()) {
                Long id1 = resultSet.getLong("id1");
                Long id2 = resultSet.getLong("id2");
                LocalDateTime date = LocalDateTime.parse(resultSet.getString("data"));

                Prietenie  prietenie = new Prietenie(date);
                prietenie.setId(new Tuple(id1,id2));


                return prietenie;
            }
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Iterable<Prietenie> findAll() {
        Set<Prietenie> prietenii = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from prietenii");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id1 = resultSet.getLong("id1");
                Long id2 = resultSet.getLong("id2");
                LocalDateTime date = LocalDateTime.parse(resultSet.getString("data"));
                Prietenie  prietenie = new Prietenie(date);
                prietenie.setId(new Tuple(id1,id2));

                prietenii.add(prietenie);

            }
            return prietenii;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prietenii;
    }

    @Override
    public Prietenie save(Prietenie entity) {
        String q = "INSERT INTO prietenii Values(?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,entity.getId().getLeft().intValue());
            statement.setInt(2,entity.getId().getRight().intValue());
            statement.setString(3,entity.getDate().toString());

            statement.executeUpdate();

        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Prietenie delete(Tuple<Long, Long> id) {
        if(findOne(id)==null)
            throw new IllegalArgumentException("nu sunt aceste id uri");
        Prietenie ret=findOne(id);

        String q = "DELETE from prietenii where id1=? and id2 = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,id.getLeft().intValue());
            statement.setInt(2,id.getRight().intValue());
            statement.executeUpdate();


        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return ret;
    }



    @Override
    public Prietenie update(Prietenie entity) {
        return null;
    }



}
