
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PageSubstitution {

	private static PageSubstitution instance = null;
	private BufferedWriter escritor = null;
	private ArrayList<String> instrucoes = new ArrayList<String>();

	private PageSubstitution() {
		try {
			if (new File("saida.txt").exists() == false) {

				new File("saida.txt").createNewFile();
				
				escritor = new BufferedWriter(new FileWriter("saida.txt"));

			}else{
				escritor = new BufferedWriter(new FileWriter("saida.txt"));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static PageSubstitution getInstance() {

		if (instance == null) {
			instance = new PageSubstitution();
		}
		return instance;
	}

	public ArrayList<String> getInstrucoes() {
		return this.instrucoes;
	}

	public void lerArquivo() {

		BufferedReader leitor = null;

		try {

			leitor = new BufferedReader(new FileReader("entrada.txt"));

			String linha = "";

			while ((linha = leitor.readLine()) != null) {
				if (linha.length() > 0)
					instrucoes.add(linha);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				leitor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public ArrayList<Processo> getListProcessos() {

		String element = null;
		ArrayList<Processo> ListaProcessos = new ArrayList<>();
		Processo genericProcesso = new Processo();
		int tempoChegada = 0, tempoComputacao = 0, prioridade = 0, periodo = 0, deadline = 0;
		ArrayList<Integer> temposIO = new ArrayList<>();
		List<String> list = null;
		String AuxIO = null;
    
		lerArquivo();

		for (byte k = 0; k < instrucoes.size(); k++) {
			
			element = instrucoes.get(k);
			String Alt1[] = element.split(Pattern.quote(" "));
      
			if (Alt1.length == 6) {

				for (byte j = 0; j < Alt1.length; j++) {

					switch (j) {

					case 0:

						tempoChegada = Integer.valueOf(Alt1[j]);

						break;
					case 1:

						tempoComputacao = Integer.valueOf(Alt1[j]);

						break;
					case 2:

						AuxIO = Alt1[j];

						if (AuxIO.length() > 2) {
							
							list = Arrays.asList(AuxIO.substring(1, AuxIO.length() - 1).split(";"));

							String auxNum = "";
							String num1 = "", num2 = "";
							int x = 0, y = 0;
							int ponto = 0;

							for (byte w = 0; w < list.size(); w++) {
								auxNum = list.get(w);
								ponto = auxNum.indexOf(':');
								num1 = auxNum.substring(0, ponto);
								num2 = auxNum.substring(ponto + 1);
								x = Integer.valueOf(num1);
								y = Integer.valueOf(num2);
								
								for (int z = x; z <= y; z++) {
									temposIO.add(z);
								}
							}

						} 
						
						break;
					case 3:

						prioridade = Integer.valueOf(Alt1[j]);

						break;
					case 4:

						periodo = Integer.valueOf(Alt1[j]);

						break;
					case 5:

						deadline = Integer.valueOf(Alt1[j]);

						break;

					}

				}

				genericProcesso = new Processo(k, tempoChegada, tempoComputacao, temposIO, prioridade, periodo,
						deadline);
				genericProcesso.setTBLOQ(temposIO.size());
				ListaProcessos.add(genericProcesso);

				list = null;
				temposIO.clear();

			} else {
				return null;
			}
		}

		return ListaProcessos;

	}

	public void arquivoSaida(String texto) {

		try {

			escritor.append("\r\n" + texto + "\r\n");
			escritor.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
