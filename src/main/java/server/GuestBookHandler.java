package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GuestBookHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        String method = exchange.getRequestMethod();

        if (method.equals("GET")) {
            response = createHTMLForm();
        }

        if(method.equals("POST")) {
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String formData = bufferedReader.readLine();
            System.out.println(formData);
            Map<String, String> inputs = parseFromData(formData);

        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();

    }

    private Map<String, String> parseFromData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }

    private String createHTMLForm() {
        return "<html><body>\n" +
                "    <form method=\"POST\">\n" +
                "    <label for=\"name\">Name</label><br>\n" +
                "    <input id=\"name\" type=\"text\" name=\"name\" placeholder=\"Name\"><br><br>\n" +
                "    <label for=\"message\">Message</label><br>\n" +
                "    <textarea name=\"message\" id=\"message\" placeholder=\"Message...\"></textarea><br><br>\n" +
                "    <input type=\"submit\" value=\"Submit\">\n" +
                "    </form>\n" +
                "</body></html>";
    }
}
