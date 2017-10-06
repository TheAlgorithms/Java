/**
 * Columnar Transposition Cipher Encryption and Decryption.
 * @author <a href="https://github.com/freitzzz">freitzzz</a>
 */
public class ColumnarTranspositionCipher {
    private static String keyword;
    private static Object[][] table;
    private static String abecedarium;
    public static final String ABECEDARIUM="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.;:-@";
    private static final String ENCRYPTION_FIELD="≈";
    private static final char ENCRYPTION_FIELD_CHAR='≈';
    /**
     * Encrypts a certain String with the Columnar Transposition Cipher Rule
     * @param word Word being encrypted
     * @param keyword String with keyword being used
     * @return a String with the word encrypted by the Columnar Transposition Cipher Rule
     */
    public static String encrpyter(String word,String keyword){
        ColumnarTranspositionCipher.keyword=keyword;
        abecedariumBuilder(500);
        table=tableBuilder(word);
        Object[][] sortedTable=sortTable(table);
        String wordEncrypted="";
        for(int i=0;i<sortedTable[0].length;i++){
            for(int j=1;j<sortedTable.length;j++){
                wordEncrypted+=sortedTable[j][i];
            }
        }
        return wordEncrypted;
    }

    /**
     * Encrypts a certain String with the Columnar Transposition Cipher Rule
     * @param word Word being encrypted
     * @param keyword String with keyword being used
     * @param abecedarium String with the abecedarium being used. null for default one
     * @return a String with the word encrypted by the Columnar Transposition Cipher Rule
     */
    public static String encrpyter(String word,String keyword,String abecedarium){
        ColumnarTranspositionCipher.keyword=keyword;
        if(abecedarium!=null){
            ColumnarTranspositionCipher.abecedarium=abecedarium;
        }else{
            ColumnarTranspositionCipher.abecedarium=ABECEDARIUM;
        }
        table=tableBuilder(word);
        Object[][] sortedTable=sortTable(table);
        String wordEncrypted="";
        for(int i=0;i<sortedTable[0].length;i++){
            for(int j=1;j<sortedTable.length;j++){
                wordEncrypted+=sortedTable[j][i];
            }
        }
        return wordEncrypted;
    }
    /**
     * Decryps a certain encrypted String with the Columnar Transposition Cipher Rule
     * @return a String decrypted with the word ecrypted by the Columnar Transpositiion Cipher Rule
     */
    public static String decrypter(){
        String wordDecrypted="";
        for(int i=1;i<table.length;i++){
            for(int j=0;j<table[i].length;j++){
                wordDecrypted+=table[i][j];
            }
        }
        return wordDecrypted.replaceAll(ENCRYPTION_FIELD,"");
    }
    /**
     * Builds a table with the word to be encrpyted in rows by the Columnar Transposition Cipher Rule
     * @return An Object[][] with the word to be encrypted filled in rows and columns
     */
    private static Object[][] tableBuilder(String word){
        Object[][] table=new Object[rows(word)+1][keyword.length()];
        char[] wordInChards=word.toCharArray();
        //Fils in the respective numbers
        table[0]=findElements();
        int charElement=0;
        for(int i=1;i<table.length;i++){
            for(int j=0;j<table[i].length;j++){
                if(charElement<wordInChards.length){
                    table[i][j]=(char)wordInChards[charElement];
                    charElement++;
                }else{
                    table[i][j]=ENCRYPTION_FIELD_CHAR;
                }
            }
        }
        return table;
    }
    /**
     * Determines the number of rows the table should have regarding the Columnar Transposition Cipher Rule
     * @return an int with the number of rows that the table should have in order to respect the Columnar Transposition Cipher Rule.
     */
    private static int rows(String word){
        if((double)word.length()/keyword.length()>word.length()/keyword.length()){
            return (word.length()/keyword.length())+1;
        }else{
            return word.length()/keyword.length();
        }
    }
    private static Object[] findElements(){
        Object[] charValues=new Object[keyword.length()];
        for(int i=0;i<charValues.length;i++){
            for(int j=0;j<abecedarium.length();j++){
                if(keyword.charAt(i)==abecedarium.charAt(j))charValues[i]=j;
            }
        }
        return charValues;
    }
    private static Object[][] sortTable(Object[][] table){
        Object[][] tableSorted=new Object[table.length][table[0].length];
        for(int i=0;i<tableSorted.length;i++){
            for(int j=0;j<tableSorted[i].length;j++){
                tableSorted[i][j]=table[i][j];
            }
        }
        for(int i=0;i<tableSorted[0].length;i++){
            for(int j=i+1;j<tableSorted[0].length;j++){
                if((int)tableSorted[0][i]>(int)table[0][j]){
                    Object[] column=getColumn(tableSorted,tableSorted.length,i);
                    switchColumns(tableSorted,j,i,column);
                }
            }
        }
        return tableSorted;
    }
    private static Object[] getColumn(Object[][] table,int rows,int column){
        Object[] columnArray=new Object[rows];
        for(int i=0;i<rows;i++){
            columnArray[i]=table[i][column];
        }
        return columnArray;
    }
    private static void switchColumns(Object[][] table, int firstColumnIndex,int secondColumnIndex,Object[] columnToSwitch){
        for(int i=0;i<table.length;i++){
            table[i][secondColumnIndex]=table[i][firstColumnIndex];
            table[i][firstColumnIndex]=columnToSwitch[i];
        }
    }
    /**
     * Creates an abecedarium with a specified ascii inded
     * @param value Number of characters being used based on the ASCII Table
     */
    private static void abecedariumBuilder(int value){
        abecedarium="";
        for(int i=0;i<value;i++){
            abecedarium+=(char)i;
        }
    }
    private static void showTable(){
        for(int i=0;i<table.length;i++){
            for(int j=0;j<table[i].length;j++){
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        String keywordForExample="asd215";
        String wordBeingEncrypted="This is a test of the Columnar Transposition Cipher";
        System.out.println("### Example of Columnar Transposition Cipher ###\n");
        System.out.println("Word being encryped ->>> "+wordBeingEncrypted);
        System.out.println("Word encrypted ->>> "+ColumnarTranspositionCipher.encrpyter(wordBeingEncrypted,keywordForExample));
        System.out.println("Word decryped ->>> "+ColumnarTranspositionCipher.decrypter());
        System.out.println("\n### Encrypted Table ###");
        showTable();
    }
}
