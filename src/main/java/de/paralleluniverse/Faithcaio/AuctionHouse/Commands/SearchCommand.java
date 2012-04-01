package de.paralleluniverse.Faithcaio.AuctionHouse.Commands;

import de.paralleluniverse.Faithcaio.AuctionHouse.*;
import java.util.List;
import org.apache.commons.lang.time.DateFormatUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Faithcaio
 */
public class SearchCommand extends AbstractCommand
{
    public SearchCommand(BaseCommand base)
    {
        super("search", base);
    }
    public boolean execute(CommandSender sender, String[] args)
    {
        if (args.length < 1)
        {
            sender.sendMessage("/ah search <Item> [s:<date|id|price>]");
            return false;
        }
        Arguments arguments = new Arguments(args);
        List<Auction> auctionlist;
        
        if (arguments.getString("1") == null) return false;
        if (AuctionHouse.debugMode) sender.sendMessage("Debug: Search for: "+arguments.getString("1"));
        if (arguments.getMaterial("1") != null)
        {
            if (AuctionHouse.debugMode) sender.sendMessage("Debug: Item detected: "+arguments.getMaterial("1").toString());
            auctionlist = AuctionManager.getInstance().getAuctionItems(arguments.getMaterial("1"));
        }
        else
        {
            sender.sendMessage("Error: Item does not exist!");
            return true;
        }
        if (arguments.getString("s")!=null)
        {
            AuctionSort sorter = new AuctionSort();
            //TODO anders
            if (arguments.getString("s").equalsIgnoreCase("date"))
                sorter.SortAuction(auctionlist, "date");   
            if (arguments.getString("s").equalsIgnoreCase("id"))
                sorter.SortAuction(auctionlist, "id");   
            if (arguments.getString("s").equalsIgnoreCase("price"))
                sorter.SortAuction(auctionlist, "price"); 
        }        
        this.sendInfo(sender, auctionlist);
        return true;    
    }
    
    public void sendInfo(CommandSender sender,List<Auction> auctionlist)
    {
        int max = auctionlist.size();
        for (int i=0;i<max;++i)
        {
        sender.sendMessage("#"+auctionlist.get(i).id+": "+auctionlist.get(i).item.toString()+
                           " Leading Bidder: "+auctionlist.get(i).bids.peek().getBidder().toString()+
                           "with "+String.valueOf(auctionlist.get(i).bids.peek().getAmount())+
                           "Auction ends: "+DateFormatUtils.format(auctionlist.get(i).auctionEnd, "dd/MM/yy HH:mm")
                //TODO TimeString in config
                          );       
        }
    }

    @Override
    public String getDescription()
    {
        return "Finds Auctions with Item in it. Sorting optional.";
    }
    
    public String getUsage()
    {
        return "/ah search <Item> [s:<date|id|price>]";
    }
}