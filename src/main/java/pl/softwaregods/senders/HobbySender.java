package pl.softwaregods.senders;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;
import pl.softwaregods.config.Config;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HobbySender extends ListenerAdapter {

    @Override
    public void onMessageReceived( MessageReceivedEvent e) {

        String[] a = e.getMessage().getContentRaw().split(" ");
        EmbedBuilder x = new EmbedBuilder();
        Date nowDate = new Date();
        SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        if (a[0].equalsIgnoreCase(Config.commands.hobby_command)) {
            e.getMessage().delete().queue();
            x.setTitle("Wybierz Swoją działalność:  ");
            x.setColor(Color.decode(Config.embedColorAll));
            x.setDescription("");
            x.setFooter("© Klub ASBiRO Polska  •" + " " + " "+ sdf4.format(nowDate), "https://i.imgur.com/apRJAXJ.png");
            e.getChannel().sendMessageEmbeds(x.build()).setActionRow(
                            StringSelectMenu.create("choosehobby")
                                    .addOption("Informatyka", "Informatyka")
                                    .addOption("E-Commerce", "E-Commerce")
                                    .addOption("Nieruchomości", "Nieruchomości")
                                    .addOption("Motoryzacja", "Motoryzacja")
                                    .addOption("Wolny Zawód", "Wolny Zawód")
                                    .build())
                    .queue();
        }
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent e) {
        if (e.getComponent().getId().equals("choosehobby")) {
            Role it = e.getGuild().getRoleById("1087740719059447808");
            Role ec = e.getGuild().getRoleById("1087740748088229928");
            Role nr = e.getGuild().getRoleById("1087740772750725141");
            Role mo = e.getGuild().getRoleById("1087740918016249866");
            Role wz = e.getGuild().getRoleById("1087741199911243796");
            Role space1 = e.getGuild().getRoleById("1087723916367249428");
            Role space2 = e.getGuild().getRoleById("1087740070687162428");

            for (int i = 0; i < e.getValues().size(); i++){
                switch (e.getValues().get(i)) {
                    case "Informatyka" :
                        e.getGuild().addRoleToMember(e.getUser(), space1).queue();
                        e.getGuild().addRoleToMember(e.getUser(), space2).queue();
                        e.getGuild().addRoleToMember(e.getUser(), it).queue();
                        e.reply("Pomyślnie wybrałeś swoją działalność").setEphemeral(true).queue();
                        break;

                    case "E-Commerce" :
                        e.getGuild().addRoleToMember(e.getUser(), space1).queue();
                        e.getGuild().addRoleToMember(e.getUser(), space2).queue();
                        e.getGuild().addRoleToMember(e.getUser(), ec);
                        e.reply("Pomyślnie wybrałeś swoją działalność").setEphemeral(true).queue();
                        break;

                    case "Nieruchomości" :
                        e.getGuild().addRoleToMember(e.getUser(), space1).queue();
                        e.getGuild().addRoleToMember(e.getUser(), space2).queue();
                        e.getGuild().addRoleToMember(e.getUser(), nr).queue();
                        e.reply("Pomyślnie wybrałeś swoją działalność").setEphemeral(true).queue();
                        break;

                    case "Motoryzacja" :
                        e.getGuild().addRoleToMember(e.getUser(), space1).queue();
                        e.getGuild().addRoleToMember(e.getUser(), space2).queue();
                        e.getGuild().addRoleToMember(e.getUser(), mo).queue();
                        e.reply("Pomyślnie wybrałeś swoją działalność").setEphemeral(true).queue();
                        break;

                    case "Wolny Zawód" :
                        e.getGuild().addRoleToMember(e.getUser(), space1).queue();
                        e.getGuild().addRoleToMember(e.getUser(), space2).queue();
                        e.getGuild().addRoleToMember(e.getUser(), wz).queue();
                        e.reply("Pomyślnie wybrałeś swoją działalność").setEphemeral(true).queue();
                        break;

                    default:
                        break;
                }
            }
        }
    }
}

