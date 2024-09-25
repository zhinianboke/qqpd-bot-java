package io.github.kloping.qqbot.plugins.weather;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.github.kloping.qqbot.entities.ex.MessageAsyncBuilder;
import io.github.kloping.qqbot.plugins.PluginsInfo;
import io.github.kloping.spt.annotations.AutoStand;
import io.github.kloping.spt.interfaces.Logger;

public class WeatherService implements PluginsInfo{
	


    @AutoStand
    Logger logger;

	@Override
	public MessageAsyncBuilder dealmsg(String content) {
		MessageAsyncBuilder builder = new MessageAsyncBuilder();
		content = content.replaceAll("天气", "");
		content = content.replaceAll(" ", "");
		String resultStr = w0(content);
		resultStr = resultStr.replaceAll(" ", "\n");
		builder.append(resultStr);
		return builder;
	}
	
	public static String w0(String s0) {
        try {
            Document document = null;
            try {
                document = Jsoup.connect("http://hm.suol.cc/API/tq.php?n=1&msg=" + s0).ignoreContentType(true)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String json = document.body().text();
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "查询失败";
        }
    }

	public static void main(String[] args) {
		
	}
}
