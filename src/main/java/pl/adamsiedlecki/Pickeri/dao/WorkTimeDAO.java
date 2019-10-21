package pl.adamsiedlecki.Pickeri.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamsiedlecki.Pickeri.entity.WorkTime;

@Repository
public interface WorkTimeDAO extends JpaRepository<WorkTime, Long> {
}