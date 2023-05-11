# Labirinto
## Algoritmo que resolve um labirinto que é construido em forma de Matriz

- Na caixa de dialogo deve ser passado o caminho até o arquivo de entrada contendo a matriz que representa o labirinto, onde "1" representa as paredes e "0" os caminhos livres.
- O algoritmo percorre a matris atravez de recursividade onde toda vez que chega em um beco sem saida, volta no movimento anterior e procura um novo caminho livre.
- O algoritmo finaliza quando encontra um caminho livre em uma extremidade do labirinto e por fim, gera um arquivo de saida, com os caminhos percorrifos gravados , no formato axaixo:
 
 ex:
  [2,3]
  [3,5]
  [3,5]
  [3,5]
  [3,5]
  
  o arquivo de saida é gerado no mesmo diretório onde se encontram os arquivos de entrada.
