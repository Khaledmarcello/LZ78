import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class LZ78{
    public String ReadFromFile (String Path) throws IOException {
        String content = null;
        File file = new File(Path);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }
    public void WriteToFile(String content,String name){
        BufferedWriter writer = null;
        String fileName = name+".txt" ;
        File logFile = new File(fileName);
        try{
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}

        }
    }
    public int Exist(String chars, Vector<String> d){
        int len = d.size();
        for (int i = 0; i < len ; i++)
        {
            if (chars.equals(d.get(i)))
                return i+1;
        }
        return -1;
    }
    public void Compress(String path , String name) {
        String text = new String();
        String compressed = new String ("") ;
        Vector<String> d = new Vector<String>() ;

        try {
            text = ReadFromFile(path);
        } catch (IOException e) {}

        int len = text.length();
        for (int i = 0; i<len; i++)
        {
            String curr =new String("");
            curr += (text.toCharArray()[i]) ;
            int ptr = 0 ;
            String next = "";
            while (Exist(curr, d)!=-1)
            {
                i++;
                ptr = Exist(curr , d) ;
                if (i==len)
                    break ;
                curr += text.toCharArray()[i];
            }
            if(Exist(curr,d)==-1)
                ptr=ptr;
            else
                i--;

            d.addElement(curr);


            if (i==len)
                next += "NULL" ;
            else
            {
                next += text.toCharArray()[i];
            }

            String tag = new String("<" + ptr +","+ next + ">");
            compressed+=tag;
        }

        WriteToFile(compressed , name);

    }
    public void Decompress(String path , String name)
    {
        String text = new String();
        String decompressed = new String ("") ;
        Vector<String> d = new Vector<String>() ;

        try {
            text = ReadFromFile(path);
        } catch (IOException e) {}

        int len = text.length();

        for (int i=0 ; i<len ; i++)
        {
            int ptr = 0 , length = 0 ;
            String next = "" ;
            String temp ="";
            i++ ;
            while (text.toCharArray()[i]!=',')
            {
                temp += text.toCharArray()[i];
                i++ ;
            }
            i++ ;
            ptr = Integer.parseInt(temp);
            if (i==len-5)
            {
                for (int k=i ; k<i+4 ; k++)
                {
                    next +=text.toCharArray()[k];
                }
                i=len-1 ;
            }
            else
            {
                next += text.toCharArray()[i];
                i++ ;
            }

            if (ptr == 0)
            {
                decompressed += next ;
                d.add(next);
            }
            else
            {
                if (!next.equals("NULL")) {

                    temp = d.elementAt(ptr - 1);
                    temp += next;
                    decompressed += temp;
                    d.add(temp);
                }
                else{
                    decompressed+=d.elementAt(ptr-1);
                }

            }
        }
        WriteToFile(decompressed , name);

    }


}
