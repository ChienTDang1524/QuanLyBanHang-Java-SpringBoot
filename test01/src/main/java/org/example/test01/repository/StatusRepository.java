// StatusRepository.java
package org.example.test01.repository;

import org.example.test01.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("SELECT DISTINCT s.statusName FROM Status s")
    List<String> findAllStatusNames();
}