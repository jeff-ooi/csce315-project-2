package cashierpos;

import java.util.*;
import java.io.*;

public class item implements Serializable {
    private String name;
    private double price;
    private Integer id;
    private ArrayList<Integer> addOns;
    private ArrayList<String> addOnAmount;
    private ArrayList<String> addOnName = new ArrayList<String>();

    public item(String name, double price, Integer id, ArrayList<Integer> addOns, ArrayList<String> addOnAmount) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.addOns = addOns;
        this.addOnAmount = addOnAmount;
        this.addOnName.add("Coconut Jelly");
        this.addOnName.add("Snow Velvet");
        this.addOnName.add("Rainbow Jelly");
        this.addOnName.add("Brown Sugar Boba");
        this.addOnName.add("Regular Boba");
        this.addOnName.add("Crystal Boba");
        this.addOnName.add("Creme Brulee");
        this.addOnName.add("Strawberry");
        this.addOnName.add("Orange");
        this.addOnName.add("Lychee Bits");
        this.addOnName.add("Mango");
        this.addOnName.add("Green Tea Jelly");
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

    public void addAddOnAmount(String addOnAmount) {
        this.addOnAmount.add(addOnAmount);
    }

    public String getAddOnName(Integer addOn) {
        return this.addOnName.get(addOn);
    }

    public String getAddOnAmount(int addOn) {
        return this.addOnAmount.get(addOn);
    }
}
