package com.example.lock.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionalStockService {
	private final StockService stockService;

	public void decrease(Long id, Long quantity) {
		startTransaction();
		stockService.decrease(id, quantity);
		endTransaction();
	}

	private void startTransaction() {}
	private void endTransaction() {}
}
