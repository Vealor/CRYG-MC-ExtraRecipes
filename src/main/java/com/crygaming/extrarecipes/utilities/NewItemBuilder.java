package com.crygaming.extrarecipes.utilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NewItemBuilder {
	private Material type;
	private String name;
	private int amount = 1; //set default
	
	//constructor
	public static NewItemBuilder begin(){
		return new NewItemBuilder();
	}
	
	public NewItemBuilder setName(String name){
		this.name = name;
		return this;
	}
	
	public NewItemBuilder setAmount(int amount){
		this.amount = amount;
		return this;
	}
	
	public NewItemBuilder setType(Material type){
		this.type = type;
		return this;
	}
	
	public ItemStack create(){
		ItemStack itemStack = new ItemStack(this.type, this.amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(this.name);
        itemStack.setItemMeta(meta);
        return itemStack;
	}

}
