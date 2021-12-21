package entity;

public class Garden {
    private int idgarden;
    private int count_plant_in_garden;
    private int count_cactus_in_garden;
    private int count_flower_in_garden;

    public int getCount_cactus_in_garden() {
        return count_cactus_in_garden;
    }

    public int getCount_flower_in_garden() {
        return count_flower_in_garden;
    }

    public int getCount_plant_in_garden() {
        return count_plant_in_garden;
    }

    public int getIdgarden() {
        return idgarden;
    }

    public void setCount_cactus_in_garden(int count_cactus_in_garden) {
        this.count_cactus_in_garden = count_cactus_in_garden;
    }

    public void setCount_flower_in_garden(int count_flower_in_garden) {
        this.count_flower_in_garden = count_flower_in_garden;
    }

    public void setCount_plant_in_garden(int count_plant_in_garden) {
        this.count_plant_in_garden = count_plant_in_garden;
    }

    public void setIdgarden(int idgarden) {
        this.idgarden = idgarden;
    }
}
