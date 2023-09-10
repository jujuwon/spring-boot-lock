package com.example.lock.facade;

import org.springframework.stereotype.Component;

import com.example.lock.service.OptimisticLockStockService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OptimisticLockStockFacade {
	private final OptimisticLockStockService optimisticLockStockService;

	/**
	 * 해결법 3) 낙관락 (OPTIMISTIC) 이용. findByIdWithOptimisticLock 메소드 참고
	 * 업데이트 시에 변경된 사항이 있는지를 검사하고 변경되었다면 재시도.
	 * 문제점 : 충돌 시에 재시도 로직 구현 필요.
	 */
	public void decrease(Long id, Long quantity) throws InterruptedException {
		while (true) {
			try {
				optimisticLockStockService.decrease(id, quantity);
				break;
			} catch (Exception e) {
				Thread.sleep(50);
			}
		}
	}
}
