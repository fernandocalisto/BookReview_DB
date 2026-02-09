package calistofernando.view;

import calistofernando.factory.CreateDatabase;

public class Main {
    public static void main(String[] args) {
        new CreateDatabase().createDatabase();
        App app = new App();
        app.run();
    }
}