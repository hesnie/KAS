package model;

import model.service.Service;
import model.model.Conference;

public class App {

    public static void main(String[] args) {

        Service s1 = new Service();
        s1.initContent();

        // s1.printTest();
        System.out.println();
        s1.printTest2();

        System.out.println();
        s1.printTest3();
        System.out.println();
        s1.printTest4();
        System.out.println();
        s1.printTest3();
        System.out.println();

        s1.printTest4();
        System.out.println();
    }
}