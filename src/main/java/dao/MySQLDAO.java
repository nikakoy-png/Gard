package dao;

import entity.Cactus;
import entity.Flower;
import entity.Garden;
import entity.IndoorPlant;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MySQLDAO extends Config implements IDAO {

    public static Connection con = null;

    private static final String GET_ALL_GARDEN = "SELECT * FROM garden";
    private static final String GET_GARDEN_BY_IDGARDEN = "SELECT * FROM garden WHERE idgarden = ?";
    private static final String GET_INDOORPLANT = "SELECT * FROM indoorplant WHERE idplant = ?";
    private static final String GET_GARDEN_BY_COUNT_PLANT = "SELECT * FROM garden WHERE count_plant_in_garden > ?";
    private static final String GET_COUNT_ALL_GARDEN = "SELECT COUNT(*) AS total FROM garden";
    private static final String ADD_GARDEN = "INSERT INTO garden (idgarden, count_plant_in_garden, count_cactus_in_garden, count_flower_in_garden) VALUES (?, 0, 0, 0)";
    private static final String ADD_INDOORPLANT = "INSERT INTO indoorplant (garden_idgarden, age, name, bloom, kind_of_plant, beloved_sun, prolific) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_FLOWER = "INSERT INTO flower (IndoorPlant_idplant, height) VALUE (?, ?)";
    private static final String ADD_CACTUS = "INSERT INTO cactus (IndoorPlant_idplant, number_of_needles) VALUE (?, ?)";
    private static final String UPDATE_GARDEN_INCREMENT_FL = "UPDATE garden SET count_flower_in_garden = count_flower_in_garden + 1, count_plant_in_garden = count_plant_in_garden + 1 WHERE idgarden = ?";
    private static final String UPDATE_GARDEN_INCREMENT_CT = "UPDATE garden SET count_plant_in_garden = count_plant_in_garden + 1, count_cactus_in_garden = count_cactus_in_garden + 1 WHERE idgarden = ?";
    private static final String UPDATE_GARDEN_DECREMENT_FL = "UPDATE garden SET count_flower_in_garden = count_flower_in_garden - 1, count_plant_in_garden = count_plant_in_garden - 1 WHERE idgarden = ?";
    private static final String UPDATE_GARDEN_DECREMENT_CT = "UPDATE garden SET count_plant_in_garden = count_plant_in_garden - 1, count_cactus_in_garden = count_cactus_in_garden - 1 WHERE idgarden = ?";
    private static final String UPDATE_INDOORPLANT = "UPDATE indoorplant SET age = ? WHERE idplant = ?";
    private static final String DELETE_GARDEN = "DELETE FROM garden WHERE idgarden = ?";
    private static final String DELETE_INDOORPLANT = "DELETE FROM indoorplant WHERE idplant = ?";

    public MySQLDAO() {
        String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        String username = dbUser;
        String password = dbPass;

        System.out.println("Connecting database...");

        System.out.println("Loading driver...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }



    }

    private ResultSet ThrowQuery_sel(String query) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            return ps.executeQuery();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    private void addFlower(Flower ip, int id_Id, int id_garden) throws SQLException {
        con.setAutoCommit(false);
        try (PreparedStatement ps_4 = con.prepareStatement(ADD_FLOWER)) {
            ps_4.setInt(1, id_Id);
            ps_4.setInt(2, ip.getHeight());
            ps_4.executeUpdate();
        }
        try (PreparedStatement ps_5 = con.prepareStatement(UPDATE_GARDEN_INCREMENT_FL)) {
            ps_5.setInt(1, id_garden);
            ps_5.executeUpdate();
        }
        con.commit();
    }

    private void addCactus(Cactus ip, int id_Id, int id_garden) throws SQLException {
        con.setAutoCommit(false);
        try (PreparedStatement ps_4 = con.prepareStatement(ADD_CACTUS)) {
            ps_4.setInt(1, id_Id);
            ps_4.setInt(2, ip.getNumberOfNeedles());
            ps_4.executeUpdate();
        }
        try (PreparedStatement ps_5 = con.prepareStatement(UPDATE_GARDEN_INCREMENT_CT)) {
            ps_5.setInt(1, id_garden);
            ps_5.executeUpdate();
        }
        con.commit();
    }

    @Override
    public void AddPlant(IndoorPlant ip, int id_garden) throws SQLException {
        con.setAutoCommit(false);
        try (PreparedStatement ps_1 = con.prepareStatement(GET_GARDEN_BY_IDGARDEN)) {
            ps_1.setInt(1, id_garden);
            if (!ps_1.executeQuery().next()) {
                try (PreparedStatement ps_2 = con.prepareStatement(ADD_GARDEN)) {
                    ps_2.setString(1, String.valueOf(id_garden));
                    ps_2.executeUpdate();
                }
            }
        }
        try (PreparedStatement ps_3 = con.prepareStatement(ADD_INDOORPLANT, Statement.RETURN_GENERATED_KEYS)) {
            ps_3.setInt(1, id_garden);
            ps_3.setInt(2, ip.getAge());
            ps_3.setString(3, ip.getName());
            ps_3.setBoolean(4, ip.isBloom());
            ps_3.setString(5, ip.getKind_of_plant());
            ps_3.setBoolean(6, ip.isBeloved_sun());
            ps_3.setBoolean(7, ip.isProlific());
            ps_3.executeUpdate();
            try(ResultSet key = ps_3.getGeneratedKeys()) {
                key.next();
                int id_Id = key.getInt(1);
                if (("Flower").equals(ip.getKind_of_plant())) {
                    this.addFlower((Flower) ip, id_Id, id_garden);
                }
                if (("Cactus").equals(ip.getKind_of_plant())) {
                    this.addCactus((Cactus) ip, id_Id, id_garden);
                }
            }
        }
        con.commit();
    }

    @Override
    public List<Garden> GetAllGarden() throws SQLException {
        try (PreparedStatement ps_1 = con.prepareStatement(GET_COUNT_ALL_GARDEN)){
            try (ResultSet rs_1 = ps_1.executeQuery()){
                rs_1.next();
                int count_garden = rs_1.getInt("total");
                System.out.println("=========================\n" +
                                   "Count garden : " + count_garden +
                                   "\n=========================");
            }
        }

        List<Garden> list = new ArrayList<Garden>();

        try (ResultSet rs_ = ThrowQuery_sel(GET_ALL_GARDEN)) {
            while (rs_.next()) {
                Garden garden = new Garden();
                garden.setIdgarden(rs_.getInt("idgarden"));
                garden.setCount_plant_in_garden(rs_.getInt("count_plant_in_garden"));
                garden.setCount_cactus_in_garden(rs_.getInt("count_cactus_in_garden"));
                garden.setCount_flower_in_garden(rs_.getInt("count_flower_in_garden"));

                list.add(garden);
            }
        }
        return list;
    }

    @Override
    public void DeleteIndoorplant(int idplant) throws SQLException {
        con.setAutoCommit(false);
        try (PreparedStatement ps_1 = con.prepareStatement(GET_INDOORPLANT)) {
            ps_1.setInt(1, idplant);
            try (ResultSet rs_1 = ps_1.executeQuery()) {
                rs_1.next();
                int id_garden = rs_1.getInt(2);
                String result = rs_1.getString("kind_of_plant");
                try (PreparedStatement ps_3 = result.equals("Flower") ? con.prepareStatement(UPDATE_GARDEN_DECREMENT_FL) : con.prepareStatement(UPDATE_GARDEN_DECREMENT_CT)) {
                    ps_3.setInt(1, id_garden);
                    ps_3.executeUpdate();
                    try (PreparedStatement ps_2 = con.prepareStatement(DELETE_INDOORPLANT)) {
                        ps_2.setInt(1, idplant);
                        ps_2.executeUpdate();
                        ps_2.close();
                        ps_3.executeUpdate();
                        con.commit();
                    }
                }
            }
        }
    }
    @Override
    public void UpdateIndoorplant(int idplant, int age) throws SQLException {
        con.setAutoCommit(false);
        try (PreparedStatement ps_1 = con.prepareStatement(UPDATE_INDOORPLANT)) {
            ps_1.setInt(1, age);
            ps_1.setInt(2, idplant);
            ps_1.executeUpdate();
            con.commit();
        }
    }

    @Override
    public void DeleteGarden(int id_garden) throws SQLException {
        con.setAutoCommit(false);
        try (PreparedStatement ps_1 = con.prepareStatement(DELETE_GARDEN)) {
            ps_1.setInt(1, id_garden);
            ps_1.executeUpdate();
            con.commit();
        }
    }

    @Override
    public List<Garden> GetGardenByCountPlant(int count_plant) throws SQLException {
        List<Garden> list = new ArrayList<Garden>();
        System.out.println("list");
        con.setAutoCommit(false);
        try (CallableStatement cs_1 = con.prepareCall(GET_GARDEN_BY_COUNT_PLANT)) {
            cs_1.setInt(1, count_plant);
            try (ResultSet rs_1 = cs_1.executeQuery()) {
                while (rs_1.next()) {
                    Garden garden = new Garden();
                    garden.setIdgarden(rs_1.getInt("idgarden"));
                    garden.setCount_plant_in_garden(rs_1.getInt("count_plant_in_garden"));
                    garden.setCount_cactus_in_garden(rs_1.getInt("count_cactus_in_garden"));
                    garden.setCount_flower_in_garden(rs_1.getInt("count_flower_in_garden"));
                    list.add(garden);
                    System.out.println(list);
                }
                con.commit();
            }
        }
        return list;
    }
}