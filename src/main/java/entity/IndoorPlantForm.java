package entity;

public class IndoorPlantForm {
    private int id_garden;
    private int age;
    private String name;
    private boolean bloom;
    private String kind_of_plant;
    private boolean beloved_sun;
    private boolean prolific;
    private int NumberOfNeedles;
    private int Height;

    public int getAge() {
        return age;
    }

    public boolean getBeloved_sun() {
        return beloved_sun;
    }

    public boolean getBloom() {
        return bloom;
    }

    public int getHeight() {
        return Height;
    }

    public int getId_garden() {
        return id_garden;
    }

    public String getKind_of_plant() {
        return kind_of_plant;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfNeedles() {
        return NumberOfNeedles;
    }

    public boolean getProlific() {
        return prolific;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBeloved_sun(boolean beloved_sun) {
        this.beloved_sun = beloved_sun;
    }

    public void setBloom(boolean bloom) {
        this.bloom = bloom;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public void setId_garden(int id_garden) {
        this.id_garden = id_garden;
    }

    public void setKind_of_plant(String kind_of_plant) {
        this.kind_of_plant = kind_of_plant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfNeedles(int numberOfNeedles) {
        NumberOfNeedles = numberOfNeedles;
    }

    public void setProlific(boolean prolific) {
        this.prolific = prolific;
    }
}
