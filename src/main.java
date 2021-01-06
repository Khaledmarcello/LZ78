public class main {
    public static void main(String args[]){
        LZ78 lz78=new LZ78();
        lz78.Compress("C:\\Users\\RCSC\\Documents\\GitHub\\LZ78\\test.txt", "compressed");
        lz78.Decompress("C:\\Users\\RCSC\\Documents\\GitHub\\LZ78\\compressed.txt", "decompressed");
    }
}