package pl.softwaregods.config;

import java.util.ArrayList;

public class Config {
    public static String embedColorAll;
    public static StatusBot statusBot;
    public static NeedRole needRole;
    public static Commands commands;
    public static Buttons buttons;
    public static Messages messages;
    public static Categories categories;

    public Config() {
        embedColorAll = "#00ff00";
        needRole = new NeedRole();
        statusBot = new StatusBot();
        commands = new Commands();
        buttons = new Buttons();
        messages = new Messages();
        categories = new Categories();
    }

    public static class NeedRole {
        public ArrayList<Long> all_perms = new ArrayList<Long>() {{
            add(856191668697038849L); // Role id
        }};
    }

    public static class StatusBot {
        //public String[] messages = {"Dc: DevFoxey#3752"}; // Messages in status (Infinite number of messages)
    }

    public static class Commands {
        public String sender_command = "-uhger564729";



    }

    public static class Buttons {
        public String ticket_start_button_id = "ticket-start";
        public String ticket_close_button_id = "ticket-close";
    }

    public static class Messages {
        public String ticket_start_button_message = "✏️ Napisz!";
        public String ticket_start_embed_title = "Masz Pytanie? Pisz ✏️";
        public String ticket_start_embed_description = "Jeśli masz jakąś sprawe, kliknij przycisk poniżej i napisz do Naszej administracji postaramy się jak najszybciej odpisać. Nadużywanie tej funkcji będzię kończyło się banem dla użytkownika.";
        public String ticket_created_message = "Twój ticket został utworzony";
        public String ticket_close_button_message = "Zamknij ticket \uD83D\uDD12";
        public String ticket_close_message = "Twój ticket zostanie zamknięty za 5 sekund";
    }

    public static class Categories {
        public String tickets_category = "1087440506864275557";
    }

}