# Labirinto
## Algoritmo que resolve um labirinto

- O labirinto é uma matriz definida em um arquivo .txt, no seguinte formato:

 ![image](https://github.com/euFilpeSilva/Labirinto/assets/79103757/536e4e8b-6e54-42b9-9d4f-5561bfb2b5ba)

 
 Matriz contendo 5 linhas e 8 colunas.


- Na caixa de dialogo deve ser passado o caminho até o arquivo de entrada contendo a matriz que representa o labirinto, onde "1" representa as paredes e "0" os caminhos livres.
- O algoritmo percorre a matriz atraves de recursividade onde toda vez que chega em um beco sem saida, volta no movimento anterior e procura um novo caminho livre.
- O algoritmo finaliza quando encontra um caminho livre em uma extremidade do labirinto e depois gera um arquivo de saida, com os caminhos percorrifos gravados , no formato axaixo:
 
 ![image](https://github.com/euFilpeSilva/Labirinto/assets/79103757/6bce7e24-817d-4e6b-a79a-ca8cba8ae015)

  
  o arquivo de saida é gerado no mesmo diretório onde se encontram os arquivos de entrada.
