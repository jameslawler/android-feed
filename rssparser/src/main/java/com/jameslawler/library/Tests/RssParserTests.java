package com.jameslawler.library.Tests;

import com.jameslawler.library.Rss20.Rss;
import com.jameslawler.library.RssParser;

import junit.framework.Assert;

import org.junit.Test;

import static java.nio.file.Paths.get;

/**
 * Created by james on 06/11/2015.
 */
public class RssParserTests {
    String xml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<rss version=\"2.0\" xmlns:itunes=\"http://www.itunes.com/dtds/podcast-1.0.dtd\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:content=\"http://purl.org/rss/1.0/modules/content/\" xmlns:georss=\"http://www.georss.org/georss/\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\n" +
                    " <channel>\n" +
                    "  <title>Top-Thema mit Vokabeln | Deutsch lernen | Deutsche Welle</title>\n" +
                    "  <link>http://www.dw.de/deutsch-lernen/top-thema/s-8031?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "  <description>Deutsch lernen mit Realitätsbezug: aktuelle Berichte der Deutschen Welle – leicht verständlich und mit Vokabelglossar.</description>\n" +
                    "  <language>de</language>\n" +
                    "  <copyright>2015 DW.COM, Deutsche Welle</copyright>\n" +
                    "  <pubDate>Fri, 6 Nov 2015 15:17:27 GMT</pubDate>\n" +
                    "  <lastBuildDate>Fri, 6 Nov 2015 15:17:27 GMT</lastBuildDate>\n" +
                    "  <atom:link href=\"http://rss.dw.com/xml/DKpodcast_topthemamitvokabeln_de\" rel=\"self\"/>\n" +
                    "  <image>\n" +
                    "   <url>http://www.dw.com/image/0,,18800104_10,00.jpg</url>\n" +
                    "   <title>Top-Thema mit Vokabeln | Deutsch lernen | Deutsche Welle</title>\n" +
                    "   <link>http://www.dw.de/deutsch-lernen/top-thema/s-8031?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "  </image>\n" +
                    "  <itunes:image href=\"http://www.dw.com/image/0,,18800104_10,00.jpg\"/>\n" +
                    "  <itunes:block>no</itunes:block>\n" +
                    "  <itunes:explicit>clean</itunes:explicit>\n" +
                    "  <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "  <itunes:owner>\n" +
                    "   <itunes:name>DW.COM | Deutsche Welle</itunes:name>\n" +
                    "   <itunes:email>podcasts@dw.com</itunes:email>\n" +
                    "  </itunes:owner>\n" +
                    "  <itunes:subtitle>Deutsch lernen mit Realitätsbezug: aktuelle Berichte der Deutschen Welle – leicht verständlich und mit Vokabelglossar.</itunes:subtitle>\n" +
                    "  <itunes:summary>Deutsch lernen mit Realitätsbezug: aktuelle Berichte der Deutschen Welle – leicht verständlich und mit Vokabelglossar.</itunes:summary>\n" +
                    "  <itunes:category text=\"Education\">\n" +
                    "   <itunes:category text=\"Language Courses\"/>\n" +
                    "   <itunes:category text=\"Higher Education\"/>\n" +
                    "   <itunes:category text=\"Training\"/>\n" +
                    "  </itunes:category>\n" +
                    "  <ttl>30</ttl>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/ein-dorf-versinkt-im-meer/a-18746717?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Fri, 6 Nov 2015 09:12:00 GMT</pubDate>\n" +
                    "   <title>Ein Dorf versinkt im Meer</title>\n" +
                    "   <link>http://www.dw.com/de/ein-dorf-versinkt-im-meer/a-18746717?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Das Dorf Kivalina in Alaska ist in Gefahr. Das Meer kommt den Häusern immer näher: eine Folge des Klimawandels. Die Bewohner planen deshalb den Umzug des Dorfes. Doch das wird teuer – und der Staat will nicht zahlen.</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>Klimawandel, Alaska, Eis, Ozean,Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/9221FA20_1-podcast-2296-18746717.mp3\" type=\"audio/mpeg\" length=\"1747296\"/>\n" +
                    "   <itunes:duration>02:51</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/wie-ehrlich-ist-deutschlands-presse/a-18822130?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Tue, 3 Nov 2015 10:08:00 GMT</pubDate>\n" +
                    "   <title>Wie ehrlich ist Deutschlands Presse?</title>\n" +
                    "   <link>http://www.dw.com/de/wie-ehrlich-ist-deutschlands-presse/a-18822130?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Fast die Hälfte der Deutschen glaubt, dass die Medien nicht objektiv sind und viele Themen falsch darstellen. Die Pegida-Bewegung spricht sogar von „Lügenpresse“. Medienexperten prüfen, ob das stimmt.</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>Medien, Pressefreiheit, Flüchtlinge, Pegida, Manipulation, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/82A78A68_1-podcast-2296-18822130.mp3\" type=\"audio/mpeg\" length=\"1713184\"/>\n" +
                    "   <itunes:duration>02:47</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/kein-platz-für-afrikas-löwen/a-18813192?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Fri, 30 Oct 2015 09:01:00 GMT</pubDate>\n" +
                    "   <title>Kein Platz für Afrikas Löwen?</title>\n" +
                    "   <link>http://www.dw.com/de/kein-platz-für-afrikas-löwen/a-18813192?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Der König der Tiere ist vom Aussterben bedroht. Eine Studie sagt, dass Löwen in freier Wildbahn nicht überleben können. In einigen Regionen Afrikas sind sie schon verschwunden. Nur in Reservaten haben sie eine Chance.</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>Artenschutz, Löwen, Afrika, WWF, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/782FF4F7_1-podcast-2296-18813192.mp3\" type=\"audio/mpeg\" length=\"1696752\"/>\n" +
                    "   <itunes:duration>02:45</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/70-jahre-vereinte-nationen/a-18806737?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Tue, 27 Oct 2015 10:11:00 GMT</pubDate>\n" +
                    "   <title>70 Jahre Vereinte Nationen</title>\n" +
                    "   <link>http://www.dw.com/de/70-jahre-vereinte-nationen/a-18806737?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>1945 wurden die UN gegründet, um Kriege zu verhindern. 70 Jahre später stehen sie vor dem Problem, wie sie mit den vielen Konflikten weltweit umgehen sollen. Kritiker glauben, dass sie damit überfordert sind.&amp;lrm;</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>UN, Vereinte Nationen, Jubiläum, Krise, Blauhelmsoldat, Sicherheitsrat, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/69B40240_2-podcast-2296-18806737.mp3\" type=\"audio/mpeg\" length=\"3197990\"/>\n" +
                    "   <itunes:duration>02:56</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/die-schallplatte-lebt/a-18798124?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Fri, 23 Oct 2015 07:32:00 GMT</pubDate>\n" +
                    "   <title>Die Schallplatte lebt!</title>\n" +
                    "   <link>http://www.dw.com/de/die-schallplatte-lebt/a-18798124?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Als die CDs auf den Markt kamen, glaubten viele an das Ende der Schallplatte. Lange Zeit interessierten sich nur noch wenige Leute für die alte Technik, vor allem DJs. Doch das hat sich inzwischen geändert. &amp;lrm;</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>Schallplatte, Musik, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/7F9BDF5F_2-podcast-2296-18798124.mp3\" type=\"audio/mpeg\" length=\"2840328\"/>\n" +
                    "   <itunes:duration>02:54</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/messerangriff-auf-kölner-politikerin/a-18791808?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Tue, 20 Oct 2015 09:25:00 GMT</pubDate>\n" +
                    "   <title>Messerangriff auf Kölner Politikerin</title>\n" +
                    "   <link>http://www.dw.com/de/messerangriff-auf-kölner-politikerin/a-18791808?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Die Flüchtlingspolitik spaltet die deutsche Bevölkerung. Jetzt wurde in Köln eine Politikerin mit einem Messer angegriffen, weil sie die Aufnahme von Flüchtlingen unterstützt. &amp;lrm;</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>Rechtsextremismus, Köln, Attentat, Henriette Reker, Wahl, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/3A5397A9_2-podcast-2296-18791808.mp3\" type=\"audio/mpeg\" length=\"2462943\"/>\n" +
                    "   <itunes:duration>02:30</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/flüchtlinge-auf-dem-deutschen-arbeitsmarkt/a-18783575?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Fri, 16 Oct 2015 11:36:00 GMT</pubDate>\n" +
                    "   <title>Flüchtlinge auf dem deutschen Arbeitsmarkt</title>\n" +
                    "   <link>http://www.dw.com/de/flüchtlinge-auf-dem-deutschen-arbeitsmarkt/a-18783575?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Viele Betriebe in Deutschland suchen Lehrlinge, viele Flüchtlinge suchen Arbeit. Trotzdem können Flüchtlinge oft nicht in Betrieben arbeiten. Für die schlechte Integration auf dem Arbeitsmarkt gibt es viele Gründe.</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>Flüchtlinge, Ausbildung, Integration, Arbeitsmarkt, Migration, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/376BF9C8_2-podcast-2296-18783575.mp3\" type=\"audio/mpeg\" length=\"2928315\"/>\n" +
                    "   <itunes:duration>02:59</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/demonstration-gegen-ttip/a-18777507?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Tue, 13 Oct 2015 09:13:00 GMT</pubDate>\n" +
                    "   <title>Demonstration gegen TTIP</title>\n" +
                    "   <link>http://www.dw.com/de/demonstration-gegen-ttip/a-18777507?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Die USA und die EU verhandeln gerade über das Freihandelsabkommen TTIP. 150.000 Menschen haben in Berlin gegen das Abkommen demonstriert. Sie sehen darin große Gefahren. Für die Wirtschaft aber überwiegen die Vorteile.</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>TTIP, Freihandel, EU, USA, Umwelt, Demonstration, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/C0712C2C_2-podcast-2296-18777507.mp3\" type=\"audio/mpeg\" length=\"2992116\"/>\n" +
                    "   <itunes:duration>03:03</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/mehr-datenschutz-für-europa/a-18766706?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Fri, 9 Oct 2015 08:47:00 GMT</pubDate>\n" +
                    "   <title>Mehr Datenschutz für Europa</title>\n" +
                    "   <link>http://www.dw.com/de/mehr-datenschutz-für-europa/a-18766706?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Wegen eines Abkommens zwischen Europa und den USA konnten Unternehmen bisher persönliche Daten von EU-Bürgern einfach in die USA weitergeben. Der Europäische Gerichtshof erklärte dieses Abkommen nun für ungültig.</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>Datenschutz, EU, USA, EuGH, Safe Harbor, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/2BB8ED9E_2-podcast-2296-18766706.mp3\" type=\"audio/mpeg\" length=\"2862012\"/>\n" +
                    "   <itunes:duration>02:55</itunes:duration>\n" +
                    "  </item>\n" +
                    "  <item>\n" +
                    "   <guid isPermaLink=\"true\">http://www.dw.com/de/sichere-taxis-für-frauen-in-ägypten/a-18762340?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</guid>\n" +
                    "   <pubDate>Tue, 6 Oct 2015 09:16:00 GMT</pubDate>\n" +
                    "   <title>Sichere Taxis für Frauen in Ägypten</title>\n" +
                    "   <link>http://www.dw.com/de/sichere-taxis-für-frauen-in-ägypten/a-18762340?maca=de-DKpodcast_topthemamitvokabeln_de-2296-xml-mrss</link>\n" +
                    "   <description>Fast jede Frau in Ägypten wurde schon mal sexuell belästigt. Reem Fawzy hat in Kairo das Projekt „Pink Taxi“ gestartet. Es ist ein Taxi-Service von Frauen für Frauen. So können sie sich sicher durch die Stadt bewegen.</description>\n" +
                    "   <category>Deutsch XXL</category>\n" +
                    "   <itunes:author>DW.COM | Deutsche Welle</itunes:author>\n" +
                    "   <itunes:keywords>Frauenrechte, Ägypten, sexuelle Belästigung, Taxi, Top-Thema, B1</itunes:keywords>\n" +
                    "   <itunes:explicit>clean</itunes:explicit>\n" +
                    "   <enclosure url=\"http://radio-download.dw.com/Events/podcasts/de/2296_DKpodcast_topthemamitvokabeln_de/3E57F1C6_2-podcast-2296-18762340.mp3\" type=\"audio/mpeg\" length=\"2709807\"/>\n" +
                    "   <itunes:duration>02:46</itunes:duration>\n" +
                    "  </item>\n" +
                    " </channel>\n" +
                    "</rss>";

    @Test
    public void WhenOpenRss20FileShouldParseCorrectly() {
        // Arrange
        Rss result = null;
        RssParser rssParser = new RssParser();

        // Act
        try {
            result = rssParser.Parse(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assert
        Assert.assertEquals("Top-Thema mit Vokabeln | Deutsch lernen | Deutsche Welle", result.channel.title);
    }

    @Test
    public void WhenOpenRss20FileShouldParseObservableCorrectly() {
        // Arrange
        RssParser rssParser = new RssParser();

        // Act and Assert
        try {
            rssParser.ParseObservable(xml)
                    .subscribe(rss ->
                            org.junit.Assert.assertEquals("Top-Thema mit Vokabeln | Deutsch lernen | Deutsche Welle", rss.channel.title));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
