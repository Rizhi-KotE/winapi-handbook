package common.service;

import model.WinApiFunction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinApiFunctionRepository extends JpaRepository<WinApiFunction, Long> {
}
