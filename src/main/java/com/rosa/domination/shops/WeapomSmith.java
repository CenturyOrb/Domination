package com.rosa.domination.shops;

import Item.CreateAndStorage.Offense.CustomOffenseManager;
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

public class WeapomSmith implements Listener {

    CustomOffenseManager offenseManager;

    public WeapomSmith(CustomOffenseManager offenseManager)   {

        this.offenseManager = offenseManager;

    }

    @EventHandler
    public void playerRightClickWeaponSmith(PlayerInteractEntityEvent e)   {

        // cancel event from firign twice
        if (!e.getHand().equals(EquipmentSlot.HAND))   { return; }

        Player player = e.getPlayer();
        UUID entityUUID = e.getRightClicked().getUniqueId();

        // if the player clicked on ArmorSmith
        if (!entityUUID.toString().equals("33910d3e-ec77-2678-886b-8bc50b6c280d"))   { return; }

        Merchant merchant = Bukkit.createMerchant("Weapon Smith");

        // setup trading recipes:
        List<MerchantRecipe> merchantRecipes = new ArrayList<MerchantRecipe>();
        MerchantRecipe recipe = new MerchantRecipe(offenseManager.getCustomOffenseItem("Trishula"), 10000); // no max-uses limit
        recipe.setExperienceReward(false); // no experience rewards
        recipe.addIngredient(new ItemStack(Material.DRAGON_EGG, 9));
        merchantRecipes.add(recipe);

        MerchantRecipe recipe2 = new MerchantRecipe(offenseManager.getCustomOffenseItem("Elysium"), 10000); // no max-uses limit
        recipe2.setExperienceReward(false); // no experience rewards
        recipe2.addIngredient(new ItemStack(Material.DRAGON_EGG, 11));
        merchantRecipes.add(recipe2);

        MerchantRecipe recipe3 = new MerchantRecipe(offenseManager.getCustomOffenseItem("Magnar"), 10000); // no max-uses limit
        recipe3.setExperienceReward(false); // no experience rewards
        recipe3.addIngredient(new ItemStack(Material.DRAGON_EGG, 12));
        merchantRecipes.add(recipe3);

        MerchantRecipe recipe4 = new MerchantRecipe(offenseManager.getCustomOffenseItem("Tetherius"), 10000); // no max-uses limit
        recipe4.setExperienceReward(false); // no experience rewards
        recipe4.addIngredient(new ItemStack(Material.DRAGON_EGG, 12));
        merchantRecipes.add(recipe4);

        MerchantRecipe recipe5 = new MerchantRecipe(offenseManager.getCustomOffenseItem("Sorrow Blade"), 10000); // no max-uses limit
        recipe5.setExperienceReward(false); // no experience rewards
        recipe5.addIngredient(new ItemStack(Material.DRAGON_EGG, 11));
        merchantRecipes.add(recipe5);

        MerchantRecipe recipe6 = new MerchantRecipe(offenseManager.getCustomOffenseItem("Ancilia"), 10000); // no max-uses limit
        recipe6.setExperienceReward(false); // no experience rewards
        recipe6.addIngredient(new ItemStack(Material.DRAGON_EGG, 15));
        merchantRecipes.add(recipe6);

        MerchantRecipe recipe7 = new MerchantRecipe(offenseManager.getCustomOffenseItem("Xiphos"), 10000); // no max-uses limit
        recipe7.setExperienceReward(false); // no experience rewards
        recipe7.addIngredient(new ItemStack(Material.DRAGON_EGG, 9));
        merchantRecipes.add(recipe7);

        // apply recipes to merchant:
        merchant.setRecipes(merchantRecipes);

        // open trading window:
        player.openMerchant(merchant, true);

    }

}
