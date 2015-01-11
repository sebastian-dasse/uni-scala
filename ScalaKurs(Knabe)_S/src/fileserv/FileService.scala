package fileserv
import java.io.File

/**Trait for File operations.*/
trait FileService {
  
  /**Opens a file for reading.*/
  def open(filename: File)
  
  /**Gets a Stream for lazily reading the File managed by this service. Each line will become an element of the returned Stream.*/
  def getStream(): Stream[String]
  
  /**Gets a List for immediately reading the File managed by this service. Each line will become an element of the returned List.*/
  def getList(): List[String]
  
  /**Closes this file service. Should be invoked after usage in order to free resources.*/
  def close()

}