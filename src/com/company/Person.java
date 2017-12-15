package com.company;

/**
 * Created by _kbluue_ on 9/20/2017.
 */
public class Person {

    Person() {

    }

    public Person(String sex) {
        this.sex = sex;
    }

    Person(String sex,String name){
        this.sex = sex;
        this.name = name;
    }

    public String name;

    private int age = 0;

    public String religion;

    public String sex;

    public String relationship;

    public boolean canSee;

    public void setName(String newName){
        name = newName;
    }

    public void printName(){
        System.out.println("My name is "+name);
    }

    public static void main(String[] args){
        Person baby001 = new Person("male");
        Person baby002 = new Person("female", "Eve");
        Person baby003 = new Person();
        baby001.setName("Adam");
        baby001.printName();
        int out = 2;
}

}