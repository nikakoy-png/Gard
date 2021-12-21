package entity;

abstract class Plant {
    protected int age;
    protected String name;
    protected boolean bloom;
    public Plant(int age, String name, boolean bloom) {
        this.age = age;
        this.bloom = bloom;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean isBloom() {
        return bloom;
    }

    public String Photosynthesis() { ++age; return (name + " is photosynthesising!"); }

    public String toString() {
        return (
                "\n\n\t[ Name of plant:" + name +
                        "\n\tAge of plant:" + age +
                        "\n\tIs bloom:" + bloom
        );
    }
}

