package com.example.lock.service;

import org.springframework.stereotype.Service;

import com.example.lock.domain.Stock;
import com.example.lock.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SyncStockService {
	private final StockRepository stockRepository;

	/**
	 * 해결법 1) synchronized 를 이용해서 동시성 제어.
	 * Transactional 을 제거하고 내부적으로 em.save 해주기
	 * 문제점 : 하나의 어플리케이션/서버에서만 가능한 방법
	 * 왜 Transactional 을 붙이면 synchronized 를 붙여도 정상 동작을 하지 않는가?
	 * Transactional 의 동작원리, Spring AOP 동작 원리 때문 -> TransactionalStockService 참조
	 */
	public synchronized void decrease(Long stockId, Long quantity) {
		Stock stock = stockRepository.findById(stockId).orElseThrow();
		stock.decrease(quantity);
		stockRepository.save(stock);
	}
}
