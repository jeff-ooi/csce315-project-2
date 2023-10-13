package cashierpos;

import java.util.*;
import java.io.*;

public class item implements Serializable{
    private String name;
    private double price;
    private Integer id;
    private ArrayList<Integer> addOns;

    public item(String name, double price, Integer id, ArrayList<Integer> addOns) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.addOns = addOns;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<Integer> getAddOns() {
        return addOns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddOns(ArrayList<Integer> addOns) {
        this.addOns = addOns;
    }

    public void addAddOn(Integer addOn) {
        this.addOns.add(addOn);
    }
}
