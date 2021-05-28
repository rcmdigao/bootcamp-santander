package com.br.santander.mapper;

import com.br.santander.dto.StockDTO;
import com.br.santander.model.Stock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
///Todo Mapea os DTO para Entidade
public class StockMapper {

    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setDate(dto.getDate());
        stock.setVariation(dto.getVariation());
        return stock;
    }

    ///Todo Mapea os Entidade para DTO
    public StockDTO toDto(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setDate(stock.getDate());
        dto.setVariation(stock.getVariation());
        return dto;
    }

    ///Todo Converte para uma lista DTO
    public List<StockDTO> listToDto(List<Stock> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
