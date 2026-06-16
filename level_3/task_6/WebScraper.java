import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {
    public static String removeHtmlTags(String input) {
        return input.replaceAll("<[^>]*>", "").trim();
    }

    public static String decodeHtml(String input) {
        return input.replace("&ldquo;", "\"")
                    .replace("&rdquo;", "\"")
                    .replace("&#39;", "'")
                    .replace("&quot;", "\"")
                    .replace("&amp;", "&");
    }

    public static void main(String[] args) {
        String websiteUrl = "https://quotes.toscrape.com/";

        try {
            URL url = new URI(websiteUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );

            StringBuilder html = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                html.append(line);
            }

            reader.close();

            String pageContent = html.toString();

            Pattern quotePattern = Pattern.compile(
                "<div class=\"quote\".*?<span class=\"text\".*?>(.*?)</span>.*?<small class=\"author\".*?>(.*?)</small>",
                Pattern.DOTALL
            );

            Matcher matcher = quotePattern.matcher(pageContent);

            System.out.println("\n===== Interactive Web Scraper =====");
            System.out.println("Website: " + websiteUrl);
            System.out.println("--------------------------");

            int count = 1;

            while (matcher.find()) {
                String quote = decodeHtml(removeHtmlTags(matcher.group(1)));
                String author = decodeHtml(removeHtmlTags(matcher.group(2)));

                System.out.println("Quote " + count);
                System.out.println("Text: " + quote);
                System.out.println("Author: " + author);
                System.out.println("--------------------------");

                count++;
            }

            if (count == 1) {
                System.out.println("No quotes found.");
            }

        } catch (Exception e) {
            System.out.println("Error: Unable to fetch data from the website.");
        }
    }
}
