package de.paralleluniverse.Faithcaio.AuctionHouse.Commands;

import de.paralleluniverse.Faithcaio.AuctionHouse.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Faithcaio
 */
public class RemoveCommand extends AbstractCommand
{    
    private static final AuctionHouse plugin = AuctionHouse.getInstance();
    private static final AuctionHouseConfiguration config = plugin.getConfigurations();
    
    public RemoveCommand(BaseCommand base)
    {
        super("remove", base);
    }


    public boolean execute(CommandSender sender, String[] args)
    {
        if (args.length < 1)
        {
            sender.sendMessage("/ah remove <AuctionID>");
            sender.sendMessage("/ah remove all <Player>");
            return false;
        }
        int id = 0;
        try {id = Integer.parseInt(args[0]); }
        catch (NumberFormatException ex) 
        { 
            if ( (args[0] != "all") || (args.length < 2) )
                return false; 
            //else check Player...
            Player player = plugin.getServer().getPlayer(args[1]);
            if (player == null) return false;
            sender.sendMessage("Debug:delete per Player");
            //TODO Permission
            if(!(Bidder.getInstance(player).activeBids.isEmpty()))
            {    
                int bids = Bidder.getInstance(player).activeBids.size();
                for (int i=0; i<bids; ++i)
                    Bidder.getInstance(player).activeBids.get(i).abortAuction();
                sender.sendMessage("Info:Deleted "+String.valueOf(bids)+" auctions of "+player.toString());
                return true;
            }
        }
        //TODO Permission
        sender.sendMessage("Debug:delete per Id");
        AuctionManager.getInstance().getAuction(id).abortAuction();      
        sender.sendMessage("Info:Deleted auction #"+String.valueOf(id));
        return true;
    }

    @Override
    public String getDescription()
    {
        return "Removes an auction.";
    }
}