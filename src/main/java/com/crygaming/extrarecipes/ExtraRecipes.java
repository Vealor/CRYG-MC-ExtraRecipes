package com.crygaming.extrarecipes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.*;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;



public class ExtraRecipes extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable(){
		addRecipes();
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){
		
	}
	
	@EventHandler
	public void itemCraft(CraftItemEvent event){
		Player p = (Player) event.getWhoClicked();
		//use this event to subtract specific things from player (such as exp) based on crafting recipe
		
	}
	
	/*
	 * List of Added Recipes
	 */
	private void addRecipes(){
		createEXPBottle();
	}
	
	/*========================================================*/
	/* Recipes Below */
	/*========================================================*/
	private void createEXPBottle(){
		ShapelessRecipe recipeEXPBottle = new ShapelessRecipe(new ItemStack(Material.EXP_BOTTLE));
		recipeEXPBottle.addIngredient(Material.GLASS_BOTTLE).addIngredient(Material.REDSTONE);
		this.getServer().addRecipe(recipeEXPBottle);
		//MUST REMOVE EXP FROM PLAYER WITH CRAFT EVENT!
	}
	
	//Others to add:
	/* Sapling to house?
	 * string to cobweb and vice-versa
	 * slabs to blocks
	 * breakdown tools in anvil to base materials based on durability (different mod maybe?)
	 */
}
