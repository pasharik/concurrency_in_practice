package ru.pasharik.chapter0.crawling.developerslife;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pasharik on 08/05/17.
 */
public class Parser {
    private static final String ADDRESS = "http://poetory.ru/content/list?type=3&per-page=30&page=";

    public static void main(String[] args) throws IOException {
        for (Pirojok p : Parser.parse(1)) {
            System.out.println(p);
        }
    }

    public static List<Pirojok> parse(int pageNum) {
        List<Pirojok> result = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(ADDRESS + pageNum).get();
            Elements items = doc.select("div[id^='content-item-']");
            Iterator<Element> it = items.iterator();
            while (it.hasNext()) {
                Element pirojokDiv = it.next();

                Elements likes = pirojokDiv.select("span.likes-count");
                int like = Integer.parseInt(likes.iterator().next().html());

                Elements texts = pirojokDiv.select("div.item-text");
                String text = clearText(texts.iterator().next().html());
                int id = retrieveId(pirojokDiv);

                result.add(new Pirojok(id, like, text));
            }
        } catch (IOException e) { e.printStackTrace(); }

        return result;
    }

    private static String clearText(String src) { return src.replaceAll("<br>", ""); }
    private static int retrieveId(Element div) { return Integer.parseInt(div.id().replace("content-item-", "")); }
}
