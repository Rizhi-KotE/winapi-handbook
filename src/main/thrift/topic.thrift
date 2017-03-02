namespace java model

service HandbookThrift {
  string getContent(i64 id),
  map<i64, string> findTopicsHeaders(string keyword),
  i64 createTopic(string name, string content),
  void updateTopic(i64 id, string content),
  void removeTopic(i64 id)
}