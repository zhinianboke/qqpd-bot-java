import io.github.kloping.qqbot.Starter;
import io.github.kloping.qqbot.api.Intents;
import io.github.kloping.qqbot.api.message.MessageChannelReceiveEvent;
import io.github.kloping.qqbot.api.v2.GroupMessageEvent;
import io.github.kloping.qqbot.entities.ex.Image;
import io.github.kloping.qqbot.entities.ex.MessageAsyncBuilder;
import io.github.kloping.qqbot.entities.ex.msg.MessageChain;
import io.github.kloping.qqbot.entities.qqpd.data.Emoji;
import io.github.kloping.qqbot.entities.qqpd.message.RawMessage;
import io.github.kloping.qqbot.impl.ListenerHost;
import io.github.kloping.qqbot.plugins.weather.WeatherService;
import io.github.kloping.spt.annotations.AutoStand;
import io.github.kloping.spt.interfaces.Logger;
/**
 * @author github.kloping
 */
public class test_group {


	
	public static void main(String[] args) {
        //==============================================必要↓↓↓↓↓↓↓
        Starter starter = new Starter();
        //===================================公域推荐订阅===============↓群聊/好友 事件订阅
        starter.getConfig().setCode(Intents.PUBLIC_INTENTS.and(Intents.GROUP_INTENTS));
        starter.run();
        starter.registerListenerHost(new ListenerHost() {

            @EventReceiver
            public void onMessage(MessageChannelReceiveEvent event) {
                MessageAsyncBuilder builder = new MessageAsyncBuilder();
                builder.append("测试发图!");
                builder.append(new Image("http://kloping.top/icon.jpg"));
                builder.append(Emoji.K歌);
                event.send(builder.build());
            }

            /**
             * 因为是公域 所以仅当bot被at时才能触发事件
             * @param event
             */
            @EventReceiver
            public void onMessage(GroupMessageEvent event) {
            	WeatherService weatherService = new WeatherService();
            	RawMessage message = event.getRawMessage();
            	String content = message.getContent();
            	MessageAsyncBuilder builder = new MessageAsyncBuilder();
            	builder.append("\n");
            	if(content.contains("天气")) {
            		builder = weatherService.dealmsg(content);
            	}
            	else {
            		builder.append("测试发图!");
            	}
            	event.sendMessage(builder.build());
            	
            	
            	
//                MessageAsyncBuilder builder = new MessageAsyncBuilder();
//                builder.append("测试发图!");
//                builder.append(new Image("http://kloping.top/icon.jpg"));
//                builder.append(Emoji.K歌);
//                event.sendMessage(builder.build());
            }
        });
    }

}
