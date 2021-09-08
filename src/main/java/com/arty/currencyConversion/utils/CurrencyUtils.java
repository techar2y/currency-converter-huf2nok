package com.arty.currencyConversion.utils;

import com.arty.currencyConversion.domains.Currency;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyUtils
{

    public CurrencyUtils() {

    }

    public Map<String, Object> xmlToCurrency(String xmlContent) {
        List<Currency> currencyList = new ArrayList<>();
        String date = "";
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));

            doc.getDocumentElement().normalize();

            date = doc.getDocumentElement().getAttribute("Date");
            NodeList list = doc.getElementsByTagName("Valute");


            for (int i = 0; i < list.getLength(); i++)
            {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE)
                {

                    Element element = (Element) node;

                    BigDecimal value =
                            BigDecimal.valueOf(
                                    Double.parseDouble(
                                            element.getElementsByTagName("Value").item(0).getTextContent().replaceAll(",", ".")
                                    )
                            );
                    Currency cur = new Currency(element.getAttribute("ID"),
                            Integer.valueOf(element.getElementsByTagName("Nominal").item(0).getTextContent()),
                            element.getElementsByTagName("NumCode").item(0).getTextContent(),
                            element.getElementsByTagName("CharCode").item(0).getTextContent(),
                            element.getElementsByTagName("Name").item(0).getTextContent(),
                            value
                            );
                    currencyList.add(cur);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("date", date);
        data.put("currencies", currencyList);
        return data;
    }

    public String requestCbr()
    {
        String xmlContent = "";
        HttpURLConnection con = null;

        try{
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "cp1251"));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            xmlContent = content.toString();
        } catch (IOException e)  {
            con.disconnect();
            e.printStackTrace();
        }

        return xmlContent;
    }

    public List<Currency> getCurrencyHufNok(List<Currency> currencies) {
        return currencies.stream()
                .filter(x -> "NOK".equals(x.getCharCode()) || "HUF".equals(x.getCharCode()))
                .collect(Collectors.toList());
    }

}
