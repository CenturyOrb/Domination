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

public class Butcher implements Listener {

    @EventHandler
    public void playerRightClickButcher(PlayerInteractEntityEvent e)   {

        // cancel event from firign twice
        if (!e.getHand().equals(EquipmentSlot.HAND))   { return; }

        Player player = e.getPlayer();
        UUID entityUUID = e.getRightClicked().getUniqueId();

        // if the player clicked on ArmorSmith
        if (!entityUUID.toString().equals("c6e14406-e863-284e-984a-ca7fba0d5fe8"))   { return; }

        Merchant merchant = Bukkit.createMerchant("Butcher");

        // setup trading recipes:
        List<MerchantRecipe> butcherRecipes = new ArrayList<MerchantRecipe>();
        MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Material.COOKED_BEEF, 5), 10000); // no max-uses limit
        recipe.setExperienceReward(false); // no experience rewards
        recipe.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        butcherRecipes.add(recipe);

        MerchantRecipe recipe2 = new MerchantRecipe(new ItemStack(Material.COOKED_PORKCHOP, 5), 10000); // no max-uses limit
        recipe2.setExperienceReward(false); // no experience rewards
        recipe2.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        butcherRecipes.add(recipe2);

        MerchantRecipe recipe3 = new MerchantRecipe(new ItemStack(Material.COOKED_CHICKEN, 6), 10000); // no max-uses limit
        recipe3.setExperienceReward(false); // no experience rewards
        recipe3.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        butcherRecipes.add(recipe3);

        MerchantRecipe recipe4 = new MerchantRecipe(new ItemStack(Material.COOKED_RABBIT, 6), 10000); // no max-uses limit
        recipe4.setExperienceReward(false); // no experience rewards
        recipe4.addIngredient(new ItemStack(Material.DRAGON_EGG, 1));
        butcherRecipes.add(recipe4);

        // apply recipes to merchant:
        merchant.setRecipes(butcherRecipes);

        // open trading window:
        player.openMerchant(merchant, true);
    }

}
