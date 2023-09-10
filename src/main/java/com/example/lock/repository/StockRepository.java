package com.example.lock.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.lock.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select s from Stock s where s.id = :id")
	Stock findByIdWithPessimisticLock(Long id);

	@Lock(LockModeType.OPTIMISTIC)
	@Query("select s from Stock s where s.id = :id")
	Stock findByIdWithOptimisticLock(Long id);
}