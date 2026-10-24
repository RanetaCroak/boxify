package com.albcas.boxify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Inventory {
    private List<Item> items;


    public Inventory() {
        this.items = new ArrayList<Item>();
    }

    // methods

    public void addItem(Item item){
        this.items.add(item);
        System.out.println("Added "+item.getName());
    }

    public void deleteItem(Item item) {
        if (this.items.remove(item)) {
            System.out.println("Removed: " + item.getName());
        } else {
            System.out.println("Item not found: " + item.getName());
        }
    }

    public Item lookupItem(UUID id){
        for (Item item : items) {
            if (item.getId().equals(id)){ // Check each ID
                return item;
            }
        }
        System.out.println("Item not found. ID:"+id);
        return null;
    }

    public String listItems(){
        StringBuilder itemList = new StringBuilder();

        for (Item item : items) {
            itemList.append(item.getName()).append("\n");
        }

        return itemList.toString();
    }
}
