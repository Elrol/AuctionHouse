package de.cubeisland.AuctionHouse.Commands;

import de.cubeisland.AuctionHouse.AbstractCommand;
import de.cubeisland.AuctionHouse.Auction.Bidder;
import static de.cubeisland.AuctionHouse.AuctionHouse.t;
import de.cubeisland.AuctionHouse.BaseCommand;
import de.cubeisland.AuctionHouse.CommandArgs;
import de.cubeisland.AuctionHouse.Manager;
import de.cubeisland.AuctionHouse.Perm;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Undoing Bids
 * 
 * @author Faithcaio
 */
public class UndoBidCommand extends AbstractCommand
{
    public UndoBidCommand(BaseCommand base)
    {
        super(base, "undobid");
    }

    public boolean execute(CommandSender sender, CommandArgs args)
    {
        if (!Perm.command_undobid.check(sender)) return true;
        if (args.isEmpty())
        {
            sender.sendMessage(t("undo_title1"));
            sender.sendMessage(t("undo_title2"));
            sender.sendMessage(t("undo_title3"));
            sender.sendMessage("");
            return true;
        }
        Player psender = (Player) sender;
        if (args.getString(0).equals("last"))
        {
            Bidder bidder = Bidder.getInstance(psender);
            if (bidder.getlastAuction(bidder)==null)
            {
                sender.sendMessage(t("pro")+" "+t("undo_pro"));
                return true;
            }
            if (bidder.getlastAuction(bidder).undobid(bidder))
            {
                sender.sendMessage(t("i")+" "+t("undo_redeem"));
                return true;
            }
            else
            {
                sender.sendMessage(t("pro")+" "+t("undo_pro"));
                return true;
            }
        }
        if (args.getInt(0) != null)
        {
            Manager manager = Manager.getInstance();
            if (manager.getAuction(args.getInt(0)) == null)
            {
                sender.sendMessage(t("e")+" "+t("auction_no_exist",args.getInt(0)));
                return true;
            }
            if (manager.getAuction(args.getInt(0)).undobid(Bidder.getInstance(psender)))
            {
                sender.sendMessage(t("i")+" "+t("undo_bid_n",args.getInt(0)));
                return true;
            }
            else return true;
        }
        sender.sendMessage(t("e")+" "+t("undo_fail"));
        return true;
    }

    @Override
    public String getUsage()
    {
        return super.getUsage() + " last";
    }

    public String getDescription()
    {
        return t("command_undo");
    }
}
