package com.vishal.alexanews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * NOTES: This class will change and will be triggered by Alexa ---somwhow or API Gateway
 **/

@Slf4j
public class NewsApplication implements RequestHandler<String, String> {

    private static final String apikey = System.getenv("API_KEY");
    private static final String newsUrl = System.getenv("NEWS_URL");

    @SneakyThrows
    @Override
    public String handleRequest(String input, Context context) {
        log.info("Calling function NewsService");

        NewsService newsService = new NewsService();

        newsService.setNewsApi(newsUrl.concat("country=").concat(Country.US.getCountry())
                        .concat("&apikey=").concat(apikey));


        log.info("Calling News Service API-->" + newsService.getNewsApi());

        JSONObject newsRespnonse = newsService.readJsonFromUrl();

        String jsonArray = parseNews(newsRespnonse);

        return "Successfull !!!!!!!!";

    }

    private String parseNews(JSONObject newsRespnonse)
    {
        // Get Gson object
//        String test = "{\"status\":\"ok\",\"totalResults\":10,\"articles\":[{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"Robot dog enforces social distancing in city park\",\"description\":\"The four-legged robot carries a camera and speaker to play social distancing messages in Singapore.\",\"url\":\"http://www.bbc.co.uk/news/technology-52619568\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/15178/production/_112229368_p08cvbqh.jpg\",\"publishedAt\":\"2020-05-11T11:34:23Z\",\"content\":null},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"S Dakota's Sioux refuse to pull virus checkpoints\",\"description\":\"Tribal officials say the checkpoints are the only way to ensure the virus does not enter their land.\",\"url\":\"http://www.bbc.co.uk/news/world-us-canada-52615311\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/462A/production/_112226971_gettyimages-646296378-1.jpg\",\"publishedAt\":\"2020-05-11T09:46:06Z\",\"content\":\"Image copyrightGetty ImagesImage caption\\r\\n Tribal leader Harold Frazier says the tribes would not \\\"apologise for being an island of safety in a sea of uncertainty and death\\\"\\r\\nSioux tribes in the US state of South Dakota are refusing to remove coronavirus chec… [+3400 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"Seinfeld star Jerry Stiller dies at 92\",\"description\":\"The father of actor Ben played the volatile and cantankerous Frank Costanza for six years.\",\"url\":\"http://www.bbc.co.uk/news/entertainment-arts-52616060\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/CD5C/production/_112227525_gettyimages-109019758.jpg\",\"publishedAt\":\"2020-05-11T09:28:46Z\",\"content\":\"Image copyrightGetty ImagesImage caption\\r\\n Jerry Stiller with son Ben in February 2011\\r\\nComedian Jerry Stiller, best known for his recurring role as George Costanza's father on TV's Seinfeld, has died aged 92, his actor son Ben has confirmed.\\r\\n\\\"I'm sad to say… [+1011 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"Wuhan in first virus cluster since end of lockdown\",\"description\":\"As China continues easing restrictions, new virus clusters in Wuhan and elsewhere have emerged.\",\"url\":\"http://www.bbc.co.uk/news/world-asia-china-52613138\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/C38E/production/_112226005_gettyimages-1208565436.jpg\",\"publishedAt\":\"2020-05-11T08:45:45Z\",\"content\":\"Image copyrightGetty ImagesImage caption\\r\\n All of the latest cases in Wuhan were previously classified as asymptomatic\\r\\nNew coronavirus clusters have been reported in Wuhan city - where the virus fist emerged - and the north-eastern province of Jilin in China… [+4156 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"Australian 'taunted dying police at crash scene'\",\"description\":\"Richard Pusey, who was uninjured, mocked the officers as they lay pinned under a truck say police.\",\"url\":\"http://www.bbc.co.uk/news/world-australia-52612957\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/904E/production/_112224963_ricahrdpusey.png\",\"publishedAt\":\"2020-05-11T05:36:23Z\",\"content\":\"Image copyrightEPAImage caption\\r\\n Melbourne man Richard Pusey is facing a dozen charges related to the fatal crash\\r\\nAn Australian man filmed and mocked four police officers as they lay dying, a court has heard according to local media.\\r\\nRichard Pusey, 41, who… [+1904 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"PM to reveal further details on lockdown roadmap\",\"description\":\"Boris Johnson is to face MPs over his plan to reopen society, which were announced on Sunday.\",\"url\":\"http://www.bbc.co.uk/news/uk-52612449\",\"urlToImage\":\"https://ichef.bbci.co.uk/images/ic/1024x576/p08ct5jl.jpg\",\"publishedAt\":\"2020-05-11T04:08:39Z\",\"content\":\"Media captionBoris Johnson: \\\"We are taking the first careful steps to modify our measures\\\"\\r\\nBoris Johnson will reveal more detail on his plans to reopen society in England, after unveiling the \\\"first sketch\\\" of his \\\"road map\\\" out of the coronavirus lockdown.\\r… [+6361 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"Iranian navy ship 'sunk by friendly fire'\",\"description\":\"Iran's navy was reportedly testing a new anti-ship missile when the accident took place.\",\"url\":\"http://www.bbc.co.uk/news/world-middle-east-52612511\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/C69A/production/_112224805_gettyimages-804955638-594x594.jpg\",\"publishedAt\":\"2020-05-11T03:12:19Z\",\"content\":\"Image copyrightGetty ImagesImage caption\\r\\n The incident took place near the Strait of Hormuz\\r\\nAn Iranian navy \\\"friendly fire\\\" incident in which a ship was sunk has killed dozens of sailors, unofficial reports say.\\r\\nLocal journalists said the frigate Jamaran w… [+738 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"Coronavirus updates: China infections rise as new virus cluster emerges\",\"description\":\"Seventeen new infections are reported in China, as concern grows over cases in a north-eastern city.\",\"url\":\"http://www.bbc.co.uk/news/live/world-52612438\",\"urlToImage\":\"https://m.files.bbci.co.uk/modules/bbc-morph-news-waf-page-meta/4.1.2/bbc_news_logo.png\",\"publishedAt\":\"2020-05-11T01:37:23.358572Z\",\"content\":\"Welcome back to our rolling coverage of the coronavirus pandemic. Well keep you posted on all news from around the world with our teams based across Asia and Australia and later Europe, UK and the US.\\r\\nHeres what you need to know as Asia starts a new week.\\r\\n<… [+1170 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"'I met my troll'\",\"description\":\"We brought together an angry troll who posts bigoted comments – and one of the people he abuses.\",\"url\":\"http://www.bbc.co.uk/news/stories-52488074\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/ED94/production/_112102806_p08bd91f.jpg\",\"publishedAt\":\"2020-05-10T23:24:02Z\",\"content\":null},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"BBC News\",\"title\":\"French passengers exempt from UK quarantine plans\",\"description\":\"The UK government said the new measures would not apply to those travelling from France.\",\"url\":\"http://www.bbc.co.uk/news/business-52610594\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/5C50/production/_112223632__112097913_heathrowgetty.jpg\",\"publishedAt\":\"2020-05-10T22:49:48Z\",\"content\":\"Image copyrightGetty Images\\r\\nFrench passengers will be exempt from quarantine measures that will come into force in the UK amid the pandemic.\\r\\nBoris Johnson said on Sunday they would be imposed on people arriving by air into the UK, to prevent Covid-19 being … [+2865 chars]\"}]}";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonElement element = gson.fromJson (newsRespnonse.toString(), JsonElement.class);
        JsonObject jsonObj = element.getAsJsonObject();
        JsonArray jsonArray = (JsonArray) jsonObj.get(Constants.ARTICLES);

        Iterator<JsonElement> iterator = jsonArray.iterator();
        final List newslist = new ArrayList();
        while(iterator.hasNext()) {

            JsonElement newelement = gson.fromJson (iterator.next(), JsonElement.class);
            JsonObject jsonObj1 = newelement.getAsJsonObject();

            JsonElement title = jsonObj1.get(Constants.TITLE);
            JsonElement description = jsonObj1.get(Constants.DESCRIPTION);

            News news = new News();
            news.setTitle(title.toString().replaceAll(" - "," says ").replaceAll("[^a-zA-Z0-9]", " "));
            news.setDescription(description.toString().replaceAll("[^a-zA-Z0-9]", " "));

            newslist.add(news);

        }

        log.info("List size"+newslist.size());
        log.info("List of news" + gson.toJson(newslist));
        return "Vishal";
    }
}
