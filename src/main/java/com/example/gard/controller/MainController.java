package com.example.gard.controller;

import dao.*;
import dao.TDAO;
import entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;


@Controller
public class MainController {
    private IDAO dao = DAOFactory.getDAOInstance(TDAO.MySQL);
//    private IDAO dao = DAOFactory.getDAOInstance(TDAO.COLLECTION);
    @GetMapping("/allGarden")
    public String showAllStudents(Model model) throws SQLException {
        List<Garden> list = dao.GetAllGarden();
        model.addAttribute("allGardens", list);
        return "allGarden";
    }

    @RequestMapping(value={"/addIndoorPlant"}, method = RequestMethod.GET)
    public String addPlant(Model model) {
        IndoorPlantForm addPlant = new IndoorPlantForm();
        model.addAttribute("IndoorPlantForm", addPlant);
        return "addIndoorPlantPage";
    }

    @RequestMapping(value = { "/addIndoorPlant" }, method = RequestMethod.POST)
    public String add_plant(Model model, IndoorPlantForm indoorPlantForm) throws SQLException {
        IndoorPlant id = null;



        Flower ob = new Flower(indoorPlantForm.getAge(), indoorPlantForm.getName(), indoorPlantForm.getBloom(),
                indoorPlantForm.getBeloved_sun(), indoorPlantForm.getProlific(), indoorPlantForm.getHeight());

        id = ob;

        if ("Cactus".equals(indoorPlantForm.getKind_of_plant())) {

            Cactus ob_ = new Cactus(indoorPlantForm.getAge(), indoorPlantForm.getName(), indoorPlantForm.getBloom(),
                    indoorPlantForm.getBeloved_sun(), indoorPlantForm.getProlific(), indoorPlantForm.getNumberOfNeedles());

            id = ob_;
        }

        dao.AddPlant(id, indoorPlantForm.getId_garden());
        return "redirect:/allGarden";
    }

    @RequestMapping(value = { "/deleteGarden" }, method = RequestMethod.GET)
    public String showDeleteGardenView(Model model) {

        deleteGardenForm delGardenForm = new deleteGardenForm();
        model.addAttribute("deleteGardenForm", delGardenForm);

        return "delGarden";
    }

    @RequestMapping(value = { "/deleteGarden" }, method = RequestMethod.POST)
    public String deleteGarden(Model model, deleteGardenForm delGardenForm) throws SQLException {

        dao.DeleteGarden(delGardenForm.getId_garden());

        return "redirect:/allGarden";
    }

    @RequestMapping(value = { "/gardenByCountPlant" }, method = RequestMethod.GET)
    public String showGetGardenByCountPlant(Model model) {

        GetGardenByCountPlantForm GForm = new GetGardenByCountPlantForm();

        model.addAttribute("GetGardenByCountPlantForm", GForm);

        return "getGardenByCountPlant";
    }

    @RequestMapping(value = { "/gardenByCountPlant" }, method = RequestMethod.POST)
    public String GardenByCountPlant(Model model, GetGardenByCountPlantForm GForm) throws SQLException {

        List<Garden> list = dao.GetGardenByCountPlant(GForm.getCount_plant());

        model.addAttribute("allGardens", list);

        return "allGarden";

    }

    @GetMapping("/")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("title", "Main Page");
        return "index";
    }
}
