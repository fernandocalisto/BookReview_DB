package calistofernando.view;

import calistofernando.factory.CreateDatabase;

public class Main {
    public static void main(String[] args) {
        CreateDatabase cd = new CreateDatabase();
        cd.createDatabase();
    }
}