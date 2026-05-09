package services;

import java.util.List;

import model.dtos.request.GeoRequest;
import model.dtos.response.CityDto;

public interface IGeoApiService {
    public List<CityDto> searchByName(GeoRequest request);
}
