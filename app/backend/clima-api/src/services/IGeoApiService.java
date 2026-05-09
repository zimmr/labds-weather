package services;

import java.util.List;

import model.dtos.CityDto;

public interface IGeoApiService {
    public List<CityDto> buscarCidade(String cidade);
}
