namespace java model

struct Topic {
  1: i64 id,
  2: string content,
  3: string header
}

common.service HandbookThrift {
  Topic getTopic(i64 id),
  list<Topic> findTopicsHeaders(string keyword),
  i64 createTopic(Topic topic),
  void updateTopic(Topic topic),
  void removeTopic(i64 id)
}