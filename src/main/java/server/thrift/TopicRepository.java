package server.thrift;

import org.springframework.data.jpa.repository.JpaRepository;
import common.service.Topic;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByHeaderContaining(String keyword);
}
