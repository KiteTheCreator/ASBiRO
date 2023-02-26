package pl.gamecrewstudios.tasks;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WelcomeMessageTask extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e) {
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
        e.getUser().openPrivateChannel().flatMap(channel -> channel.sendMessageEmbeds(welcome.build())).queue();
    }
}

