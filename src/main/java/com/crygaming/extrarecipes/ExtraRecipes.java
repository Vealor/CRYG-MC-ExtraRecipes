package com.crygaming.extrarecipes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Need to rework, discovered that exp bottles give
 * a varying quantity of EXP.  Will need to make a
 * custom EXP bottle that has a set quantity of EXP.
 */


public class ExtraRecipes extends JavaPlugin implements Listener {
	
	public static ShapelessRecipe recipeEXPBottle;
	
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
		Material result = event.getRecipe().getResult().getType();
		ItemStack[] sourceItems = event.getInventory().getContents();
		
		
		//use this event to subtract specific things from player (such as exp) based on crafting recipe
		p.sendMessage("YERH CRAFTIN");
		
		if(result == Material.EXP_BOTTLE){
			//possible check for ingredients from sourceItems?
//			p.
			p.sendMessage("YOU GOT AN EXP BOTTLE");
		}
		
		
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
		recipeEXPBottle = new ShapelessRecipe(new ItemStack(Material.EXP_BOTTLE));
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
