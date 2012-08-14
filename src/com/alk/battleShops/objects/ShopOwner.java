package com.alk.battleShops.objects;

import java.io.Serializable;

import org.bukkit.entity.Player;

import com.alk.battleShops.BattleShops;
import com.alk.battleShops.Defaults;
import com.alk.battleShops.controllers.PermissionController;
/**
 * 
 * @author alkarin
 *
 */
public class ShopOwner implements Serializable{
	private static final long serialVersionUID = 1L;
	String name;
	
	public ShopOwner(){}

	public ShopOwner(String player){
		if (PermissionController.isAdmin(player))
			this.name = Defaults.ADMIN_NAME;
		else
			this.name = player;
	}
	public ShopOwner(Player player){
		if (PermissionController.isAdmin(player))
			this.name = Defaults.ADMIN_NAME;
		else
			this.name = player.getName();
	}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;

        ShopOwner owner = (ShopOwner) obj;
        return getName().compareTo(owner.getName()) == 0;
	}
 
 	public int hashCode(){return name.hashCode();}
 	
	public static boolean sameOwner(ShopOwner owner1, ShopOwner owner2) {return owner1.equals(owner2);}
	public boolean sameOwner(Player player) {
		return getName().equalsIgnoreCase(player.getName());
	}

    public static void sendMsgToOwner(ShopOwner so, String msg){
        if(isAdmin(so)){
            return;}
        Player player = BattleShops.getBukkitServer().getPlayer(so.getName());
        if(player == null){
            return;}
        player.sendMessage(msg);
    }

	public boolean isAdmin() {return ShopOwner.isAdmin(name);}
 	static boolean isAdmin(Player player) {return ShopOwner.isAdmin(player.getDisplayName());}
 	static boolean isAdmin(ShopOwner so) {return ShopOwner.isAdmin(so.getName());}
	public static boolean isAdmin(String name){
		return name.equals(Defaults.ADMIN_NAME);
	}
	
	public String getKey() {return ShopOwner.getShopOwnerKey(this);}
	public static String getShopOwnerKey(ShopOwner p){return p.getName();}

	public String toString(){return name;}


}