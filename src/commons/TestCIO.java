package commons;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Commons IO 核心操作
 */

public class TestCIO {

    public static void main(String[] args) throws IOException, URISyntaxException {

//        readFile();
//        writeFile();
        copyFile();

    }


    /*查找文件夹下特定后缀的文件，对子文件夹有效*/
    public static void checkFile() {

        long len = FileUtils.sizeOf(new File("src/commons/TestCIO.java"));        //文件大小
        System.out.println(len);
        len = FileUtils.sizeOf(new File("E:/Idea_file"));        //目录大小
        System.out.println(len);


        Collection<File> files = FileUtils.listFiles(new File("E:/Idea_file"), FileFilterUtils.or(new SuffixFileFilter("java"), new SuffixFileFilter("class"), EmptyFileFilter.NOT_EMPTY), DirectoryFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());   //对目录以及子孙级操作
        }
    }

    /*读取文件*/
    public static void readFile() throws IOException {

        String msg = FileUtils.readFileToString(new File("src/commons/TestCIO.java"),"UTF-8");
        System.out.println(msg);
        byte[] datas = FileUtils.readFileToByteArray(new File("src/commons/TestCIO.java"));  //读取到字符数组中
        System.out.println(datas.length);

        List<String> str = FileUtils.readLines(new File("src/commons/TestCIO.java"),"UTF-8");  //逐行读取
        for (String string : str){
            System.out.println(string);
        }

        LineIterator it = FileUtils.lineIterator(new File("src/commons/TestCIO.java"));  //迭代器读取
        while (it.hasNext()){
            System.out.println(it.nextLine());
        }
    }

    /*写出文件*/
    public static void writeFile() throws IOException {

        FileUtils.write(new File("src/shiyan.txt"),"学习是一件伟大的事业\r\n","UTF-8",true);
        FileUtils.writeStringToFile(new File("src/shiyan.txt"),"学习是一件辛苦的事业\r\n","UTF-8",true);
        FileUtils.writeByteArrayToFile(new File("src/shiyan.txt"),"学习是一件幸福的事情\r\n".getBytes("UTF-8"),true);

        //写出列表
        List<String> ls = new ArrayList<String>();
        ls.add("弼马温");
        ls.add("孙悟空");
        ls.add("齐天大圣");
        FileUtils.writeLines(new File("src/shiyan.txt"),ls,"-----",true);

    }

    /*拷贝文件*/
    public static void copyFile() throws IOException, URISyntaxException {

        //复制文件
        FileUtils.copyFile(new File("src/shiyan.txt"),new File("src/shiyan-copy.txt"));
        //复制文件到目录
        FileUtils.copyFileToDirectory(new File("src/shiyan-copy.txt"),new File("src/commons"));
        //复制目录到目录
        FileUtils.copyDirectoryToDirectory(new File("src/commons"),new File("src/shiyan"));
        //复制目录
        FileUtils.copyDirectory(new File("src/commons"),new File("src/shiyan2"));
        //复制URL内容
        String datas = IOUtils.toString(new URI("http://www.163.com"),"gbk");
        System.out.println(datas);

    }

}
