package net.blay09.mods.twitchintegration.api.event;

import net.blay09.mods.twitchintegration.chat.TwitchChannel;
import net.minecraftforge.eventbus.api.Event;

public class TwitchChannelDisabledEvent extends Event {
    private final TwitchChannel twitchChannel;

    public TwitchChannelDisabledEvent(TwitchChannel twitchChannel) {
        this.twitchChannel = twitchChannel;
    }

    public TwitchChannel getTwitchChannel() {
        return twitchChannel;
    }
}