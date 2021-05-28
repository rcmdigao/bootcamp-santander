package com.br.santander.service;

import com.br.santander.dto.StockDTO;
import com.br.santander.exception.BusinessException;
import com.br.santander.exception.NotFoundException;
import com.br.santander.mapper.StockMapper;
import com.br.santander.model.Stock;
import com.br.santander.repositories.StockRepository;
import com.br.santander.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {


    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    ///Todo Save
    @Transactional
    public StockDTO save(StockDTO dto) {

        // Verificar se já possui dados com o mesmo nome e data do dia.
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtil.STOK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    ///Todo Put
    @Transactional
    public StockDTO update(StockDTO dto) {

        // Verificar se já possui dados com o mesmo nome e data do dia.
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtil.STOK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    ///Todo Listar Todos
    @Transactional(readOnly = true)
    public List<StockDTO> findAll(){
        return mapper.listToDto(repository.findAll());
   }

    ///Todo Procurar por ID
    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    ///Todo Exclusao
    @Transactional
    public StockDTO delete(Long id) {
        // Verificando se o registro existe na base
        StockDTO dto = this.findById(id);
        repository.deleteById(dto.getId());
        return dto;
    }

    ///Todo Listar todos de hoje
    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::listToDto).orElseThrow(NotFoundException::new);
    }
}
