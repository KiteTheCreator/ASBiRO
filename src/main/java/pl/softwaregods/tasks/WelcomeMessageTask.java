package pl.softwaregods.tasks;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WelcomeMessageTask extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e) {
        Role def = e.getGuild().getRoleById("1078416900167573655");
        Role chuj1 = e.getGuild().getRoleById("1078430016863477790");
        Role chuj2 = e.getGuild().getRoleById("1078433659083829278");
        EmbedBuilder welcome = new EmbedBuilder();
        welcome.setTitle("Cześć! 😀");
        welcome.setDescription("Zachęcamy do sprawdzenia następujących kanałów:\n" +
                "<#1077750726157148230> - Zapoznanie się z regulaminem serwera jest obowiązkowe.\n" +
                "<#1077754512632205314> - Tutaj pojawią się najważniejsze informacje na temat turnieju.\n" +
                "<#1077648289152901252>- Utwórz zgłoszenie, na które odpowiedzą Ci administratorzy.\n" +
                "<#1077595752144052326> - Porozmawiaj z innymi użytkownikami serwera.\n" +
                "\n" +
                "Zapraszamy również do zaobserwowania nas na innych Social Mediach:\n" +
                "Facebook: Kliknij tutaj!\n" +
                "Instagram: Kliknij tutaj!\n" +
                "Youtube: Kliknij tutaj!\n" +
                "TikTok: Kliknij tutaj!\n");
        welcome.setImage("https://i.imgur.com/XJV7Fo7.png");
       // e.getUser().openPrivateChannel().flatMap(channel -> channel.sendMessageEmbeds(welcome.build())).queue();
        e.getGuild().getTextChannelById("1078853366832173087").sendMessageEmbeds(welcome.build()).queue();
        e.getGuild().addRoleToMember(e.getUser(), def).queue();
        e.getGuild().addRoleToMember(e.getUser(), chuj1).queue();
        e.getGuild().addRoleToMember(e.getUser(), chuj2).queue();
    }
}

