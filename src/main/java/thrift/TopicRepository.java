package thrift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import service.Topic;

import java.util.List;
import java.util.Map;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByHeaderContaining(String keyword);
}
