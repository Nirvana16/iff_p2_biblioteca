Versão em PostGree para o projeto biblioteca.
https://github.com/Nirvana16/iff-p2-biblioteca-MYSQL

Sistema de Gestão de Bibliotecas. Projeto realizado para a disciplina de Laboratório de Orientação a Objeto, IFF - Campus Centro - 2017.2

Trata-se de um mini sistema para gerenciamento de livros na Base de dados. Elaborado com o Framework Spring com a utilização da linguagem Java e outros recursos como: BootStrap JQuery Spring Security Spring Data Upload de Imagem

Essa aplicação esta disponível em : https://iff2018-loo.herokuapp.com/ Também existe uma versão no meu GitHub com o Deploy no heroku realizado com Postgree : https://github.com/Nirvana16/iff_p2_biblioteca

Observações:

Usuario e senha para testes da aplicação: 
usuario: admin  
Senha: 1

usuario: user  
Senha : 1

Levei algumas horas para perceber que havia feito todo meu projeto utilizado o empacotamento em WAR, essa forma ocasionou um erro de incompatibilidade no heroku resultando na falha ao startar a aplicação (Erro H10 e H14) Acabei tendo de mudar meu empacotamento para JAR de forma a ficar compatível com Drive indicado no meu Procfile.

Mantive essa versão utilizando Mysql pois queria testar como funciona esse Banco de dados dentro do heroku com o ClearDBMySQL

O pacote storage é apenas uma firula que coloquei, trata-se de um serviço de armazenamento de arquivos o qual queria implantar. 4.1 - Dentro do projeto existe uma view chamada UP que não esta mapeada, porém a mesma é funcional. Nela o usuário pode subir qualquer tipo de ficheiro para servidor A ideia iniciar era permitir que usuário subisse livros em PDF, porém não tive tempo hábil para terminar nessa implementação, apesar disso, o upload de ficheiros esta totalmente funcional na view supracitada.

"Confie no Senhor de todo o seu coração e não se apoie em seu próprio entendimento; reconheça o Senhor em todos os seus caminhos, e ele endireitará as suas veredas." Provérbios 3:5-6
