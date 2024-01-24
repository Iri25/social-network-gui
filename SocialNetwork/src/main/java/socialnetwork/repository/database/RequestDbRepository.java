package socialnetwork.repository.database;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Request;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class RequestDbRepository implements Repository<Tuple<Long,Long>, Request> {
    private String url;
    private String username;
    private String password;
    private Validator<Utilizator> validator;

    public RequestDbRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

    }



    @Override
    public Request findOne(Tuple<Long, Long> tuple) {
        String q = "SELECT * FROM requests where request = ? and recive = ?";


        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,tuple.getLeft().intValue());
            statement.setInt(2,tuple.getRight().intValue());
            ResultSet resultSet = statement.executeQuery();
//            System.out.println("rares");
            //System.out.println(resultSet);


            if(resultSet.next()) {
                Long request = resultSet.getLong("request");
                Long recive = resultSet.getLong("recive");
                String status = resultSet.getString("status");
                LocalDateTime date = LocalDateTime.parse(resultSet.getString("data"));
                Request req =  new Request(request,recive,status,date);
                req.setId(new Tuple(request,recive));
                return req;

            }
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterable<Request> findAll() {
        String q = "SELECT * from requests";

        Set<Request> reqs = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = connection.prepareStatement(q);
            ResultSet resultSet = statement.executeQuery();
            //System.out.println(resultSet);

            while(resultSet.next()) {
                Long request = resultSet.getLong("request");
                Long recive = resultSet.getLong("recive");
                String status = resultSet.getString("status");
                LocalDateTime date = LocalDateTime.parse(resultSet.getString("data"));

                Request req =  new Request(request,recive,status,date);
                req.setId(new Tuple(request,recive));

                reqs.add(req);

            }
            return reqs;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return reqs;
    }

    @Override
    public Request save(Request entity) {
        System.out.println("salvam");
        String q = "INSERT INTO requests Values(?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,entity.getWhoRequested().intValue());
            statement.setInt(2,entity.getWhoRecived().intValue());
            statement.setString(3,entity.getStatus());
            statement.setString(4,entity.getdata().toString());

            statement.executeUpdate();


        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Request delete(Tuple<Long, Long>tuple) {
        if(findOne(tuple)==null)
            throw new IllegalArgumentException("this id dont exists...");
        Request req=findOne(tuple);

        String q = "DELETE from requests where request = ? and recive = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1,tuple.getLeft().intValue());
            statement.setInt(2,tuple.getRight().intValue());
            statement.executeUpdate();

        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return req;
    }

    @Override
    public Request update(Request entity) {
        Tuple<Long,Long> tuple = entity.getId();
        if(findOne(tuple)==null)
            throw new IllegalArgumentException("this id dont exists...");

        String q = "Update requests set status = ? where request=? and recive = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setString(1,entity.getStatus());
            statement.setInt(2,entity.getWhoRequested().intValue());
            statement.setInt(3,entity.getWhoRecived().intValue());
            statement.executeUpdate();

        }catch(SQLException e)
        {
            e.printStackTrace();
        }


        return findOne(tuple);
    }
}
