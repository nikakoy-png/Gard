package entity;

public class Flower extends IndoorPlant{
    public int height;

    public int getHeight() { return height; }

    public Flower(int age, String name, boolean bloom, boolean beloved_sun, boolean prolific, int height) {
        super(age, name, bloom, "Flower", beloved_sun, prolific);
        this.height = height;
    }
    @Override
    public String toString() {
        return String.format(
                super.toString() +
                        "\n\tHeight of flower:%s", getHeight() + " ]"
        );
    }
}
