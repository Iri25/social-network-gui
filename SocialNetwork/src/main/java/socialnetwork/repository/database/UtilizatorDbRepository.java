package socialnetwork.repository.database;

import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UtilizatorDbRepository implements Repository<Long, Utilizator> {
    private String url;
    private String username;
    private String password;
    private Validator<Utilizator> validator;

    public UtilizatorDbRepository(String url, String username, String password, Validator<Utilizator> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }
    @Override
    public Utilizator findOne(Long aLong) {
        String q = "SELECT * FROM users where id = ?";


        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,aLong.intValue());
            ResultSet resultSet = statement.executeQuery();
            //System.out.println(resultSet);


            if(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("nume");
                String lastName = resultSet.getString("prenume");

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                Utilizator utilizator = new Utilizator(firstName, lastName,username,password,email);
                utilizator.setId(id);
                return utilizator;
            }
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterable<Utilizator> findAll() {
        Set<Utilizator> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("nume");
                String lastName = resultSet.getString("prenume");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                Utilizator utilizator = new Utilizator(firstName, lastName,username,password,email);
                utilizator.setId(id);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Utilizator save(Utilizator entity) {
        String q = "INSERT INTO users Values(?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,entity.getId().intValue());
            statement.setString(2,entity.getFirstName());
            statement.setString(3,entity.getLastName());
            statement.setString(4,entity.getusername());
            statement.setString(5,entity.getpassword());
            statement.setString(6,entity.getEmail());

            statement.executeUpdate();


        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Utilizator  delete(Long aLong) {
        if(findOne(aLong)==null)
            throw new IllegalArgumentException("nu este acest id");
        Utilizator ret=findOne(aLong);

        String q = "DELETE from users where id=?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,aLong.intValue());
            statement.executeUpdate();

        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return ret;
    }


    @Override
    public Utilizator update(Utilizator entity) {
        return null;
    }
}
