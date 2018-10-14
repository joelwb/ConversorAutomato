# TRABALHO 01 : LFA - Conversão Autômato Mealy <-> Moore
Autor:  Joel Will Belmiro

Descrição do Código Fonte:

        O Código foi feito em Java, sendo subdividido em  etapas para a convesão:
                1º - Leitura do Arquivo e criação de uma lista da classe SExp;
                2º - Conversão das SExp para a Classe do Autômato correspondente;
                3º - Conversão dos Autômato para Mealy (se o for de Moore originalmente) ou Moore (Se for o de Mealy originalmente)
                4º - Conversão de cada Autômato para a SExp e escrita da SExp no arquivo
            
        A classe SExp é construida de forma recursiva, tendo filhos e pai da mesma classe, sendo que,
        ela sabe como se converter numa String que a representa, que pode ser usada para escrever no arquivo.
        Existem também classes que sabem como converter de uma SExp em um Autômato e vice-versa.
        E as classes de Mealy e Moore herdam da classe AutomatoConversivel que possui atributos em comum entre os 2
        autômatos, e possui um metódo abstrato que obriga seus filhos a implementar a conversão para o outro tipo de autômato.
        A Main, portanto, tem a responsabilidade de receber os argumentos de entrada e saida de arquivo e coordenar os passos
        descritos acima.
        
Compilação: Não será necessário, pois já está dispolibilizado o arquivo .jar neste repositório.

Nome e Modo de uso:

        O nome do programa é ConversorAutomatos
        Deve-se abrir o terminal na pasta onde se encontra o arquivo ConversorAutomatos.jar e executar o seguinte comando
                java ConversorAutomatos.jar -i <arquivo de entrada> -o <arquivo de saida>
                Ex: java ConversorAutomatos.jar -i Input.txt -o Output.txt
