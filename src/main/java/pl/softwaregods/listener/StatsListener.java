package pl.softwaregods.listener;

import lombok.NonNull;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import pl.softwaregods.config.Config;

public class StatsListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e) {
        VoiceChannel all = e.getGuild().getVoiceChannelById(1087722718000074802L);
        VoiceChannel online = e.getGuild().getVoiceChannelById(1087724634243334164L);
        all.getManager()
             .setName("｜🌐・osoby: " + e.getGuild().getMembers().size())
             .queue();
        Guild guild = e.getGuild();
        long people = guild.getMembers().stream()
                        .filter(member ->
                                !member.getOnlineStatus().equals(OnlineStatus.OFFLINE))
                                .count();
        online.getManager()
                .setName("｜☀️・Online: " + people)
                .queue();
    }
    //TODO: Funkcja odejścia użytkownika

}
