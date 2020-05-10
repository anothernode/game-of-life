package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebControllerTests {

  @LocalServerPort
  private int port;

  private String baseUri;

  @BeforeEach
  void setUp() {
    baseUri = "http://localhost:" + port + "/";
  }

  @Test
  void deliversHtmlDocumentAtBaseUri() throws Exception {

    Document doc = Jsoup.connect(baseUri).get();

    assertThat(doc.documentType().name()).isEqualTo("html");
  }

  @Test
  void deliversBoardAsTableWithAtLeastOneHeaderAndOneCellAtBaseUri() throws Exception {

    Document doc = Jsoup.connect(baseUri).get();
    Element table = doc.getElementById("board");

    assertThat(table).as("element with id='board'").isNotNull();
    assertThat(table.tagName()).isEqualTo("table");
    assertThat(table.getElementsByTag("th")).as("table header").isNotEmpty();
    assertThat(table.getElementsByTag("td")).as("table data").isNotEmpty();
  }

}
