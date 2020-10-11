public class DnaStrand {
  public static String makeComplement(String dna) {
    char op[] = new char[100];
    int i = 0;
    while(i < dna.length()){
      switch(dna.charAt(i)){
          case 'A':
            op[i] = 'T';
            break;
          case 'T':
            op[i] = 'A';
            break;
          case 'C':
            op[i] = 'G';
            break;
          case 'G':
            op[i] = 'C';
            break;
      }
      i++;
    }
    String flip = new String(op);
    return flip.trim();
  }
}
