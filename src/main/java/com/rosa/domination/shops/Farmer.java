package com.rosa.domination.shops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Farmer implements Listener {

    @EventHandler
    public void playerRightClickFarmer(PlayerInteractEntityEvent e)   {

        // cancel event from firign twice
        if (!e.getHand().equals(EquipmentSlot.HAND))   { return; }

        Player player = e.getPlayer();
        UUID entityUUID = e.getRightClicked().getUniqueId();

        // if the player clicked on ArmorSmith
        if (!entityUUID.toString().equals("980d812d-0dd6-2978-be47-58d72f1d7d6f"))   { return; }

        Merchant merchant = Bukkit.createMerchant("Farmer");

        // setup trading recipes:
        List<MerchantRecipe> farmerRecipes = new ArrayList<MerchantRecipe>();
        MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Material.BREAD, 6), 10000); // no max-uses limit
        recipe.setExperienceReward(false); // no experience rewards
        recipe.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        farmerRecipes.add(recipe);

        MerchantRecipe recipe2 = new MerchantRecipe(new ItemStack(Material.APPLE, 7), 10000); // no max-uses limit
        recipe2.setExperienceReward(false); // no experience rewards
        recipe2.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        farmerRecipes.add(recipe2);

        MerchantRecipe recipe3 = new MerchantRecipe(new ItemStack(Material.MUSHROOM_STEW, 5), 10000); // no max-uses limit
        recipe3.setExperienceReward(false); // no experience rewards
        recipe3.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        farmerRecipes.add(recipe3);

        MerchantRecipe recipe4 = new MerchantRecipe(new ItemStack(Material.BAKED_POTATO, 6), 10000); // no max-uses limit
        recipe4.setExperienceReward(false); // no experience rewards
        recipe4.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        farmerRecipes.add(recipe4);

        MerchantRecipe recipe5 = new MerchantRecipe(new ItemStack(Material.PUMPKIN_PIE, 6), 10000); // no max-uses limit
        recipe5.setExperienceReward(false); // no experience rewards
        recipe5.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        farmerRecipes.add(recipe5);

        MerchantRecipe recipe6 = new MerchantRecipe(new ItemStack(Material.PUMPKIN_PIE, 4), 10000); // no max-uses limit
        recipe6.setExperienceReward(false); // no experience rewards
        recipe6.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        farmerRecipes.add(recipe6);

        // apply recipes to merchant:
        merchant.setRecipes(farmerRecipes);

        // open trading window:
        player.openMerchant(merchant, true);
    }

}
