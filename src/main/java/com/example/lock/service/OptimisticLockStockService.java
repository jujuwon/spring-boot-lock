package com.example.lock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lock.domain.Stock;
import com.example.lock.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OptimisticLockStockService {
	private final StockRepository stockRepository;

	@Transactional
	public void decrease(Long id, Long quantity) {
		Stock stock = stockRepository.findByIdWithOptimisticLock(id);
		stock.decrease(quantity);
	}
}
