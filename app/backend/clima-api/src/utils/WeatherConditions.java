package utils;

import java.util.Map;

public final class WeatherConditions {

    private WeatherConditions() {
    }

    // TODO: traduzir
    public static final Map<Integer, String> descriptions = Map.ofEntries(
            Map.entry(200, "thunderstorm with light rain"),
            Map.entry(201, "thunderstorm with rain"),
            Map.entry(202, "thunderstorm with heavy rain"),
            Map.entry(210, "light thunderstorm"),
            Map.entry(211, "thunderstorm"),
            Map.entry(212, "heavy thunderstorm"),
            Map.entry(221, "ragged thunderstorm"),
            Map.entry(230, "thunderstorm with light drizzle"),
            Map.entry(231, "thunderstorm with drizzle"),
            Map.entry(232, "thunderstorm with heavy drizzle"),

            Map.entry(300, "light intensity drizzle"),
            Map.entry(301, "drizzle"),
            Map.entry(302, "heavy intensity drizzle"),
            Map.entry(310, "light intensity drizzle rain"),
            Map.entry(311, "drizzle rain"),
            Map.entry(312, "heavy intensity drizzle rain"),
            Map.entry(313, "shower rain and drizzle"),
            Map.entry(314, "heavy shower rain and drizzle"),
            Map.entry(321, "shower drizzle"),

            Map.entry(500, "light rain"),
            Map.entry(501, "moderate rain"),
            Map.entry(502, "heavy intensity rain"),
            Map.entry(503, "very heavy rain"),
            Map.entry(504, "extreme rain"),
            Map.entry(511, "freezing rain"),
            Map.entry(520, "light intensity shower rain"),
            Map.entry(521, "shower rain"),
            Map.entry(522, "heavy intensity shower rain"),
            Map.entry(531, "ragged shower rain"),

            Map.entry(600, "light snow"),
            Map.entry(601, "snow"),
            Map.entry(602, "heavy snow"),
            Map.entry(611, "sleet"),
            Map.entry(612, "light shower sleet"),
            Map.entry(613, "shower sleet"),
            Map.entry(615, "light rain and snow"),
            Map.entry(616, "rain and snow"),
            Map.entry(620, "light shower snow"),
            Map.entry(621, "shower snow"),
            Map.entry(622, "heavy shower snow"),

            Map.entry(701, "mist"),
            Map.entry(711, "smoke"),
            Map.entry(721, "haze"),
            Map.entry(731, "sand/dust whirls"),
            Map.entry(741, "fog"),
            Map.entry(751, "sand"),
            Map.entry(761, "dust"),
            Map.entry(762, "volcanic ash"),
            Map.entry(771, "squalls"),
            Map.entry(781, "tornado"),

            Map.entry(800, "clear sky"),

            Map.entry(801, "few clouds"),
            Map.entry(802, "scattered clouds"),
            Map.entry(803, "broken clouds"),
            Map.entry(804, "overcast clouds")
    );

    public static String getDescription(int code) {
        return descriptions.getOrDefault(code, "unknown");
    }
}