include "WinApiStructs.thrift"
include "WinApiExceptions.thrift"

namespace java server.thrift

service TWinApiHandbookService {

  WinApiStructs.TWinApiClass getWinApiClass(1: i64 id) throws (1: WinApiExceptions.THandbookException e),
  list<WinApiStructs.TWinApiClass> findClasses(1: string keyword) throws (1: WinApiExceptions.THandbookException e),
  WinApiStructs.TWinApiClass createWinApiClass(1: WinApiStructs.TWinApiClass clazz) throws (1: WinApiExceptions.THandbookException e),
  void updateClass(1: WinApiStructs.TWinApiClass clazz) throws (1: WinApiExceptions.THandbookException e),
  void removeClass(1: i64 id) throws (1: WinApiExceptions.THandbookException e),
  void updateFunction(1: WinApiStructs.TWinApiFunction func) throws (1: WinApiExceptions.THandbookException e),
  void removeFunction(1: i64 id) throws (1: WinApiExceptions.THandbookException e),
  void updateParameter(1: WinApiStructs.TWinApiParams params) throws (1: WinApiExceptions.THandbookException e),
  void removeParameter(1: i64 id) throws (1: WinApiExceptions.THandbookException e)
}