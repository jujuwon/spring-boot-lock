package com.example.lock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lock.domain.Stock;
import com.example.lock.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessimisticLockStockService {
	private final StockRepository stockRepository;

	/**
	 * 해결법 2) 비관락 (PESSIMISTIC_WRITE) 이용. findByIdWithPessimisticLock 메소드 참고
	 * 충돌이 빈번하다면 낙관락보다 비관락이 성능이 좋을 수 있음.
	 * 문제점 : 성능 감소, 데드락 주의 필요
	 */
	@Transactional
	public void decrease(Long id, Long quantity) {
		Stock stock = stockRepository.findByIdWithPessimisticLock(id);
		stock.decrease(quantity);
	}
}
