package dao;

import entity.Garden;
import entity.IndoorPlant;

import java.sql.SQLException;
import java.util.List;

public interface IDAO {
    void AddPlant(IndoorPlant ip, int id_garden) throws SQLException;
    List<Garden> GetAllGarden() throws SQLException;
    List<Garden> GetGardenByCountPlant(int count_plant) throws SQLException;
    void DeleteGarden(int id_garden) throws SQLException;
    void DeleteIndoorplant(int idplant) throws SQLException;
    void UpdateIndoorplant(int idplant, int age) throws SQLException;
}
