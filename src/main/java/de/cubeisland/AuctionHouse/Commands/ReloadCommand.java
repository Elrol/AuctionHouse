package de.cubeisland.AuctionHouse.Commands;

import de.cubeisland.AuctionHouse.AbstractCommand;
import de.cubeisland.AuctionHouse.BaseCommand;
import de.cubeisland.AuctionHouse.CommandArgs;
import org.bukkit.command.CommandSender;
import static de.cubeisland.AuctionHouse.AuctionHouse.t;

/**
 * reload the plugin
 * 
 * @author Faithcaio
 */
public class ReloadCommand extends AbstractCommand
{
 
    public ReloadCommand(BaseCommand base)
    {
        super(base, "reload");
    }
    
    public boolean execute(CommandSender sender, CommandArgs args)
    {
        //TODO reloadIT  
        //TODO do not show in help perm
        /*
         *       auctionhouse.admun.reload: true
      auctionhouse.admin.save: true
         */
        return true;
    }
    
        @Override
    public String getUsage()
    {
        return super.getUsage();
    }

    public String getDescription()
    {
        return t("command_reload");
    }
}
