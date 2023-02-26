package pl.gamecrewstudios.senders;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import pl.gamecrewstudios.config.Config;

import java.awt.*;

public class StartTicketMessageSender extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String[] a = e.getMessage().getContentRaw().split(" ");
        EmbedBuilder x = new EmbedBuilder();
        if (a[0].equalsIgnoreCase(Config.commands.sender_command)) {
            e.getMessage().delete().queue();
            x.setTitle(Config.messages.ticket_start_embed_title);
            x.setColor(Color.decode(Config.embedColorAll));
            x.setDescription("*" + Config.messages.ticket_start_embed_description + "*");
            e.getChannel().sendMessageEmbeds(x.build()).setActionRow(Button.success(Config.buttons.ticket_start_button_id, Config.messages.ticket_start_button_message)).queue();
        }
    }
}

