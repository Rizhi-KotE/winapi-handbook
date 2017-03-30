include "WinApiStructs.thrift"
include "WinApiExceptions.thrift"

namespace java server.thrift

service TWinApiHandbookService {

  WinApiStructs.TWinApiClass getClass(i64 id),
  list<WinApiStructs.TWinApiClass> findClass(string keyword),
  list<WinApiStructs.TWinApiClass> findFunction(string keyword),
  i64 createClass(WinApiStructs.TWinApiClass topic),
  void updateClass(WinApiStructs.TWinApiClass topic) throws (WinApiExceptions.TNoSuchEntityException e),
  void removeClass(i64 id)
}