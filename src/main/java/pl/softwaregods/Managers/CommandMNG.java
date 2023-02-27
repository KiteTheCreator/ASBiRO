package pl.softwaregods.Managers;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
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
import java.util.ArrayList;
import java.util.List;

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
                    "  1.1 Nieprzestrzeganie poniższego regulaminu wiąże się z otrzymaniem kary.\n" +
                    "  1.2 Nieznajomość regulaminu nie zwalnia z jego przestrzegania.\n" +
                    "  1.3 Administracja ma pełne prawa do zmieniania treści regulaminu bez wcześniejszego\n" +
                    "      powiadomienia użytkowników o zmianie.\n" +
                    "  1.4 Niniejszy regulamin wchodzi w życie z dniem 15 lutego 2021 roku.\n" +
                    "2. Zasady kanałów tekstowych\n" +
                    "  2.1 Zakazane jest spamowanie i floodowanie.\n" +
                    "  2.2 Zabrania się pisania wielkimi literami. (CapsLock)\n" +
                    "  2.3 Zakaz używania wulgaryzmów na kanałach tekstowych, a także głosowych.\n" +
                    "  2.4 Zakazane jest prowokowanie kłótni, dyskusji które mają negatywny wpływ na serwer.\n" +
                    "  2.5 Zakaz wykorzystywania, oszukiwania i szantażowania innych użytkowników.\n" +
                    "  2.6 Zabroniony jest wszelkiego rodzaju trolling oraz inne formy zachowań anty społecznych, które\n" +
                    "      służą za przynętę do prowokowania (§2.4) różnych użytkowników.\n" +
                    "  2.7 Zakaz obrażania graczy, administracji i serwera oraz działania na ich szkody.\n" +
                    "  2.8 Reklamowanie jakichkolwiek serwerów zewnętrznych: gier, stron www, serwerów discord itp. bez\n" +
                    "      pisemnej zgody właścicieli Esportology jest karalne.\n" +
                    "  2.9 Zakaz wykorzystywania możliwych błędów na serwerze. Należy je natychmiast bezzwłocznie\n" +
                    "      zgłosić administracji z zachowaniem poufności wobec osób trzecich.\n" +
                    "  2.10 Zakazane jest poruszanie tematów wulgarnych/erotycznych/religijnych/rasistowskich itp.\n" +
                    "  2.11 Podszywanie się pod graczy będzie karane kickiem, następnie banem. Podszywanie się pod\n" +
                    "       administrację będzie skutkowało natychmiastowym banem.\n" +
                    "  2.12 Komend można używać tylko na kanale do tego stworzonym.\n" +
                    "2.13 Zakaz pisania na rzeczy niezgodnych z tematyką kanału.\n" +
                    "  2.14 Zabronione jest wysyłanie linków lub plików zawierających jakiekolwiek treści\n" +
                    "       wulgarne/rasistowskie/pornograficzne/religijne itp. oraz plików szkodliwych (wirusy).\n" +
                    "  2.15 Awatar oraz nick nie może zawierać treści obraźliwych/rasistowskich/wulgarnych itp.\n" +
                    "  2.16 Karą za złe używanie emotikon jest ostrzeżenie a następnie ban czasowy.\n" +
                    "  2.17 Przeszkadzanie administracji jest surowo karane.\n" +
                    "3. Zasady kanałów głosowych\n" +
                    "  3.1 Wszystkie zasady kanałów tekstowych obowiązują także w głosowych.\n" +
                    "  3.2 Zakaz krzyczenia i mocnego podnoszenia głosu.\n" +
                    "  3.3 Zakazane jest puszczanie do mikrofonu muzyki itp.\n" +
                    "  3.4 Zabrania się puszczania różnych bliżej nieokreślonych dźwięków, przesterów itp.\n" +
                    "4. Zasady przyznawania rang\n" +
                    "  4.1 Przyznanie rangi drużynowej, osobom do tego nieuprawnionym jest surowo zabronione.");
            rules.setColor(Color.decode(Config.embedColorAll));
            rules.setFooter("© Technikum TEB Edukacja Katowice");
            e.getChannel().sendMessageEmbeds(rules.build()).setActionRow(Button.success("accept", "✅ Akceptuję ✅")).queue();
            e.reply("Pomyślnie stworzyłeś regulamin serwera");
        } else if (command.equals("turrules")) {
            EmbedBuilder rules = new EmbedBuilder();
            rules.setTitle("🎮 Zasady Turnieju 🎮");
            rules.setDescription("bla bla");
            rules.setColor(Color.decode(Config.embedColorAll));
            rules.setFooter("© Technikum TEB Edukacja Katowice");
            e.getChannel().sendMessageEmbeds(rules.build()).queue();
            e.reply("Pomyślnie stworzyłeś regulamin turnieju");
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
        if (e.getComponentId().equalsIgnoreCase("accept")){
            Role rules = e.getGuild().getRoleById("1077790377530114079");
            e.getGuild().addRoleToMember(e.getUser(), rules).queue();
            e.reply("Pomyślnie zaakceptowałeś zasady serwera").setEphemeral(true).queue();
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
            acc.setFooter("© Technikum TEB Edukacja Katowice");
            e.getChannel().sendMessage("||@everyone||").addEmbeds(acc.build()).queue();
            e.reply("Pomyślnie stworzyłeś ogłoszenie").setEphemeral(true).queue();
            acc.clear();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("ogłoszenie", "Napisz co chcesz ogłosić").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
        commandData.add(Commands.slash("rules", "zasady-admin-only").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
        commandData.add(Commands.slash("turrules", "zasady-turnieju-admin-only").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));

        e.getGuild().updateCommands().addCommands(commandData).queue();
    }
}

