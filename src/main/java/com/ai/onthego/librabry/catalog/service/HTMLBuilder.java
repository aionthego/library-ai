package com.ai.onthego.librabry.catalog.service;

public class HTMLBuilder {
    public static final String TOP_HTML = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Library Catalog integration with Generative AI</title>\n" +
            "<style>\n" +
            "        body {\n" +
            "            width: 70%;\n" +
            "            margin: 10px 10px 10px;\n" +
            "            padding: 5px 5px 5px 5px;\n" +
            "            background-color: #EEEEEE;\n" +
            "        }" +
            "        p { background-color: #004444; color: #FFFFFF; margin: 10px 10px 10px 10px;" +
            "        }"+
            "        input[type=text] {\n" +
            "          width: 50%;\n" +
            "          padding: 12px 20px 5px 5px;\n" +
            "          margin: 8px 0;\n" +
            "          box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        button[type=submit] {\n" +
            "          background-color: #04AA6D;\n" +
            "          border: none;\n" +
            "          color: white;\n" +
            "          padding: 16px 32px;\n" +
            "          text-decoration: none;\n" +
            "          font-size: 14px;\n" +
            "          font-weight: bold;\n" +
            "          margin: 4px 2px;\n" +
            "          cursor: pointer;\n" +
            "        }\n" +
            "    </style>" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Library Catalog integration with Generative AI</h1>";
    public static final String BOTTOM_HTML = "<form action=\"/ai\" method=\"post\">\n" +
            "    <input type=\"text\" width=\"80%\" name=\"message\"><br>\n" +
            "    <button type=\"submit\">Ask AI</button>\n" +
            "\n" +
            "</form>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    public static String getServerException(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(TOP_HTML);
        sb.append("<p><span>");
        sb.append(message);
        sb.append("</span></p>");
        sb.append(BOTTOM_HTML);
        return sb.toString();
    }

    public static String getBadRequest() {
        StringBuilder sb = new StringBuilder();
        sb.append(TOP_HTML);
        sb.append("<p><span>Invalid Request. Could you try with valid input?</span></p>");
        sb.append(BOTTOM_HTML);
        return sb.toString();
    }

    public static String getForm() {
        StringBuilder sb = new StringBuilder();
        sb.append(TOP_HTML);
        sb.append("<br>");
        sb.append(BOTTOM_HTML);
        return sb.toString();
    }
}
