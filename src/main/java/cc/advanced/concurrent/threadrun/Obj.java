package cc.advanced.concurrent.threadrun;

/**
 * Yukino
 * 2020/6/1
 */
public class Obj {
    private String name;
    private int age;

    public Obj() {
    }

    public Obj(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
