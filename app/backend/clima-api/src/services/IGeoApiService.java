package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import model.dtos.CityDto;
import model.requests.GeoRequest;

public interface IGeoApiService {
    public List<CityDto> searchByName(GeoRequest request) throws MalformedURLException, IOException, Exception;
}
