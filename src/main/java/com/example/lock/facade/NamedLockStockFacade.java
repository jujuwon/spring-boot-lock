package com.example.lock.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.lock.repository.LockRepository;
import com.example.lock.service.StockService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NamedLockStockFacade {
	private final LockRepository lockRepository;
	private final StockService stockService;

	/**
	 * 해결법 4) Named 락 사용. (분산락 구현 예시)
	 * 문제점 : 트랜잭션 종료 시 락 해제 필요. 락을 위한 별도 datasource 사용 추천
	 */
	@Transactional
	public void decrease(Long id, Long quantity) {
		try {
			lockRepository.getLock(id.toString());
			stockService.decreaseAnotherTx(id, quantity);
		} finally {
			lockRepository.releaseLock(id.toString());
		}
	}
}
