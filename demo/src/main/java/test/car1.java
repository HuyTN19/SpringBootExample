package test;

public class car1 extends Car{
    @Override
    void vehicle() {
        super.vehicle();
        System.out.println("capacity is 6000");
    }

    public static void main(String[] args) {
        Car car1 = new car1();
        car1.vehicle();
    }
}
