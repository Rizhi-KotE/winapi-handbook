namespace java model

struct Topic {
  1: i64 id,
  2: string content,
}

service HandbookThrift {
  Topic getTopic(i64 id),
  list<Topic> findTopics(string keyword),
  void updateTopic(Topic topic),
  void removeTopic(i64 id)
}