package com.jameslawler.library.tests;

import com.jameslawler.library.OpenGraph;
import com.jameslawler.library.OpenGraphParser;

import org.junit.*;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by james on 06/11/2015.
 */
public class OpenGraphParserTests {

    @Test
    public void WhenEmptyHtmlShouldReturnEmptyList() {

        // Arrange
        OpenGraphParser openGraphParser = new OpenGraphParser();

        // Act
        String html = "";
        List<OpenGraph> result = openGraphParser.Parse(html);

        // Assert
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void WhenInvalidHtmlShouldReturnEmptyList() {

        // Arrange
        OpenGraphParser openGraphParser = new OpenGraphParser();

        // Act
        String html = "qwerty";
        List<OpenGraph> result = openGraphParser.Parse(html);

        // Assert
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void WhenValidHtmlWithOpenGraphTagsShouldReturnCorrectData() {

        // Arrange
        OpenGraphParser openGraphParser = new OpenGraphParser();

        // Act
        String html = "<html prefix=\"og: http://ogp.me/ns#\">\n" +
                "<head>\n" +
                "<title>The Rock (1996)</title>\n" +
                "<meta property=\"og:title\" content=\"The Rock\" />\n" +
                "<meta property=\"og:type\" content=\"video.movie\" />\n" +
                "<meta property=\"og:url\" content=\"http://www.imdb.com/title/tt0117500/\" />\n" +
                "<meta property=\"og:image\" content=\"http://ia.media-imdb.com/images/rock.jpg\" />\n" +
                "</head>\n" +
                "</html>";

        List<OpenGraph> result = openGraphParser.Parse(html);

        // Assert
        Assert.assertEquals(4, result.size());
        Assert.assertEquals("og:title", result.get(0).getProperty());
        Assert.assertEquals("The Rock", result.get(0).getContent());
        Assert.assertEquals("og:type", result.get(1).getProperty());
        Assert.assertEquals("video.movie", result.get(1).getContent());
        Assert.assertEquals("og:url", result.get(2).getProperty());
        Assert.assertEquals("http://www.imdb.com/title/tt0117500/", result.get(2).getContent());
        Assert.assertEquals("og:image", result.get(3).getProperty());
        Assert.assertEquals("http://ia.media-imdb.com/images/rock.jpg", result.get(3).getContent());
    }

    @Test
    public void WhenValidHtmlWithOpenGraphTagsAndOtherTagsShouldReturnCorrectData() {

        // Arrange
        OpenGraphParser openGraphParser = new OpenGraphParser();

        // Act
        String html = "<html prefix=\"og: http://ogp.me/ns#\">\n" +
                "<head>\n" +
                "<title>The Rock (1996)</title>\n" +
                "<meta property=\"og:title\" content=\"The Rock\" />\n" +
                "<meta property=\"fb:id\" content=\"1234567\" />\n" +
                "<meta property=\"og:type\" content=\"video.movie\" />\n" +
                "</head>\n" +
                "</html>";

        List<OpenGraph> result = openGraphParser.Parse(html);

        // Assert
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("og:title", result.get(0).getProperty());
        Assert.assertEquals("The Rock", result.get(0).getContent());
        Assert.assertEquals("og:type", result.get(1).getProperty());
        Assert.assertEquals("video.movie", result.get(1).getContent());
    }

    @Test
    public void WhenParseObservableShouldReturnValidObservable() {

        // Arrange
        OpenGraphParser openGraphParser = new OpenGraphParser();

        // Act and Assert
        String html = "<html prefix=\"og: http://ogp.me/ns#\">\n" +
                "<head>\n" +
                "<title>The Rock (1996)</title>\n" +
                "<meta property=\"og:title\" content=\"The Rock\" />\n" +
                "<meta property=\"og:type\" content=\"video.movie\" />\n" +
                "<meta property=\"og:url\" content=\"http://www.imdb.com/title/tt0117500/\" />\n" +
                "<meta property=\"og:image\" content=\"http://ia.media-imdb.com/images/rock.jpg\" />\n" +
                "</head>\n" +
                "</html>";

        openGraphParser.ParseObservable(html)
                .filter(openGraph -> openGraph.getProperty().equalsIgnoreCase("og:image"))
                .subscribe(openGraph ->
                        Assert.assertEquals("http://ia.media-imdb.com/images/rock.jpg", openGraph.getContent()));
    }
}
