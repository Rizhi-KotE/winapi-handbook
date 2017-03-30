package common.service;

import model.WinApiClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinApiClassRepository extends JpaRepository<WinApiClass, Long> {

    List<WinApiClass> findByNameContaining(String keyword);
}
