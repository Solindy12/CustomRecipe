import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.spigotmc.SpigotConfig.attackDamage;

public class main extends JavaPlugin{
    //플러그인 실행 시에 실행되는 부분
    @Override
    public void onEnable(){
        System.out.println("[PluginTest01] Plugin Enabled!");
        Bukkit.addRecipe(DragonSword_GetRecipe());


    }


    //플러그인 종료 시에 실행되는 부분
    @Override
    public void onDisable(){
        System.out.println("[PluginTest01] Plugin Disabled!");
    }

    public  ShapedRecipe DragonSword_GetRecipe() {

        //ItemStack - DragonSword

        AttributeModifier dsmodad = new AttributeModifier(UUID.randomUUID(),"dsAttackDamage", 8.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier dsmodas = new AttributeModifier(UUID.randomUUID(),"dsAttackSpeed", 1.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        ItemStack ds = new ItemStack(Material.DIAMOND_SWORD);
        ds.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
        ItemMeta dsmeta = ds.getItemMeta();
        dsmeta.setDisplayName("§5Dragon Sword");
        //dsmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, dsmodad);
        //dsmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, dsmodas);
        dsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        dsmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        ds.setItemMeta(dsmeta);


        //Shaped Recipe - DragonSword

        NamespacedKey key = new NamespacedKey(this, "diamond_sword");
        ShapedRecipe dssr = new ShapedRecipe(key, ds);
        dssr.shape("bdb", " d ", " s ");
        dssr.setIngredient('b', Material.BLAZE_POWDER);
        dssr.setIngredient('d', Material.DIAMOND);
        dssr.setIngredient('s', Material.STICK);

        return dssr;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("giveitem")){
            if(args.length==0) {
                p.sendMessage("§c[아이템] 매개변수가 누락되었습니다");
            }else if(args[0].equals("item")){
                p.sendMessage("<사용 가능한 아이템 목록>");
                p.sendMessage("");
                p.sendMessage("[§5dragon_sword§f]");
            }
            else if(args[0].equals("dragon_sword")){ // Give Dragon Sword to Player
                AttributeModifier dsmodad = new AttributeModifier(UUID.randomUUID(),"dsAttackDamage", 8.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                AttributeModifier dsmodas = new AttributeModifier(UUID.randomUUID(),"dsAttackSpeed", 1.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                ItemStack ds = new ItemStack(Material.DIAMOND_SWORD);
                ds.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
                ItemMeta dsmeta = ds.getItemMeta();
                dsmeta.setDisplayName("§5Dragon Sword");
                //dsmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, dsmodad);
                //dsmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, dsmodas);
                dsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                dsmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                ds.setItemMeta(dsmeta);
                p.getInventory().addItem(ds);
                p.sendMessage("§5[Dragon Sword] §e아이템을 지급했습니다");
            }
            else{
                p.sendMessage("§c<사용법>");
                p.sendMessage("");
                p.sendMessage("/giveitem [아이템 종류]");
                p.sendMessage("§c사용 가능한 아이템 목록를 확인하려면 §e'/giveitem item'§c을(를) 입력하십시오.");
            }
        }
        return true;
    }
}
