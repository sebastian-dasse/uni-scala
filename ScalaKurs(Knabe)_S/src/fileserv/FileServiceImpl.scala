package fileserv

import java.io.File
import java.io.BufferedReader
import java.io.FileReader

class FileServiceImpl extends FileService {
  
  private var reader: BufferedReader = _
  
  def open(filename: File) = {
    reader = new BufferedReader(new FileReader(filename))
  }
  
  def getStream(): Stream[String] = reader.readLine match {
    case null => Stream.empty
    case line: String => Stream.cons(line, getStream)
  }
  
  def getList(): List[String] = {
    var list = List[String]()
    var line = reader.readLine
    while(line != null) {
      list = line :: list
      line = reader.readLine
    }
    list.reverse
  }
  
  def close() = {
    reader.close()
  }

}