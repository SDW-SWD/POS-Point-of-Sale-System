package org.CypsoLabs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.CypsoLabs.dto.StockDto;
import org.CypsoLabs.entity.Stock;
import org.CypsoLabs.repository.StockRepository;
import org.CypsoLabs.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/stock/api")
public class StockController {
    @Autowired
    StockService stockService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private StockRepository stockRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addStock( @RequestBody StockDto stockDto){
        Boolean  saved = stockService.addStock(stockDto);
        if (saved)return ResponseEntity.status(HttpStatus.CREATED).body("Stock added successfully ");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Stock");
    }

    @GetMapping("/getById/{id}")
    public  ResponseEntity<StockDto>getStockById(@PathVariable Long id){
        StockDto stockDto = stockService.getStockById(id);
        if (stockDto.getId()==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(stockDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StockDto>updateStock(@PathVariable Long id,@RequestBody StockDto stockDto){
        StockDto upadateStockDto = stockService.updateStock(id, stockDto);
        if (upadateStockDto!=null)return ResponseEntity.ok(upadateStockDto);
        else return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteStock(@PathVariable Long id){
        Boolean deleted = stockService.deleteStock(id);
        if (deleted)return ResponseEntity.ok("Stock deleted");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occurred when delete Stock");
    }



}
