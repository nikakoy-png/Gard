package entity;


public class Cactus extends IndoorPlant {
    private int number_of_needles;

    public int getNumberOfNeedles() {
        return number_of_needles;
    }

    public Cactus(int age, String name, boolean bloom, boolean beloved_sun, boolean prolific, int number_of_needles) {
        super(age, name, bloom, "Cactus", beloved_sun, prolific);
        this.number_of_needles = number_of_needles;
    }

    @Override
    public String toString() {
        return String.format(
                super.toString() +
                        "\n\tNumber of needles:%s", getNumberOfNeedles() + " ]"
        );
    }
}
