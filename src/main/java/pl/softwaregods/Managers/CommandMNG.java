package pl.softwaregods.Managers;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;
import pl.softwaregods.config.Config;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandMNG extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        String command = e.getName();

        if (command.equals("ogłoszenie")){

            TextInput content = TextInput.create("acc-content", "Zawartość Ogłoszenia: ", TextInputStyle.SHORT)
                    .setPlaceholder("Wpisz co chcesz ogłosić")
                    .setMinLength(0)
                    .setMaxLength(1024)
                    .setRequired(true)
                    .build();

            Modal acc = Modal.create("acc", "📣Ogłoszenie📣").addActionRows(ActionRow.of(content)).build();
            e.replyModal(acc).queue();

        } else if (command.equals("rules")) {
            EmbedBuilder rules = new EmbedBuilder();
            rules.setTitle("📚 Regulamin 📚");
            rules.setDescription("");
            rules.setColor(Color.decode(Config.embedColorAll));
            rules.setFooter("© SoftwareGods.pl");
            e.getChannel().sendMessageEmbeds(rules.build()).setActionRow(Button.success("accept", "✅ Akceptuję ✅")).queue();
            e.reply("Pomyślnie stworzyłeś regulamin serwera");
        } else if (command.equals("głosowanie")) {
            TextInput contentvote = TextInput.create("vote-cont", "Zawartość ankiety", TextInputStyle.SHORT)
                    .setPlaceholder("Wpisz do czego ma dotyczyć ankieta")
                    .setMinLength(0)
                    .setMaxLength(1024)
                    .setRequired(true)
                    .build();
            Modal vote = Modal.create("vote", "📊Ankieta📊").addActionRows(ActionRow.of(contentvote)).build();
           e.replyModal(vote).queue();
        } else if (command.equals("konkurs")) {
            TextInput contentkonkurs = TextInput.create("kon-cont", "Zawartość konkursu", TextInputStyle.SHORT)
                    .setPlaceholder("Wpisz do czego ma dotyczyć ankieta")
                    .setMinLength(0)
                    .setMaxLength(1024)
                    .setRequired(true)
                    .build();
            Modal konkurs = Modal.create("kon", "📊Ankieta📊").addActionRows(ActionRow.of(contentkonkurs)).build();
            e.replyModal(konkurs).queue();
        } else if (command.equals("zmiany")) {
            TextInput contentchange = TextInput.create("kon-chan", "Zawartość zmian", TextInputStyle.SHORT)
                    .setPlaceholder("Wpisz co zostało zmienione")
                    .setMinLength(0)
                    .setMaxLength(1024)
                    .setRequired(true)
                    .build();
            Modal change = Modal.create("chan", "🔧Change-Log🔧").addActionRows(ActionRow.of(contentchange)).build();
            e.replyModal(change).queue();
        }
    }


    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent e) {
        EmbedBuilder acc = new EmbedBuilder();
        if (e.getModalId().equals("acc")){
            String content = e.getValue("acc-content").getAsString();
            acc.setTitle("❗️ Ogłoszenie ❗️");
            acc.setColor(Color.decode(Config.embedColorAll));
            acc.setDescription(content);
            acc.setFooter("© SoftwareGods.pl");
            e.getChannel().sendMessage("||@everyone||").addEmbeds(acc.build()).queue();
            e.reply("Pomyślnie stworzyłeś ogłoszenie").setEphemeral(true).queue();
            acc.clear();
        }

        if (e.getModalId().equals("vote")) {
            String votecont = e.getValue("vote-cont").getAsString();
            EmbedBuilder vote = new EmbedBuilder();
            vote.setTitle("📊 Ankieta 📊");
            vote.setDescription(votecont);
            vote.setColor(Color.decode(Config.embedColorAll));
            vote.setFooter("© SoftwareGods.pl");
            e.getChannel().sendMessageEmbeds(vote.build()).queue(message -> {
                message.addReaction(Emoji.fromUnicode("U+2705")).queue();
                message.addReaction(Emoji.fromUnicode("U+274C")).queue();
            });
            e.reply("Pomyślnie stworzyłeś głosowanie na kanale").setEphemeral(true).queue();
        }

        if (e.getModalId().equals("kon")){
            String koncont = e.getValue("kon-cont").getAsString();
            EmbedBuilder kon = new EmbedBuilder();
            kon.setTitle("📊 Konkurs 📊");
            kon.setDescription(koncont);
            kon.setColor(Color.decode(Config.embedColorAll));
            kon.setFooter("© SoftwareGods.pl");
            e.getChannel().sendMessageEmbeds(kon.build()).setActionRow(Button.danger("Join", "Weź udział w konkursie  •  ")).queue();
            e.reply("Pomyślnie stworzyłeś konkurs").setEphemeral(true).queue();
        }
        if (e.getModalId().equals("chan")){
            String koncont = e.getValue("kon-chan").getAsString();
            EmbedBuilder cha = new EmbedBuilder();
            Date nowDate = new Date();
            SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy • HH:mm");
            cha.setTitle("🔄 Zmiany 🔄");
            cha.addField("Data zmiany: ", sdf4.format(nowDate), true);
            cha.addField("Administrator: ", e.getMember().getAsMention(), true);
            cha.addField("Treść", koncont, false);
            cha.setColor(Color.decode(Config.embedColorAll));
            cha.setFooter("© SoftwareGods.pl");
            e.getChannel().sendMessageEmbeds(cha.build()).queue(message -> {
                message.addReaction(Emoji.fromUnicode("U+2705")).queue();
                message.addReaction(Emoji.fromUnicode("U+274C")).queue();
            });
            e.reply("Pomyślnie stworzyłeś changelog").setEphemeral(true).queue();
        }

    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
        if (e.getComponentId().equalsIgnoreCase("accept")){
            Role rules = e.getGuild().getRoleById("1077790377530114079");
            e.getGuild().addRoleToMember(e.getUser(), rules).queue();
            e.reply("Pomyślnie zaakceptowałeś zasady serwera").setEphemeral(true).queue();
        }
    if (e.getComponentId().equalsIgnoreCase("Join")) {
        String[] users = new String[]{e.getUser().getId()};

        EmbedBuilder win = new EmbedBuilder();
        Date nowDate = new Date();
        Date konDate = new Date();
        Timer timer = new Timer();
        SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy • HH:mm");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                String id = users.get((int) (Math.random() * users.size())).getId();
//                win.setTitle("Wygrał:");
//                win.setDescription("<@" + id + ">");
//                e.getChannel().sendMessageEmbeds(win.build()).queue();

                for(int i = 0; i < users.length; i++) {
                    e.getChannel().sendMessage(users[i]).queue();
                }
            }
        }, 20000);
        e.getMessage().delete().queue();
    }
    if (e.getComponentId().equalsIgnoreCase("Joined")){
    }
        e.editButton(Button.success("Joined", "Wziąłeś już udział w konkursie")).queue();
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("ogłoszenie", "Napisz co chcesz ogłosić").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
        commandData.add(Commands.slash("rules", "zasady-admin-only").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
        commandData.add(Commands.slash("głosowanie", "rozpoczęcie głosowania").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
        commandData.add(Commands.slash("konkurs", "rozpoczęcie konkursu").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
        commandData.add(Commands.slash("zmiany", "Change-list").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));

        e.getGuild().updateCommands().addCommands(commandData).queue();
    }
}

