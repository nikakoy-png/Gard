package dao;

import entity.Cactus;
import entity.Flower;
import entity.Garden;
import entity.IndoorPlant;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionLDAO implements IDAO{

    private static List<Garden> gardens = new ArrayList<Garden>();
    private static List<IndoorPlant> indoorPlants = new ArrayList<IndoorPlant>();

    static
    {
        Garden garden = new Garden();
        garden.setCount_flower_in_garden(4);
        garden.setCount_cactus_in_garden(7);
        garden.setIdgarden(4);
        garden.setCount_plant_in_garden(11);
        gardens.add(garden);
    }


    @Override
    public void AddPlant(IndoorPlant ip, int id_garden) throws SQLException {
        indoorPlants.add(ip);
        Garden garden = new Garden();
        garden.setCount_flower_in_garden(1);
        garden.setCount_cactus_in_garden(0);
        garden.setIdgarden(id_garden);
        garden.setCount_plant_in_garden(1);
        gardens.add(garden);
    }

    @Override
    public List<Garden> GetAllGarden() throws SQLException {
        return gardens;
    }

    @Override
    public List<Garden> GetGardenByCountPlant(int count_plant) throws SQLException {
        List<Garden> resgr = new ArrayList<Garden>();

        for (Garden gr : gardens) {
            if (gr.getCount_plant_in_garden() == (count_plant)) {
                resgr.add(gr);
            }
        }
        if (resgr.size() == 0) {
            return null;
        } else {
            return resgr;
        }
    }

    @Override
    public void DeleteGarden(int id_garden) throws SQLException {
        gardens = gardens.stream().filter(stud -> stud.getIdgarden() == (id_garden) == false).collect(Collectors.toList());
    }

    @Override
    public void DeleteIndoorplant(int idplant) throws SQLException {

    }

    @Override
    public void UpdateIndoorplant(int idplant, int age) throws SQLException {

    }

//    @Override
//    public void DeleteIndoorplant(int idplant) throws SQLException {
//
//    }
//
//    @Override
//    public void UpdateIndoorplant(int idplant, int age) throws SQLException {
//
//    }
}
