package com.crygaming.extrarecipes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.crygaming.extrarecipes.utilities.NewItemBuilder;

/*
 * Need to rework, discovered that exp bottles give
 * a varying quantity of EXP.  Will need to make a
 * custom EXP bottle that has a set quantity of EXP.
 * 
 * Could possibly rework that ALL exp bottles give 
 * the same exp quantity.  Means could revert to
 * original craft item and have listener/method
 * to remove exp from player?
 */


public class ExtraRecipes extends JavaPlugin implements Listener {
	
	public static ShapelessRecipe recipeEXPBottle;
	public static ItemStack craftedEXPBottle;	
	
	@Override
	public void onEnable(){
		addRecipes();
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){
		this.getServer().clearRecipes();
	}
	
//	@EventHandler
//	public void tossEXPBottle(ExpBottleEvent event){
//		//possible? if toss is type of bottle, then change to diff exp give
//		//maybe...cd 
//		
//	}
	
//	@EventHandler
//    public void expChange(PlayerExpChangeEvent event) {
//		
//	}
	
	@EventHandler
	public void itemCraft(CraftItemEvent event){
		Player p = (Player) event.getWhoClicked();
		Material result = event.getRecipe().getResult().getType();
		ItemStack[] sourceItems = event.getInventory().getContents();
		
		this.getLogger().info(p.toString() + " Crafted " + result.toString());
		
		
		//use this event to subtract specific things from player (such as exp) based on crafting recipe
//		p.sendMessage("YERH CRAFTIN");
//		p.sendMessage(craftedEXPBottle.getClass().toString());
		
//		if(result == Material.EXP_BOTTLE){}
//		if(result == Material.ELYTRA){}
		
	}
	
	/*
	 * List of Added Recipes
	 * Comment out if want to remove recipe temporarily
	 */
	private void addRecipes(){
		
		//new recipes
//		createEXPBottle();
		createElytra();			//crafts Elytra wings
		wool2String();			//changes wool to string
		
		//conversions
		swapStringWeb();		//changes web to string, and string to web
		
	}
	
	/*========================================================*/
	/* Recipes Below */
	/*========================================================*/
	//	private void createEXPBottle(){
	//		craftedEXPBottle = NewItemBuilder.begin().setName("Crafted EXP Bottle").setType(Material.EXP_BOTTLE).create();
	//		recipeEXPBottle = new ShapelessRecipe(craftedEXPBottle);
	//		recipeEXPBottle.addIngredient(Material.GLASS_BOTTLE).addIngredient(Material.REDSTONE);
	//		this.getServer().addRecipe(recipeEXPBottle);
	//		//MUST REMOVE EXP FROM PLAYER WITH CRAFT EVENT!
	//		//must make recipe/potion a consumable!
	//	}
	
	private void createElytra(){
		ShapedRecipe recipeElytra;
		ItemStack craftedElytra;
		craftedElytra = new ItemStack(Material.ELYTRA);
		recipeElytra = new ShapedRecipe(craftedElytra);
		recipeElytra.shape("SLS","FTF","FTF");
		recipeElytra.setIngredient('L', Material.LEATHER);
		recipeElytra.setIngredient('S', Material.STICK);
		recipeElytra.setIngredient('F', Material.FEATHER);
		recipeElytra.setIngredient('T', Material.STRING);
		this.getServer().addRecipe(recipeElytra);
	}
	
	private void swapStringWeb(){
		ShapelessRecipe web2string;
		web2string = new ShapelessRecipe(new ItemStack(Material.STRING, 9));
		web2string.addIngredient(Material.WEB);
		this.getServer().addRecipe(web2string);
		
		ShapedRecipe string2web;
		string2web = new ShapedRecipe(new ItemStack(Material.WEB));
		string2web.shape("SSS","SSS","SSS");
		string2web.setIngredient('S', Material.STRING);
		this.getServer().addRecipe(string2web);
	}
	
	private void wool2String(){
		ShapelessRecipe wool2string;
		wool2string = new ShapelessRecipe(new ItemStack(Material.STRING, 4));
		wool2string.addIngredient(Material.WOOL);
		this.getServer().addRecipe(wool2string);
	}
	
	
	
	//Others to add:
	/* Sapling to house?
	 * string to cobweb and vice-versa DONE
	 * cook bones to make blaze rods?
	 * slime and redstone to make token, cook token into fire charge??
	 * slabs to blocks???
	 * breakdown tools in anvil to base materials based on durability (different mod maybe?)
	 * compressed cobblestone -> bedrock
	 * bedrock x9 -> diamond
	 * compressed cobblestone + redstone -> obsidian
	 */
}
