package pl.softwaregods.listener;

import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostCountEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BoostListener extends ListenerAdapter {

    @Override
    public void onGuildUpdateBoostCount(@NotNull GuildUpdateBoostCountEvent e) {
        //e.getGuild().get
    }
}
