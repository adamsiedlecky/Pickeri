package pl.adamsiedlecki.Pickeri.tools.ApiReader;

import elemental.json.JsonArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;

public class InfoGetter {

    private static final Logger log = LoggerFactory.getLogger(InfoGetter.class);

    public static String getBitcoinPrice(Environment env){
        String url = env.getProperty("bitcoin.api");
        try {
            JSONObject json = JsonReader.readJsonFromUrl(url);
            JSONArray bids = (JSONArray) json.get("bids");
            JSONArray offer = (JSONArray) bids.get(0);
            String price = offer.get(0).toString();
            return price;
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (JSONException e) {
            log.error(e.getMessage());
        }
        return "";
    }

    public static String getDollarPrice(Environment env){
        String url = env.getProperty("dollar.api");
        try {
            JSONObject object = JsonReader.readJsonFromUrl(url);
            JSONArray array = (JSONArray) object.get("rates");
            JSONObject rate = array.getJSONObject(0);
            return  Double.toString((Double) rate.get("bid"));
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (JSONException e) {
            log.error(e.getMessage());
        }
        return "";
    }

}
