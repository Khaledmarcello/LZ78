public class main {
    public static void main(String args[]) {
        LZ78 lz78 = new LZ78();
        //Take Care of the Path of the project
        lz78.Compress("D:\\Java Repos\\LZ78\\test.txt", "compressed");
        lz78.Decompress("D:\\Java Repos\\LZ78\\compressed.txt", "decompressed");
    }
}