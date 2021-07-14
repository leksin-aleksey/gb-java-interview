package refactoring;

interface Moveable {

    void move();
}

interface Stopable {

    void stop();
}

interface Openable {
    void open();
}

interface Startable {
    void start();
}

abstract class Car implements Moveable, Stopable, Openable, Startable {

    private Engine engine;
    private String color;
    private String name;

    public Car(Engine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }

    @Override
    public void start() {
        System.out.println("Car starting");
    }

    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

interface Engine{
    float getSize();
}

class DieselEngine implements Engine{
    @Override
    public float getSize() {
        return 3.0f;
    }
}

class LightWeightCar extends Car{

    public LightWeightCar(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    @Override
    public void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

    @Override
    public void stop() {
        System.out.println("Car has been stopped");
    }
}


class Lorry extends Car{

    public Lorry(String color, String name) {
        super(new DieselEngine(), color, name);
    }

    @Override
    public void start() {
        System.out.println("Lorry is starting");
    }

    public void move(){
        System.out.println("Lorry is moving");
    }
    public void stop(){
        System.out.println("Lorry is stop");
    }

    @Override
    public void open() {
        System.out.println("Lorry is always open");
    }
}