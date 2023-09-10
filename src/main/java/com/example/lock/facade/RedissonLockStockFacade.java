package com.example.lock.facade;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import com.example.lock.service.StockService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {
	private final RedissonClient redissonClient;
	private final StockService stockService;

	public void decrease(Long id, Long quantity) {
		RLock lock = redissonClient.getLock(id.toString());

		try {
			boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
			if (!available) {
				System.out.println("Lock 획득 실패");
				return;
			}
			stockService.decrease(id, quantity);
		} catch (InterruptedException e) {
			e.getStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
