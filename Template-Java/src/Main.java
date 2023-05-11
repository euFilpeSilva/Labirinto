import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

	public static boolean encontrarSaida(String[][] labirinto, int lAtual, int cAtual, int lSaida, int cSaida, List<String> resultado, List<String> visitados) {
		int linhas = labirinto.length;
		int colunas = labirinto[0].length;

		// Verifica se a posição atual é a saída
		if (lAtual == lSaida && cAtual == cSaida) {
			resultado.add("S [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			return true;
		}

		// Verifica se a posição atual já foi visitada
		String posicaoAtual = "[" + lAtual + ", " + cAtual + "]";
		if (visitados.contains(posicaoAtual)) {
			return false;
		}

		// Adiciona a posição atual à lista de visitados
		visitados.add(posicaoAtual);

		// Verifica as possibilidades de movimento de acordo com a ordem de prioridade
		if (lAtual > 0 && !visitados.contains("[" + (lAtual - 1) + ", " + cAtual + "]") && !labirinto[lAtual - 1][cAtual].equals("1")) {
			// Movimento para cima
			resultado.add("C [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			if (encontrarSaida(labirinto, lAtual - 1, cAtual, lSaida, cSaida, resultado, visitados)) {
				return true;
			}
			resultado.remove(resultado.size() - 1);
		}

		if (cAtual > 0 && !visitados.contains("[" + lAtual + ", " + (cAtual - 1) + "]") && !labirinto[lAtual][cAtual - 1].equals("1")) {
			// Movimento para a esquerda
			resultado.add("E [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			if (encontrarSaida(labirinto, lAtual, cAtual - 1, lSaida, cSaida, resultado, visitados)) {
				return true;
			}
			resultado.remove(resultado.size() - 1);
		}

		if (cAtual < colunas - 1 && !visitados.contains("[" + lAtual + ", " + (cAtual + 1) + "]") && !labirinto[lAtual][cAtual + 1].equals("1")) {
			// Movimento para a direita
			resultado.add("D [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			if (encontrarSaida(labirinto, lAtual, cAtual + 1, lSaida, cSaida, resultado, visitados)) {
				return true;
			}
			resultado.remove(resultado.size() - 1);
		}

		if (lAtual < linhas - 1 && !visitados.contains("[" + (lAtual + 1) + ", " + cAtual + "]") && !labirinto[lAtual + 1][cAtual].equals("1")) {
			// Movimento para baixo
			resultado.add("B [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			if (encontrarSaida(labirinto, lAtual + 1, cAtual, lSaida, cSaida, resultado, visitados)) {
				return true;
			}
			resultado.remove(resultado.size() - 1);
		}

		// Se chegou até aqui, significa que não é possível avançar para nenhuma direção
		// É necessário retornar ao ponto anterior
		return false;
	}

	public static void main(String[] args) {
		// LE O ARQUIVO
		String filePath = JOptionPane.showInputDialog("Informe o caminho completo do arquivo de entrada do labirinto:");

		if (filePath == null || filePath.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Caminho do arquivo deve ser informado", "Alerta", JOptionPane.WARNING_MESSAGE);
			return;
		}

		File f = new File(filePath);
		if (!f.exists() || f.isDirectory()) {
			JOptionPane.showMessageDialog(null, "Caminho do arquivo informado é inválido", "Alerta", JOptionPane.WARNING_MESSAGE);
			return;
		}

		List<String> lines = new ArrayList<String>();
		try {
			FileInputStream fstream = new FileInputStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			while ((strLine = br.readLine()) != null)
				lines.add(strLine);

			fstream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível ler o arquivo de entrada", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] dimensoes = lines.get(0).split(" ");
		int linhas = Integer.parseInt(dimensoes[0]);
		int colunas = Integer.parseInt(dimensoes[1]);

		// Preenche matriz do labirinto
		String[][] matriz = new String[linhas][colunas];
		int lAtual = -1; // Posição inicial: linha
		int cAtual = -1; // Posição inicial: coluna
		int lSaida = -1; // Saída: linha
		int cSaida = -1; // Saída: coluna

		// Percorre toda a matriz (a partir da segunda linha do arquivo texto) para identificar a posição inicial e a saída
		for (int l = 0; l < linhas; l++) {
			String[] line = lines.get(l + 1).split(" ");
			for (int c = 0; c < colunas; c++) {
				String ll = line[c];
				matriz[l][c] = ll;

				if (ll.equals("X")) {
					// Posição inicial
					lAtual = l;
					cAtual = c;
				} else if (ll.equals("0") && (l == 0 || c == 0 || l == linhas - 1 || c == colunas - 1)) {
					// Saída
					lSaida = l;
					cSaida = c;
				}
			}
		}

		// Posição máxima de linha e coluna que pode ser movida (borda)
		int extremidadeLinha = linhas - 1;
		int extremidadeColuna = colunas - 1;

		// Guarda o trajeto em uma list de string e já inicia com a posição de origem
		List<String> resultado = new ArrayList<>();
		resultado.add("O [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");

		// Declara a lista de visitados
		List<String> visitados = new ArrayList<>();

		// Percorre a matriz (labirinto) até encontrar a saída, usando as regras de prioridade e posições não visitadas, e vai armazenando o trajeto na lista resultado
		boolean achouSaida = lAtual == lSaida && cAtual == cSaida;

		encontrarSaida(matriz, lAtual, cAtual, lSaida, cSaida, resultado, visitados); // Remova new ArrayList<String>()

		// Escreve no arquivo texto a saída
		String folderPath = f.getParent();
		String fileName = f.getName();
		String outputPath = folderPath + "\\saida-" + fileName;

		try {
			File fout = new File(outputPath);
			FileOutputStream fos = new FileOutputStream(fout);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (int i = 0; i < resultado.size(); i++) {
				bw.write(resultado.get(i));
				bw.newLine();
			}

			bw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível escrever o arquivo de saída", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
