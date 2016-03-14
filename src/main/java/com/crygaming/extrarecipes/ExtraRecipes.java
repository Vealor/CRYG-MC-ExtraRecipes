package com.crygaming.extrarecipes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.inventory.ItemStack;
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
	public void onDisable(){}
	
	@EventHandler
	public void tossEXPBottle(ExpBottleEvent event){
		//possible? if toss is type of bottle, then change to diff exp give
		//maybe...
		
	}
	
	@EventHandler
    public void expChange(PlayerExpChangeEvent event) {
		
	}
	
	@EventHandler
	public void itemCraft(CraftItemEvent event){
		Player p = (Player) event.getWhoClicked();
		Material result = event.getRecipe().getResult().getType();
		ItemStack[] sourceItems = event.getInventory().getContents();
		
		
		//use this event to subtract specific things from player (such as exp) based on crafting recipe
		p.sendMessage("YERH CRAFTIN");
		p.sendMessage(craftedEXPBottle.getClass().toString());
		
		if(result == Material.EXP_BOTTLE){
			int redstoneCheck = 0;
			int glassBottleCheck = 0;
			int resultCheck = 0;
			int other = 0;
			for(int i=0;i<sourceItems.length;i++){
				if(sourceItems[i].getType() == Material.REDSTONE){
					p.sendMessage("redstone");
					redstoneCheck++;
				}else if(sourceItems[i].getType() == Material.GLASS_BOTTLE){
					p.sendMessage("bottle");
					glassBottleCheck++;
				}else if(sourceItems[i].getType() == Material.EXP_BOTTLE){
					p.sendMessage("result");
					resultCheck++;
				}else if(sourceItems[i].getType() != Material.AIR){
					p.sendMessage(sourceItems[i].getType().toString());
					other++;
				}
			}
			p.sendMessage(redstoneCheck + " " + glassBottleCheck + " "+ other);
			if(redstoneCheck == 1 && glassBottleCheck == 1 && resultCheck == 1 && other == 0){
				//remove exp from player
				p.sendMessage("YOU GOT AN EXP BOTTLE");
			}else{
				p.sendMessage("Canceled craft!");
				event.setCancelled(true);
			}			
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
		craftedEXPBottle = NewItemBuilder.begin().setName("Crafted EXP Bottle").setType(Material.EXP_BOTTLE).create();
		
		recipeEXPBottle = new ShapelessRecipe(craftedEXPBottle);
		recipeEXPBottle.addIngredient(Material.GLASS_BOTTLE).addIngredient(Material.REDSTONE);
		this.getServer().addRecipe(recipeEXPBottle);
		//MUST REMOVE EXP FROM PLAYER WITH CRAFT EVENT!
	}
	
	//Others to add:
	/* Sapling to house?
	 * string to cobweb and vice-versa
	 * cook bones to make blaze rods?
	 * slime and redstone to make token, cook token into fire charge
	 * slabs to blocks
	 * breakdown tools in anvil to base materials based on durability (different mod maybe?)
	 */
}
