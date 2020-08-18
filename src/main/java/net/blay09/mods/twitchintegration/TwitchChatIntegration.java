package net.blay09.mods.twitchintegration;

import net.blay09.mods.twitchintegration.auth.TwitchAuthManager;
import net.blay09.mods.twitchintegration.command.TwitchCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SharedConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TwitchChatIntegration.MOD_ID)
public class TwitchChatIntegration {

    // V1 Vanilla-only
    // - Save and load the channels that were joined
    // - Remaining TODOs
    // V2 Chat Tweaks
    // ...

    public static final String MOD_ID = "twitchchatintegration";

    public static Logger logger = LogManager.getLogger(MOD_ID);

    public TwitchChatIntegration() {
        TwitchAuthManager.loadStoredToken();
        TwitchSessionManager.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TwitchIntegrationConfig.clientSpec);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        TwitchCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onWorldJoined(EntityJoinWorldEvent event) {
        if (event.getEntity() == Minecraft.getInstance().player) {
            if (!TwitchSessionManager.isConnected()) {
                TwitchSessionManager.connect();
            }
        }
        // TODO disconnect on world leave
    }

}
