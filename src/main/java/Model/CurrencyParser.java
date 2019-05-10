package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurrencyParser {
    private String fromCur;
    private String toCur;
    private List<String> currNames;

    public CurrencyParser(String fromC, String toC) throws IOException {
        fromCur = fromC;
        toCur = toC;
        currNames = parseCurrNames();
    }

    public String parseRate() throws IOException {
        String ftCur = fromCur + "_" + toCur;
        String u = "https://free.currconv.com/api/v7/convert?q=" + ftCur + "&compact=ultra&apiKey="; // API KEY REMOVED
        URL url = new URL(u);
        BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
        JSONObject s = new JSONObject(b.readLine());
        return Double.toString(s.getDouble(fromCur + "_" + toCur));
    }

    public List<String> parseCurrNames() throws IOException {
        List<String> currNames = new ArrayList<String>();

        URL u = new URL("https://free.currconv.com/api/v7/currencies?apiKey="); // API KEY REMOVED
        BufferedReader b = new BufferedReader((new InputStreamReader(u.openStream())));
        String s = b.readLine();

        JSONObject all = new JSONObject(s).getJSONObject("results");
        JSONArray allKeys = all.names();
        for (Object j : allKeys) {
            String key = j.toString();
            JSONObject val = all.getJSONObject(key);
            String name = val.getString("currencyName");
            currNames.add(name + " " + "(" + key + ")");
        }

        java.util.Collections.sort(currNames);
        return currNames;
    }

    public List<String> getCurrNames() {
        return currNames;
    }
}
