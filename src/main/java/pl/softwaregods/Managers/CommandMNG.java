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
            rules.setDescription("1. Postanowienia Ogólne\n" +
                    "1.1 Nieprzestrzeganie poniższego regulaminu wiąże się z otrzymaniem kary.\n" +
                    "1.2 Nieznajomość regulaminu nie zwalnia z jego przestrzegania.\n" +
                    "1.3 Administracja ma pełne prawa do zmieniania treści regulaminu bez wcześniejszego\n" +
                    "powiadomienia użytkowników o zmianie.\n" +
                    "1.4 Niniejszy regulamin wchodzi w życie z dniem 21 marca 2023 roku.\n" +
                    "2. Zasady kanałów tekstowych\n" +
                    "2.1 Zakazane jest spamowanie i floodowanie.\n" +
                    "2.2 Zabrania się pisania wielkimi literami. (CapsLock)\n" +
                    "2.3 Zakaz używania wulgaryzmów na kanałach tekstowych, a także głosowych.\n" +
                    "2.4 Zakazane jest prowokowanie kłótni, dyskusji które mają negatywny wpływ na serwer.\n" +
                    "2.5 Zakaz wykorzystywania, oszukiwania i szantażowania innych użytkowników.\n" +
                    "2.6 Zabroniony jest wszelkiego rodzaju trolling oraz inne formy zachowań anty społecznych, które\n" +
                    "służą za przynętę do prowokowania (§2.4) różnych użytkowników.\n" +
                    "2.7 Zakaz obrażania graczy, administracji i serwera oraz działania na ich szkody.\n" +
                    "2.8 Reklamowanie jakichkolwiek serwerów zewnętrznych: gier, stron www, serwerów discord itp. bez\n" +
                    "pisemnej zgody właścicieli jest karalne.\n" +
                    "2.9 Zakaz wykorzystywania możliwych błędów na serwerze. Należy je natychmiast bezzwłocznie\n" +
                    "zgłosić administracji z zachowaniem poufności wobec osób trzecich.\n" +
                    "2.10 Zakazane jest poruszanie tematów wulgarnych/erotycznych/religijnych/rasistowskich itp.\n" +
                    "2.11 Podszywanie się pod graczy będzie karane kickiem, następnie banem. Podszywanie się pod\n" +
                    "administrację będzie skutkowało natychmiastowym banem.\n" +
                    "2.12 Komend można używać tylko na kanale do tego stworzonym.\n" +
                    "2.13 Zakaz pisania na rzeczy niezgodnych z tematyką kanału.\n" +
                    "2.14 Zabronione jest wysyłanie linków lub plików zawierających jakiekolwiek treści\n" +
                    "wulgarne/rasistowskie/pornograficzne/religijne itp. oraz plików szkodliwych (wirusy).\n" +
                    "2.15 Awatar oraz nick nie może zawierać treści obraźliwych/rasistowskich/wulgarnych itp.\n" +
                    "2.17 Przeszkadzanie administracji jest surowo karane.\n" +
                    "3. Zasady kanałów głosowych\n" +
                    "3.1 Wszystkie zasady kanałów tekstowych obowiązują także w głosowych.\n" +
                    "3.2 Zakaz krzyczenia i mocnego podnoszenia głosu.\n" +
                    "3.3 Zakazane jest puszczanie do mikrofonu muzyki itp.\n" +
                    "3.4 Zabrania się puszczania różnych bliżej nieokreślonych dźwięków, przesterów itp.\n");
            rules.setColor(Color.decode(Config.embedColorAll));
            rules.setFooter("© Klub ASBiRO Polska");
            rules.setImage("https://i.imgur.com/BGx2F1K.png");
            e.getChannel().sendMessageEmbeds(rules.build()).setActionRow(Button.success("accept", "✅ Akceptuję ✅")).queue();
            e.reply("Pomyślnie stworzyłeś regulamin serwera");
        } else if (command.equals("głosowanie")) {
            TextInput contentvote = TextInput.create("vote-cont", "Zawartość ankiety", TextInputStyle.SHORT)
                    .setPlaceholder("Wpisz do czego ma dotyczyć ankieta")
                    .setMinLength(0)
                    .setMaxLength(1024)
                    .setRequired(true)
                    .build();
            Modal vote = Modal.create("vote", "📊    Ankieta 📊").addActionRows(ActionRow.of(contentvote)).build();
           e.replyModal(vote).queue();
        } else if (command.equals("konkurs")) {
            TextInput contentkonkurs = TextInput.create("kon-cont", "Zawartość konkursu", TextInputStyle.SHORT)
                    .setPlaceholder("Wpisz do czego ma dotyczyć ankieta")
                    .setMinLength(0)
                    .setMaxLength(1024)
                    .setRequired(true)
                    .build();
            Modal konkurs = Modal.create("kon", "📊  Ankieta 📊").addActionRows(ActionRow.of(contentkonkurs)).build();
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
            acc.setFooter("© Klub ASBiRO Polska");
            acc.setThumbnail("https://i.imgur.com/apRJAXJ.png");
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
            vote.setFooter("© Klub ASBiRO Polska");
            vote.setImage("https://i.imgur.com/ZT2uDmt.png");
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
            kon.setFooter("© Klub ASBiRO Polska");
            kon.setImage("https://i.imgur.com/ETzjMvp.png");
            e.getChannel().sendMessageEmbeds(kon.build()).setActionRow(Button.primary("Join", "✅")).queue();
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
            cha.addField("Co się zmieniło: ", "```"+koncont+"```", false);
            cha.setThumbnail("https://i.imgur.com/apRJAXJ.png");
            cha.setColor(Color.decode(Config.embedColorAll));
            cha.setFooter("© Klub ASBiRO Polska");
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
            Role rules = e.getGuild().getRoleById("1078431116303810610");
            Role ver = e.getGuild().getRoleById("1078430865765445795");
            e.getGuild().addRoleToMember(e.getUser(), rules).queue();
            e.getGuild().addRoleToMember(e.getUser(), ver).queue();
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

                for (int i = 0; i < users.length; i++) {
                    //  e.getChannel().sendMessageEmbeds().queue();
                }
                e.getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
                //String roll = String.valueOf((int) (Math.random() * users.length));
               // win.setTitle("Wygrał:");
              //  win.setDescription("<@"+roll+">");
                e.getChannel().sendMessageEmbeds(win.build()).queue();
            }
        }, 20000);
        e.reply("Zarejestrowałeś się do konkursu").setEphemeral(true).queue();
        }
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

