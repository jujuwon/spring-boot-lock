package com.example.lock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.lock.domain.Stock;
import com.example.lock.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	private final StockRepository stockRepository;

	@Transactional
	public void decrease(Long stockId, Long quantity) {
		Stock stock = stockRepository.findById(stockId).orElseThrow();
		stock.decrease(quantity);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void decreaseAnotherTx(Long stockId, Long quantity) {
		Stock stock = stockRepository.findById(stockId).orElseThrow();
		stock.decrease(quantity);
	}
}
