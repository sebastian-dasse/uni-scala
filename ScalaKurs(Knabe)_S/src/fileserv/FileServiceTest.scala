package fileserv

import org.junit.Assert._
import org.junit.{Test, BeforeClass}
import java.io.File
import java.io.PrintWriter

/**JUnit4-Testtreiber für die zu erstellende Implementierung des Traits FileService.
 * @author Christoph Knabe 2011-12-29*/
//Für Eclipse müssen Sie in der Datei .classpath hinter dem Scala- und Java-Container folgenden Eintrag ergänzen:
//   <classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/4"/>
class FileServiceTest {    
  
  import FileServiceTest._
    
  @Test def readShortFileCompletelyByStream(){
    _testFile(shortFile, shortFileLineCount, byStream=true)
  }  
  
  @Test def readLongFilePartiallyByStream(){
    _testFile(longFile, shortFileLineCount, byStream=true)
  }  
  
  @Test def readLongFileCompletelyByStream(){
    _testFile(longFile, longFileLineCount, byStream=true)
  }  
    
  @Test def readShortFileCompletelyByList(){
    _testFile(shortFile, shortFileLineCount, byStream=false)
  }  
  
  @Test def readLongFilePartiallyByList(){
    _testFile(longFile, shortFileLineCount, byStream=false)
  }  
  
  @Test def readLongFileCompletelyByList(){
    _testFile(longFile, longFileLineCount, byStream=false)
  }  
  
  private def _testFile(file: File, maxLines: Int, byStream: Boolean): Unit = {
    val prefixLen = linePrefix.length
    val testee: FileService = new FileServiceImpl
    testee open file
    val fileLines: Seq[String] = if(byStream) testee.getStream() else testee.getList()
    var i = 1
    for(elem <- fileLines){   
      assertEquals(linePrefix, elem.substring(0,prefixLen))   
      assertEquals(i, Integer parseInt elem.substring(prefixLen))   
      i = i + 1
      if(i>maxLines)return
    }
    assertEquals(i, maxLines+1)
    testee.close()
  }
  
}

object FileServiceTest {
  
  /**Number of lines to be written into the long file. 
   * Adjust so that on your machine the whole test lasts some 10 seconds and can execute!*/
  private val longFileLineCount = 1000000
  
  private var shortFile: File = _
  private var longFile: File = _
  
  @BeforeClass def createShortAndLongFiles(){
    shortFile = _writeToTemp("short", shortFileLineCount)
    longFile = _writeToTemp("long", longFileLineCount)
  }
  
  private val linePrefix = "----- line ----- " 
        
  private val shortFileLineCount = 10
    
  /**Creates a temporary file with the name prefix "FileServiceTest"+length; and fills it with lineCount lines.*/
  private def _writeToTemp(length: String, lineCount: Int): File = {
    val file = File.createTempFile("FileServiceTest"+length, null)
    val out = new PrintWriter(file)
    for(i <- 1 to lineCount){
      out.println(linePrefix + i)
    }
    out.close()
    file
  }
  
}
