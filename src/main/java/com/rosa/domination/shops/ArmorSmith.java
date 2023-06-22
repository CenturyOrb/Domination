package com.rosa.domination.shops;

import Item.CreateAndStorage.Armor.CustomArmorManager;
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

public class ArmorSmith implements Listener {

    CustomArmorManager armorManager;

    public ArmorSmith(CustomArmorManager armorManager)   {

        this.armorManager = armorManager;

    }

    @EventHandler
    public void playerRightClickArmorSmith(PlayerInteractEntityEvent e)   {

        // cancel event from firign twice
        if (!e.getHand().equals(EquipmentSlot.HAND))   { return; }

        Player player = e.getPlayer();
        UUID entityUUID = e.getRightClicked().getUniqueId();

        // if the player clicked on ArmorSmith
        if (!entityUUID.toString().equals("ce6d94b0-8d7e-2381-ab7a-f12f4d9ee369"))   { return; }

        Merchant merchant = Bukkit.createMerchant("Armor Smith");

        // setup trading recipes:
        List<MerchantRecipe> armorRecipes = new ArrayList<MerchantRecipe>();
        MerchantRecipe recipe = new MerchantRecipe(armorManager.getCustomArmorItem("Corinthian"), 10000); // no max-uses limit
        recipe.setExperienceReward(false); // no experience rewards
        recipe.addIngredient(new ItemStack(Material.DRAGON_EGG, 14));
        armorRecipes.add(recipe);

        MerchantRecipe recipe2 = new MerchantRecipe(armorManager.getCustomArmorItem("Aphaia"), 10000); // no max-uses limit
        recipe2.setExperienceReward(false); // no experience rewards
        recipe2.addIngredient(new ItemStack(Material.DRAGON_EGG, 14));
        armorRecipes.add(recipe2);

        MerchantRecipe recipe17 = new MerchantRecipe(armorManager.getCustomArmorItem("Rooks Decree"), 10000); // no max-uses limit
        recipe17.setExperienceReward(false); // no experience rewards
        recipe17.addIngredient(new ItemStack(Material.DRAGON_EGG, 15));
        armorRecipes.add(recipe17);

        MerchantRecipe recipe3 = new MerchantRecipe(armorManager.getCustomArmorItem("Thorax"), 10000); // no max-uses limit
        recipe3.setExperienceReward(false); // no experience rewards
        recipe3.addIngredient(new ItemStack(Material.DRAGON_EGG, 14));
        armorRecipes.add(recipe3);

        MerchantRecipe recipe4 = new MerchantRecipe(armorManager.getCustomArmorItem("Halcyon Chargers"), 10000); // no max-uses limit
        recipe4.setExperienceReward(false); // no experience rewards
        recipe4.addIngredient(new ItemStack(Material.DRAGON_EGG, 13));
        armorRecipes.add(recipe4);

        MerchantRecipe recipe5 = new MerchantRecipe(armorManager.getCustomArmorItem("Illrian"), 10000); // no max-uses limit
        recipe5.setExperienceReward(false); // no experience rewards
        recipe5.addIngredient(new ItemStack(Material.DRAGON_EGG, 13));
        armorRecipes.add(recipe5);

        MerchantRecipe recipe6 = new MerchantRecipe(armorManager.getCustomArmorItem("Guardian Plates"), 10000); // no max-uses limit
        recipe6.setExperienceReward(false); // no experience rewards
        recipe6.addIngredient(new ItemStack(Material.DRAGON_EGG, 12));
        armorRecipes.add(recipe6);

        MerchantRecipe recipe7 = new MerchantRecipe(armorManager.getCustomArmorItem("Chausses"), 10000); // no max-uses limit
        recipe7.setExperienceReward(false); // no experience rewards
        recipe7.addIngredient(new ItemStack(Material.DRAGON_EGG, 11));
        armorRecipes.add(recipe7);

        MerchantRecipe recipe8 = new MerchantRecipe(armorManager.getCustomArmorItem("War Treads"), 10000); // no max-uses limit
        recipe8.setExperienceReward(false); // no experience rewards
        recipe8.addIngredient(new ItemStack(Material.DRAGON_EGG, 11));
        armorRecipes.add(recipe8);

        MerchantRecipe recipe9 = new MerchantRecipe(armorManager.getCustomArmorItem("Rogue Hood"), 10000); // no max-uses limit
        recipe9.setExperienceReward(false); // no experience rewards
        recipe9.addIngredient(new ItemStack(Material.DRAGON_EGG, 12));
        armorRecipes.add(recipe9);

        MerchantRecipe recipe10 = new MerchantRecipe(armorManager.getCustomArmorItem("Rogue Mantle"), 10000); // no max-uses limit
        recipe10.setExperienceReward(false); // no experience rewards
        recipe10.addIngredient(new ItemStack(Material.DRAGON_EGG, 12));
        armorRecipes.add(recipe10);

        MerchantRecipe recipe11 = new MerchantRecipe(armorManager.getCustomArmorItem("Rogue Trousers"), 10000); // no max-uses limit
        recipe11.setExperienceReward(false); // no experience rewards
        recipe11.addIngredient(new ItemStack(Material.DRAGON_EGG, 12));
        armorRecipes.add(recipe11);

        MerchantRecipe recipe12 = new MerchantRecipe(armorManager.getCustomArmorItem("Rogue Boots"), 10000); // no max-uses limit
        recipe12.setExperienceReward(false); // no experience rewards
        recipe12.addIngredient(new ItemStack(Material.DRAGON_EGG, 13));
        armorRecipes.add(recipe12);

        MerchantRecipe recipe13 = new MerchantRecipe(armorManager.getCustomArmorItem("Hekaerge"), 10000); // no max-uses limit
        recipe13.setExperienceReward(false); // no experience rewards
        recipe13.addIngredient(new ItemStack(Material.DRAGON_EGG, 10));
        armorRecipes.add(recipe13);

        MerchantRecipe recipe14 = new MerchantRecipe(armorManager.getCustomArmorItem("Oupis Cloak"), 10000); // no max-uses limit
        recipe14.setExperienceReward(false); // no experience rewards
        recipe14.addIngredient(new ItemStack(Material.DRAGON_EGG, 10));
        armorRecipes.add(recipe14);

        MerchantRecipe recipe15 = new MerchantRecipe(armorManager.getCustomArmorItem("Loxo"), 10000); // no max-uses limit
        recipe15.setExperienceReward(false); // no experience rewards
        recipe15.addIngredient(new ItemStack(Material.DRAGON_EGG, 10));
        armorRecipes.add(recipe15);

        MerchantRecipe recipe16 = new MerchantRecipe(armorManager.getCustomArmorItem("Delos"), 10000); // no max-uses limit
        recipe16.setExperienceReward(false); // no experience rewards
        recipe16.addIngredient(new ItemStack(Material.DRAGON_EGG, 11));
        armorRecipes.add(recipe16);

        // apply recipes to merchant:
        merchant.setRecipes(armorRecipes);

        // open trading window:
        player.openMerchant(merchant, true);

    }

}
