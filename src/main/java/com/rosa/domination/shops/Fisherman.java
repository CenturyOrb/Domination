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

public class Fisherman implements Listener {

    @EventHandler
    public void playerRightClickFisherman(PlayerInteractEntityEvent e)   {

        // cancel event from firign twice
        if (!e.getHand().equals(EquipmentSlot.HAND))   { return; }

        Player player = e.getPlayer();
        UUID entityUUID = e.getRightClicked().getUniqueId();

        // if the player clicked on ArmorSmith
        if (!entityUUID.toString().equals("0e882737-2e4d-2dff-a04b-465e10d7d31b"))   { return; }

        Merchant merchant = Bukkit.createMerchant("Fisherman");

        // setup trading recipes:
        List<MerchantRecipe> fishRecipes = new ArrayList<MerchantRecipe>();
        MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Material.COOKED_COD, 8), 10000); // no max-uses limit
        recipe.setExperienceReward(false); // no experience rewards
        recipe.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        fishRecipes.add(recipe);

        MerchantRecipe recipe2 = new MerchantRecipe(new ItemStack(Material.COOKED_SALMON, 7), 10000); // no max-uses limit
        recipe2.setExperienceReward(false); // no experience rewards
        recipe2.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        fishRecipes.add(recipe2);

        MerchantRecipe recipe3 = new MerchantRecipe(new ItemStack(Material.PUFFERFISH, 1), 10000); // no max-uses limit
        recipe3.setExperienceReward(false); // no experience rewards
        recipe3.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        fishRecipes.add(recipe3);

        // apply recipes to merchant:
        merchant.setRecipes(fishRecipes);

        // open trading window:
        player.openMerchant(merchant, true);

    }

}
