package io.github.kloping.qqbot.impl.message;

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.qqbot.api.event.ChannelEvent;
import io.github.kloping.qqbot.api.message.MessageChannelReceiveEvent;
import io.github.kloping.qqbot.entities.Bot;
import io.github.kloping.qqbot.entities.qqpd.Channel;
import io.github.kloping.qqbot.entities.qqpd.Guild;
import io.github.kloping.qqbot.entities.qqpd.data.Emoji;
import io.github.kloping.qqbot.entities.qqpd.message.RawMessage;
import io.github.kloping.qqbot.utils.BaseUtils;

/**
 * @author github.kloping
 */
public class BaseMessageChannelReceiveEvent extends BaseMessageEvent implements ChannelEvent, MessageChannelReceiveEvent {
    public BaseMessageChannelReceiveEvent(RawMessage message, JSONObject jo, Bot bot) {
        super(message, jo, bot);
        this.channel = getGuild().getChannel(message.getChannelId());
        this.sender = getGuild().getMember(message.getAuthor().getId());
        this.content = BaseUtils.parseToMessageChain(getRawMessage()).toString();
        this.channelId = getChannel().getId();
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    protected String content;
    protected String channelId;

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getChannelId() {
        return channelId;
    }

    @Override
    public String toString() {
        return String.format("[channel(%s)]member(%s)=>%s", getChannel().getName(),
                getSender().getNick(), getContent());
    }

    @Override
    public void addEmoji(Emoji emoji) {
       getRawMessage().addEmoji(emoji);
    }

    @Override
    public void removeEmoji(Emoji emoji) {
        getRawMessage().addEmoji(emoji);
    }
}