package entity;


public class IndoorPlant extends Plant {
    protected boolean prolific;
    protected boolean beloved_sun;
    protected String kind_of_plant;
    private int NumberOfNeedles;

    public IndoorPlant(int age, String name, boolean bloom, String kind_of_plant, boolean beloved_sun, boolean prolific) {
        super(age, name, bloom);
        this.kind_of_plant = kind_of_plant;
        this.beloved_sun = beloved_sun;
        this.prolific = prolific;
    }

    public String getKind_of_plant() {
        return kind_of_plant;
    }

    public boolean isBeloved_sun() {
        return beloved_sun;
    }

    public boolean isProlific() {
        return prolific;
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() +
                        "\n\tbeloved_sun of plant:" + beloved_sun +
                        "\n\tprolific of plant:" + prolific +
                        "\n\tkind_of_plant:%s", kind_of_plant
        );
    }
}