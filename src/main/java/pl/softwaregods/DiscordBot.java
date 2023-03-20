package pl.softwaregods;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import pl.softwaregods.Managers.CommandMNG;
import pl.softwaregods.config.Config;
import pl.softwaregods.senders.StartTicketMessageSender;
import pl.softwaregods.tasks.ModeratorLog;
import pl.softwaregods.tasks.TicketTask;
import pl.softwaregods.tasks.WelcomeMessageTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Getter
@Setter
public class DiscordBot {

    public Config config;
    private static JDA jda;

    private final ScheduledExecutorService executorService;

    private static final ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(4);

    public DiscordBot() throws InterruptedException {
        executorService = Executors.newScheduledThreadPool(10);
        jda = createUser();
        if (jda == null) return;
        config = new Config();
    }

    JDA createUser() {
        JDABuilder jda = JDABuilder.createDefault("MTA3OTQxMjMyNDk0NDg1OTE5Ng.GsjR6g.n3IIaeAmpYVgnuJNnhNUS9CtbaCXGQkJKAZWCs");
        jda.addEventListeners(
                // Main
                new Main(),
                // Senders
                new StartTicketMessageSender(),
                new CommandMNG(),

                // Tasks
                new TicketTask(),
                new WelcomeMessageTask(),
                new ModeratorLog()

        );

        jda.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.setMemberCachePolicy(MemberCachePolicy.ONLINE);
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.SCHEDULED_EVENTS);
        jda.enableCache(CacheFlag.ACTIVITY);
        jda.setRateLimitPool(SCHEDULED_THREAD_POOL_EXECUTOR);
        jda.setActivity(Activity.watching("ASBiRO Polska"));
        try {
            return jda.build().awaitReady();
        } catch (Exception e) {
            e.printStackTrace();
            return this.jda;
        }
    }

    public static JDA getJda() {
        return jda;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

}

