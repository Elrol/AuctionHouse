package de.paralleluniverse.Faithcaio.AuctionHouse;

import java.util.HashMap;
import java.util.LinkedList;
import org.apache.commons.lang.time.DateFormatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
/**
 *
 * @author Faithcaio
 */
public class ItemContainer {

    LinkedList<AuctionItem> itemList;
    public final Bidder bidder;
    
    
    public ItemContainer (Bidder bidder)
    {
        this.bidder = bidder;
        this.itemList = new LinkedList<AuctionItem>();        
    }
    
    public void addItem (Auction auction)
    {
        this.itemList.add(new AuctionItem(auction));
    }

    public void giveNextItem ()
    {
        Player player = this.bidder.player.getPlayer();
        AuctionItem auctionItem = this.itemList.pollFirst();
        
        player.sendMessage("Info: Receiving "+auctionItem.item.toString()+
                           " for "+auctionItem.price+
                           " from "+auctionItem.owner+
                           " at "+DateFormatUtils.formatUTC(auctionItem.date, "MMM dd"));
        ItemStack remain = player.getInventory().addItem(auctionItem.item).get(0);
        if (remain==null)
            AuctionHouse.debug("Player: "+player.getName()+": all Items received");
        else
        {
            player.sendMessage("Info: Could not give all Items. Remains are stored again!");
            itemList.add(new AuctionItem(remain,bidder));
        }
            
    }
}