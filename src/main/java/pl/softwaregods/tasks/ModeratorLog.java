package pl.softwaregods.tasks;

import lombok.NonNull;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import pl.softwaregods.config.Config;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModeratorLog extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent e) {
        Date nowDate = new Date();
        SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        EmbedBuilder leave = new EmbedBuilder();
        leave.setTitle("MOD Log");
        leave.setColor(Color.decode(Config.embedColorAll));
        leave.setDescription("Użytkownik: " + e.getMember().getAsMention() + " Wyszedł z serwera");
        leave.setFooter("© Klub ASBiRO Polska  •" + " " + " "+ sdf4.format(nowDate), "https://i.imgur.com/6yfMcr6.png");
        e.getGuild().getTextChannelById("1087439498025107466").sendMessageEmbeds(leave.build()).queue();
    }


    @Override
    public void onGuildUnban(@NotNull GuildUnbanEvent e) {
        Date nowDate = new Date();
        SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        EmbedBuilder unban = new EmbedBuilder();
        unban.setTitle("MOD Log");
        unban.setColor(Color.decode(Config.embedColorAll));
        unban.setDescription("Użytkownik: " + e.getUser().getAsMention() + " został odbanowany przez: ");
        unban.setFooter("© Klub ASBiRO Polska •" + " " + " "+ sdf4.format(nowDate), "https://i.imgur.com/6yfMcr6.png");
        e.getGuild().getTextChannelById("1087439498025107466").sendMessageEmbeds(unban.build()).queue();
    }

    @Override
    public void onGuildBan(@NotNull GuildBanEvent event) {

    }
}
