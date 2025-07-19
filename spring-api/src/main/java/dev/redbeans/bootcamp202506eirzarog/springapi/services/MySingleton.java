package dev.redbeans.bootcamp202506eirzarog.springapi.services;

public class MySingleton {

    private static MySingleton self = null;

    private MySingleton() {}

    static public MySingleton getInstance() {
        if (self == null) {
            self = new MySingleton();
        }
        return self;
    }
}
