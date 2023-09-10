package com.example.lock.facade;

import org.springframework.stereotype.Component;

import com.example.lock.repository.RedisLockRepository;
import com.example.lock.service.StockService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LettuceLockStockFacade {
	private final RedisLockRepository redisLockRepository;
	private final StockService stockService;

	public void decrease(Long id, Long quantity) throws InterruptedException {
		while (!redisLockRepository.lock(id)) {
			Thread.sleep(100);
		}

		try {
			stockService.decrease(id, quantity);
		} finally {
			redisLockRepository.unlock(id);
		}
	}
}
